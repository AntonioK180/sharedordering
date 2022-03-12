import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
	selector: 'app-login-form',
	templateUrl: './login-form.component.html',
	styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {

	loginForm = this.fb.group({
		email: [null, [Validators.required, Validators.email]],
		password: [null, [Validators.required, Validators.minLength(6)]],
	});

	constructor(private fb: FormBuilder, private authService: AuthService, private tokenStorage: TokenStorageService, private router: Router) { }

	onSubmit(): void {
		if (!this.loginForm.valid) {
			return;
		}

		let email = this.loginForm.value['email'];
		let password = this.loginForm.value['password'];

		this.authService.login(email, password).subscribe(
			(data) => {
				this.tokenStorage.saveToken(data.accessToken);
				this.tokenStorage.saveUser(data);

				this.router.navigate(['/allorders']);
			},
			(err) => {
				console.log(err.error);
			}
		);
	}

}
