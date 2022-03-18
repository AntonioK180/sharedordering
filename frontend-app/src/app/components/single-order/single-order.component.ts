import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Order } from 'src/app/interfaces/order';
import { SeleniumService } from 'src/app/services/selenium.service';

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

	constructor(private seleniumService: SeleniumService) { }

	ngOnInit(): void {
		this.setStoreURL();
	}

	setStoreURL() {
		switch (this.order.storeName) {
			case 'waterstones.com':
				this.storeURL = 'https://www.waterstones.com/';
				break;

			case 'amazon.com':
				this.storeURL = 'https://www.amazon.com/';

		}
	}

	onDelete(order: Order): void {
		this.onDeleteOrder.emit(order);
	}

	executeOrder() {
		if (this.order.id !== undefined) {
			this.seleniumService.makeOrder(this.order.id).subscribe(
				(response) => {
					console.log(response);
				},
				(error) => {
					console.log(error);
				}
			)
		}
	}
}
