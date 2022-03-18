import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Order } from 'src/app/interfaces/order';
import { formatDate } from '@angular/common';
import { OrderService } from 'src/app/services/order.service';
import { ProductService } from 'src/app/services/product.service';
import { HttpErrorResponse } from '@angular/common/http';
import { SeleniumService } from 'src/app/services/selenium.service';
import { Product } from 'src/app/interfaces/product';

@Component({
	selector: 'app-add-order-form',
	templateUrl: './add-order-form.component.html',
	styleUrls: ['./add-order-form.component.css']
})
export class AddOrderFormComponent implements OnInit {
	private urlRegex = '(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?';
	public moreThanOneInputs = false;
	public errorText = "";
	public orderForm = this.fb.group({
		products: this.fb.array([new FormControl(null, [Validators.required, Validators.pattern(this.urlRegex)])]),
		store: ['amazon', Validators.required]
	});
	public allOrders: Order[] = [];

	constructor(private fb: FormBuilder, private orderService: OrderService, private productService: ProductService, private seleniumService: SeleniumService) { }

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
		this.products().push(new FormControl(null, [Validators.required, Validators.pattern(this.urlRegex)]));
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

	public addNewOrder(): void {
		let newOrder = this.getOrderFormValue();

		this.seleniumService.checkLinks(newOrder.products).subscribe(
			(resposne: Array<Product>) => {
				console.log(resposne);
				newOrder.products = resposne;

				this.orderService.addOrder(newOrder).subscribe(
					(resposne: Order) => {
						this.allOrders.push(resposne);
						this.errorText = "";
					},
					(error: HttpErrorResponse) => {
						console.log(error);
					}
				);

			},
			(error) => {
				console.log(error);

				switch (error.status) {
					case 401:
						this.errorText = "You need to be logged in!";
						break;
				}
			}
		);

	}

	public updateExistingOrder(existingOrder: Order): void {
		let newOrder = this.getOrderFormValue();
		newOrder.id = existingOrder.id;

		this.seleniumService.checkLinks(newOrder.products).subscribe(
			(resposne: Array<Product>) => {
				newOrder.products = resposne;
				this.orderService.updateOrder(newOrder).subscribe(
					(response: Order) => {
						console.log(response);
					},

					(error) => {
						console.log(error);
					}
				);
			},
			(error) => {
				console.log(error);
			}
		);


	}

	onSubmit(): void {
		console.log(this.orderForm.controls['products'].valid);
		// if (!this.orderForm.valid) {
		// 	return;
		// }

		let potentialOrder = this.orderExists();

		potentialOrder ? this.updateExistingOrder(potentialOrder) : this.addNewOrder();
	}

}
