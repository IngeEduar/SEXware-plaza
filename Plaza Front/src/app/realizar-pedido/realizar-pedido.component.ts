import { Component } from '@angular/core';
import { LoginService } from '../services/login.service';
import { ActivatedRoute } from '@angular/router';
import { ClienteService } from '../services/cliente.service';
import { Pedido } from '../pedido';

@Component({
  selector: 'app-realizar-pedido',
  templateUrl: './realizar-pedido.component.html',
  styleUrls: ['./realizar-pedido.component.css'],
})
export class RealizarPedidoComponent {
  constructor(
    public loginService: LoginService,
    private route: ActivatedRoute,
    private clienteService:ClienteService
  ) {}


  lista: Pedido[] = [
    // Agrega los elementos a tu lista
    // Ejemplo: { id: 1, nombre: 'Elemento 1' }

  ];


  correo = '';
  nombreRestaurante = '';
  nombrePlato = '';

  public pedido = {
    nombrePlato: '',
    cantidad: '',
  };

  salir() {
    this.loginService.logout();
    console.log('Saliendo');
    close();
    open('login');
  }

  ngOnInit(): void {
    const correo = this.route.snapshot.paramMap.get('correo')!;
    const nombreRestaurante = this.route.snapshot.paramMap.get('nombreRestaurante')!;
    const nombrePlato = this.route.snapshot.paramMap.get('nombrePlato')!;

    this.correo = correo;
    this.nombreRestaurante = nombreRestaurante;
    this.nombrePlato = nombrePlato;

    this.pedido.nombrePlato = this.nombrePlato;

    
  }


  formSubmit()
  {
    console.log(this.pedido.cantidad);
  }

  realizarPedido()
  {

    this.clienteService.hacerPedido(this.lista, this.correo, this.nombreRestaurante).subscribe(
      (data) => {
        console.log(data);
      }
    )
    
  }
}
