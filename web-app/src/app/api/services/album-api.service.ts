import { Injectable } from '@angular/core';
import {AlbumDTO} from '../dtos/album-dto';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {Album} from '../../model/album';
import {AlbumMapper} from '../mappers/album-mapper';

@Injectable()
export class AlbumApiService {

  constructor(private http: HttpClient) { }
  private readonly javaRestEndpoint = `http://localhost:8080/pa165/rest`;

  private static createDefaultHeaders(): HttpHeaders {
    return new HttpHeaders({ Accept: 'application/json' });
  }

  /**
   * Sends http request to retrieve all albums
   */
  getAll(): Observable<Album[]> {
    return this.http
      .get<AlbumDTO[]>(`${this.javaRestEndpoint}/albums`, {
        headers: AlbumApiService.createDefaultHeaders()
      })
      .pipe(
        map((response) => AlbumMapper.fromDTOs(response))
      );
  }

  /**
   * Sends http request to retrieve album by given id
   * @param id id of searched album
   */
  getById(id: number): Observable<Album> {
    return this.http
      .get<AlbumDTO>(`${this.javaRestEndpoint}/albums/${id}`, {
        headers: AlbumApiService.createDefaultHeaders()
      })
      .pipe(
        map((response) => AlbumMapper.fromDTO(response))
      );
  }

  /**
   * Sends http request to delete an album
   * @param id id of searched album
   */
  delete(id: number): Observable<any> {
    return this.http
      .delete(`${this.javaRestEndpoint}/albums/${id}`, {
        headers: AlbumApiService.createDefaultHeaders()
      });
  }

  /**
   * Sends http request to create an album
   * @param album album to be created
   */
  create(album: Album): Observable<Album> {
    return this.http
      .post<AlbumDTO>(
        `${this.javaRestEndpoint}/albums`,
        AlbumMapper.toDTO(album),
        { headers: AlbumApiService.createDefaultHeaders() }
      )
      .pipe(
        map((response) => AlbumMapper.fromDTO(response))
      );
  }

  /**
   * Sends http request to update an album
   * @param album album to be created
   */
  update(album: Album): Observable<Album> {
    return this.http
      .put<AlbumDTO>(
        `${this.javaRestEndpoint}/albums`,
        AlbumMapper.toDTO(album),
        { headers: AlbumApiService.createDefaultHeaders() }
      )
      .pipe(
        map((response) => AlbumMapper.fromDTO(response))
      );
  }
}
