import { Component } from '@angular/core';
import { ListaPedidos } from 'src/app/lista-pedidos';
import { LoginService } from 'src/app/services/login.service';
import { PropietarioService } from 'src/app/services/propietario.service';
import Swal from 'sweetalert2';

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

     return this.propietarioService
     .misPedidosCliente(logeado)
     .subscribe((dato) => {
     console.log(dato);

       this.pedido = dato;
     });
  }

  eliminarPedido(id: string)
  {


     return this.propietarioService
     .eliminarPedido(id)
     .subscribe((data) => {
      console.log(data);
      Swal.fire({
        title: '<strong>Pedido cancelado con éxito</strong>',
        icon: 'success',
        html:
          '<form (ngSubmit) ="recargar()">' +
          '<button id="but" type="submit" class="btn">' +
          'Hecho' +
          '</button>' +
          '</form>',
        showCloseButton: true,
        showConfirmButton: false,
      });
     }, (error)=>{

      Swal.fire({
        title: '<strong>Pedido cancelado con éxito</strong>',
        icon: 'error',
        html:
        '<p>'+error+'</p>'+
          '<form (ngSubmit) ="recargar()">' +
          '<button id="but" type="submit" class="btn">' +
          'Hecho' +
          '</button>' +
          '</form>',
        showCloseButton: true,
        showConfirmButton: false,
      });

     });
  }


  salir() {
    this.LoginService.logout();
    console.log('Saliendo');
    close();
    open('login');
  }

}
