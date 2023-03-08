import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ListarPlatos } from 'src/app/listar-platos';
import { NombreRestaurante } from 'src/app/nombre-restaurante';
import { LoginService } from 'src/app/services/login.service';
import { PropietarioService } from 'src/app/services/propietario.service';
import { ListaRestaurantesComponent } from '../lista-restaurantes/lista-restaurantes.component';

@Component({
  selector: 'app-lista-plato',
  templateUrl: './lista-plato.component.html',
  styleUrls: ['./lista-plato.component.css']
})
export class ListaPlatoComponent {

  nombre = ' ';
  constructor(private propietarioService:PropietarioService, public loginService:LoginService, private router:Router, private route:ActivatedRoute)
  {}

  @Input() resultadoNombre:string

  listaPlato:ListarPlatos[];

  ngOnInit(): void{

    const nombre = this.route.snapshot.paramMap.get('nombre')!;
    //this.nombre = this.route.snapshot.paramMap.get('nombre')
    console.log(nombre)
    this.obtenerListaPlato(nombre)
  }

  obtenerListaPlato(nombre:string)
  {
    
  
    return this.propietarioService.obtenerListaPlatos(nombre).subscribe(dato=>{

      this.listaPlato = dato;
    })
  }

}
