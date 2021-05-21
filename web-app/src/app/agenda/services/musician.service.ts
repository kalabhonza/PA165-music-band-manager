import { Injectable } from '@angular/core';
import {Band} from '../../model/band';
import {Observable, throwError} from 'rxjs';
import {MusicianApiService} from '../../api/services/musician-api.service';
import {catchError, map, tap} from 'rxjs/operators';
import {ErrorAlertService} from '../../shared/services/error-alert.service';
import {SessionService} from '../../shared/services/session.service';
import {Musician} from '../../model/musician';
import {AlertMessageService} from '../../shared/services/message-alert.service';

@Injectable()
export class MusicianService {

  constructor(
    private musicianApiService: MusicianApiService,
    private errorAlertService: ErrorAlertService,
    private alertMessageService: AlertMessageService,
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

  // acceptOffer(musicianId: number, bandId: number): Observable<any> {
  //   return this.musicianApiService.accept(musicianId, bandId)
  //     .pipe(
  //       tap(
  //         _ => this.alertMessageService.display('Offer was accepted'),
  //         err => this.errorAlertService.handleError(err)
  //       )
  //     );
  // }

  declineOffer(musicianId: number, bandId: number): Observable<any> {
    return this.musicianApiService.decline(musicianId, bandId)
      .pipe(
        tap(
          _ => this.alertMessageService.display('Offer was declined'),
          err => this.errorAlertService.handleError(err)
        )
      );
  }
  /**
   * Returns musician of given id
   * @param id id of searched musician
   */
  getMusicianById(id: number): Observable<Musician> {
    return this.musicianApiService.getById(id).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Deletes musician of given id
   * @param id id of musician to be deleted
   */
  deleteMusician(id: number): Observable<Musician> {
    return this.musicianApiService.delete(id).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Creates a musician
   * @param musician musician to be created
   */
  createMusician(musician: Musician): Observable<Musician> {
    return this.musicianApiService.create(musician).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Updates musician of given id
   * @param musician musician to be updated
   */
  updateMusician(musician: Musician): Observable<Musician> {
    return this.musicianApiService.update(musician).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }
}
