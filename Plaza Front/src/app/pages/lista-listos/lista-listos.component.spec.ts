import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaListosComponent } from './lista-listos.component';

describe('ListaListosComponent', () => {
  let component: ListaListosComponent;
  let fixture: ComponentFixture<ListaListosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListaListosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaListosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
