import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { OriginComponent } from './origin.component';
import { OriginDetailComponent } from './origin-detail.component';
import { OriginPopupComponent } from './origin-dialog.component';
import { OriginDeletePopupComponent } from './origin-delete-dialog.component';

export const originRoute: Routes = [
    {
        path: 'origin',
        component: OriginComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Origins'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'origin/:id',
        component: OriginDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Origins'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const originPopupRoute: Routes = [
    {
        path: 'origin-new',
        component: OriginPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Origins'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'origin/:id/edit',
        component: OriginPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Origins'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'origin/:id/delete',
        component: OriginDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Origins'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
