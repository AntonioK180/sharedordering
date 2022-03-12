import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const REVOLUT_API = 'https://merchant.revolut.com/api/1.0';
const BACKEND_REVOLUT = environment.apiBaseUrl + '/api/v1/revolut'

const httpOptions = {
	headers: new HttpHeaders({
		'Content-Type': 'application/json',
		'Authentication': 'Bearer ' + 'sk_IHwOxXkZKSThJTEc7XWq2ubjFg-PnJZz5lxTuvD1ZPku1yfIDx1kLI94nN8QnuoM'
	})
};

@Injectable({
	providedIn: 'root'
})
export class RevolutService {

	constructor(private http: HttpClient) { }

	createPayment(): Observable<any> {
		return this.http.post(
			REVOLUT_API + "/orders",
			{
				"amount": 5,
				"currency": "GBP"
			}
		);
	}

	triggerBackendPayment(): Observable<any> {
		return this.http.get(
			BACKEND_REVOLUT
		);
	}


}
