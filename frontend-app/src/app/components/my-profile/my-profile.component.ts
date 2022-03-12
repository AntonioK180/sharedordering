import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/interfaces/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
	selector: 'app-my-profile',
	templateUrl: './my-profile.component.html',
	styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

	public myProducts: Product[] = [];

	constructor(private productService: ProductService) { }

	ngOnInit(): void {
		this.getUserProducts();
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
