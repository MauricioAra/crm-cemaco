import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Origin } from './origin.model';
import { OriginService } from './origin.service';

@Component({
    selector: 'jhi-origin-detail',
    templateUrl: './origin-detail.component.html'
})
export class OriginDetailComponent implements OnInit, OnDestroy {

    origin: Origin;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private originService: OriginService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInOrigins();
    }

    load(id) {
        this.originService.find(id)
            .subscribe((originResponse: HttpResponse<Origin>) => {
                this.origin = originResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInOrigins() {
        this.eventSubscriber = this.eventManager.subscribe(
            'originListModification',
            (response) => this.load(this.origin.id)
        );
    }
}
