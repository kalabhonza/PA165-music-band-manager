import { Injectable } from '@angular/core';
import {Router} from '@angular/router';

@Injectable()
export class RedirectService {

  constructor(private router: Router) { }

  /**
   * Redirects to given route
   * @param route desired route
   */
  navigateTo(route: string): void {
    this.router.navigate([route]);
  }
}
