import { Component } from '@angular/core';
import { ListaPedidos } from 'src/app/lista-pedidos';
import { LoginService } from 'src/app/services/login.service';
import { PropietarioService } from 'src/app/services/propietario.service';

@Component({
  selector: 'app-mis-pedidos-cliente',
  templateUrl: './mis-pedidos-cliente.component.html',
  styleUrls: ['./mis-pedidos-cliente.component.css']
})
export class MisPedidosClienteComponent {

  constructor(public LoginService:LoginService, public propietarioService:PropietarioService)
  {

  }

  pedido: ListaPedidos[];

  ngOnInit(): void {

    this.obtenerMisPedidos();


  }

  obtenerMisPedidos()
  {

    const logeado = this.LoginService.getUser().username;

    // return this.propietarioService
    // .misPedidosCliente('cliente@gmail.com')
    // .subscribe((dato) => {
    //   console.log(dato);

    //   this.pedido = dato;
    // });
  }


  salir() {
    this.LoginService.logout();
    console.log('Saliendo');
    close();
    open('login');
  }

}
