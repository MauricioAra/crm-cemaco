import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Interest } from './interest.model';
import { InterestService } from './interest.service';

@Component({
    selector: 'jhi-interest-detail',
    templateUrl: './interest-detail.component.html'
})
export class InterestDetailComponent implements OnInit, OnDestroy {

    interest: Interest;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private interestService: InterestService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInInterests();
    }

    load(id) {
        this.interestService.find(id)
            .subscribe((interestResponse: HttpResponse<Interest>) => {
                this.interest = interestResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInInterests() {
        this.eventSubscriber = this.eventManager.subscribe(
            'interestListModification',
            (response) => this.load(this.interest.id)
        );
    }
}
