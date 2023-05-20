import { Component } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent {
  constructor(public loginService: LoginService) {}

  salir() {
    console.log('Saliendo');
    close();
    open('/login');
    this.loginService.logout();
  }
}
