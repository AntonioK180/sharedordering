import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormControl, Validators } from '@angular/forms';

@Component({
	selector: 'app-add-order-form',
	templateUrl: './add-order-form.component.html',
	styleUrls: ['./add-order-form.component.css']
})
export class AddOrderFormComponent {
	moreThanOne = false;
	orderForm = this.fb.group({
		products: this.fb.array([new FormControl(null, Validators.required)]),
		store: ['amazon', Validators.required]
	});

	constructor(private fb: FormBuilder) { }

	products(): FormArray {
		let productsArray = this.orderForm.get('products') as FormArray;  
		this.moreThanOne = (productsArray.length > 1);
		return productsArray;
	}

	addProduct() {
		this.products().push(new FormControl(null, Validators.required));
	}

	removeProduct(index: number) {
		this.products().removeAt(index);
	}

	onSubmit(): void {
		console.log(this.orderForm);
		console.log('FIRST INPUT:' + JSON.stringify(this.orderForm.value));
	}

}
