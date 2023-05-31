import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActualizarPasswordComponent } from './actualizar-password.component';

describe('ActualizarPasswordComponent', () => {
  let component: ActualizarPasswordComponent;
  let fixture: ComponentFixture<ActualizarPasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActualizarPasswordComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActualizarPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
