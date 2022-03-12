import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
	selector: 'app-navbar',
	templateUrl: './navbar.component.html',
	styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
	isLoggedIn: boolean = false;

	constructor(private tokenStorage: TokenStorageService) { }

	ngOnInit(): void {
		this.updateIsLoggedIn();
	}

	updateIsLoggedIn(): void {
		this.isLoggedIn = this.tokenStorage.getToken() !== null;
	}

}
