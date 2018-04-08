import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ValidateContactComponent } from './validate-contact.component';

export const validateContactRoute: Routes = [
    {
        path: 'validate-contact',
        component: ValidateContactComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Validar contacto'
        },
        canActivate: [UserRouteAccessService]
    }
];

