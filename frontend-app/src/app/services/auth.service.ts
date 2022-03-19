import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const AUTH_API = environment.apiBaseUrl + '/api/auth/';

const httpOptions = {
	headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
	providedIn: 'root'
})
export class AuthService {
	constructor(private http: HttpClient) { }

	login(username: string, password: string): Observable<any> {
		console.log(AUTH_API + 'signin');
		return this.http.post(AUTH_API + 'signin', {
			username,
			password
		}, httpOptions);
	}

	register(username: string, password: string, roles: string[]): Observable<any> {
		return this.http.post(AUTH_API + 'signup', {
			username,
			password,
			roles
		});
	}
}

