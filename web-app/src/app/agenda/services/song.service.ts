import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {ErrorAlertService} from '../../shared/services/error-alert.service';
import {SongApiService} from '../../api/services/song-api.service';
import {Song} from '../../model/song';

@Injectable()
export class SongService {

  constructor(private songApiService: SongApiService, private errorAlertService: ErrorAlertService) { }

  /**
   * Return all songs present in system
   */
  getAllSongs(): Observable<Song[]> {
    return this.songApiService.getAll().pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Return song by given id
   * @param id id of searched song
   */
  getSongById(id: number): Observable<Song> {
    return this.songApiService.getById(id).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Delete song by given id
   * @param id id of song
   */
  deleteSong(id: number): Observable<Song> {
    return this.songApiService.delete(id).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Creates a song
   * @param song song to be created
   */
  createSong(song: Song): Observable<Song> {
    return this.songApiService.create(song).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }

  /**
   * Update song by given id
   * @param song song to be updated
   */
  updateManager(song: Song): Observable<Song> {
    return this.songApiService.update(song).pipe(
      tap(
        _ => _,
        err => this.errorAlertService.handleError(err)
      )
    );
  }
}
