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

	triggerBackendPayment(revolutDTO: Object): Observable<any> {
		return this.http.post(
			BACKEND_REVOLUT, revolutDTO
		);
	}


}
