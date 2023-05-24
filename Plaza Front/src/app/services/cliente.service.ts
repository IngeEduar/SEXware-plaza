import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private httpClient: HttpClient) { }

  public hacerPedido(pedido:any, correo: string, restaurante: string)
  {
    return this.httpClient.post(`${baseUrl}/cliente/realizar-pedido/`+ restaurante + `/` + correo, pedido);
  }
}
