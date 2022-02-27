import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/interfaces/order';
import { OrderService } from 'src/app/services/order.service';

@Component({
	selector: 'app-view-orders',
	templateUrl: './view-orders.component.html',
	styleUrls: ['./view-orders.component.css']
})
export class ViewOrdersComponent implements OnInit {

	public orders: Order[] = [];

	constructor(private orderService: OrderService) { }

	ngOnInit(): void {
		this.getOrders();
	}

	public getOrders(): void {
		this.orderService.getOrders().subscribe(
			(response: Order[]) => {
				this.orders = response.reverse();
			},
			(error: HttpErrorResponse) => {
				console.log(error.message);
			}
		);
	}

	public deleteOrder(order: Order): void {
		this.orderService.deleteOrder(order.id).subscribe(
			(response: void) => {
				console.log(response);
				this.getOrders();
			},
			(error: HttpErrorResponse) => {
				console.log(error.message);
			}
		);
	}

}
