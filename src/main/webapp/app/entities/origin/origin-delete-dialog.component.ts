import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Origin } from './origin.model';
import { OriginPopupService } from './origin-popup.service';
import { OriginService } from './origin.service';

@Component({
    selector: 'jhi-origin-delete-dialog',
    templateUrl: './origin-delete-dialog.component.html'
})
export class OriginDeleteDialogComponent {

    origin: Origin;

    constructor(
        private originService: OriginService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.originService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'originListModification',
                content: 'Deleted an origin'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-origin-delete-popup',
    template: ''
})
export class OriginDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private originPopupService: OriginPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.originPopupService
                .open(OriginDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
