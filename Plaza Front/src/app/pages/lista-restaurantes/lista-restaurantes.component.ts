import { Component, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ListaRestaurantes } from 'src/app/lista-restaurantes';
import { NombreRestaurante } from 'src/app/nombre-restaurante';
import { LoginService } from 'src/app/services/login.service';
import { PropietarioService } from 'src/app/services/propietario.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-lista-restaurantes',
  templateUrl: './lista-restaurantes.component.html',
  styleUrls: ['./lista-restaurantes.component.css'],
})
export class ListaRestaurantesComponent {
  resultadoNombre: string;

  nombreRest = '';

  public nombre = {
    nombreRestaurante: '',
  };

  listaRestaurantes: ListaRestaurantes[];

  nombreRestaurante: NombreRestaurante;

  constructor(
    private propietarioService: PropietarioService,
    public loginService: LoginService,
    private userServices: UserService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.obtenerListaRestaurantes();
  }

  salir() {
    console.log('Saliendo');
    close();
    open('/login');
    this.loginService.logout();
  }

  obtenerNombreRestaurante(nombre: string) {
    //this.nombreRestaurante.nombre= '';

    this.resultadoNombre = nombre;
    this.nombre.nombreRestaurante = this.resultadoNombre;
    this.nombreRestaurante = {
      nombre: this.resultadoNombre,
    };
    console.log(this.nombreRestaurante.nombre);

    window.location.href = '/lista-plato/' + nombre;
  }

  obtenerNombreRestauranteRegistro(nombre: string) {
    this.resultadoNombre = nombre;
    this.nombre.nombreRestaurante = this.resultadoNombre;
    this.nombreRestaurante = {
      nombre: this.resultadoNombre,
    };
    console.log(this.nombreRestaurante.nombre);

    window.location.href = '/registrar-plato/' + nombre;
  }

  obtenerNombreRestauranteRegistroEmpleado(nombre: string) {
    this.resultadoNombre = nombre;
    this.nombre.nombreRestaurante = this.resultadoNombre;
    this.nombreRestaurante = {
      nombre: this.resultadoNombre,
    };
    console.log(this.nombreRestaurante.nombre);
    window.location.href = '/registrar-empleado/' + nombre;
  }

  obtenerNombreRestauranteListaEmpleado(nombre: string) {
    window.location.href = '/lista.empleados/' + nombre;
  }

  obtenerListaRestaurantes() {
    return this.propietarioService
      .obtenerListaRestaurantes()
      .subscribe((dato) => {
        console.log(dato);
        this.listaRestaurantes = dato;
      });
  }
}
