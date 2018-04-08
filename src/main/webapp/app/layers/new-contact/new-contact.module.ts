import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmcemacoSharedModule } from '../../shared';

import {
    NewContactComponent,
    newContactRoute
} from './';

import {ContactService} from '../../entities/contact/contact.service';
import {FollowService} from '../../entities/follow/follow.service';

const ENTITY_STATES = [
    ...newContactRoute,
];

@NgModule({
    imports: [
        CrmcemacoSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        NewContactComponent
    ],
    entryComponents: [
        NewContactComponent
    ],
    providers: [
        ContactService,
        FollowService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NewContactModule {}
