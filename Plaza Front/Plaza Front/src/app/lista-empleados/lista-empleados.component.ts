import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Empleado } from '../empleado';
import { LoginService } from '../services/login.service';
import { PropietarioService } from '../services/propietario.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-lista-empleados',
  templateUrl: './lista-empleados.component.html',
  styleUrls: ['./lista-empleados.component.css'],
})
export class ListaEmpleadosComponent {
  constructor(
    private propietarioService: PropietarioService,
    public loginService: LoginService,
    private userServices: UserService,
    private router: Router,
    private route: ActivatedRoute
  ) {}
  empleado: Empleado[];

  ngOnInit(): void {
    const nombre = this.route.snapshot.paramMap.get('nombre')!;
    //this.nombre = this.route.snapshot.paramMap.get('nombre')
    console.log(nombre);
    this.obtenerEmpleados(nombre);
  }

  salir() {
    console.log('Saliendo');
    close();
    open('/login');
    this.loginService.logout();
  }

  obtenerEmpleados(nombre: string) {
    return this.propietarioService
      .obtenerListaEmpleados(nombre)
      .subscribe((dato) => {
        this.empleado = dato;
      });
  }
}
