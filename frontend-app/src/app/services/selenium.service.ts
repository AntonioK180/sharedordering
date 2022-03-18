import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Product } from '../interfaces/product';

const SELENIUM_API = environment.apiBaseUrl + '/api/v1/selenium';

@Injectable({
	providedIn: 'root'
})
export class SeleniumService {

	constructor(private http: HttpClient) { }

	public makeOrder(orderId: number, storeName: string): Observable<void> {
		return this.http.get<void>(SELENIUM_API + `/${storeName}/${orderId}`);
	}

	checkLinks(productsDTO: object): Observable<Array<Product>> {
		return this.http.post<Array<Product>>(SELENIUM_API, productsDTO, {
			headers: new HttpHeaders({
				'Content-Type': 'application/json'
			})
		});
	}
}
