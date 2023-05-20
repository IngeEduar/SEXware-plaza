import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { PropietarioService } from 'src/app/services/propietario.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-actualizar-plato',
  templateUrl: './actualizar-plato.component.html',
  styleUrls: ['./actualizar-plato.component.css'],
})
export class ActualizarPlatoComponent {
  constructor(
    private route: ActivatedRoute,
    private loginService: LoginService,
    private propietarioService: PropietarioService,
    private snack: MatSnackBar
  ) {}

  public actualizarPlato = {
    nombre: '',
    descripcion: '',
    precio: '',
    logeado: this.loginService.getUser().username,
    restaurante: '',
  };

  salir() {
    console.log('Saliendo');
    close();
    open('/login');
    this.loginService.logout();
  }

  ngOnInit() {
    const nombre = this.route.snapshot.paramMap.get('nombre')!;
    console.log(nombre);
    this.actualizarPlato.nombre = nombre;

    const nombreRestaurante =
      this.route.snapshot.paramMap.get('nombreRestaurante')!;
    console.log(nombreRestaurante);
    this.actualizarPlato.restaurante = nombreRestaurante;
  }

  formSubmit() {
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
      if (this.actualizarPlato.precio.includes(letras[i])) {
        this.snack.open('El precio solo puede contener numeros', 'Aceptar', {
          duration: 8000,
          verticalPosition: 'top',
          horizontalPosition: 'right',
        });
        return;
      }
    }

    var price = this.actualizarPlato.precio;
    var priceInt: number = +price;

    if (priceInt <= 50) {
      this.snack.open('El precio no puede ser menor de $50', 'Aceptar', {
        duration: 8000,
        verticalPosition: 'top',
        horizontalPosition: 'right',
      });
      return;
    }

    this.propietarioService
      .modificarPlato(this.actualizarPlato)
      .subscribe((data) => {
        console.log(data);
        Swal.fire({
          title: '<strong>El plato ha sido actualizado</strong>',
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
      });
  }

  recargar() {
    location.reload();
  }
}
