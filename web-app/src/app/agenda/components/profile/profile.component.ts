import {Component, OnDestroy, OnInit} from '@angular/core';
import {SessionService} from '../../../shared/services/session.service';
import {AlertMessageService} from '../../../shared/services/message-alert.service';
import {AuthService} from '../../../shared/services/auth.service';
import {ErrorAlertService} from '../../../shared/services/error-alert.service';
import {ManagerService} from '../../services/manager.service';
import {MusicianService} from '../../services/musician.service';
import {Musician} from '../../../model/musician';
import {Manager} from '../../../model/manager';
import {takeUntil} from 'rxjs/operators';
import {ReplaySubject} from 'rxjs';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, OnDestroy {

  isLoading: boolean;
  activeUser: Musician | Manager;

  private destroyed$: ReplaySubject<boolean> = new ReplaySubject(1);

  constructor(private sessionService: SessionService,
              private alertMessageService: AlertMessageService,
              private authService: AuthService,
              private managerService: ManagerService,
              private musicianService: MusicianService,
              private errorAlertService: ErrorAlertService) { }

  ngOnInit(): void {
    this.isLoading = true;
    if (this.sessionService.getUser().commonUser) {
      this.musicianService.getMusicianById(this.sessionService.getUserId())
        .pipe(takeUntil(this.destroyed$)).subscribe(
        (user) => {
          this.activeUser = user as Musician;
          this.isLoading = false;
        },
        _ => this.isLoading = false
      );
    } else {
      this.managerService.getManagerById(this.sessionService.getUserId())
        .pipe(takeUntil(this.destroyed$)).subscribe(
        (user) => {
          this.activeUser = user as Manager;
          this.isLoading = false;
        },
        _ => this.isLoading = false
      );
    }
  }

  ngOnDestroy(): void {
    this.destroyed$.next(true);
    this.destroyed$.complete();
  }
}
