import { Injectable } from '@angular/core';
import {BehaviorSubject, of, Subject} from 'rxjs';
import {NavigationCancel, NavigationEnd, NavigationError, NavigationStart, Router} from '@angular/router';

/**
 * Loading service which sets loading state when switching over routes
 */
@Injectable()
export class LoadingService {

  private isLoadingSubject$: Subject<boolean> = new Subject<boolean>();
  isLoading$ = this.isLoadingSubject$.asObservable();

  private activeRouteSubject$: Subject<string> = new Subject<string>();
  activeRoute$ = this.activeRouteSubject$.asObservable();

  constructor(private router: Router) {
    this.router.events.subscribe( event => {
      if (event instanceof NavigationStart) {
        this.setIsLoading(true);
      } else if (event instanceof NavigationEnd || event instanceof NavigationCancel || event instanceof NavigationError) {
        this.activeRouteSubject$.next(event.url);
        this.setIsLoading(false);
      }
    });
  }

  setIsLoading(state: boolean): void {
    this.isLoadingSubject$.next(state);
  }
}
