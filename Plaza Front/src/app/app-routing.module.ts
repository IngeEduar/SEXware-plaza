import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActualizarPasswordComponent } from './actualizar-password/actualizar-password.component';
import { ListaAuditoriaaComponent } from './lista-auditoriaa/lista-auditoriaa.component';
import { ListaRestaurantes } from './lista-restaurantes';
import { DashboardAdminComponent } from './pages/dashboard-admin/dashboard-admin.component';
import { HomeComponent } from './pages/home/home.component';
import { ListaPlatoComponent } from './pages/lista-plato/lista-plato.component';
import { ListaRestaurantesComponent } from './pages/lista-restaurantes/lista-restaurantes.component';
import { LoginComponent } from './pages/login/login.component';
import { DashboardPropietarioComponent } from './pages/propietario/dashboard-propietario/dashboard-propietario.component';
import { RegistrarPlatoComponent } from './pages/registrar-plato/registrar-plato.component';
import { SignupComponent } from './pages/signup/signup.component';
import { RegistroRestauranteComponent } from './registro-restaurante/registro-restaurante.component';
import { AdminGuard } from './services/admin.guard';
import { PropietarioGuard } from './services/propietario.guard';

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
    path:'actualizar-contrasegna',
    component:ActualizarPasswordComponent,
    pathMatch:'full'
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
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
