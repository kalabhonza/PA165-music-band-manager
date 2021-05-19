import { Injectable } from '@angular/core';
import {TourDTO} from '../dtos/tour-dto';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TourMapper} from '../mappers/tour-mapper';
import {map} from 'rxjs/operators';
import {Tour} from '../../model/tour';

@Injectable()
export class TourApiService {

  constructor(private http: HttpClient) { }
  private readonly javaRestEndpoint = `http://localhost:8080/pa165/rest`;

  private static createDefaultHeaders(): HttpHeaders {
    return new HttpHeaders({ Accept: 'application/json' });
  }

  /**
   * Sends http request to retrieve all tours
   */
  getAll(): Observable<Tour[]> {
    return this.http
      .get<TourDTO[]>(`${this.javaRestEndpoint}/tours`, {
        headers: TourApiService.createDefaultHeaders()
      })
      .pipe(
        map((response) => TourMapper.fromDTOs(response))
      );
  }

  /**
   * Sends http request to retrieve tour by given id
   * @param id id of searched tour
   */
  getById(id: number): Observable<Tour> {
    return this.http
      .get<TourDTO>(`${this.javaRestEndpoint}/tours/${id}`, {
        headers: TourApiService.createDefaultHeaders()
      })
      .pipe(
        map((response) => TourMapper.fromDTO(response))
      );
  }

  /**
   * Sends http request to delete a tour
   * @param id id of searched tour
   */
  delete(id: number): Observable<any> {
    return this.http
      .delete(`${this.javaRestEndpoint}/tours/${id}`, {
        headers: TourApiService.createDefaultHeaders()
      });
  }

  /**
   * Sends http request to create a tour
   * @param tour tour be created
   */
  create(tour: Tour): Observable<Tour> {
    return this.http
      .post<TourDTO>(
        `${this.javaRestEndpoint}/tours`,
        TourMapper.toDTO(tour),
        { headers: TourApiService.createDefaultHeaders() }
      )
      .pipe(
        map((response) => TourMapper.fromDTO(response))
      );
  }

  /**
   * Sends http request to update a tour
   * @param tour tour to be created
   */
  update(tour: Tour): Observable<Tour> {
    return this.http
      .put<TourDTO>(
        `${this.javaRestEndpoint}/tours`,
        TourMapper.toDTO(tour),
        { headers: TourApiService.createDefaultHeaders() }
      )
      .pipe(
        map((response) => TourMapper.fromDTO(response))
      );
  }
}
