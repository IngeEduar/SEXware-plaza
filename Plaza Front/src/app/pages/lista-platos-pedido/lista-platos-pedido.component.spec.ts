import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaPlatosPedidoComponent } from './lista-platos-pedido.component';

describe('ListaPlatosPedidoComponent', () => {
  let component: ListaPlatosPedidoComponent;
  let fixture: ComponentFixture<ListaPlatosPedidoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListaPlatosPedidoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaPlatosPedidoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
