/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { CrmcemacoTestModule } from '../../../test.module';
import { InterestDetailComponent } from '../../../../../../main/webapp/app/entities/interest/interest-detail.component';
import { InterestService } from '../../../../../../main/webapp/app/entities/interest/interest.service';
import { Interest } from '../../../../../../main/webapp/app/entities/interest/interest.model';

describe('Component Tests', () => {

    describe('Interest Management Detail Component', () => {
        let comp: InterestDetailComponent;
        let fixture: ComponentFixture<InterestDetailComponent>;
        let service: InterestService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [CrmcemacoTestModule],
                declarations: [InterestDetailComponent],
                providers: [
                    InterestService
                ]
            })
            .overrideTemplate(InterestDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(InterestDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InterestService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Interest(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.interest).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
