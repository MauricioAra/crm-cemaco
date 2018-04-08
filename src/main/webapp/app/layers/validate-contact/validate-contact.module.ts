import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmcemacoSharedModule } from '../../shared';

import {
    ValidateContactComponent,
    validateContactRoute
} from './';

import {ContactService} from '../../entities/contact/contact.service';
import {FollowService} from '../../entities/follow/follow.service';

const ENTITY_STATES = [
    ...validateContactRoute,
];

@NgModule({
    imports: [
        CrmcemacoSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ValidateContactComponent
    ],
    entryComponents: [
        ValidateContactComponent
    ],
    providers: [
        ContactService,
        FollowService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ValidateContactModule {}
