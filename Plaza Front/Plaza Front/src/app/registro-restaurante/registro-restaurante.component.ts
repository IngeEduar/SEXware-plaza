import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from '../services/user.service';
import Swal from 'sweetalert2';
import { LoginService } from '../services/login.service';
import { Restaurante } from 'src/restaurante';
import { FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { ListaPropietarios } from '../lista-propietarios';

@Component({
  selector: 'app-registro-restaurante',
  templateUrl: './registro-restaurante.component.html',
  styleUrls: ['./registro-restaurante.component.css'],
})
export class RegistroRestauranteComponent {
  restaurante: Restaurante = new Restaurante();

  listaPropietarios: ListaPropietarios[];

  public restaurantes = {
    nombre: '',
    direccion: '',
    telefono: '',
    nit: '',
    adm: 'adminFesc@fesc.edu.co',
    user: '',
  };

  title = 'formImage';
  formData = new FormData();

  constructor(
    private userService: UserService,
    private snack: MatSnackBar,
    private LoginService: LoginService,
    private httpClient: HttpClient
  ) {}

  enviarFormulario() {
    //Creamos el objeto usuario, con las propiedades de nuestro formulario

    console.log(this.restaurantes.adm);

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
      '_',
      '{',
      '}',
      '[',
      ']',
      '*',
    ];
    //validaciones NIT

    for (let i = 0; i < letras.length; i++) {
      if (
        this.restaurantes.nit.includes(letras[i]) ||
        this.restaurantes.nit.includes('+')
      ) {
        this.snack.open(
          'El NIT no puede contener letras o simbolos',
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

    //validaciones de telefono
    for (let i = 0; i < letras.length; i++) {
      if (this.restaurantes.telefono.includes(letras[i])) {
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

    if (this.restaurantes.telefono.length > 13) {
      this.snack.open('El telefono no es valido', 'Aceptar', {
        duration: 8000,
        verticalPosition: 'top',
        horizontalPosition: 'right',
      });
      return;
    }

    if (!this.restaurantes.telefono.includes('+57')) {
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

    //validaciones de logo

    //validacion de usuario
    if (!this.restaurantes.user.includes('@')) {
      this.snack.open('El Email del propietario no es valido', 'Aceptar', {
        duration: 8000,
        verticalPosition: 'top',
        horizontalPosition: 'right',
      });
      return;
    }

    this.restaurantes.adm = this.LoginService.getUser().Email;

    //Añadimos a nuestro objeto formData nuestro objeto convertido a String
    this.formData.append('restaurante', JSON.stringify(this.restaurantes));

    //Realizamos la petición a SpringBoot
    this.httpClient
      .post<any>(
        'http://localhost:8080/restaurante/guardar/' +
          this.LoginService.getUser().username,
        this.formData
      )
      .subscribe((data) => {
        //En este punto nuestra petición ha funcionado correctamente
        Swal.fire({
          title: '<strong>Restaurante Creado</strong>',
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

  salir() {
    console.log('Saliendo');
    close();
    open('/login');
    this.LoginService.logout();
  }

  formSubmit() {}

  ngOnInit() {
    this.obtenerListaPropietarios();
  }

  obtenerListaPropietarios() {
    this.userService.obtenerListaPropietarios().subscribe((dato) => {
      console.log(dato);
      this.listaPropietarios = dato;
    });
  }

  /*
  public restaurante =
  {
    nombre: '',
    nit : '',
    direccion : '',
    telefono : '',
    logo : '',
    admin : 'adminFesc@fesc.edu.co',
    user : ''

  }


  salir(){
    console.log("Saliendo");
    close();
    open("/login");
    this.LoginService.logout();
  }

  ngOnInit(): void
  {

  }


  formSubmit()
  {
    console.log(this.restaurante);

    const CARACTERES = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "!", "#", "$", "%", "&", "/", "(", ")", "=", "'", "?", "¿", "¡", ",", ";", ".", ":", "-", "_", "{", "}", "[", "]", "*" ];
    let letras = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "ñ", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "Ñ", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "!", "#", "$", "%", "&", "/", "(", ")", "=", "'", "?", "¿", "¡", ",", ";", ".", ":", "_", "{", "}", "[", "]", "*"];
    //validaciones NIT

    for(let i=0; i<letras.length; i++){
      if(this.restaurante.nit.includes(letras[i]) || this.restaurante.nit.includes("+")){
        this.snack.open('El NIT no puede contener letras o simbolos', 'Aceptar', {
          duration: 8000,
          verticalPosition: 'top',
          horizontalPosition: 'right',
  
        });
        return;
      }
    }

    //validaciones de telefono
    for(let i=0; i<letras.length; i++){
      
      if(this.restaurante.telefono.includes(letras[i])){
        this.snack.open('El telefono solo puede contener numero o el simbolo +', 'Aceptar', {
          duration: 8000,
          verticalPosition: 'top',
          horizontalPosition: 'right',
  
        });
        return;
      }
      

    }

    if(this.restaurante.telefono.length > 13){
      this.snack.open('El telefono no es valido', 'Aceptar', {
        duration: 8000,
        verticalPosition: 'top',
        horizontalPosition: 'right',

      });
      return;
    }

    if(!this.restaurante.telefono.includes("+57")){
      this.snack.open('Por favor inidicar el prefijo del telefono. Ej: +57 3115263782', 'Aceptar', {
        duration: 8000,
        verticalPosition: 'top',
        horizontalPosition: 'right',

      });
      return;
    }

    //validaciones de logo

    

    if(!this.restaurante.logo.includes(".png")){
      if(!this.restaurante.logo.includes(".jpg")){
        if(!this.restaurante.logo.includes(".jpeg")){
          this.snack.open('Por favor indique un  formato de imagen valido', 'Aceptar', {
            duration: 8000,
            verticalPosition: 'top',
            horizontalPosition: 'right',
    
          });
          return;
        }
      }
    }

    //validacion de usuario
    if(!this.restaurante.user.includes("@")){
      this.snack.open('El Email del propietario no es valido', 'Aceptar', {
        duration: 8000,
        verticalPosition: 'top',
        horizontalPosition: 'right',

      });
      return;
    }



    this.userService.registrarRestaurante

    this.userService.registrarRestaurante(this.restaurante).subscribe(
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
