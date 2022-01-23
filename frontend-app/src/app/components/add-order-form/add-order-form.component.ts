import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
	selector: 'app-add-order-form',
	templateUrl: './add-order-form.component.html',
	styleUrls: ['./add-order-form.component.css']
})
export class AddOrderFormComponent {
	orderForm = this.fb.group({
		products: this.fb.array([new FormControl(null, Validators.required)]),
		store: ['amazon', Validators.required]
	});

	hasUnitNummeber = false;

	constructor(private fb: FormBuilder) { }

	onSubmit(): void {
		console.log(this.orderForm);
		console.log('FIRST INPUT:' + JSON.stringify(this.orderForm.value));
	}

	products(): FormArray {
		return this.orderForm.get('products') as FormArray;
	}

	addProduct() {
		this.products().push(new FormControl(null, Validators.required));
	}


}
