import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PageNavComponent } from './shared/components/page-nav/page-nav.component';
import {RedirectService} from './shared/services/redirect.service';
import {MaterialSharedModule} from './shared/components/material-shared.module';
import {BreadcrumbsComponent} from './shared/components/breadcrumbs/breadcrumbs.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {InterceptorService} from './shared/services/interceptor.service';
import {AuthService} from './shared/services/auth.service';
import {SessionService} from './shared/services/session.service';
import { BreadcrumbsPipe } from './shared/pipe/breadcrumbs.pipe';
import {HomeComponent} from './shared/components/home/home.component';
import {LoadingService} from './shared/services/loading.service';
import {LoginDialogComponent} from './shared/components/login-dialog/login-dialog.component';
import {AlertMessageService} from './shared/services/message-alert.service';
import {ErrorAlertService} from './shared/services/error-alert.service';
import {AuthUserGuard} from './shared/services/auth-user-guard.service';
import {AuthManagerGuard} from './shared/services/auth-manager-guard.service';

@NgModule({
  declarations: [
    AppComponent,
    BreadcrumbsComponent,
    PageNavComponent,
    HomeComponent,
    LoginDialogComponent,
    BreadcrumbsPipe,
  ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        AppRoutingModule,
        MaterialSharedModule,
    ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi: true },
    RedirectService,
    AuthService,
    SessionService,
    LoadingService,
    AlertMessageService,
    ErrorAlertService,
    AuthUserGuard,
    AuthManagerGuard
  ],
  entryComponents: [
    LoginDialogComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
