import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Order } from 'src/app/interfaces/order';

@Component({
	selector: 'app-single-order',
	templateUrl: './single-order.component.html',
	styleUrls: ['./single-order.component.css']
})
export class SingleOrderComponent implements OnInit {

	@Input()
	order!: Order;

	@Output()
	onDeleteOrder: EventEmitter<Order> = new EventEmitter();

	constructor() { }

	ngOnInit(): void { }

	onDelete(order: Order): void {
		this.onDeleteOrder.emit(order);
	}
}
