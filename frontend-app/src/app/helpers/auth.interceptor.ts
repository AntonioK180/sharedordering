import { HTTP_INTERCEPTORS, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';

import { TokenStorageService } from '../services/token-storage.service';
import { Observable, of, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

	constructor(private token: TokenStorageService, private router: Router) { }

	intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
		let authReq = req;
		const token = this.token.getToken();

		if (token != null) {
			authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
		}

		return next.handle(authReq).pipe(catchError(x => this.handleAuthError(x)));;
	}

	private handleAuthError(err: HttpErrorResponse): Observable<any> {
		if (err.status === 401 || err.status === 403) {
			this.router.navigateByUrl(`/login`);
			return of(err.message);
		}
		return throwError(err);
	}
}

export const authInterceptorProviders = [
	{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];
