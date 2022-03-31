import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddOrderFormComponent } from './components/add-order-form/add-order-form.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { ReactiveFormsModule } from '@angular/forms';
import { MatMenuModule } from '@angular/material/menu';
import { HttpClientModule } from '@angular/common/http';
import { RegistrationFormComponent } from './components/registration-form/registration-form.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { authInterceptorProviders } from './helpers/auth.interceptor';
import { ViewOrdersComponent } from './components/view-orders/view-orders.component';
import { SingleOrderComponent } from './components/single-order/single-order.component';
import { HeroLandingComponent } from './components/hero-landing/hero-landing.component';
import { MyProfileComponent } from './components/my-profile/my-profile.component';
import { SingleProductComponent } from './components/single-product/single-product.component';

@NgModule({
	declarations: [
		AppComponent,
		AddOrderFormComponent,
		NavbarComponent,
		RegistrationFormComponent,
		LoginFormComponent,
		ViewOrdersComponent,
		SingleOrderComponent,
		HeroLandingComponent,
		MyProfileComponent,
		SingleProductComponent
	],
	imports: [
		BrowserModule,
		AppRoutingModule,
		BrowserAnimationsModule,
		LayoutModule,
		MatToolbarModule,
		MatButtonModule,
		MatSidenavModule,
		MatIconModule,
		MatListModule,
		MatInputModule,
		MatSelectModule,
		MatRadioModule,
		MatCardModule,
		MatGridListModule,
		ReactiveFormsModule,
		MatMenuModule,
		HttpClientModule,
	],
	providers: [authInterceptorProviders],
	bootstrap: [AppComponent]
})
export class AppModule { }
