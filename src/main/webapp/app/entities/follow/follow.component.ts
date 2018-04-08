import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Follow } from './follow.model';
import { FollowService } from './follow.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-follow',
    templateUrl: './follow.component.html'
})
export class FollowComponent implements OnInit, OnDestroy {
follows: Follow[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private followService: FollowService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.followService.query().subscribe(
            (res: HttpResponse<Follow[]>) => {
                this.follows = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInFollows();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Follow) {
        return item.id;
    }
    registerChangeInFollows() {
        this.eventSubscriber = this.eventManager.subscribe('followListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
