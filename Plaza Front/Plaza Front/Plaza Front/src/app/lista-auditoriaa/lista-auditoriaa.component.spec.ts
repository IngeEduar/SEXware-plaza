import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaAuditoriaaComponent } from './lista-auditoriaa.component';

describe('ListaAuditoriaaComponent', () => {
  let component: ListaAuditoriaaComponent;
  let fixture: ComponentFixture<ListaAuditoriaaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListaAuditoriaaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaAuditoriaaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
