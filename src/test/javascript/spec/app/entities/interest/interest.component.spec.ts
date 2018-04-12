/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CrmcemacoTestModule } from '../../../test.module';
import { InterestComponent } from '../../../../../../main/webapp/app/entities/interest/interest.component';
import { InterestService } from '../../../../../../main/webapp/app/entities/interest/interest.service';
import { Interest } from '../../../../../../main/webapp/app/entities/interest/interest.model';

describe('Component Tests', () => {

    describe('Interest Management Component', () => {
        let comp: InterestComponent;
        let fixture: ComponentFixture<InterestComponent>;
        let service: InterestService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [CrmcemacoTestModule],
                declarations: [InterestComponent],
                providers: [
                    InterestService
                ]
            })
            .overrideTemplate(InterestComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(InterestComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InterestService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Interest(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.interests[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
