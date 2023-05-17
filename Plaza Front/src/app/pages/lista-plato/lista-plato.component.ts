import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ListarPlatos } from 'src/app/listar-platos';
import { NombrePlato } from 'src/app/nombre-plato';
import { NombreRestaurante } from 'src/app/nombre-restaurante';
import { LoginService } from 'src/app/services/login.service';
import { PropietarioService } from 'src/app/services/propietario.service';
import { ListaRestaurantesComponent } from '../lista-restaurantes/lista-restaurantes.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-lista-plato',
  templateUrl: './lista-plato.component.html',
  styleUrls: ['./lista-plato.component.css']
})
export class ListaPlatoComponent {

  nombreRestaurante = '';

  resultadoNombre:string;

  public nombrePlatos ={
    nombrePlato : ''
  }

  public plato={
    "nombreRest": this.nombreRestaurante,
    "nombrePlato": '',
    "estado": true,
    "userLogeado": this.loginService.getUser().username
  }

  nombrePlato:NombrePlato;

  nombre = ' ';
  snack: any;
  constructor(private propietarioService:PropietarioService, public loginService:LoginService, private router:Router, private route:ActivatedRoute)
  {}

  listaPlato:ListarPlatos[];

  ngOnInit(): void{

    const nombre = this.route.snapshot.paramMap.get('nombre')!;
    //this.nombre = this.route.snapshot.paramMap.get('nombre')
    console.log(nombre)
    this.obtenerListaPlato(nombre)
    this.plato.nombreRest = nombre;

  }

  salir(){
    console.log("Saliendo");
    close();
    open("/login");
    this.loginService.logout();
  }


  obtenerListaPlato(nombre:string)
  {

    this.nombreRestaurante = nombre;
    
    return this.propietarioService.obtenerListaPlatos(nombre).subscribe(dato=>{

      this.listaPlato = dato;
    })
  }

  cambiarEstado(nombre:string, estado:boolean){


    this.plato.estado = estado;
    this.plato.nombrePlato = nombre;

    this.propietarioService.inhabilitarPlato(this.plato).subscribe(
      (data)=> 
      {
        console.log(data);
        Swal.fire({title: '<strong>Plato actualizado con éxito</strong>',
            icon: 'success',
            html:
              '<form (ngSubmit) ="recargar()">'+
              '<button id="but" type="submit" class="btn">'+
              'Hecho'+
              '</button>'+
            '</form>',
            showCloseButton: true,
            showConfirmButton: false,

          });

      }, (error) =>{
        console.log(error);
        Swal.fire({title: '<strong>Plato actualizado con éxito</strong>',
            icon: 'success',
            html:
              '<form (ngSubmit) ="recargar()">'+
              '<button id="but" type="submit" class="btn">'+
              'Hecho'+
              '</button>'+
            '</form>',
            showCloseButton: true,
            showConfirmButton: false,

          });

      }
    )

  }

  obtenerNombrePlato(nombre:string)
  {

    this.resultadoNombre = nombre;
    this.nombrePlatos.nombrePlato = this.resultadoNombre;
    this.nombrePlato =
    {
      nombre : this.resultadoNombre
    }
    console.log(this.nombrePlato.nombre)

    window.location.href = "/actualizar-plato/"+nombre+"/"+this.nombreRestaurante;

  }

}
