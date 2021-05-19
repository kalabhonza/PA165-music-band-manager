import { Component, OnInit } from '@angular/core';
import {RedirectService} from '../../services/redirect.service';
import {SessionService} from '../../services/session.service';

@Component({
  selector: 'app-page-nav',
  templateUrl: './page-nav.component.html',
  styleUrls: ['./page-nav.component.css']
})
export class PageNavComponent implements OnInit {

  constructor(private redirectService: RedirectService, private sessionService: SessionService) { }

  managerUser: boolean;

  ngOnInit(): void {
    console.log(this.sessionService.sessionActive);
    this.managerUser = this.sessionService.sessionActive === 'ROLE_MANAGER';
  }

  navigateTo(route: string): void {
    this.redirectService.navigateTo(route);
  }
}
