import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from '../services/user.service';
import Swal from 'sweetalert2';
import { LoginComponent } from '../pages/login/login.component';
import { LoginService } from '../services/login.service';
import { ActivatedRoute } from '@angular/router';
import { delay } from 'rxjs';

@Component({
  selector: 'app-actualizar-password',
  templateUrl: './actualizar-password.component.html',
  styleUrls: ['./actualizar-password.component.css'],
})
export class ActualizarPasswordComponent {
  password = 'Ac.ñ@1p!87Da$-';

  nombreNuevo = '';

  comprobacion = '';

  nuevaPass = '';

  constructor(
    private userService: UserService,
    private snack: MatSnackBar,
    public LoginService: LoginService,
    private route: ActivatedRoute
  ) {}

  passwordActual = this.LoginService.getUser().password;

  salir() {
    console.log('Saliendo');
    close();
    close();
    open('/login');
    this.LoginService.logout();
  }

  public contrasegna = {
    pass: '',
    email: this.LoginService.getUser().username,
    rol: this.LoginService.getUserRol(),
  };

  ngOnInit() {
    const nombre = this.route.snapshot.paramMap.get('nombre')!;
    //this.nombre = this.route.snapshot.paramMap.get('nombre')

    this.nombreNuevo = nombre;
    console.log(this.nombreNuevo);
    console.log(this.password);
    //this.obtenerListaPlato(nombre)
  }

  obtenerNuevaPass(nombre: string) {
    this.nuevaPass = nombre;

    //Es aqui donde eciste el contenido de nuevaPass
    console.log(this.nuevaPass);

    this.enviarFormulario();

    //window.location.href = "/admin"
  }

  formSubmit() {}

  enviarFormulario() {
    console.log(this.contrasegna);

    if (this.contrasegna.pass.length <= 6) {
      this.snack.open('La contraseña no puede ser tan corta', 'Aceptar', {
        duration: 3000,
        verticalPosition: 'top',
        horizontalPosition: 'right',
      });
      return;
    }
    if (this.contrasegna.pass == 'Ac.ñ@1p!87Da$-') {
      this.snack.open(
        'La contraseña no puede ser igual a la que se le dió por defecto',
        'Aceptar',
        {
          duration: 8000,
          verticalPosition: 'top',
          horizontalPosition: 'right',
        }
      );
      return;
    }

    this.userService.cambiarContrasegna(this.contrasegna).subscribe(
      (data) => {
        console.log(data);
        Swal.fire({
          title: '<strong>Contraseña actualizada</strong>',
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
      },
      (error) => {
        console.log(error);
        Swal.fire({
          title: '<strong>No se ha podido actualizar la contraseña</strong>',
          icon: 'error',
          html:
            '<form (ngSubmit) ="recargar()">' +
            '<button id="but" type="submit" class="btn">' +
            'Hecho' +
            '</button>' +
            '</form>',
          showCloseButton: true,
          showConfirmButton: false,
        });
      }
    );

    if (this.LoginService.getUserRol() == 'ADMIN') {
      window.location.href = '/admin';
    } else {
      window.location.href = '/propietario';
    }
  }

  recargar() {
    location.reload();
  }
}
