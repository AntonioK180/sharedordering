import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddOrderFormComponent } from './components/add-order-form/add-order-form.component';

const routes: Routes = [
  { path: 'neworder', component: AddOrderFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
