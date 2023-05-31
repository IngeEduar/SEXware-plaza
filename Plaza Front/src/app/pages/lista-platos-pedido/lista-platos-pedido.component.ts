import { Component } from '@angular/core';
import { ListaPedidos } from 'src/app/lista-pedidos';
import { LoginService } from 'src/app/services/login.service';
import { PropietarioService } from 'src/app/services/propietario.service';

@Component({
  selector: 'app-lista-platos-pedido',
  templateUrl: './lista-platos-pedido.component.html',
  styleUrls: ['./lista-platos-pedido.component.css'],
})
export class ListaPlatosPedidoComponent {
  pedido: ListaPedidos[];

  constructor(
    public LoginService: LoginService,
    public propietarioService: PropietarioService
  ) {}

  ngOnInit(): void {

    this.obtenerListaPlatosPedido();
  }

  salir() {
    this.LoginService.logout();
    console.log('Saliendo');
    close();
    open('login');
  }

  obtenerListaPlatosPedido() {
    return this.propietarioService
      .obtenerListaPlatoPedidos('1')
      .subscribe((dato) => {

        this.pedido = dato;
        console.log(dato);
      });
  }
}
