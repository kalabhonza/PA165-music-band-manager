import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree} from '@angular/router';
import {AuthService} from './auth.service';
import {SessionService} from './session.service';
import {Observable} from 'rxjs';

@Injectable()
export class AuthManagerGuard implements CanActivate {

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
    return this.sessionService.sessionActive === 'MANAGER_ROLE';
  }
}
