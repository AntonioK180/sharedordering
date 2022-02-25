import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';

@Component({
	selector: 'app-registration-form',
	templateUrl: './registration-form.component.html',
	styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent {
	registrationForm = this.fb.group({
		name: [null, Validators.required],
		username: [null, Validators.required],
		email: [null, [Validators.required, Validators.email]],
		password: [null, [Validators.required, Validators.minLength(6)]],
	});

	isSuccessful = false;
	isSignUpFailed = false;

	constructor(private fb: FormBuilder, private authService: AuthService) { }

	onSubmit(): void {
		let username = this.registrationForm.value['username'];
		let email = this.registrationForm.value['email'];
		let password = this.registrationForm.value['password'];

		this.authService.register(username, email, password).subscribe(
			(data) => {
				console.log(data);
				this.isSuccessful = true;
				this.isSignUpFailed = false;
			},
			(err) => {
				console.log(err.error);
				this.isSignUpFailed = true;
			}
		);
	}
}
