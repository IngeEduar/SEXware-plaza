import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActualizarPasswordComponent } from './actualizar-password/actualizar-password.component';
import { ListaAuditoriaaComponent } from './lista-auditoriaa/lista-auditoriaa.component';
import { ListaEmpleadosComponent } from './lista-empleados/lista-empleados.component';
import { ListaRestaurantes } from './lista-restaurantes';
import { ActualizarPlatoComponent } from './pages/actualizar-plato/actualizar-plato.component';
import { DashboardAdminComponent } from './pages/dashboard-admin/dashboard-admin.component';
import { HomeComponent } from './pages/home/home.component';
import { ListaPlatoComponent } from './pages/lista-plato/lista-plato.component';
import { ListaRestaurantesComponent } from './pages/lista-restaurantes/lista-restaurantes.component';
import { LoginComponent } from './pages/login/login.component';
import { DashboardPropietarioComponent } from './pages/propietario/dashboard-propietario/dashboard-propietario.component';
import { RegistrarPlatoComponent } from './pages/registrar-plato/registrar-plato.component';
import { SignupComponent } from './pages/signup/signup.component';
import { RegistrarEmpleadoComponent } from './registrar-empleado/registrar-empleado.component';
import { RegistroRestauranteComponent } from './registro-restaurante/registro-restaurante.component';
import { AdminGuard } from './services/admin.guard';
import { PropietarioGuard } from './services/propietario.guard';
import { ClienteRegisterComponent } from './cliente-register/cliente-register.component';
import { ClienteDasboardComponent } from './cliente-dasboard/cliente-dasboard.component';
import { RealizarPedidoComponent } from './realizar-pedido/realizar-pedido.component';
import { EmpleadoComponent } from './empleado/empleado.component';
import { ListaPlatosPedidoComponent } from './pages/lista-platos-pedido/lista-platos-pedido.component';
import { ListaListosComponent } from './pages/lista-listos/lista-listos.component';
import { MisPedidosClienteComponent } from './pages/mis-pedidos-cliente/mis-pedidos-cliente.component';

const routes: Routes = [
  {
    path:'',
    component:HomeComponent,
    pathMatch:'full'
  },
  {
    path:'signup',
    component:SignupComponent,
    pathMatch:'full'
  },
  {
    path:'admin',
    component: DashboardAdminComponent,
    pathMatch:'full',
    canActivate:[AdminGuard]
  },
  {
    path:'propietario',
    component:DashboardPropietarioComponent,
    pathMatch:'full',
    canActivate:[PropietarioGuard]
  },

  {
    path:'login',
    component:LoginComponent,
    pathMatch:'full'
  },
  {
    path:'auditoria',
    component:ListaAuditoriaaComponent,
    pathMatch:'full'
  },
  {
    path: 'registro-restaurante',
    component:RegistroRestauranteComponent,
    pathMatch:'full'
  },
  {
    path:'actualizar-contrasegna/:nombre',
    component:ActualizarPasswordComponent,
    pathMatch:'full',
  },
  {
    path:'registrar-plato/:nombre',
    component:RegistrarPlatoComponent,
    pathMatch:'full'
  },
  {
    path:'lista-restaurantes',
    component:ListaRestaurantesComponent,
    pathMatch:'full'
  },
  {
    path:'lista-plato/:nombre',
    component:ListaPlatoComponent,
    pathMatch:'full'
  },
  {
    path:'actualizar-plato/:nombre/:nombreRestaurante',
    component:ActualizarPlatoComponent,
    pathMatch:'full'
  },
  {
    path:'registrar-empleado/:nombre',
    component:RegistrarEmpleadoComponent,
    pathMatch:'full'
  },
  {
    path:'lista.empleados/:nombre',
    component:ListaEmpleadosComponent,
    pathMatch:'full'
  },
  {
    path:'registro-cliente',
    component:ClienteRegisterComponent,
    pathMatch:'full'
  },
  {
    path:'cliente',
    component:ClienteDasboardComponent,
    pathMatch:'full'
  },
  {
    path:'realizar-pedido/:correo/:nombreRestaurante/:nombrePlato',
    component:RealizarPedidoComponent,
    pathMatch:'full'
  },
  {
    path:'empleado',
    component:EmpleadoComponent,
    pathMatch:'full'
  },
  {
    path:'lista-platos-pedido',
    component:ListaPlatosPedidoComponent,
    pathMatch:'full'
  },
  {
    path:'lista-listos',
    component:ListaListosComponent,
    pathMatch:'full'
  },
  {
    path:'mis-pedidos-cliente',
    component:MisPedidosClienteComponent,
    pathMatch:'full'

  }
 
  
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
