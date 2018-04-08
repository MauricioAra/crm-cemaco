import { BaseEntity } from './../../shared';

export class Contact implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public firstName?: string,
        public lastName?: string,
        public email?: string,
        public socialId?: string,
        public sex?: string,
        public registryDate?: string,
        public updateDate?: string,
        public status?: string,
        public follows?: BaseEntity[],
    ) {
    }
}
