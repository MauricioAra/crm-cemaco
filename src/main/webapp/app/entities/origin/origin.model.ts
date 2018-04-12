import { BaseEntity } from './../../shared';

export class Origin implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public description?: string,
        public status?: string,
    ) {
    }
}
