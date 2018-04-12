import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Interest } from './interest.model';
import { InterestPopupService } from './interest-popup.service';
import { InterestService } from './interest.service';

@Component({
    selector: 'jhi-interest-delete-dialog',
    templateUrl: './interest-delete-dialog.component.html'
})
export class InterestDeleteDialogComponent {

    interest: Interest;

    constructor(
        private interestService: InterestService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.interestService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'interestListModification',
                content: 'Deleted an interest'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-interest-delete-popup',
    template: ''
})
export class InterestDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private interestPopupService: InterestPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.interestPopupService
                .open(InterestDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
