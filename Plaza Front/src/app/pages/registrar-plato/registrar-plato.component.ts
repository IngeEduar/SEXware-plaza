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
  styleUrls: ['./registrar-plato.component.css']
})
export class RegistrarPlatoComponent {

  nombre = '';
  constructor(private propietarioServices:PropietarioService, private snack:MatSnackBar, public loginService:LoginService, private route:ActivatedRoute)
  {

  }

  public plato = {
    nombre : '',
    descripcion : '',
    precio : '',
    img : '',
    categoria : '',
    logeado : this.loginService.getUser().username,
    restaurante : ''
  }



  ngOnInit(): void
  {
    const nombre = this.route.snapshot.paramMap.get('nombre')!;
    console.log(nombre)
    this.plato.restaurante = nombre;
  }

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

}
