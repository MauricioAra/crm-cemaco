import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Origin } from './origin.model';
import { OriginService } from './origin.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-origin',
    templateUrl: './origin.component.html'
})
export class OriginComponent implements OnInit, OnDestroy {
origins: Origin[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private originService: OriginService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.originService.query().subscribe(
            (res: HttpResponse<Origin[]>) => {
                this.origins = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInOrigins();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Origin) {
        return item.id;
    }
    registerChangeInOrigins() {
        this.eventSubscriber = this.eventManager.subscribe('originListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
