import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Contact } from './contact.model';
import { ContactService } from './contact.service';
import { Follow } from '../follow/follow.model';
import {MatPaginator, MatTableDataSource} from '@angular/material';

@Component({
    selector: 'jhi-contact-detail',
    templateUrl: './contact-detail.component.html'
})
export class ContactDetailComponent implements OnInit, OnDestroy {
    follows:Follow[];
    contact: Contact;
    private subscription: Subscription;
    private eventSubscriber: Subscription;
    @ViewChild(MatPaginator) paginator: MatPaginator;

    dataSource:any;
    displayedColumns = ['no', 'origin', 'mediante', 'next','registry','status','reason','contact','actions'];

    constructor(
        private eventManager: JhiEventManager,
        private contactService: ContactService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInContacts();
    }

    load(id) {
        this.contactService.find(id)
            .subscribe((contactResponse: HttpResponse<Contact>) => {
                this.contact = contactResponse.body;
            });

        this.contactService.findFollowByContact(id).subscribe(
            (res: HttpResponse<Follow[]>) => {
                this.follows = res.body;
                this.dataSource = new MatTableDataSource<Follow>(this.follows);
                this.dataSource.paginator = this.paginator;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );

    }

    private onError(error) {

    }

    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInContacts() {
        this.eventSubscriber = this.eventManager.subscribe(
            'contactListModification',
            (response) => this.load(this.contact.id)
        );
    }
}
