import { Injectable } from '@angular/core';
import { Product } from '../interfaces/product';

@Injectable({
	providedIn: 'root'
})
export class ProductService {

	constructor() { }

	public convertArrayToProductsArray(urlsArray: []): Array<Product> {
		let productsArray = new Array<Product>();
		for (let url of urlsArray) {
			productsArray.push({
				value: url,
				price: 0,
				storeName: ""
			});
		}
		return productsArray;
	}
}
