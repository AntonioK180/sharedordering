import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/interfaces/order';
import { OrderService } from 'src/app/services/order.service';
import { RevolutService } from 'src/app/services/revolut.service';
import { SeleniumService } from 'src/app/services/selenium.service';

@Component({
	selector: 'app-revolut-test',
	templateUrl: './revolut-test.component.html',
	styleUrls: ['./revolut-test.component.css']
})
export class RevolutTestComponent implements OnInit {

	private orderId: number = 0;
	private allOrders: Order[] = [];

	constructor(private revolutService: RevolutService, private seleniumService: SeleniumService, private orderService: OrderService) { }

	ngOnInit(): void {
		this.getOrders();
	}

	public getOrders(): void {
		this.orderService.getOrders().subscribe(
			(response: Order[]) => {
				this.allOrders = response.reverse();
			},
			(error: HttpErrorResponse) => {
				console.log(error.message);
			}
		);
	}

	public orderFromWaterstones() {
		for (let order of this.allOrders) {
			console.log(order.storeName);
			if (order.storeName === 'waterstones' && order.id) {
				this.seleniumService.makeOrder(order.id, order.storeName).subscribe(
					(response) => {
						console.log(response);
					},
					(error) => {
						console.log(error);
					}
				);
			}
		}
		console.log("I will execute an order from Waterstones!");
	}


	public orderFromAmazon() {
		for (let order of this.allOrders) {
			console.log(order.storeName);
			if (order.storeName === 'amazon' && order.id) {
				this.seleniumService.makeOrder(order.id, order.storeName).subscribe(
					(response) => {
						console.log(response);
					},
					(error) => {
						console.log(error);
					}
				);
			}
		}
		console.log("I will execute an order from Amazon!");
	}


	public initPayment() {
		console.log('GO GO GO GO');

		this.revolutService.createPayment().subscribe(
			(response) => {
				console.log(response);
			},
			(error) => {
				console.log("Error: " + JSON.stringify(error));
			}
		);
	}

	public triggerBackendPayment() {
		console.log("triggered backend payment");
		this.revolutService.triggerBackendPayment().subscribe(
			(response) => {
				console.log("Response: " + JSON.stringify(response));
			},
			(error) => {
				console.log("Error: " + JSON.stringify(error));
			}
		);
	}

}
