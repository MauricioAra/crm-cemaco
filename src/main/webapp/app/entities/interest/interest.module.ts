import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmcemacoSharedModule } from '../../shared';
import {
    InterestService,
    InterestPopupService,
    InterestComponent,
    InterestDetailComponent,
    InterestDialogComponent,
    InterestPopupComponent,
    InterestDeletePopupComponent,
    InterestDeleteDialogComponent,
    interestRoute,
    interestPopupRoute,
} from './';

const ENTITY_STATES = [
    ...interestRoute,
    ...interestPopupRoute,
];

@NgModule({
    imports: [
        CrmcemacoSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        InterestComponent,
        InterestDetailComponent,
        InterestDialogComponent,
        InterestDeleteDialogComponent,
        InterestPopupComponent,
        InterestDeletePopupComponent,
    ],
    entryComponents: [
        InterestComponent,
        InterestDialogComponent,
        InterestPopupComponent,
        InterestDeleteDialogComponent,
        InterestDeletePopupComponent,
    ],
    providers: [
        InterestService,
        InterestPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CrmcemacoInterestModule {}
