import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { NewContactComponent } from './new-contact.component';

export const newContactRoute: Routes = [
    {
        path: 'new-contact',
        component: NewContactComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Nuevo contacto'
        },
        canActivate: [UserRouteAccessService]
    }
];

