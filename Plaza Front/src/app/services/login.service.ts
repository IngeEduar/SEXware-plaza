import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient:HttpClient) { }

  //Llama a un metodo del servidor en springboot y va a generar el token
  public generateToken(loginData:any)
  {
    return this.httpClient.post(`${baseUrl}/login`, loginData);

  }

  //Inicio de sesion y se establece el token el el LocalStorage
  public loginUser(token:any)
  {
    //Se almacena en el localStorage el token que se generó en el momento de iniciar sesion
    localStorage.setItem('token', token);

  }

  public isLoggedIn()
  {
    let tokenStr = localStorage.getItem('token');

    if(tokenStr == undefined || tokenStr == '' || tokenStr == null)
    {
      return false;


    }
    else{
      return true;
    }
  }

  //Se cierra sesion y se elimina el token del localStorage
  public logout()
  {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    return true;
  }

  //Obtenemos el toke 
  public getToken()
  {
    return localStorage.getItem('token');
  }

  public setUsuario(user:any)
  {

    //Convierte un valor de JS a un JSON
    localStorage.setItem('user', JSON.stringify(user));

  }

  public getUser()
  {
    let userStr = localStorage.getItem('user');

    if(userStr != null)
    {
      return JSON.parse(userStr);
    }else{
      this.logout();
      return null;
    }
  }

  public getUserRol()
  {
    let user = this.getUser();
    return user.authorities[0].authority;
  }

  public getCurrentUser(loginData:any)
  {
    return this.httpClient.post(`${baseUrl}/actual-usuario`, loginData);
  }

  public getLoginData()
  {
    
  }

  
}
