import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddOrderFormComponent } from './components/add-order-form/add-order-form.component';
import { FormForComparisonComponent } from './components/form-for-comparison/form-for-comparison.component';

const routes: Routes = [
  { path: 'neworder', component: AddOrderFormComponent },
  { path: 'compform', component: FormForComparisonComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
