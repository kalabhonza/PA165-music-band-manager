import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SessionService} from './session.service';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {tap} from 'rxjs/operators';
import {UserCredentials} from '../models/user-credentials';

@Injectable()
export class AuthService {

  constructor(private http: HttpClient,
              private sessionService: SessionService) { }

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
   */
  login(username: string, password: string): Observable<any> {
    const credentials = new UserCredentials();
    credentials.username = username;
    credentials.password = password;
    return this.http.post<UserCredentials>(`${environment.backendApi}/auth/login`, credentials)
      .pipe(
        tap(
          session => this.sessionService.createSession(session)
        )
      );
  }

  /**
   * Log's out current session on server side
   */
  logout(): Observable<any> {
    return this.http.get(`${environment.backendApi}/auth/logout`)
      .pipe(
        tap(
          session => this.sessionService.endSession()
        )
      );
  }
}
