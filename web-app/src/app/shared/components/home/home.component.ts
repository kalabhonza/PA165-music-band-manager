import { Component, OnInit } from '@angular/core';
import {RedirectService} from '../../services/redirect.service';
import {SessionService} from '../../services/session.service';
import {AlertMessageService} from '../../services/message-alert.service';
import {ErrorAlertService} from '../../services/error-alert.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  disablePopulate = false;
  isLoading: boolean;

  constructor(
    private redirectService: RedirectService,
    private sessionService: SessionService,
    private alertMessageService: AlertMessageService,
    private errorAlertService: ErrorAlertService) { }

  ngOnInit(): void {
  }

  navigateTo(route: string): void {
    this.redirectService.navigateTo(route);
  }

  populateData(): void {
    this.isLoading = true;
    this.sessionService.populateSession().subscribe(
      _ => {
        this.alertMessageService.display('Created template data for application');
        this.disablePopulate = true;
        this.isLoading = false;
      },
      err => {
        this.errorAlertService.handleError(err);
        this.isLoading = false;
      }
    );
  }
}
