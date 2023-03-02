import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  public user =
  {

    password : '',
    nombre : '',
    apellido : '',
    email : '',
    celular : '',
    nidentidad : ''

  }

  constructor(private userServices:UserService, private snack: MatSnackBar)
  {

  }

  ngOnInit(): void
  {}

  formSubmit()
  {
    console.log(this.user);
    if(this.user.email =='' || this.user.email == null)
    {
      this.snack.open('El nombre de usuario es requerido', 'Aceptar', {
        duration: 8000,
        verticalPosition: 'top',
        horizontalPosition: 'right',

      });
      return;
    }

    const CARACTERES = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "!", "#", "$", "%", "&", "/", "(", ")", "=", "'", "?", "¿", "¡", ",", ";", ".", ":", "-", "_", "{", "}", "[", "]", "*" ];

    for(let i=0; i<CARACTERES.length; i++){
      if(this.user.nombre.includes(CARACTERES[i])){
        this.snack.open('El nombre no puede llevar caracteres especiales', 'Aceptar', {
          duration: 8000,
          verticalPosition: 'top',
          horizontalPosition: 'right',
  
        });
        return;
      }
      else if(this.user.nombre.length < 2){
        if(this.user.nombre.includes(CARACTERES[i])){
          this.snack.open('El nombre no puede ser tan corto', 'Aceptar', {
            duration: 8000,
            verticalPosition: 'top',
            horizontalPosition: 'right',
    
          });
          return;
        }
      }
    }

    this.userServices.añadirUsuario(this.user).subscribe(
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

  

  

}
