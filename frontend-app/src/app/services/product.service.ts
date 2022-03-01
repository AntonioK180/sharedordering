import { Injectable } from '@angular/core';
import { Product } from '../interfaces/product';

@Injectable({
	providedIn: 'root'
})
export class ProductService {

	constructor() { }

	public convertArrayToProductsArray(urlsArray: []): Array<Product> {
		let productsArray = new Array<Product>();
		for (let productUrl of urlsArray) {
			productsArray.push({
				url: productUrl,
				price: 0,
				paid: false,
			});
		}
		return productsArray;
	}
}
