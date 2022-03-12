import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Product } from '../interfaces/product';

const PRODUCTS_API = environment.apiBaseUrl + '/api/v1/products';
@Injectable({
	providedIn: 'root'
})
export class ProductService {

	constructor(private http: HttpClient) { }

	public getCurrentUserProducts(): Observable<Product[]> {
		return this.http.get<Product[]>(PRODUCTS_API);
	}

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
