import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { Observable } from 'rxjs/Observable';

import { ContactService } from '../../entities/contact/contact.service';
import { Contact } from '../../entities/contact/contact.model';
import { Principal } from '../../shared';

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

    constructor(
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal,
        private contactService:ContactService
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
    }

    ngOnDestroy() {

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
