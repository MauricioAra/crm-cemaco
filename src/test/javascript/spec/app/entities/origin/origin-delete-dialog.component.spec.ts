/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { CrmcemacoTestModule } from '../../../test.module';
import { OriginDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/origin/origin-delete-dialog.component';
import { OriginService } from '../../../../../../main/webapp/app/entities/origin/origin.service';

describe('Component Tests', () => {

    describe('Origin Management Delete Component', () => {
        let comp: OriginDeleteDialogComponent;
        let fixture: ComponentFixture<OriginDeleteDialogComponent>;
        let service: OriginService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [CrmcemacoTestModule],
                declarations: [OriginDeleteDialogComponent],
                providers: [
                    OriginService
                ]
            })
            .overrideTemplate(OriginDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OriginDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OriginService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
