import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {ConcertDTO} from '../dtos/concert-dto';
import {Concert} from '../../model/concert';
import {ConcertMapper} from '../mappers/concert-mapper';


@Injectable()
export class ConcertApiService {

  constructor(private http: HttpClient) { }
  private readonly javaRestEndpoint = `http://localhost:8080/pa165/rest`; // TODO SPECIFY THIS ACCORDING TO REQUIREMENTS OF MILTESTONE 3

  private static createDefaultHeaders(): HttpHeaders {
    return new HttpHeaders({ Accept: 'application/json' });
  }

  /**
   * Sends http request to retrieve all concerts
   */
  getAll(): Observable<Concert[]> {
    return this.http
      .get<ConcertDTO[]>(`${this.javaRestEndpoint}/concerts`, {
        headers: ConcertApiService.createDefaultHeaders()
      })
      .pipe(
        map((response) => ConcertMapper.fromDTOs(response))
      );
  }

  /**
   * Sends http request to retrieve concert by given id
   * @param id id of searched concert
   */
  getById(id: number): Observable<Concert> {
    return this.http
      .get<ConcertDTO>(`${this.javaRestEndpoint}/concert/${id}`, {
        headers: ConcertApiService.createDefaultHeaders()
      })
      .pipe(
        map((response) => ConcertMapper.fromDTO(response))
      );
  }

  /**
   * Sends http request to delete a concert
   * @param id id of concert to be deleted
   */
  delete(id: number): Observable<any> {
    return this.http
      .delete(`${this.javaRestEndpoint}/concert/${id}`, {
        headers: ConcertApiService.createDefaultHeaders()
      });
  }

  /**
   * Sends http request to create a concert
   * @param concert concert to be created
   */
  create(concert: Concert): Observable<Concert> {
    return this.http
      .post<ConcertDTO>(
        `${this.javaRestEndpoint}/concert`,
        ConcertMapper.toDTO(concert),
        { headers: ConcertApiService.createDefaultHeaders() }
      )
      .pipe(
        map((response) => ConcertMapper.fromDTO(response))
      );
  }

  /**
   * Sends http request to update a concert
   * @param concert concert to be updated
   */
  update(concert: Concert): Observable<Concert> {
    return this.http
      .put<ConcertDTO>(
        `${this.javaRestEndpoint}/concert`,
        ConcertMapper.toDTO(concert),
        { headers: ConcertApiService.createDefaultHeaders() }
      )
      .pipe(
        map((response) => ConcertMapper.fromDTO(response))
      );
  }
}
