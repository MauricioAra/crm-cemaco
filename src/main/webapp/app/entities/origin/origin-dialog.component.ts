import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Origin } from './origin.model';
import { OriginPopupService } from './origin-popup.service';
import { OriginService } from './origin.service';

@Component({
    selector: 'jhi-origin-dialog',
    templateUrl: './origin-dialog.component.html'
})
export class OriginDialogComponent implements OnInit {

    origin: Origin;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private originService: OriginService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.origin.id !== undefined) {
            this.subscribeToSaveResponse(
                this.originService.update(this.origin));
        } else {
            this.subscribeToSaveResponse(
                this.originService.create(this.origin));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Origin>>) {
        result.subscribe((res: HttpResponse<Origin>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Origin) {
        this.eventManager.broadcast({ name: 'originListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-origin-popup',
    template: ''
})
export class OriginPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private originPopupService: OriginPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.originPopupService
                    .open(OriginDialogComponent as Component, params['id']);
            } else {
                this.originPopupService
                    .open(OriginDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
