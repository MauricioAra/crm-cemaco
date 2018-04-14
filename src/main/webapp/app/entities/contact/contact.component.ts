import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Contact } from './contact.model';
import { ContactService } from './contact.service';
import { Principal } from '../../shared';
import {MatPaginator, MatTableDataSource} from '@angular/material';

import { Origin } from '../../entities/origin/origin.model';
import { OriginService } from '../../entities/origin/origin.service';

import { Interest } from '../../entities/interest/interest.model';
import { InterestService } from '../../entities/interest/interest.service';

@Component({
    selector: 'jhi-contact',
    templateUrl: './contact.component.html'
})
export class ContactComponent implements OnInit, OnDestroy {
    contacts: Contact[];
    currentAccount: any;
    eventSubscriber: Subscription;
    @ViewChild(MatPaginator) paginator: MatPaginator;

    dataSource:any;
    displayedColumns = ['position', 'name', 'firstName', 'email','phone','origin','interest','action'];

    // interests = ['TV-s','Bodas'];
    // origins = ['OnceNoticias','DailyPlanet'];

    selectedInterest:any;
    selectedOrigin:any;

    origins: Origin[];
    interests: Interest[];


    constructor(
        private contactService: ContactService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal,
        private originService: OriginService,
        private interestService: InterestService) {}

    loadAll() {
        this.contactService.query().subscribe(
            (res: HttpResponse<Contact[]>) => {
                this.contacts = res.body;
                this.dataSource = new MatTableDataSource<Contact>(this.contacts);
                this.dataSource.paginator = this.paginator;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );

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
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInContacts();
    }

    filterInterest(interest){
        let temp = [];
        for(let i = 0; i < this.contacts.length; i++){
            if(this.contacts[i].interest === interest){
                temp.push(this.contacts[i]);
            }
        }
        this.dataSource = new MatTableDataSource<Contact>(temp);
        this.dataSource.paginator = this.paginator;
    }

    filterOrigin(origin){
        let temp = [];
        for(let i = 0; i < this.contacts.length; i++){
            if(this.contacts[i].origin === origin){
                temp.push(this.contacts[i]);
            }
        }
        this.dataSource = new MatTableDataSource<Contact>(temp);
        this.dataSource.paginator = this.paginator;
    }

    clean(){
        this.selectedInterest = null;
        this.selectedOrigin = null;
        this.dataSource = new MatTableDataSource<Contact>(this.contacts);
        this.dataSource.paginator = this.paginator;
    }


    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Contact) {
        return item.id;
    }

    applyFilter(filterValue: string) {
        filterValue = filterValue.trim();
        filterValue = filterValue.toLowerCase();
        this.dataSource.filter = filterValue;
    }

    registerChangeInContacts() {
        this.eventSubscriber = this.eventManager.subscribe('contactListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
