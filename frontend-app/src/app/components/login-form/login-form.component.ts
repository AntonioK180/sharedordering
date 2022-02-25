import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';

@Component({
	selector: 'app-login-form',
	templateUrl: './login-form.component.html',
	styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {

	loginForm = this.fb.group({
		username: [null, Validators.required],
		password: [null, [Validators.required, Validators.minLength(6)]],
	});

	constructor(private fb: FormBuilder, private authService: AuthService) { }

	onSubmit(): void {
		let username = this.loginForm.value['username'];
		let password = this.loginForm.value['password'];

		this.authService.login(username, password).subscribe(
			(data) => {
				console.log(data);
			},
			(err) => {
				console.log(err.error);
			}
		);
	}

}
