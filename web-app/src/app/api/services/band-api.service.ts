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
  private readonly javaRestEndpoint = `http://localhost:8080/pa165/rest`; // TODO SPECIFY THIS ACCORDING TO REQUIREMENTS OF MILTESTONE 3

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
}
