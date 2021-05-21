import {Component, OnInit} from '@angular/core';
import {SessionService} from '../../../shared/services/session.service';
import {AlertMessageService} from '../../../shared/services/message-alert.service';
import {AuthService} from '../../../shared/services/auth.service';
import {ErrorAlertService} from '../../../shared/services/error-alert.service';
import {ManagerService} from '../../services/manager.service';
import {MusicianService} from '../../services/musician.service';
import {Musician} from '../../../model/musician';
import {Manager} from '../../../model/manager';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  isLoading: boolean;
  activeUser: Musician | Manager;

  constructor(private sessionService: SessionService,
              private alertMessageService: AlertMessageService,
              private authService: AuthService,
              private managerService: ManagerService,
              private musicianService: MusicianService,
              private errorAlertService: ErrorAlertService) { }

  ngOnInit(): void {
    this.isLoading = true;
    if (this.sessionService.getUser().commonUser) {
      this.musicianService.getMusicianById(this.sessionService.getUserId()).subscribe(
        (user) => {
          this.activeUser = user as Musician;
          this.isLoading = false;
        },
        _ => this.isLoading = false
      );
    } else {
      this.managerService.getManagerById(this.sessionService.getUserId()).subscribe(
        (user) => {
          this.activeUser = user as Manager;
          this.isLoading = false;
        },
        _ => this.isLoading = false
      );
    }
  }
}
