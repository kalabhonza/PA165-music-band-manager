import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {AuthService} from './auth.service';
import {SessionService} from './session.service';

@Injectable()
export class AuthUserGuard implements CanActivate {

  constructor(private authService: AuthService,
              private sessionService: SessionService) {}

  /**
   * Checks if given url can be activated depending on session state
   * @param next
   * @param state url being checked
   */
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.sessionService.sessionActive === 'USER_ROLE';
  }
}
