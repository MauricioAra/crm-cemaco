import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { Observable } from 'rxjs/Observable';

import { ContactService } from '../../entities/contact/contact.service';
import { Contact } from '../../entities/contact/contact.model';
import { Principal } from '../../shared';

import { Origin } from '../../entities/origin/origin.model';
import { OriginService } from '../../entities/origin/origin.service';

import { Interest } from '../../entities/interest/interest.model';
import { InterestService } from '../../entities/interest/interest.service';

@Component({
    selector: 'new-follow',
    templateUrl: './new-contact.component.html'
})
export class NewContactComponent implements OnInit, OnDestroy {
    currentAccount: any;
    eventSubscriber: Subscription;
    contact: Contact = new Contact();
    success:boolean = false;
    fail:boolean = false;
    now: any = new Date();
    currentDate:any;

    origins: Origin[];
    interests: Interest[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal,
        private contactService:ContactService,
        private originService: OriginService,
        private interestService: InterestService
    ) {
    }

    ngOnInit() {
        let temp = this.now.getMonth()+1;
        let temp2 = this.now.getDate();
        this.currentDate = temp2+'-'+temp+'-'+this.now.getFullYear();

        (this.contact as any).sex = 'm';
        (this.contact as any).registryDate = this.currentDate;
        (this.contact as any).updateDate =  this.currentDate;
        (this.contact as any).status = 'PL';

        this.originService.query().subscribe(
            (res: HttpResponse<Origin[]>) => {
                this.origins = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );

        this.interestService.query().subscribe(
            (res: HttpResponse<Interest[]>) => {
                this.interests = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );

    }

    ngOnDestroy() {

    }

    private onError(error) {

    }

    create(){
        this.subscribeToSaveResponse(
            this.contactService.create(this.contact));
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Contact>>) {
        result.subscribe((res: HttpResponse<Contact>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Contact) {
        this.success = true;
        this.contact = result;
    }

    private onSaveError() {
        this.fail = true;
    }
}
