import { Injectable } from '@angular/core';
import {Band} from '../../model/band';
import {BandApiService} from '../../api/services/band-api.service';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {ErrorAlertService} from '../../shared/services/error-alert.service';

@Injectable()
export class BandService {

  constructor(private bandApiService: BandApiService, private errorAlertService: ErrorAlertService) { }

  /**
   * Return all bands present in system
   */
  getAllBands(): Observable<Band[]> {
    return this.bandApiService.getAll().pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }
}
