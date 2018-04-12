import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrmcemacoSharedModule } from '../../shared';
import {
    OriginService,
    OriginPopupService,
    OriginComponent,
    OriginDetailComponent,
    OriginDialogComponent,
    OriginPopupComponent,
    OriginDeletePopupComponent,
    OriginDeleteDialogComponent,
    originRoute,
    originPopupRoute,
} from './';

const ENTITY_STATES = [
    ...originRoute,
    ...originPopupRoute,
];

@NgModule({
    imports: [
        CrmcemacoSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        OriginComponent,
        OriginDetailComponent,
        OriginDialogComponent,
        OriginDeleteDialogComponent,
        OriginPopupComponent,
        OriginDeletePopupComponent,
    ],
    entryComponents: [
        OriginComponent,
        OriginDialogComponent,
        OriginPopupComponent,
        OriginDeleteDialogComponent,
        OriginDeletePopupComponent,
    ],
    providers: [
        OriginService,
        OriginPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CrmcemacoOriginModule {}
