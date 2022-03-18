import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const REVOLUT_API = 'https://merchant.revolut.com/api/1.0';
const BACKEND_REVOLUT = environment.apiBaseUrl + '/api/v1/revolut'

const httpOptions = {
	headers: new HttpHeaders({
		'Content-Type': 'application/json',
		'Authorization': 'Bearer ' + 'sk_nxFIfOdfuHqOE2wUbcja6MkeI1FoFOFjpH5sLlrv070l0CCZJx_l0oNTZTrus8hR'
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
