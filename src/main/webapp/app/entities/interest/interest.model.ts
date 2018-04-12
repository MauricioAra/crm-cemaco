import { BaseEntity } from './../../shared';

export class Interest implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public description?: string,
        public status?: string,
    ) {
    }
}
