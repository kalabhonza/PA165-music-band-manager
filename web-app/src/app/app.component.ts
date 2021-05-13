import { Component } from '@angular/core';
import {RedirectService} from './shared/services/redirect.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'web-app';

  constructor(private redirectService: RedirectService) {
  }

  login(): void {}

  logout(): void {}

  navigateTo(route: string): void {
    this.redirectService.navigateTo(route);
  }

}
