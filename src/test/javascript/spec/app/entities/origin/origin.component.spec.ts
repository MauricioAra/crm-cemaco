/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CrmcemacoTestModule } from '../../../test.module';
import { OriginComponent } from '../../../../../../main/webapp/app/entities/origin/origin.component';
import { OriginService } from '../../../../../../main/webapp/app/entities/origin/origin.service';
import { Origin } from '../../../../../../main/webapp/app/entities/origin/origin.model';

describe('Component Tests', () => {

    describe('Origin Management Component', () => {
        let comp: OriginComponent;
        let fixture: ComponentFixture<OriginComponent>;
        let service: OriginService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [CrmcemacoTestModule],
                declarations: [OriginComponent],
                providers: [
                    OriginService
                ]
            })
            .overrideTemplate(OriginComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OriginComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OriginService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Origin(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.origins[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
