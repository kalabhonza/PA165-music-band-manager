import { Injectable } from '@angular/core';
import {Tour} from '../../model/tour';
import {TourApiService} from '../../api/services/tour-api.service';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {ErrorAlertService} from '../../shared/services/error-alert.service';

@Injectable()
export class TourService {

  constructor(private tourApiService: TourApiService, private errorAlertService: ErrorAlertService) { }

  /**
   * Return all tours present in system
   */
  getAllTours(): Observable<Tour[]> {
    return this.tourApiService.getAll().pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Return tour by given id
   * @param id id of searched tour
   */
  getTourById(id: number): Observable<Tour> {
    return this.tourApiService.getById(id).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Delete tour by given id
   * @param id id of tour
   */
  deleteTour(id: number): Observable<Tour> {
    return this.tourApiService.delete(id).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Creates a tour
   * @param tour tour to be created
   */
  createTour(tour: Tour): Observable<Tour> {
    return this.tourApiService.create(tour).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Update tour by given id
   * @param tour tour to be updated
   */
  updateTour(tour: Tour): Observable<Tour> {
    return this.tourApiService.update(tour).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }
}
