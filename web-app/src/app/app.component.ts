import { Component } from '@angular/core';
import {RedirectService} from './shared/services/redirect.service';
import {MatDialog} from '@angular/material/dialog';
import {LoginDialogComponent} from './shared/components/login-dialog/login-dialog.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'web-app';

  constructor(private redirectService: RedirectService, public dialog: MatDialog) {
  }

  login(): void {
    const dialogRef = this.dialog.open(LoginDialogComponent, {
      width: '500px',
    });

    dialogRef.afterClosed().subscribe();
  }

  logout(): void {}

  navigateTo(route: string): void {
    this.redirectService.navigateTo(route);
  }

}
