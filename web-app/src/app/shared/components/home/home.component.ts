import { Component, OnInit } from '@angular/core';
import {RedirectService} from '../../services/redirect.service';
import {SessionService} from '../../services/session.service';
import {AlertMessageService} from '../../services/message-alert.service';
import {ErrorAlertService} from '../../services/error-alert.service';
import {Observable} from 'rxjs';
import {Session} from '../../models/session';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  activeSession$: Observable<Session>;
  isLoading: boolean;

  constructor(
    private redirectService: RedirectService,
    private sessionService: SessionService,
    private alertMessageService: AlertMessageService,
    private errorAlertService: ErrorAlertService) { }

  ngOnInit(): void {
    this.activeSession$ = this.sessionService.activeSession$;
  }

  navigateTo(route: string): void {
    this.redirectService.navigateTo(route);
  }

  populateData(): void {
    this.isLoading = true;
    this.sessionService.populateSession().subscribe(
      _ => {
        this.alertMessageService.display('Created template data for application');
        this.isLoading = false;
      },
      err => {
        this.errorAlertService.handleError(err);
        this.isLoading = false;
      }
    );
  }
}
