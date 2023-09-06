import { Component } from '@angular/core';
import { Auditoria } from '../auditoria';
import { LoginService } from '../services/login.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-lista-auditoriaa',
  templateUrl: './lista-auditoriaa.component.html',
  styleUrls: ['./lista-auditoriaa.component.css'],
})
export class ListaAuditoriaaComponent {
  auditoria: Auditoria[];
  constructor(
    private usuarioServicio: UserService,
    private LoginService: LoginService
  ) {}

  ngOnInit(): void {
    this.obtenerAuditoria();
  }

  salir() {
    console.log('Saliendo');
    close();
    open('/login');
    this.LoginService.logout();
  }

  obtenerAuditoria() {
    return this.usuarioServicio.obtenerListaAuditoria().subscribe((dato) => {
      this.auditoria = dato;
    });
  }
}
