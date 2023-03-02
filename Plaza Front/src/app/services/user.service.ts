import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import baseUrl from './helper';
import { Observable } from 'rxjs';
import { Auditoria } from '../auditoria';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient : HttpClient) { }

  //Se le pasa un usario de tipo any
  public añadirUsuario(user:any)
  {
    return this.httpClient.post(`${baseUrl}/admin/registrar/adminFesc@fesc.edu.co`, user);
  }

  //Obtener el listado de la auditoria de la base de datos
  obtenerListaAuditoria(): Observable<Auditoria[]>
  {

    return this.httpClient.get<Auditoria[]>(`${baseUrl}/admin/auditoria`)
  }
  
  //Registrar un restaurante, eso lo hace el administrador
  registrarRestaurante(restaurante:any)
  {
    //return this.httpClient.post(`${baseUrl}/restaurante/guardar`, restaurante);
    return this.httpClient.post(`${baseUrl}/restaurante/guardar`, restaurante);
  }

  cambiarPassword(contrasegna:any)
  {
    return this.httpClient.post(`${baseUrl}/admin/actualizar-password`, contrasegna);

  }

  obtenerUsuarioLogeado(loginData:any)
  {
    return (loginData.email)
  }




  




}
