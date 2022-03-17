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

	@Input()
	displayProducts!: boolean;

	@Output()
	onDeleteOrder: EventEmitter<Order> = new EventEmitter();

	storeURL: string = "#";

	constructor() { }

	ngOnInit(): void {
		this.setStoreURL();
	}

	setStoreURL() {
		switch (this.order.storeName) {
			case 'waterstones':
				this.storeURL = 'https://www.waterstones.com/';
				break;

			case 'amazon':
				this.storeURL = 'https://www.amazon.com/';

		}
	}

	onDelete(order: Order): void {
		this.onDeleteOrder.emit(order);
	}
}
