import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Order } from '../interfaces/order';
import { Observable } from 'rxjs';

const ORDERS_API = environment.apiBaseUrl + '/api/v1/orders';

@Injectable({
	providedIn: 'root'
})
export class OrderService {

	constructor(private http: HttpClient) { }

	public getOrders(): Observable<Order[]> {
		return this.http.get<Order[]>(ORDERS_API);
	}

	public addOrder(order: Order): Observable<Order> {
		console.log(ORDERS_API);
		return this.http.post<Order>(ORDERS_API, order);
	}

	public updateOrder(order: Order): Observable<Order> {
		return this.http.put<Order>(ORDERS_API, order);
	}

	public deleteOrder(orderId: number | undefined): Observable<void> {
        return this.http.delete<void>(ORDERS_API + '/' + orderId);
    }
}
