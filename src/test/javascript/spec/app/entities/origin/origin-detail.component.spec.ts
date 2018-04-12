/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { CrmcemacoTestModule } from '../../../test.module';
import { OriginDetailComponent } from '../../../../../../main/webapp/app/entities/origin/origin-detail.component';
import { OriginService } from '../../../../../../main/webapp/app/entities/origin/origin.service';
import { Origin } from '../../../../../../main/webapp/app/entities/origin/origin.model';

describe('Component Tests', () => {

    describe('Origin Management Detail Component', () => {
        let comp: OriginDetailComponent;
        let fixture: ComponentFixture<OriginDetailComponent>;
        let service: OriginService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [CrmcemacoTestModule],
                declarations: [OriginDetailComponent],
                providers: [
                    OriginService
                ]
            })
            .overrideTemplate(OriginDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OriginDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OriginService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Origin(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.origin).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
