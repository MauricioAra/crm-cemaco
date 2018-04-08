import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ValidateContactModule } from './validate-contact/validate-contact.module';
import { NewContactModule } from './new-contact/new-contact.module';
import { NewFollowModule } from './new-follow/new-follow.module';

@NgModule({
    imports: [
        ValidateContactModule,
        NewContactModule,
        NewFollowModule
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LayersModule {}
