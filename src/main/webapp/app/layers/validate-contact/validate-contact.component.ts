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
    templateUrl: './validate-contact.component.html'
})
export class ValidateContactComponent implements OnInit, OnDestroy {
    currentAccount: any;
    eventSubscriber: Subscription;
    contact: Contact = new Contact();

    finded:boolean = false;
    notFinded:boolean = false;

    constructor(
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal,
        private contactService:ContactService
    ) {
    }

    ngOnInit() {

    }

    ngOnDestroy() {

    }

    find(){
        this.finded = false;
        this.notFinded = false;
        this.subscribeToSaveResponse(
            this.contactService.findEmail(this.contact));
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Contact>>) {
        result.subscribe((res: HttpResponse<Contact>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Contact) {
        this.finded = true;
        this.contact = result;
    }

    private onSaveError() {
        this.notFinded = true;
    }
}
