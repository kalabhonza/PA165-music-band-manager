import { Injectable } from '@angular/core';
import {BehaviorSubject, Subject} from 'rxjs';
import {CookieService} from 'ngx-cookie-service';
import {Session} from '../models/session';

@Injectable()
export class SessionService {

  private activeSessionSubject$: Subject<Session> = new BehaviorSubject(new Session());
  activeSession$ = this.activeSessionSubject$.asObservable();

  sessionActive: boolean;

  constructor(private cookieService: CookieService) { }

  /**
   * Creates session in @sessionStorage and set cookies with for logged user
   * @param userSession session details from back end
   */
  createSession(userSession: any): void {
    const session = this.createSessionObject(userSession.login.id, userSession.login.username);
    this.cookieService.set('active_user', JSON.stringify(session));
    this.activeSessionSubject$.next(session);
    this.sessionActive = true;
    sessionStorage.setItem(userSession.login.id, userSession.login.id);
  }

  /**
   * Destroys current session
   */
  endSession(): void {
    this.activeSessionSubject$.next(new Session());
    sessionStorage.clear();
    this.sessionActive = false;
  }

  /**
   * Reactive present session from cookies
   */
  reloadSession(): void {
    if (sessionStorage.length !== 0) {
      const session = JSON.parse(this.cookieService.get('active_user'));
      this.activeSessionSubject$.next(this.createSessionObject(session.sessionID, session.sessionUsername));
      this.sessionActive = true;
    }
  }

  getUserToken(): string {
    if (sessionStorage.length !== 0) {
      return (JSON.parse(this.cookieService.get('active_user'))).token;
    } else {
      return '';
    }
  }

  /**
   * Creates inner structure for user's session
   * @param id user id
   * @param username user's name
   */
  private createSessionObject(id: number, username: string) {
    const session = new Session();
    session.sessionID = id;
    session.sessionLoggedIn = true;
    session.sessionUsername = username;

    return session;
  }
}
