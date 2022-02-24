import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Order } from 'src/app/interfaces/order';
import { formatDate } from '@angular/common';
import { OrderService } from 'src/app/services/order.service';
import { ProductService } from 'src/app/services/product.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
	selector: 'app-add-order-form',
	templateUrl: './add-order-form.component.html',
	styleUrls: ['./add-order-form.component.css']
})
export class AddOrderFormComponent implements OnInit {
	public moreThanOneInputs = false;
	public orderForm = this.fb.group({
		products: this.fb.array([new FormControl(null, Validators.required)]),
		store: ['amazon', Validators.required]
	});

	constructor(private fb: FormBuilder, private orderService: OrderService, private productService: ProductService) { }

	public ngOnInit(): void {
		console.log("Here I am!");
	}

	public products(): FormArray {
		let productsArray = this.orderForm.get('products') as FormArray;
		this.moreThanOneInputs = (productsArray.length > 1);
		return productsArray;
	}

	public addProduct() {
		this.products().push(new FormControl(null, Validators.required));
	}

	public removeProduct(index: number) {
		this.products().removeAt(index);
	}

	onSubmit(): void {
		console.log(this.orderForm.value);
		let productURLs = this.productService.convertArrayToProductsArray(this.orderForm.value['products']);
		let today = new Date();

		let newOrder: Order = {
			storeName: "",
			creationDate: formatDate(today, 'yyyy-MM-dd', 'en'),
			orderDate: formatDate(today.setDate(today.getDate() + 7), 'yyyy-MM-dd', 'en'),
			products: productURLs
		};

		this.orderService.addOrder(newOrder).subscribe(
			(resposne: Order) => {
				console.log('You have successfully made a new order!');
			},
			(error: HttpErrorResponse) => {
				console.log(error);
			}
		);
	}

}
