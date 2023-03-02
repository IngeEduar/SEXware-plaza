import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActualizarPasswordComponent } from './actualizar-password/actualizar-password.component';
import { ListaAuditoriaaComponent } from './lista-auditoriaa/lista-auditoriaa.component';
import { DashboardAdminComponent } from './pages/dashboard-admin/dashboard-admin.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { RegistroRestauranteComponent } from './registro-restaurante/registro-restaurante.component';

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
    pathMatch:'full'
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
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
