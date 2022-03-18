import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
	selector: 'app-registration-form',
	templateUrl: './registration-form.component.html',
	styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent {
	isSignUpFailed = false;
	public errorText = "";

	registrationForm = this.fb.group({
		name: [null, Validators.required],
		email: [null, [Validators.required, Validators.email]],
		password: [null, [Validators.required, Validators.minLength(6)]],
	});

	constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) { }

	onSubmit(): void {
		if (!this.registrationForm.valid) {
			return;
		}

		let email = this.registrationForm.value['email'];
		let password = this.registrationForm.value['password'];

		this.authService.register(email, password).subscribe(
			() => {
				this.isSignUpFailed = false;
				this.router.navigate(['/login']);
			},
			(error) => {
				this.isSignUpFailed = true;
				console.log(error.error);
				switch (error.error) {
					case "Error: rawPassword cannot be null":
						this.errorText = "Password cannot be empty!";
						break;

					case "Error: Email is already in use!":
						this.errorText = "This email has already been registered!";
						break;
				}
			}
		);
	}
}
