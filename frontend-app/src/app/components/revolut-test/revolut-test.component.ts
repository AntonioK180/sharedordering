import { Component, OnInit } from '@angular/core';
import { RevolutService } from 'src/app/services/revolut.service';

@Component({
	selector: 'app-revolut-test',
	templateUrl: './revolut-test.component.html',
	styleUrls: ['./revolut-test.component.css']
})
export class RevolutTestComponent implements OnInit {

	constructor(private revolutService: RevolutService) { }

	ngOnInit(): void {

	}

	public initPayment() {
		console.log('GO GO GO GO');

		this.revolutService.createPayment().subscribe(
			(response) => {
				console.log(response);
			},
			(error) => {
				console.log("Error: " + error);
			}
		);
	}

	public triggerBackendPayment() {
		console.log("triggered backend payment");
		this.revolutService.triggerBackendPayment().subscribe(
			(response) => {
				console.log("Response: " + JSON.stringify(response));
			},
			(error) => {
				console.log("Error: " + JSON.stringify(error));
			}
		);
	}

}
