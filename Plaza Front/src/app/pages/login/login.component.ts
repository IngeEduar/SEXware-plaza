import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginRoles } from 'src/app/login-roles';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  loginRoles: LoginRoles = new LoginRoles();

  public rolUsuario = {
    rol: '',
  };

  loginData = {
    email: '',
    password: '',
    rol: '',
  };

  constructor(private snack: MatSnackBar, private loginService: LoginService) {
    this.loginService.logout();
  }

  ngOnInit(): void {}

  submitNewCliente() {}

  formSubmit() {
    console.log(this.rolUsuario.rol);
    //console.log("Click en el boton de login", this.loginData.username, this.loginData.password);
    if (
      this.loginData.email.trim() == '' ||
      this.loginData.email.trim() == null
    ) {
      this.snack.open('El nombre de usuario es requerido', 'aceptar', {
        duration: 3000,
      });

      return;
    }

    if (
      this.loginData.password.trim() == '' ||
      this.loginData.password.trim() == null
    ) {
      this.snack.open('La contraseña es requerida', 'aceptar', {
        duration: 3000,
      });

      return;
    }

    this.loginService.generateToken(this.loginData).subscribe(
      (data: any) => {
        console.log(data);

        this.loginService.loginUser(data.token);
        this.loginService
          .getCurrentUser(this.loginData)
          .subscribe((user: any) => {
            this.loginService.setUsuario(user);
            console.log(user);
            //close();
            //open("/admin");

            if (this.loginService.getUserRol() == 'ADMIN') {
              window.location.href =
                '/actualizar-contrasegna/' + this.loginData.password;
            } else if (this.loginService.getUserRol() == 'PROPIETARIO') {
              window.location.href = '/propietario';
            } else if (this.loginService.getUserRol() == 'CLIENTE') {
              window.location.href = '/cliente';
            } else if (this.loginService.getUserRol() == 'EMPLEADO') {
              window.location.href = '/empleado';
            } else {
              this.loginService.logout();
            }
          });
      },
      (error) => {
        console.log(error);

        this.snack.open('Las credenciales no son validas', 'Aceptar', {
          duration: 5000,
          verticalPosition: 'top',
          horizontalPosition: 'center',
        });
      }
    );
  }
}
