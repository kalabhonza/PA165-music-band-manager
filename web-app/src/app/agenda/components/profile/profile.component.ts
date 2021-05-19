import {Component, OnDestroy, OnInit} from '@angular/core';
import {SessionService} from '../../../shared/services/session.service';
import {AlertMessageService} from '../../../shared/services/message-alert.service';
import {AuthService} from '../../../shared/services/auth.service';
import {ErrorAlertService} from '../../../shared/services/error-alert.service';
import {Observable, Subscription} from 'rxjs';
import {Session} from '../../../shared/models/session';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  activeSession$: Observable<Session>;

  constructor(private sessionService: SessionService,
              private alertMessageService: AlertMessageService,
              private authService: AuthService,
              private errorAlertService: ErrorAlertService) { }

  ngOnInit(): void {
    this.activeSession$ = this.sessionService.activeSession$;
  }

}
