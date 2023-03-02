import { Component } from '@angular/core';
import { Auditoria } from '../auditoria';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-lista-auditoriaa',
  templateUrl: './lista-auditoriaa.component.html',
  styleUrls: ['./lista-auditoriaa.component.css']
})
export class ListaAuditoriaaComponent {

  auditoria:Auditoria[];
  constructor(private usuarioServicio:UserService)
  {

  }

  ngOnInit(): void
  {
    this.obtenerAuditoria();

  }

  obtenerAuditoria()
  {
    return this.usuarioServicio.obtenerListaAuditoria().subscribe(dato =>{
      
      this.auditoria = dato;

    });
  }




}
