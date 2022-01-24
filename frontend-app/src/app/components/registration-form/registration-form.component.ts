import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
	selector: 'app-registration-form',
	templateUrl: './registration-form.component.html',
	styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent {
	registrationForm = this.fb.group({
		firstName: [null, Validators.required],
		lastName: [null],
		email: [null, Validators.required, Validators.email],
		password: [null, Validators.required, Validators.minLength(6)],
	});

	constructor(private fb: FormBuilder) { }

	onSubmit(): void {
		alert('Thanks!');
	}
}
