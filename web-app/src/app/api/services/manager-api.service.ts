import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {ManagerDTO} from '../dtos/manager-dto';
import {Manager} from '../../model/manager';
import {ManagerMapper} from '../mappers/manager-mapper';

@Injectable()
export class ManagerApiService {

  constructor(private http: HttpClient) { }
  private readonly javaRestEndpoint = `http://localhost:8080/pa165/rest`; // TODO SPECIFY THIS ACCORDING TO REQUIREMENTS OF MILTESTONE 3

  private static createDefaultHeaders(): HttpHeaders {
    return new HttpHeaders({ Accept: 'application/json' });
  }

  /**
   * Sends http request to retrieve all managers
   */
  getAll(): Observable<Manager[]> {
    return this.http
      .get<ManagerDTO[]>(`${this.javaRestEndpoint}/managers`, {
        headers: ManagerApiService.createDefaultHeaders()
      })
      .pipe(
        map((response) => ManagerMapper.fromDTOs(response))
      );
  }

  /**
   * Sends http request to retrieve manager by given id
   * @param id id of searched manager
   */
  getById(id: number): Observable<Manager> {
    return this.http
      .get<ManagerDTO>(`${this.javaRestEndpoint}/manager/${id}`, {
        headers: ManagerApiService.createDefaultHeaders()
      })
      .pipe(
        map((response) => ManagerMapper.fromDTO(response))
      );
  }

  /**
   * Sends http request to delete a manager
   * @param id id of searched manager
   */
  delete(id: number): Observable<any> {
    return this.http
      .delete(`${this.javaRestEndpoint}/manager/${id}`, {
        headers: ManagerApiService.createDefaultHeaders()
      });
  }

  /**
   * Sends http request to create a manager
   * @param manager manager to be created
   */
  create(manager: Manager): Observable<Manager> {
    return this.http
      .post<ManagerDTO>(
        `${this.javaRestEndpoint}/manager`,
        ManagerMapper.toDTO(manager),
        { headers: ManagerApiService.createDefaultHeaders() }
      )
      .pipe(
        map((response) => ManagerMapper.fromDTO(response))
      );
  }

  /**
   * Sends http request to update a manager
   * @param manager manager to be created
   */
  update(manager: Manager): Observable<Manager> {
    return this.http
      .put<ManagerDTO>(
        `${this.javaRestEndpoint}/manager`,
        ManagerMapper.toDTO(manager),
        { headers: ManagerApiService.createDefaultHeaders() }
      )
      .pipe(
        map((response) => ManagerMapper.fromDTO(response))
      );
  }
}
