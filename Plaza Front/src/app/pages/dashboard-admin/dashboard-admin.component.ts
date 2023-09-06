import { identifierName } from '@angular/compiler';
import { Component } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-dashboard-admin',
  templateUrl: './dashboard-admin.component.html',
  styleUrls: ['./dashboard-admin.component.css'],
})
export class DashboardAdminComponent {
  constructor(public LoginService: LoginService) {}

  getName() {
    return this.LoginService.getUser().username;
  }

  salir() {
    this.LoginService.logout();
    console.log('Saliendo');
    close();
    open('login');
  }
}
