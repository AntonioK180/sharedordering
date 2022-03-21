import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormArray, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Order } from 'src/app/interfaces/order';
import { formatDate } from '@angular/common';
import { OrderService } from 'src/app/services/order.service';
import { ProductService } from 'src/app/services/product.service';
import { HttpErrorResponse } from '@angular/common/http';
import { SeleniumService } from 'src/app/services/selenium.service';
import { Product } from 'src/app/interfaces/product';
import RevolutCheckout from '@revolut/checkout';
import { RevolutService } from 'src/app/services/revolut.service';
import { trigger } from '@angular/animations';

@Component({
	selector: 'app-add-order-form',
	templateUrl: './add-order-form.component.html',
	styleUrls: ['./add-order-form.component.css']
})
export class AddOrderFormComponent implements OnInit {
	public moreThanOneInputs = false;
	public errorText = "";
	public orderForm = this.fb.group({
		products: this.fb.array([new FormControl(null, Validators.required)]),
		store: ['amazon.com', Validators.required]
	});
	public allOrders: Order[] = [];

	constructor(private fb: FormBuilder, private orderService: OrderService, private productService: ProductService, private seleniumService: SeleniumService, private revolutService: RevolutService) { }

	public ngOnInit(): void {
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

	public products(): FormArray {
		let productsArray = this.orderForm.get('products') as FormArray;
		this.moreThanOneInputs = (productsArray.length > 1);
		return productsArray;
	}

	public addProduct() {
		this.products().push(new FormControl(null, [Validators.required]));
	}

	public removeProduct(index: number) {
		this.products().removeAt(index);
	}

	private getOrderFormValue(): Order {
		let today = new Date();

		let newOrder: Order = {
			storeName: this.orderForm.value['store'],
			creationDate: formatDate(today, 'yyyy-MM-dd', 'en'),
			orderDate: formatDate(today.setDate(today.getDate() + 7), 'yyyy-MM-dd', 'en'),
			products: this.productService.convertArrayToProductsArray(this.orderForm.value['products'])
		};

		return newOrder;
	}

	private orderExists(): Order | false {
		this.getOrders();
		console.log(this.allOrders);
		if (this.allOrders.length > 0) {
			for (let order of this.allOrders) {
				if (order.storeName === this.orderForm.value['store']) {
					return order;
				}
			}
		}

		return false;

	}

	private allURLsValid(products: Product[]): string[] {
		let invalidURLs = [];
		for (let product of products) {
			if (product.id === -1) {
				invalidURLs.push(product.url);
			}
		}

		let allProducts = this.products();
		for (let productInput of allProducts.controls) {
			if (invalidURLs.includes(productInput.value)) {
				productInput.setErrors({ urlInValid: 'true' });
				console.log(productInput.value);
			}
		}

		return invalidURLs;
	}

	public addNewOrder(): void {
		let newOrder = this.getOrderFormValue();

		let productsDTO = {
			products: newOrder.products,
			storeName: this.orderForm.value['store']
		}

		this.seleniumService.checkLinks(productsDTO).subscribe(
			(response: Array<Product>) => {
				let invalidProducts = this.allURLsValid(response);
				if (invalidProducts.length !== 0 || response.length === 0) {
					return;
				}

				let priceSum = this.calcSumPrice(response);
				this.triggerBackendPayment(priceSum, 'USD');

				newOrder.products = response;

				this.orderService.addOrder(newOrder).subscribe(
					(resposne: Order) => {
						this.allOrders.push(resposne);
						this.errorText = "";
					},
					(error: HttpErrorResponse) => {
						console.log("ERROR RESPONSE: ");
						console.log(error);
					}
				);
			},
			(error) => {
				switch (error.status) {
					case 401:
						this.errorText = "You need to be logged in!";
						break;
				}
			}
		);

	}

	private calcSumPrice(products: Product[]): number {
		let priceSum = 0;
		for (let product of products) {
			priceSum += product.price;
		}

		return priceSum;
	}

	public updateExistingOrder(existingOrder: Order): void {
		let newOrder = this.getOrderFormValue();
		newOrder.id = existingOrder.id;

		let productsDTO = {
			storeName: this.orderForm.value['store'],
			products: newOrder.products
		}

		this.seleniumService.checkLinks(productsDTO).subscribe(
			(response: Array<Product>) => {
				this.errorText = "";
				let invalidProducts = this.allURLsValid(response);
				if (invalidProducts.length !== 0 || response.length === 0) {
					return;
				}

				newOrder.products = response;

				let priceSum = this.calcSumPrice(response);

				this.triggerBackendPayment(priceSum, 'USD');

				this.orderService.updateOrder(newOrder).subscribe(
					(response: Order) => {

					},
					(error) => {

					}
				);
			},

			(error) => {
				console.error(error);
			}
		);

	}

	public clearReolvutField(): void {
		let element = document.getElementById('revolut-pay');
		if (element !== null) {
			element.innerHTML = "";
		}

	}

	public triggerBackendPayment(amount: number, currency: string) {
		let revolutDTO = {
			amount: amount * 100,
			currency: currency
		}



		this.revolutService.triggerBackendPayment(revolutDTO).subscribe(
			(response) => {

				RevolutCheckout(response.public_id, 'sandbox').then(function (instance) {
					let element = document.getElementById('revolut-pay');

					if (element !== null) {
						element.innerHTML = "";
						instance.revolutPay({
							target: element,
							onSuccess() {
								console.log('Payment completed')
							},
							onError(error) {
								console.error('Payment failed: ' + error.message)
							}
						});
					}

				});


			},
			(error) => {
				console.log("Error: " + error.message);
			}
		);
	}

	onSubmit(): void {
		this.clearReolvutField();
		if (!this.orderForm.valid) {
			return;
		}

		let potentialOrder = this.orderExists();

		potentialOrder ? this.updateExistingOrder(potentialOrder) : this.addNewOrder();
	}

}
