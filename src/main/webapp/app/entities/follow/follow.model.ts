import { BaseEntity } from './../../shared';

export class Follow implements BaseEntity {
    constructor(
        public id?: number,
        public originContact?: string,
        public subOriginContact?: string,
        public result?: string,
        public nextContactDate?: string,
        public registryDate?: string,
        public status?: string,
        public nextContactReason?: string,
        public favoriteCard?: string,
        public buyInCemaco?: string,
        public interestedBuy?: string,
        public article?: string,
        public contactId?: number,
    ) {
    }
}
