import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {MatButtonModule} from '@angular/material/button';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SignupComponent } from './pages/signup/signup.component';
import { LoginComponent } from './pages/login/login.component';

import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { FormsModule } from '@angular/forms';
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

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SignupComponent,
    LoginComponent,
    HomeComponent,
    ListaAuditoriaaComponent,
    RegistroRestauranteComponent,
    ActualizarPasswordComponent
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
    MatIconModule
  ],
  providers: [
    {
        provide : HTTP_INTERCEPTORS,
        useClass : AuthInterceptor,
        multi:true
    
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
