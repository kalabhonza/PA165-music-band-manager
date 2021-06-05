import { Component } from '@angular/core';
import {RedirectService} from './shared/services/redirect.service';
import {MatDialog} from '@angular/material/dialog';
import {LoginDialogComponent} from './shared/components/login-dialog/login-dialog.component';
import {AuthService} from './shared/services/auth.service';
import {ErrorAlertService} from './shared/services/error-alert.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'web-app';

  isLoading: boolean;

  constructor(
    private redirectService: RedirectService,
    private authService: AuthService,
    private errorAlertService: ErrorAlertService,
    public dialog: MatDialog
  ) { }

  login(): void {
    const dialogRef = this.dialog.open(LoginDialogComponent, {
      width: '500px',
    });

    dialogRef.afterClosed().subscribe();
  }

  logout(): void {
    this.isLoading = true;
    this.navigateTo('/pa165');
    this.authService.logout()
      .subscribe(
        _ => {
          this.isLoading = false;
        },
        error => {
          this.isLoading = false;
          this.errorAlertService.handleError(error);
        }
      );
  }

  navigateTo(route: string): void {
    this.redirectService.navigateTo(route);
  }

}
