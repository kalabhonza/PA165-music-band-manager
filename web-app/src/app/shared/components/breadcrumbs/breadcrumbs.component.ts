import {Component, OnInit, Output, EventEmitter} from '@angular/core';
import {Observable, of} from 'rxjs';
import {SessionService} from '../../services/session.service';
import {Session} from '../../models/session';
import {LoadingService} from '../../services/loading.service';

@Component({
  selector: 'app-breadcrumbs',
  templateUrl: './breadcrumbs.component.html',
  styleUrls: ['./breadcrumbs.component.css']
})
export class BreadcrumbsComponent implements OnInit {

  activeRoute$: Observable<string>;
  activeSession$: Observable<Session>;
  @Output() returnHome = new EventEmitter();
  @Output() redirectToProfile = new EventEmitter();
  @Output() userLogout = new EventEmitter();
  @Output() userLogin = new EventEmitter();
  @Output() userRegister = new EventEmitter();

  constructor(private sessionService: SessionService, private loadingService: LoadingService) { }

  ngOnInit(): void {
    this.sessionService.reloadSession();
    this.activeRoute$ = this.loadingService.activeRoute$;
    this.activeSession$ = this.sessionService.activeSession$;
  }

  navigateHome(): void {
    this.returnHome.emit();
  }

  navigateToProfile(): void {
    this.redirectToProfile.emit();
  }

  register(): void {
    this.userRegister.emit();
  }

  login(): void {
    this.userLogin.emit();
  }

  logout(): void {
    this.userLogout.emit();
  }
}
