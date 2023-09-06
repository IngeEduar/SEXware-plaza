import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardPropietarioComponent } from './dashboard-propietario.component';

describe('DashboardPropietarioComponent', () => {
  let component: DashboardPropietarioComponent;
  let fixture: ComponentFixture<DashboardPropietarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashboardPropietarioComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashboardPropietarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
