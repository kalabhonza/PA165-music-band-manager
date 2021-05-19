import { Component, OnInit } from '@angular/core';
import {RedirectService} from '../../services/redirect.service';
import {SessionService} from '../../services/session.service';
import {Session} from '../../models/session';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-page-nav',
  templateUrl: './page-nav.component.html',
  styleUrls: ['./page-nav.component.css']
})
export class PageNavComponent implements OnInit {

  activeSession$: Observable<Session>;

  constructor(private redirectService: RedirectService, private sessionService: SessionService) { }

  managerUser: boolean;

  ngOnInit(): void {
    this.activeSession$ = this.sessionService.activeSession$;
    this.managerUser = this.sessionService.sessionActive === 'ROLE_MANAGER';
  }

  navigateTo(route: string): void {
    this.redirectService.navigateTo(route);
  }
}
