import { Component } from '@angular/core';
import { ListaPedidos } from 'src/app/lista-pedidos';
import { LoginService } from 'src/app/services/login.service';
import { PropietarioService } from 'src/app/services/propietario.service';

@Component({
  selector: 'app-lista-listos',
  templateUrl: './lista-listos.component.html',
  styleUrls: ['./lista-listos.component.css'],
})
export class ListaListosComponent {
  constructor(
    public LoginService: LoginService,
    public propietarioService: PropietarioService
  ) {}

  codigoP = {
    numeroP: '',
  };

  pedidoCodigo = {
    codigo: '',
  };

  peticion = {
    nombreCliente : '',
    email : '',
    numeroP: '',
    codigo: this.pedidoCodigo.codigo,
  };

  pedido: ListaPedidos[];

  ngOnInit(): void {
    this.listaPedidosListos();
  }

  listaPedidosListos() {
    const restaurante = this.LoginService.getUser().restaurant;

    return this.propietarioService
      .obtenerListaPedidos(restaurante, 'LISTO')
      .subscribe((dato) => {
        console.log(dato);

        for (let i = 0; i < dato.length; i++) {
          this.codigoP.numeroP = dato[i].numeroP;
          this.peticion.nombreCliente = dato[i].nombreCliente;
          this.peticion.email = dato[i].email;

          this.peticion.numeroP = dato[i].numeroP;

          console.log(this.codigoP.numeroP);
        }
        this.pedido = dato;
      });
  }

  salir() {
    this.LoginService.logout();
    console.log('Saliendo');
    close();
    open('login');
  }

  formSubmit() {
    //console.log(this.pedidoCodigo.codigo);

    this.peticion.codigo = this.pedidoCodigo.codigo;
    console.log(this.peticion.codigo);
    console.log(this.peticion.numeroP);
    this.propietarioService.entregarPedido(this.peticion).subscribe((dato) => {
      console.log(dato);
    });
  }
}
