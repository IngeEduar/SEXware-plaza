import { Component } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { PropietarioService } from '../services/propietario.service';
import { ListaRestaurantes } from '../lista-restaurantes';

@Component({
  selector: 'app-cliente-dasboard',
  templateUrl: './cliente-dasboard.component.html',
  styleUrls: ['./cliente-dasboard.component.css'],
})
export class ClienteDasboardComponent {
  constructor(
    public LoginService: LoginService,
    public propietarioService: PropietarioService
  ) {}

  restaurante: ListaRestaurantes[];

  ngOnInit(): void {
    this.listarRestaurantes();
    console.log('mostrados');
  }

  listarRestaurantes() {
    return this.propietarioService
      .obtenerListaRestaurantesOrdenada()
      .subscribe((dato) => {
        this.restaurante = dato;
      });
  }

  salir() {
    this.LoginService.logout();
    console.log('Saliendo');
    close();
    open('login');
  }

  obtenerNombreRestaurante(nombre: string) {
    console.log(nombre);

    window.location.href = '/lista-plato/' + nombre;
  }
}
