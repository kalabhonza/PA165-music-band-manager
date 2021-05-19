import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {map} from 'rxjs/operators';
import {Musician} from '../../model/musician';
import {MusicianMapper} from '../mappers/musician-mapper';
import {MusicianDTO} from '../dtos/musician-dto';
import {Style} from '../../model/style';
import {Band} from '../../model/band';

@Injectable()
export class MusicianApiService {

  constructor(private http: HttpClient) { }
  private readonly javaRestEndpoint = `http://localhost:8080/pa165/rest`;

  private static createDefaultHeaders(): HttpHeaders {
    return new HttpHeaders({ Accept: 'application/json' });
  }

  /**
   * Sends http request to retrieve all musicians
   */
  getAll(): Observable<Musician[]> {
    return this.http
      .get<MusicianDTO[]>(`${this.javaRestEndpoint}/musicians`, {
        headers: MusicianApiService.createDefaultHeaders()
      })
      .pipe(
        map((response) => MusicianMapper.fromDTOs(response))
      );
  }

  /**
   * Sends http request to retrieve musician by given id
   * @param id id of searched musician
   */
  getById(id: number): Observable<Musician> {
    return of( {
      name: 'Peter',
      offers: [{
        id: 2,
        style: Style.Rock,
        tours: [],
        name: "band",
        members: [],
        albums: [],
        manager: {name: "john"}
      }]
    });
    // return this.http
    //   .get<MusicianDTO>(`${this.javaRestEndpoint}/musicians/${id}`, {
    //     headers: MusicianApiService.createDefaultHeaders()
    //   })
    //   .pipe(
    //     map((response) => MusicianMapper.fromDTO(response))
    //   );
  }

  /**
   * Sends http request to accept given offer
   * @param musicianId musician id
   * @param bandId band id to which musician should be added
   */
  accept(musicianId: number, bandId: number): Observable<any> {
    return this.http
      .post(
        `${this.javaRestEndpoint}/musicians/offer`,
        {musician_id: musicianId, band_id: bandId},
        {
        headers: MusicianApiService.createDefaultHeaders()
      });
  }

  /**
   * Sends http request to decline given offer
   * @param musicianId musician id
   * @param bandId id of offer which should be rejected
   */
  decline(musicianId: number, bandId: number): Observable<any> {
    return this.http
      .put(
        `${this.javaRestEndpoint}/musicians/offer`,
        {musician_id: musicianId, band_id: bandId},
        {
          headers: MusicianApiService.createDefaultHeaders()
        });
  }
}
