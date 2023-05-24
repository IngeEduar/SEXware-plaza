import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import baseUrl from './helper';
import { Observable } from 'rxjs';
import { Auditoria } from '../auditoria';
import { Restaurante } from 'src/restaurante';
import { ListaPropietarios } from '../lista-propietarios';

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



  public addCliente(user:any){
    return this.httpClient.post(`${baseUrl}/cliente/registrar`, user);
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
    return this.httpClient.post(`${baseUrl}/restaurante/guardar-sin`, restaurante);
  }

  cambiarPassword(contrasegna:any)
  {
    return this.httpClient.post(`${baseUrl}/admin/actualizar-password`, contrasegna);

  }

  cambiarContrasegna(contrasegna:any)
  {
    return this.httpClient.post(`${baseUrl}/admin/change-password`, contrasegna);

  }

  obtenerUsuarioLogeado(loginData:any)
  {
    return (loginData.email)
  }

  obtenerImagen():Observable<Restaurante>{

    return this.httpClient.get<Restaurante>('http://localhost:8080/img/photos/')

  }

  obtenerListaPropietarios(): Observable<ListaPropietarios[]>
  {
    return this.httpClient.get<ListaPropietarios[]>(`${baseUrl}/admin/lista-propietarios`);

  }





  




}
