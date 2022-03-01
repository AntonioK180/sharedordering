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

	public makeOrder(): Observable<void> {
		return this.http.get<void>(SELENIUM_API)
	}

	checkLinks(links: Array<Product>): Observable<Array<Product>> {
		return this.http.post<Array<Product>>(SELENIUM_API, links, {
			headers: new HttpHeaders({
				'Content-Type': 'application/json'
			})
		});
	}
}
