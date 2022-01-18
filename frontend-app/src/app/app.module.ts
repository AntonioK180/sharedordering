import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OrderItemComponent } from './components/order-item/order-item.component';
import { AddOrderFormComponent } from './components/add-order-form/add-order-form.component';

@NgModule({
  declarations: [
    AppComponent,
    OrderItemComponent,
    AddOrderFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
