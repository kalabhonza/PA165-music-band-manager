import { Injectable } from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';

@Injectable()
export class ErrorAlertService {

  constructor(private snackBar: MatSnackBar) { }

  /**
   * Parsing error type depending on error code
   * @param error given error with error code and message
   */
  handleError(error: any): void {
    switch (error.status) {
      case 404:
        if (error.error.message === 'No bands found') {
          this.snackBar.open('No bands', 'Close', {
            duration: 4000,
            panelClass: ['snackbar-design']
          });
          break;
        }
        this.snackBar.open(error.error.serverInternalMessage, 'Close', {
          duration: 4000,
          panelClass: ['snackbar-design', 'snackbar-error']
        });
        break;
      case 401:
      case 409:
        this.snackBar.open('Authorization error: ' + error.error.serverInternalMessage, 'Close', {
          duration: 4000,
          panelClass: ['snackbar-design', 'snackbar-error']
        });
        break;
      case 503:
      default:
        const message = 'Unexpected server error';
        this.snackBar.open(message, 'Close', {
          duration: 4000,
          panelClass: ['snackbar-design', 'snackbar-error']
        });
    }
  }
}
