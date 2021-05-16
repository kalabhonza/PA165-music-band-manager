import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {FormControl, Validators} from '@angular/forms';
import {AuthService} from '../../services/auth.service';
import {AlertMessageService} from '../../services/message-alert.service';


@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.scss']
})
export class LoginDialogComponent {

  isLoading: boolean;
  state = 'visibility';

  username = new FormControl(
    '',
    [Validators.required]);
  password = new FormControl(
    '',
    [Validators.required]);

  constructor(
    public dialogRef: MatDialogRef<LoginDialogComponent>,
    private authService: AuthService,
    private alertMessageService: AlertMessageService) {
  }

  close(): void {
    this.dialogRef.close();
  }

  loginUser(): void {
    this.isLoading = true;
    this.authService.login(this.username.value, this.password.value)
      .subscribe(
      () => {
        this.isLoading = false;
        this.close();
      },
      () => {
        this.isLoading = false;
        this.alertMessageService.display('Incorrect username or password');
      }
    );
  }

  show(): void {
    if (this.state === 'visibility') {
      document.getElementsByClassName('password-input')[0].setAttribute('type', 'text');
      this.state = 'visibility_off';
    } else {
      document.getElementsByClassName('password-input')[0].setAttribute('type', 'password');
      this.state = 'visibility';
    }
  }

}
