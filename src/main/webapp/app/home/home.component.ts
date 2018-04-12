import { Component, OnInit, ViewChild } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Account, LoginModalService, Principal } from '../shared';

import { Follow } from '../entities/follow/follow.model';
import { FollowService } from '../entities/follow/follow.service';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {MatPaginator, MatTableDataSource} from '@angular/material';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: [
        'home.scss'
    ]

})
export class HomeComponent implements OnInit {
    public account: Account;
    public modalRef: NgbModalRef;
    public follows:Follow[];
    public now : any = new Date();
    public currentDate:any;
    public nextContact:any;
    public registryDate:any;

    @ViewChild(MatPaginator) paginator: MatPaginator;
    dataSource:any;
    displayedColumns = ['no', 'origin', 'mediante', 'next','registry','status','reason','contact','actions'];

    constructor(
        private principal: Principal,
        private loginModalService: LoginModalService,
        private eventManager: JhiEventManager,
        private followService:FollowService
    ) {
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.account = account;
        });
        this.registerAuthenticationSuccess();

        let temp = this.now.getMonth()+1;
        let temp2 = this.now.getDate();
        this.currentDate = temp2+'-'+temp+'-'+this.now.getFullYear();

        this.loadFollows(this.currentDate);
    }

    loadFollows(date){
        this.followService.findBydate(date).subscribe(
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

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', (message) => {
            this.principal.identity().then((account) => {
                this.account = account;
                let temp = this.now.getMonth()+1;
                let temp2 = this.now.getDate();
                this.currentDate = temp2+'-'+temp+'-'+this.now.getFullYear();

                this.loadFollows(this.currentDate);
            });
        });
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }

    findByNextContact(event: MatDatepickerInputEvent<Date>){
        let date : Date = new Date(`${event.value}`);
        let month:any = date.getMonth()+1;
        let tempDate = date.getDate().toString()+'-'+month.toString()+'-'+date.getFullYear().toString();
        this.loadFollows(tempDate);
    }

    findByRegistryContact(event: MatDatepickerInputEvent<Date>){
        let date : Date = new Date(`${event.value}`);
        let month:any = date.getMonth()+1;
        let tempDate = date.getDate().toString()+'-'+month.toString()+'-'+date.getFullYear().toString();
        this.loadFollows(tempDate);
    }

    today(){
        let temp = this.now.getMonth()+1;
        let temp2 = this.now.getDate();
        this.currentDate = temp2+'-'+temp+'-'+this.now.getFullYear();
        this.loadFollows(this.currentDate);
    }

    applyFilter(filterValue: string) {
        filterValue = filterValue.trim();
        filterValue = filterValue.toLowerCase();
        this.dataSource.filter = filterValue;
    }
}
