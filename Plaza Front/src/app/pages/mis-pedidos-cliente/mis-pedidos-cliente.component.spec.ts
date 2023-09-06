import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MisPedidosClienteComponent } from './mis-pedidos-cliente.component';

describe('MisPedidosClienteComponent', () => {
  let component: MisPedidosClienteComponent;
  let fixture: ComponentFixture<MisPedidosClienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MisPedidosClienteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MisPedidosClienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
