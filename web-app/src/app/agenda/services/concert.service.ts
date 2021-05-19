import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {ErrorAlertService} from '../../shared/services/error-alert.service';
import {ConcertApiService} from '../../api/services/concert-api.service';
import {Concert} from '../../model/concert';

@Injectable()
export class ConcertService {

  constructor(private concertApiService: ConcertApiService, private errorAlertService: ErrorAlertService) { }

  /**
   * Returns all concerts present in system
   */
  getAllConcerts(): Observable<Concert[]> {
    return this.concertApiService.getAll().pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Returns concert of given id
   * @param id id of searched concert
   */
  getConcertById(id: number): Observable<Concert> {
    return this.concertApiService.getById(id).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Deletes concert of given id
   * @param id id of concert to be deleted
   */
  deleteConcert(id: number): Observable<Concert> {
    return this.concertApiService.delete(id).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Creates a concert
   * @param concert concert to be created
   */
  createConcert(concert: Concert): Observable<Concert> {
    return this.concertApiService.create(concert).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Updates concert of given id
   * @param concert concert to be updated
   */
  updateConcert(concert: Concert): Observable<Concert> {
    return this.concertApiService.update(concert).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }
}
