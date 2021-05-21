import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable, Subject} from 'rxjs';
import {CookieService} from 'ngx-cookie-service';
import {Session} from '../models/session';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Musician} from '../../model/musician';
import {Manager} from '../../model/manager';

@Injectable()
export class SessionService {

  private activeSessionSubject$: Subject<Session> = new BehaviorSubject(new Session());
  activeSession$ = this.activeSessionSubject$.asObservable();
  sessionActive: string;

  constructor(private cookieService: CookieService, private http: HttpClient) { }

  /**
   * Creates session in @sessionStorage and set cookies with for logged user
   * @param userSession session details from back end
   */
  createSession(userSession: Manager | Musician): void {
    const session: Session = this.createSessionObject(userSession.id, userSession.username, userSession instanceof Musician);
    this.cookieService.set('active_user', JSON.stringify(session));
    this.activeSessionSubject$.next(session);
    this.sessionActive = userSession instanceof Musician ? 'USER_ROLE' : 'MANAGER_ROLE';
    sessionStorage.setItem(`${userSession.id}`, `${userSession.id}`);
  }

  /**
   * Destroys current session
   */
  endSession(): void {
    this.activeSessionSubject$.next(new Session());
    sessionStorage.clear();
    this.sessionActive = '';
  }

  /**
   * Reactive present session from cookies
   */
  reloadSession(): void {
    if (sessionStorage.length !== 0) {
      const session = JSON.parse(this.cookieService.get('active_user'));
      this.activeSessionSubject$.next(this.createSessionObject(session.sessionID, session.sessionUsername, session.commonUser));
      this.sessionActive = session.commonUser ? 'USER_ROLE' : 'MANAGER_ROLE';
    }
  }

  getUserId(): number {
    if (sessionStorage.length !== 0) {
      return (JSON.parse(this.cookieService.get('active_user'))).sessionID;
    } else {
      return -1;
    }
  }

  getUser(): any {
    if (sessionStorage.length !== 0) {
      return (JSON.parse(this.cookieService.get('active_user')));
    } else {
      return undefined;
    }
  }

  populateSession(): Observable<any> {
    return this.http
      .post(
        `http://localhost:8080/pa165/rest/populate`,
        {},
        { headers: new HttpHeaders({ Accept: 'application/json' }) }
      );
  }

  /**
   * Creates inner structure for user's session
   * @param id user id
   * @param username user's name
   * @param commonUser set to True if user is Musician
   */
  private createSessionObject(id: number, username: string, commonUser: boolean): any {
    const session = new Session();
    session.sessionID = id;
    session.sessionLoggedIn = true;
    session.sessionUsername = username;
    session.commonUser = commonUser;

    return session;
  }
}
