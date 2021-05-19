import { Injectable } from '@angular/core';
import {BandDTO} from '../dtos/band-dto';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {BandMapper} from '../mappers/band-mapper';
import {map} from 'rxjs/operators';
import {Band} from '../../model/band';

@Injectable()
export class BandApiService {

  constructor(private http: HttpClient) { }
  private readonly javaRestEndpoint = `http://localhost:8080/pa165/rest`;

  private static createDefaultHeaders(): HttpHeaders {
    return new HttpHeaders({ Accept: 'application/json' });
  }

  /**
   * Sends http request to retrieve all bands
   */
  getAll(): Observable<Band[]> {
    return this.http
      .get<BandDTO[]>(`${this.javaRestEndpoint}/bands`, {
      headers: BandApiService.createDefaultHeaders()
    })
    .pipe(
      map((response) => BandMapper.fromDTOs(response))
    );
  }

  /**
   * Sends http request to retrieve band by given id
   * @param id id of searched band
   */
  getById(id: number): Observable<Band> {
    return this.http
      .get<BandDTO>(`${this.javaRestEndpoint}/bands/${id}`, {
        headers: BandApiService.createDefaultHeaders()
      })
      .pipe(
        map((response) => BandMapper.fromDTO(response))
      );
  }

  /**
   * Sends http request to delete a band
   * @param id id of searched band
   */
  delete(id: number): Observable<any> {
    return this.http
      .delete(`${this.javaRestEndpoint}/bands/${id}`, {
        headers: BandApiService.createDefaultHeaders()
      });
  }

  /**
   * Sends http request to create a band
   * @param band band to be created
   */
  create(band: Band): Observable<Band> {
    return this.http
      .post<BandDTO>(
        `${this.javaRestEndpoint}/bands`,
        BandMapper.toDTO(band),
        { headers: BandApiService.createDefaultHeaders() }
      )
      .pipe(
        map((response) => BandMapper.fromDTO(response))
      );
  }

  /**
   * Sends http request to update a band
   * @param band band to be created
   */
  update(band: Band): Observable<Band> {
    return this.http
      .put<BandDTO>(
        `${this.javaRestEndpoint}/bands`,
        BandMapper.toDTO(band),
        { headers: BandApiService.createDefaultHeaders() }
      )
      .pipe(
        map((response) => BandMapper.fromDTO(response))
      );
  }
}
