import {Component, OnDestroy, OnInit} from '@angular/core';
import {RedirectService} from '../../services/redirect.service';
import {SessionService} from '../../services/session.service';
import {AlertMessageService} from '../../services/message-alert.service';
import {ErrorAlertService} from '../../services/error-alert.service';
import {Observable, ReplaySubject} from 'rxjs';
import {Session} from '../../models/session';
import {takeUntil} from 'rxjs/operators';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {

  activeSession$: Observable<Session>;
  isLoading: boolean;

  private destroyed$: ReplaySubject<boolean> = new ReplaySubject(1);

  constructor(
    private redirectService: RedirectService,
    private sessionService: SessionService,
    private alertMessageService: AlertMessageService,
    private errorAlertService: ErrorAlertService) { }

  ngOnInit(): void {
    this.activeSession$ = this.sessionService.activeSession$;
  }

  ngOnDestroy(): void {
    this.destroyed$.next(true);
    this.destroyed$.complete();
  }

  navigateTo(route: string): void {
    this.redirectService.navigateTo(route);
  }

  populateData(): void {
    this.isLoading = true;
    this.sessionService.populateSession().pipe(takeUntil(this.destroyed$)).subscribe(
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
