import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {MatButtonModule} from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SignupComponent } from './pages/signup/signup.component';
import { LoginComponent } from './pages/login/login.component';

import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { HomeComponent } from './pages/home/home.component';
import {MatCardModule} from '@angular/material/card';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import { AuthInterceptor, authInterceptorProviders } from './services/auth.interceptos';
import { ListaAuditoriaaComponent } from './lista-auditoriaa/lista-auditoriaa.component';
import { RegistroRestauranteComponent } from './registro-restaurante/registro-restaurante.component';
import { ActualizarPasswordComponent } from './actualizar-password/actualizar-password.component';
import { DashboardPropietarioComponent } from './pages/propietario/dashboard-propietario/dashboard-propietario.component';
import { RegistrarPlatoComponent } from './pages/registrar-plato/registrar-plato.component';
import { ListaRestaurantesComponent } from './pages/lista-restaurantes/lista-restaurantes.component';
import { ListaPlatoComponent } from './pages/lista-plato/lista-plato.component';
import { ActualizarPlatoComponent } from './pages/actualizar-plato/actualizar-plato.component';
import { RegistrarEmpleadoComponent } from './registrar-empleado/registrar-empleado.component';
import { ListaEmpleadosComponent } from './lista-empleados/lista-empleados.component';
import { ClienteRegisterComponent } from './cliente-register/cliente-register.component';
import { ClienteDasboardComponent } from './cliente-dasboard/cliente-dasboard.component';
import { RealizarPedidoComponent } from './realizar-pedido/realizar-pedido.component';
import { EmpleadoComponent } from './pages/empleado/empleado.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SignupComponent,
    LoginComponent,
    HomeComponent,
    ListaAuditoriaaComponent,
    RegistroRestauranteComponent,
    ActualizarPasswordComponent,
    DashboardPropietarioComponent,
    RegistrarPlatoComponent,
    ListaRestaurantesComponent,
    ListaPlatoComponent,
    ActualizarPlatoComponent,
    RegistrarEmpleadoComponent,
    ListaEmpleadosComponent,
    ClienteRegisterComponent,
    ClienteDasboardComponent,
    RealizarPedidoComponent,
    EmpleadoComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule, 
    MatButtonModule,
    MatFormFieldModule, 
    MatInputModule,
    FormsModule,
    HttpClientModule,
    MatSnackBarModule, 
    MatCardModule,
    MatToolbarModule,
    MatIconModule, 
    ReactiveFormsModule,
    MatSelectModule
  ],
  providers: [    {
        provide : HTTP_INTERCEPTORS,
        useClass : AuthInterceptor,
        multi:true
    
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
