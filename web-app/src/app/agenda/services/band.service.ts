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
   * Return all band-manage present in system
   */
  getAllBands(): Observable<Band[]> {
    return this.bandApiService.getAll().pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Return band by given id
   * @param id id of searched band
   */
  getBandById(id: number): Observable<Band> {
    return this.bandApiService.getById(id).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Delete band by given id
   * @param id id of band
   */
  deleteBand(id: number): Observable<Band> {
    return this.bandApiService.delete(id).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Creates a band
   * @param band band to be created
   */
  createBand(band: Band): Observable<Band> {
    return this.bandApiService.create(band).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Update band by given id
   * @param band band to be updated
   */
  updateBand(band: Band): Observable<any> {
    return this.bandApiService.update(band).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }
}
