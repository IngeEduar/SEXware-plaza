import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { PropietarioService } from 'src/app/services/propietario.service';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registrar-plato',
  templateUrl: './registrar-plato.component.html',
  styleUrls: ['./registrar-plato.component.css'],
})
export class RegistrarPlatoComponent {
  nombre = '';
  constructor(
    private propietarioServices: PropietarioService,
    private snack: MatSnackBar,
    public loginService: LoginService,
    private route: ActivatedRoute,
    private httpClient: HttpClient
  ) {}

  public plato = {
    nombre: '',
    descripcion: '',
    precio: '',
    categoria: '',
    logeado: this.loginService.getUser().username,
    restaurante: '',
  };

  formData = new FormData();

  salir() {
    console.log('Saliendo');
    close();
    open('/login');
    this.loginService.logout();
  }

  enviarFormulario() {
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
      if (this.plato.precio.includes(letras[i])) {
        this.snack.open('El precio solo puede contener numeros', 'Aceptar', {
          duration: 8000,
          verticalPosition: 'top',
          horizontalPosition: 'right',
        });
        return;
      }
    }

    var price = this.plato.precio;
    var priceInt: number = +price;

    if (priceInt <= 50) {
      this.snack.open('El precio no puede ser menor de $50', 'Aceptar', {
        duration: 8000,
        verticalPosition: 'top',
        horizontalPosition: 'right',
      });
      return;
    }

    //Añadimos a nuestro objeto formData nuestro objeto convertido a String
    this.formData.append('plato', JSON.stringify(this.plato));

    //Realizamos la petición a SpringBoot
    this.httpClient
      .post<any>('http://localhost:8080/plato/agregar-img', this.formData)
      .subscribe((data) => {
        //En este punto nuestra petición ha funcionado correctamente
        Swal.fire({
          title: '<strong>El plato ha sido creado</strong>',
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

  onFileSelected(event: any) {
    const file: File = event.target.files[0];

    this.formData.append('img', file);
  }

  ngOnInit(): void {
    const nombre = this.route.snapshot.paramMap.get('nombre')!;
    console.log(nombre);
    this.plato.restaurante = nombre;
  }

  formSubmit() {}

  /*
  
  formSubmit()
  {
    console.log(this.plato)

    this.propietarioServices.añadirPlato(this.plato).subscribe(
      (data)=> 
      {
        console.log(data);
        Swal.fire('Usuario guardado', 'Usuario registrado con exito en el sistema', 'success')

      }, (error) =>{
        console.log(error);
        this.snack.open('Ha ocurrido un error en el sistema', 'Aceptar', {
          duration: 3000,
          verticalPosition: 'top',
          horizontalPosition: 'right',
  
        });

      }
    )

  
  }

  */
}
