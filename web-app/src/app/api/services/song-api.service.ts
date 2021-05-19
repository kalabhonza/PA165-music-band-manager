import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {SongDTO} from '../dtos/song-dto';
import {Song} from '../../model/song';
import {SongMapper} from '../mappers/song-mapper';


@Injectable()
export class SongApiService {

  constructor(private http: HttpClient) { }
  private readonly javaRestEndpoint = `http://localhost:8080/pa165/rest`; // TODO SPECIFY THIS ACCORDING TO REQUIREMENTS OF MILTESTONE 3

  private static createDefaultHeaders(): HttpHeaders {
    return new HttpHeaders({ Accept: 'application/json' });
  }

  /**
   * Sends http request to retrieve all songs
   */
  getAll(): Observable<Song[]> {
    return this.http
      .get<SongDTO[]>(`${this.javaRestEndpoint}/songs`, {
        headers: SongApiService.createDefaultHeaders()
      })
      .pipe(
        map((response) => SongMapper.fromDTOs(response))
      );
  }

  /**
   * Sends http request to retrieve song by given id
   * @param id id of searched song
   */
  getById(id: number): Observable<Song> {
    return this.http
      .get<SongDTO>(`${this.javaRestEndpoint}/song/${id}`, {
        headers: SongApiService.createDefaultHeaders()
      })
      .pipe(
        map((response) => SongMapper.fromDTO(response))
      );
  }

  /**
   * Sends http request to delete a song
   * @param id id of searched song
   */
  delete(id: number): Observable<any> {
    return this.http
      .delete(`${this.javaRestEndpoint}/song/${id}`, {
        headers: SongApiService.createDefaultHeaders()
      });
  }

  /**
   * Sends http request to create a song
   * @param song song to be created
   */
  create(song: Song): Observable<Song> {
    return this.http
      .post<SongDTO>(
        `${this.javaRestEndpoint}/song`,
        SongMapper.toDTO(song),
        { headers: SongApiService.createDefaultHeaders() }
      )
      .pipe(
        map((response) => SongMapper.fromDTO(response))
      );
  }

  /**
   * Sends http request to update a song
   * @param song song to be created
   */
  update(song: Song): Observable<Song> {
    return this.http
      .put<SongDTO>(
        `${this.javaRestEndpoint}/song`,
        SongMapper.toDTO(song),
        { headers: SongApiService.createDefaultHeaders() }
      )
      .pipe(
        map((response) => SongMapper.fromDTO(response))
      );
  }
}
