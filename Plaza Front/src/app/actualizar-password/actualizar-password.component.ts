import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from '../services/user.service';
import Swal from 'sweetalert2';
import { LoginComponent } from '../pages/login/login.component';

@Component({
  selector: 'app-actualizar-password',
  templateUrl: './actualizar-password.component.html',
  styleUrls: ['./actualizar-password.component.css']
})
export class ActualizarPasswordComponent {


  constructor(private userService:UserService, private snack:MatSnackBar)
  {

  }





  public contrasegna =
  {
    pass : '',
    email : 'adminFesc@fesc.edu.co'
  }

  ngOnInit()
  {}

  formSubmit()
  {

    console.log(this.contrasegna);

    if(this.contrasegna.pass.length <= 6){
      this.snack.open('La contraseña no puede ser tan corta', 'Aceptar', {
        duration: 3000,
        verticalPosition: 'top',
        horizontalPosition: 'right',

      });
      return;
    }

    this.userService.cambiarPassword

    this.userService.cambiarPassword(this.contrasegna).subscribe(
      (data)=> 
      {
        console.log(data);
        Swal.fire('Usuario guardado', 'Usuario registrado con exito en el sistema', 'success')

      }, (error) =>{
        console.log(error);
        this.snack.open('Constraseña actualizada', 'Aceptar', {
          duration: 3000,
          verticalPosition: 'top',
          horizontalPosition: 'right',
  
        });

      }
    )

  }

}
