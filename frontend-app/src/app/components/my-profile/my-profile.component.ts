import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/interfaces/order';
import { Product } from 'src/app/interfaces/product';
import { OrderService } from 'src/app/services/order.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
	selector: 'app-my-profile',
	templateUrl: './my-profile.component.html',
	styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

	public myProducts: Product[] = [];
	public myOrders: Order[] = [];

	constructor(private productService: ProductService, private orderService: OrderService) { }

	ngOnInit(): void {
		this.getUserProducts();
		this.getUserOrders();
	}

	getUserOrders(): void {
		this.orderService.getCurrentUserOrders().subscribe(
			(response) => {
				console.log(response);
				this.myOrders = response.reverse();
			},
			(error) => {
				console.log(error);
			}
		)
	}

	getUserProducts(): void {
		this.productService.getCurrentUserProducts().subscribe(
			(response) => {
				console.log(response);
				this.myProducts = response;
			},
			(error) => {
				console.log(error);
			}
		)
	}

}
