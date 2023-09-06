import { Component } from '@angular/core';
import { LoginRoles } from '../login-roles';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from '../services/login.service';
import { UserService } from '../services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-cliente-register',
  templateUrl: './cliente-register.component.html',
  styleUrls: ['./cliente-register.component.css'],
})
export class ClienteRegisterComponent {
  user = {
    nombre: '',
    apellido: '',
    email: '',
    celular: '',
    cedula: '',
    password: '',
  };

  constructor(
    private userServices: UserService,
    private snack: MatSnackBar,
    private LoginService: LoginService
  ) {}

  ngOnInit(): void {}

  formSubmit() {
    if (this.user.email == '' || this.user.email == null) {
      this.snack.open('El nombre de usuario es requerido', 'Aceptar', {
        duration: 8000,
        verticalPosition: 'top',
        horizontalPosition: 'right',
      });
      return;
    }

    if (!this.user.email.includes('@')) {
      this.snack.open('El Email no es valido', 'Aceptar', {
        duration: 8000,
        verticalPosition: 'top',
        horizontalPosition: 'right',
      });
      return;
    }

    //validaciones clave

    if (this.user.password.length <= 6) {
      this.snack.open('La contraseña es demasiado corta', 'Aceptar', {
        duration: 8000,
        verticalPosition: 'top',
        horizontalPosition: 'right',
      });
      return;
    }

    //validaciones nombre

    const CARACTERES = [
      '1',
      '2',
      '3',
      '4',
      '5',
      '6',
      '7',
      '8',
      '9',
      '0',
      '!',
      '#',
      '$',
      '%',
      '&',
      '/',
      '(',
      ')',
      '=',
      "'",
      '?',
      '¿',
      '¡',
      ',',
      ';',
      '.',
      ':',
      '-',
      '_',
      '{',
      '}',
      '[',
      ']',
      '*',
    ];

    for (let i = 0; i < CARACTERES.length; i++) {
      for (let j = 0; j < this.user.nombre.length; j++) {
        if (this.user.nombre[j] == CARACTERES[i]) {
          this.snack.open(
            'El nombre no puede llevar caracteres especiales o números',
            'Aceptar',
            {
              duration: 8000,
              verticalPosition: 'top',
              horizontalPosition: 'right',
            }
          );
          return;
        }
      }
    }

    if (this.user.nombre.length <= 2) {
      this.snack.open('El nombre no puede ser tan corto', 'Aceptar', {
        duration: 8000,
        verticalPosition: 'top',
        horizontalPosition: 'right',
      });
      return;
    }

    //validaciones apellido

    if (this.user.apellido.length < 3) {
      this.snack.open(
        'No se puede registrar un apellido tan corto',
        'Aceptar',
        {
          duration: 8000,
          verticalPosition: 'top',
          horizontalPosition: 'right',
        }
      );
      return;
    }

    for (let i = 0; i < CARACTERES.length; i++) {
      for (let j = 0; j < this.user.apellido.length; j++) {
        if (this.user.apellido[j] == CARACTERES[i]) {
          this.snack.open(
            'El apellido no puede llevar caracteres especiales o números',
            'Aceptar',
            {
              duration: 8000,
              verticalPosition: 'top',
              horizontalPosition: 'right',
            }
          );
          return;
        }
      }
    }

    //validaciones telefono

    let letras = [
      'a',
      'b',
      'c',
      'd',
      'e',
      'f',
      'g',
      'h',
      'i',
      'j',
      'k',
      'l',
      'm',
      'n',
      'ñ',
      'o',
      'p',
      'q',
      'r',
      's',
      't',
      'u',
      'v',
      'w',
      'x',
      'y',
      'z',
      'A',
      'B',
      'C',
      'D',
      'E',
      'F',
      'G',
      'H',
      'I',
      'J',
      'K',
      'L',
      'M',
      'N',
      'Ñ',
      'O',
      'P',
      'Q',
      'R',
      'S',
      'T',
      'U',
      'V',
      'W',
      'X',
      'Y',
      'Z',
      '!',
      '#',
      '$',
      '%',
      '&',
      '/',
      '(',
      ')',
      '=',
      "'",
      '?',
      '¿',
      '¡',
      ',',
      ';',
      '.',
      ':',
      '-',
      '_',
      '{',
      '}',
      '[',
      ']',
      '*',
    ];

    for (let i = 0; i < letras.length; i++) {
      if (this.user.celular.includes(letras[i])) {
        this.snack.open(
          'El telefono solo puede contener numero o el simbolo +',
          'Aceptar',
          {
            duration: 8000,
            verticalPosition: 'top',
            horizontalPosition: 'right',
          }
        );
        return;
      }
    }

    if (this.user.celular.length > 13) {
      this.snack.open('El telefono no es valido', 'Aceptar', {
        duration: 8000,
        verticalPosition: 'top',
        horizontalPosition: 'right',
      });
      return;
    }

    if (!this.user.celular.includes('+57')) {
      this.snack.open(
        'Por favor inidicar el prefijo del telefono. Ej: +57 3115263782',
        'Aceptar',
        {
          duration: 8000,
          verticalPosition: 'top',
          horizontalPosition: 'right',
        }
      );
      return;
    }

    //validaciones nIdentidad

    for (let i = 0; i < letras.length - 1; i++) {
      if (this.user.cedula.includes(letras[i])) {
        this.snack.open(
          'El numero de documento no puede contener letras o simbolos',
          'Aceptar',
          {
            duration: 8000,
            verticalPosition: 'top',
            horizontalPosition: 'right',
          }
        );
        return;
      }
    }

    this.userServices.addCliente(this.user).subscribe(
      (data) => {
        console.log(data);
        Swal.fire({
          title: '<strong>Usuario registrado con éxito</strong>',
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
        this.snack.open(error.error, 'Aceptar', {
          duration: 3000,
          verticalPosition: 'top',
          horizontalPosition: 'right',
        });
      }
    );
  }
}
