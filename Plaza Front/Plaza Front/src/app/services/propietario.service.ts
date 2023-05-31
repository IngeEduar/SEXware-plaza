import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ListaRestaurantes } from '../lista-restaurantes';
import { Observable } from 'rxjs';
import baseUrl from './helper';
import { UserService } from './user.service';
import { LoginService } from './login.service';
import { ListarPlatos } from '../listar-platos';
import { ListaRestaurantesComponent } from '../pages/lista-restaurantes/lista-restaurantes.component';
import { Empleado } from '../empleado';

@Injectable({
  providedIn: 'root'
})
export class PropietarioService {


  constructor(private httpClient : HttpClient, private loginService:LoginService, private userServices:UserService) { }

  //añadir un plato de comida
  public añadirPlato(plato:any)
  {
    return this.httpClient.post(`${baseUrl}/plato/agregar/`, plato)

  }

  public inhabilitarPlato(plato:any){

    return this.httpClient.post(`${baseUrl}/plato/mod-estado`, plato)

  }

  public modificarPlato(actualizarPlato:any)
  {
    return this.httpClient.post(`${baseUrl}/plato/modificar/`, actualizarPlato)
  }

    //Obtener el listado de la auditoria de la base de datos
    obtenerListaRestaurantes(): Observable<ListaRestaurantes[]>
    {
      //console.log(this.loginService.getUser())
      return this.httpClient.get<ListaRestaurantes[]>(`${baseUrl}/restaurante/lista-propietario/`+ this.loginService.getUser().username)
    }

    obtenerListaRestaurantesOrdenada(): Observable<ListaRestaurantes[]>{
      return this.httpClient.get<ListaRestaurantes[]>(`${baseUrl}/cliente/listar-restaurantes`)
    }



    //Obtener el listado de platos de la base de datos
    obtenerListaPlatos(nombre:string): Observable<ListarPlatos[]>
    {
      
      return this.httpClient.get<ListarPlatos[]>(`${baseUrl}/plato/lista-rest/`+ nombre);
    }

    obtenerListaEmpleados(nombre:string): Observable<Empleado[]>
    {
      return this.httpClient.get<Empleado[]>(`${baseUrl}/empleado/listar/`+ nombre);


    }

    registrarEmpleado(user:any)
    {
      return this.httpClient.post(`${baseUrl}/empleado/guardar`, user);
    }
}
