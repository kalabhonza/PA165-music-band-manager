import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {ErrorAlertService} from '../../shared/services/error-alert.service';
import {ManagerApiService} from '../../api/services/manager-api.service';
import {Manager} from '../../model/manager';
import {Band} from '../../model/band';
import {AlertMessageService} from '../../shared/services/message-alert.service';

@Injectable()
export class ManagerService {

  constructor(
    private managerApiService: ManagerApiService,
    private errorAlertService: ErrorAlertService,
    private alertMessageService: AlertMessageService
  ) { }

  getManagerBand(bandId: number): Observable<Band> {
    return this.managerApiService.getBand(bandId).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  sendOffer(musicianId: number, bandId: number): Observable<any> {
    return this.managerApiService.sendOffer(musicianId, bandId).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Return all managers present in system
   */
  getAllManagers(): Observable<Manager[]> {
    return this.managerApiService.getAll().pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Return manager by given id
   * @param id id of searched manager
   */
  getManagerById(id: number): Observable<Manager> {
    return this.managerApiService.getById(id).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Delete manager by given id
   * @param id id of manager
   */
  deleteManager(id: number): Observable<Manager> {
    return this.managerApiService.delete(id).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Creates a manager
   * @param manager manager to be created
   */
  createManager(manager: Manager): Observable<Manager> {
    return this.managerApiService.create(manager).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Update manager by given id
   * @param manager manager to be updated
   */
  updateManager(manager: Manager): Observable<Manager> {
    return this.managerApiService.update(manager).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }
}
