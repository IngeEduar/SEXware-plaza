import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClienteDasboardComponent } from './cliente-dasboard.component';

describe('ClienteDasboardComponent', () => {
  let component: ClienteDasboardComponent;
  let fixture: ComponentFixture<ClienteDasboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClienteDasboardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClienteDasboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
