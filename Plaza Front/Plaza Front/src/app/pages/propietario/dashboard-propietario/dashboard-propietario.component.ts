import { Component } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-dashboard-propietario',
  templateUrl: './dashboard-propietario.component.html',
  styleUrls: ['./dashboard-propietario.component.css'],
})
export class DashboardPropietarioComponent {
  constructor(
    public LoginService: LoginService,
    public loginService: LoginService
  ) {}

  getName() {
    return this.LoginService.getUser().username;
  }

  salir() {
    this.LoginService.logout();
    window.location.reload();
  }
}
