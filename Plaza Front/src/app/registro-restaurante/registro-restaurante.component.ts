import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from '../services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registro-restaurante',
  templateUrl: './registro-restaurante.component.html',
  styleUrls: ['./registro-restaurante.component.css']
})
export class RegistroRestauranteComponent {

  public restaurante =
  {
    nombre: '',
    nit : '',
    direccion : '',
    telefono : '',
    logo : '',
    admin : '',
    user : ''

  }

  constructor(private userService: UserService, private snack: MatSnackBar)
  {

  }

  ngOnInit(): void
  {

  }


  formSubmit()
  {
    console.log(this.restaurante);

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

}
