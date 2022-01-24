import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
	selector: 'app-login-form',
	templateUrl: './login-form.component.html',
	styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {

	loginForm = this.fb.group({
		email: [null, Validators.required, Validators.email],
		password: [null, Validators.required, Validators.minLength(6)],
	});

	constructor(private fb: FormBuilder) { }

	onSubmit(): void {
		alert('Thanks!');
	}

}
