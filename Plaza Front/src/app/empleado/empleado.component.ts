import { Component } from '@angular/core';
import { LoginService } from '../services/login.service';
import { ListaPedidos } from '../lista-pedidos';
import { PropietarioService } from '../services/propietario.service';

@Component({
  selector: 'app-empleado',
  templateUrl: './empleado.component.html',
  styleUrls: ['./empleado.component.css'],
})
export class EmpleadoComponent {
  pedido: ListaPedidos[];

  dataPedido = {
    nombrePlato: '',
    cantidad: '',
    numeroP: '',
  };

  filtro = {
    valor: '',
  };

  codigoP = {
    numeroP: '',
  };

  constructor(
    public LoginService: LoginService,
    public propietarioService: PropietarioService
  ) {}

  ngOnInit(): void {
    console.log(this.LoginService.getUser().restaurant);

    console.log(this.filtro.valor);
  }

  salir() {
    this.LoginService.logout();
    console.log('Saliendo');
    close();
    open('login');
  }

  pag(){
    window.open("/lista-platos-pedido", "_self")
  }

  obtenerListaPedido() {
    const restaurante = this.LoginService.getUser().restaurant;

    return this.propietarioService
      .obtenerListaPedidos(restaurante, this.filtro.valor)
      .subscribe((dato) => {
        for (let i = 0; i < dato.length; i++) {
          this.codigoP.numeroP = dato[0].numeroP;
        }

        console.log(dato);

        this.pedido = dato;
      });
  }

  formSubmit() {
    console.log(this.filtro.valor);
    this.obtenerListaPedido();
  }

  asignarme() {
    const logeado = this.LoginService.getUser().username;

    console.log(this.codigoP.numeroP);

    return this.propietarioService
      .asignarmePedido(this.codigoP.numeroP, logeado)
      .subscribe((data) => {
        console.log(data);
      });
  }

  pedidoListo() {
    return this.propietarioService
      .enviarMensaje('cliente@gmail.com', this.codigoP.numeroP)
      .subscribe((data) => {
        console.log(data);
      });
  }
}
