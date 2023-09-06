import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClienteRegisterComponent } from './cliente-register.component';

describe('ClienteRegisterComponent', () => {
  let component: ClienteRegisterComponent;
  let fixture: ComponentFixture<ClienteRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClienteRegisterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClienteRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
