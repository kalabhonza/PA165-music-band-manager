import { Injectable } from '@angular/core';
import {Band} from '../../model/band';
import {Observable, throwError} from 'rxjs';
import {MusicianApiService} from '../../api/services/musician-api.service';
import {catchError, map, tap} from 'rxjs/operators';
import {ErrorAlertService} from '../../shared/services/error-alert.service';
import {SessionService} from '../../shared/services/session.service';
import {Musician} from '../../model/musician';

@Injectable()
export class MusicianService {

  constructor(
    private musicianApiService: MusicianApiService,
    private errorAlertService: ErrorAlertService,
    private sessionService: SessionService
  ) { }

  /**
   * Retrieves musician offers
   * @param id musician id
   */
  getOffers(id: number): Observable<Band[]> {
    return this.musicianApiService.getById(id)
      .pipe(
        map(
          (musician) => musician.offers
        ),
        catchError(err => {
          this.errorAlertService.handleError(err);
          return throwError(err);
        }),
      );
  }

  getAllMusicians(): Observable<Musician[]> {
    return this.musicianApiService.getAll()
      .pipe(
        tap(
          _ => _,
          err => this.errorAlertService.handleError(err)
        )
      );
  }
}
