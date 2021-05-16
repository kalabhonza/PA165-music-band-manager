import { Injectable } from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';

@Injectable()
export class AlertMessageService {

  constructor(private snackBar: MatSnackBar) { }

  /**
   * Displays red/black error message
   * @param message error text
   */
  display(message: string): void {
    this.snackBar.open(message, 'Close', {
      duration: 4000,
      panelClass: ['snackbar-design']
    });
  }
}
