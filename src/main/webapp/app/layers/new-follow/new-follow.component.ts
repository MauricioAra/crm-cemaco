import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { Observable } from 'rxjs/Observable';
import { ActivatedRoute } from '@angular/router';

import { FollowService } from '../../entities/follow/follow.service';
import { Follow } from '../../entities/follow/follow.model';
import { Principal } from '../../shared';

@Component({
    selector: 'new-follow',
    templateUrl: './new-follow.component.html'
})
export class NewFollowComponent implements OnInit, OnDestroy {
    currentAccount: any;
    eventSubscriber: Subscription;
    private subscription: Subscription;

    public follow: Follow = new Follow();
    public success:boolean = false;
    public fail:boolean = false;
    public now: any = new Date();
    public idContact:any = "";
    public origins:any = [{id:"1",name:"Daily Planet"},{id:"2",name:"Once noticias"},{id:"3",name:"Otro"}];
    public articles:any = [{id:"1",name:"Entretenimiento"},{id:"2",name:"Lista de Bodas"},{id:"3",name:"Articulos personales"},{id:"4",name:"Articulos VACACIONES"},{id:"5",name:"Articulos HOGAR"}];
    currentDate:any;


    constructor(
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal,
        private followService:FollowService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.idContact = params['id'];
        });
        let temp = this.now.getMonth()+1;
        let temp2 = this.now.getDate();
        this.currentDate = temp2+'-'+temp+'-'+this.now.getFullYear();

        (this.follow as any).favoriteCard = 'No';
        (this.follow as any).buyInCemaco = 'No';
        (this.follow as any).interestedBuy = 'No';

        (this.follow as any).registryDate = this.currentDate;
        (this.follow as any).status = 'P';
        (this.follow as any).contactId = this.idContact;
    }

    ngOnDestroy() {

    }

    create(){
        this.success = false;
        this.fail = false;
        (this.follow as any).nextContactDate = (this.follow as any).nextContactDate.day.toString()+'-'+(this.follow as any).nextContactDate.month.toString()+'-'+(this.follow as any).nextContactDate.year.toString();
        (this.follow as any).result = "Resultado pendiente";
        this.subscribeToSaveResponse(
            this.followService.create(this.follow));
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Follow>>) {
        result.subscribe((res: HttpResponse<Follow>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Follow) {
        this.success = true;
    }

    private onSaveError() {
        this.fail = true;
    }

    originChange(origin){

    }
}
