import { Injectable } from '@angular/core';
import {Album} from '../../model/album';
import {AlbumApiService} from '../../api/services/album-api.service';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {ErrorAlertService} from '../../shared/services/error-alert.service';

@Injectable()
export class AlbumService {

  constructor(private albumApiService: AlbumApiService, private errorAlertService: ErrorAlertService) { }

  /**
   * Return all albums present in system
   */
  getAllAlbums(): Observable<Album[]> {
    return this.albumApiService.getAll().pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Return album by given id
   * @param id id of searched album
   */
  getAlbumById(id: number): Observable<Album> {
    return this.albumApiService.getById(id).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Delete album by given id
   * @param id id of album
   */
  deleteAlbum(id: number): Observable<Album> {
    return this.albumApiService.delete(id).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Creates an album
   * @param album album to be created
   */
  createAlbum(album: Album): Observable<Album> {
    return this.albumApiService.create(album).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Update album by given id
   * @param album album to be updated
   */
  updateAlbum(album: Album): Observable<Album> {
    return this.albumApiService.update(album).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }
}
