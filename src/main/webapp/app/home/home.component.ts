import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Account, LoginModalService, Principal } from '../shared';

import { Follow } from '../entities/follow/follow.model';
import { FollowService } from '../entities/follow/follow.service';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

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
                console.log(this.follows);
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

    findByNextContact(date){
        let tempDate = date.day.toString()+'-'+date.month.toString()+'-'+date.year.toString();
        this.loadFollows(tempDate);
    }

    findByRegistryContact(date){
        let tempDate = date.day.toString()+'-'+date.month.toString()+'-'+date.year.toString();
        this.loadFollows(tempDate);
    }

    today(){
        let temp = this.now.getMonth()+1;
        let temp2 = this.now.getDate();
        this.currentDate = temp2+'-'+temp+'-'+this.now.getFullYear();
        this.loadFollows(this.currentDate);
    }
}
