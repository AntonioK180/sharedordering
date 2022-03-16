import { Component, Input, OnInit } from '@angular/core';
import { Product } from 'src/app/interfaces/product';

@Component({
	selector: 'app-single-product',
	templateUrl: './single-product.component.html',
	styleUrls: ['./single-product.component.css']
})
export class SingleProductComponent implements OnInit {

	@Input()
	product!: Product;

	constructor() { }

	ngOnInit(): void {
	}

}
