import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddOrderFormComponent } from './components/add-order-form/add-order-form.component';
import { HeroLandingComponent } from './components/hero-landing/hero-landing.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { MyProfileComponent } from './components/my-profile/my-profile.component';
import { RegistrationFormComponent } from './components/registration-form/registration-form.component';
import { RevolutTestComponent } from './components/revolut-test/revolut-test.component';
import { ViewOrdersComponent } from './components/view-orders/view-orders.component';

const routes: Routes = [
	{ path: 'allorders', component: ViewOrdersComponent },
	{ path: 'neworder', component: AddOrderFormComponent },
	{ path: 'register', component: RegistrationFormComponent },
	{ path: 'login', component: LoginFormComponent },
	{ path: '', component: HeroLandingComponent },
	{ path: 'revolut', component: RevolutTestComponent },
	{ path: 'myprofile', component: MyProfileComponent }
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule { }
