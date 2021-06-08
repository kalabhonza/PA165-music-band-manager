import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {SessionService} from './session.service';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {catchError, map, tap} from 'rxjs/operators';
import {UserCredentials} from '../models/user-credentials';
import {MusicianDTO} from '../../api/dtos/musician-dto';
import {MusicianMapper} from '../../api/mappers/musician-mapper';
import {ManagerMapper} from '../../api/mappers/manager-mapper';
import {ManagerDTO} from '../../api/dtos/manager-dto';

@Injectable()
export class AuthService {

  private readonly javaRestEndpoint = `http://localhost:8080/pa165/rest`;

  constructor(private http: HttpClient,
              private sessionService: SessionService) { }

  private static createDefaultHeaders(): HttpHeaders {
    return new HttpHeaders({ Accept: 'application/json' });
  }

  /**
   * Sends http request to server for user registration
   * @param username user's name
   * @param password user's password
   */
  register(username: string, password: string): Observable<any> {
    const credentials = new UserCredentials();
    credentials.username = username;
    credentials.password = password;
    return this.http.post<UserCredentials>(`${environment.backendApi}/auth/register`, credentials);
  }

  /**
   * Request to login user with given credential
   * @param username given username
   * @param password given password
   * @param managerLogin if true manager loggin is done
   */
  login(username: string, password: string, managerLogin: boolean): Observable<any> {
    const credentials = new UserCredentials();
    credentials.username = username;
    credentials.password = password;
    credentials.isManager = managerLogin;
    if (managerLogin) {
      return this.http
        .get<ManagerDTO>(`${this.javaRestEndpoint}/auth/manager-login/${username}/password/${password}`, {
          headers: AuthService.createDefaultHeaders()
        })
        .pipe(
          map((response) => this.sessionService.createSession(ManagerMapper.fromDTO(response)))
        );
    } else {
      return this.http
        .get<MusicianDTO>(`${this.javaRestEndpoint}/auth/user-login/${username}/password/${password}`, {
          headers: AuthService.createDefaultHeaders()
        })
        .pipe(
          map((response) => this.sessionService.createSession(MusicianMapper.fromDTO(response)))
        );
    }
  }

  /**
   * Log's out current session on server side
   */
  logout(): Observable<any> {
    return this.http.get(`${this.javaRestEndpoint}/auth/logout`, {
      headers: AuthService.createDefaultHeaders()
    })
      .pipe(
        tap(
          session => this.sessionService.endSession()
        ),
        catchError(error => {
          this.sessionService.endSession();
          return error;
        })
      );
  }
}
