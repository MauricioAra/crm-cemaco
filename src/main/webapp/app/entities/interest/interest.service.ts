import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Interest } from './interest.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Interest>;

@Injectable()
export class InterestService {

    private resourceUrl =  SERVER_API_URL + 'api/interests';

    constructor(private http: HttpClient) { }

    create(interest: Interest): Observable<EntityResponseType> {
        const copy = this.convert(interest);
        return this.http.post<Interest>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(interest: Interest): Observable<EntityResponseType> {
        const copy = this.convert(interest);
        return this.http.put<Interest>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Interest>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Interest[]>> {
        const options = createRequestOption(req);
        return this.http.get<Interest[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Interest[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Interest = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Interest[]>): HttpResponse<Interest[]> {
        const jsonResponse: Interest[] = res.body;
        const body: Interest[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Interest.
     */
    private convertItemFromServer(interest: Interest): Interest {
        const copy: Interest = Object.assign({}, interest);
        return copy;
    }

    /**
     * Convert a Interest to a JSON which can be sent to the server.
     */
    private convert(interest: Interest): Interest {
        const copy: Interest = Object.assign({}, interest);
        return copy;
    }
}
