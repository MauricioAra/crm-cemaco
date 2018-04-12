import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Origin } from './origin.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Origin>;

@Injectable()
export class OriginService {

    private resourceUrl =  SERVER_API_URL + 'api/origins';

    constructor(private http: HttpClient) { }

    create(origin: Origin): Observable<EntityResponseType> {
        const copy = this.convert(origin);
        return this.http.post<Origin>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(origin: Origin): Observable<EntityResponseType> {
        const copy = this.convert(origin);
        return this.http.put<Origin>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Origin>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Origin[]>> {
        const options = createRequestOption(req);
        return this.http.get<Origin[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Origin[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Origin = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Origin[]>): HttpResponse<Origin[]> {
        const jsonResponse: Origin[] = res.body;
        const body: Origin[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Origin.
     */
    private convertItemFromServer(origin: Origin): Origin {
        const copy: Origin = Object.assign({}, origin);
        return copy;
    }

    /**
     * Convert a Origin to a JSON which can be sent to the server.
     */
    private convert(origin: Origin): Origin {
        const copy: Origin = Object.assign({}, origin);
        return copy;
    }
}
