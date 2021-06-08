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
  managerLogin: boolean;
  hidden = true;
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
    this.managerLogin = false;
  }

  close(): void {
    this.dialogRef.close();
  }

  setChecked(checked: boolean): void {
    this.managerLogin = checked;
  }

  loginUser(): void {
    this.isLoading = true;
    this.authService.login(this.username.value, this.password.value, this.managerLogin)
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
      this.hidden = false;
      this.state = 'visibility_off';
    } else {
      this.hidden = true;
      this.state = 'visibility';
    }
  }

}
