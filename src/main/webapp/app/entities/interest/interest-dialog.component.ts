import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Interest } from './interest.model';
import { InterestPopupService } from './interest-popup.service';
import { InterestService } from './interest.service';

@Component({
    selector: 'jhi-interest-dialog',
    templateUrl: './interest-dialog.component.html'
})
export class InterestDialogComponent implements OnInit {

    interest: Interest;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private interestService: InterestService,
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
        if (this.interest.id !== undefined) {
            this.subscribeToSaveResponse(
                this.interestService.update(this.interest));
        } else {
            this.subscribeToSaveResponse(
                this.interestService.create(this.interest));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Interest>>) {
        result.subscribe((res: HttpResponse<Interest>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Interest) {
        this.eventManager.broadcast({ name: 'interestListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-interest-popup',
    template: ''
})
export class InterestPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private interestPopupService: InterestPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.interestPopupService
                    .open(InterestDialogComponent as Component, params['id']);
            } else {
                this.interestPopupService
                    .open(InterestDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
