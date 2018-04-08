import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { NewFollowComponent } from './new-follow.component';

export const newFollowRoute: Routes = [
    {
        path: 'new-follow/:id',
        component: NewFollowComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Nuevo seguimiento'
        },
        canActivate: [UserRouteAccessService]
    }
];

