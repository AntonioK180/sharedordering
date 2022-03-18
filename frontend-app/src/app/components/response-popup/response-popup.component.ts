import { Component, OnInit } from '@angular/core';
import { MatDialogConfig, MatDialogModule, MatDialogRef } from "@angular/material/dialog";

@Component({
	selector: 'app-response-popup',
	templateUrl: './response-popup.component.html',
	styleUrls: ['./response-popup.component.css']
})
export class ResponsePopupComponent implements OnInit {

	constructor(public dialog: MatDialogModule) { }

	ngOnInit(): void {
		this.displayPopUp();
	}

	displayPopUp() {
		const dialogConfig = new MatDialogConfig();

		dialogConfig.disableClose = true;
		dialogConfig.autoFocus = true;

		// let dialogRef = dialog.open(UserProfileComponent, {
		// 	height: '400px',
		// 	width: '600px',
		// });
	}

}

