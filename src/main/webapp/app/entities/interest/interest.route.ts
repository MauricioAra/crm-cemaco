import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { InterestComponent } from './interest.component';
import { InterestDetailComponent } from './interest-detail.component';
import { InterestPopupComponent } from './interest-dialog.component';
import { InterestDeletePopupComponent } from './interest-delete-dialog.component';

export const interestRoute: Routes = [
    {
        path: 'interest',
        component: InterestComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Interests'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'interest/:id',
        component: InterestDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Interests'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const interestPopupRoute: Routes = [
    {
        path: 'interest-new',
        component: InterestPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Interests'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'interest/:id/edit',
        component: InterestPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Interests'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'interest/:id/delete',
        component: InterestDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Interests'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
