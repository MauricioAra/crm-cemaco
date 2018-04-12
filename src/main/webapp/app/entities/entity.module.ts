import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { CrmcemacoContactModule } from './contact/contact.module';
import { CrmcemacoFollowModule } from './follow/follow.module';
import { CrmcemacoInterestModule } from './interest/interest.module';
import { CrmcemacoOriginModule } from './origin/origin.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        CrmcemacoContactModule,
        CrmcemacoFollowModule,
        CrmcemacoInterestModule,
        CrmcemacoOriginModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CrmcemacoEntityModule {}
