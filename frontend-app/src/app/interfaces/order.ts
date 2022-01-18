import { Product } from "./product";

export interface Order {
	id?: number;
	storeName: string;
	creationDate: string;
	orderDate: string;
	products: Array<Product>;
}
