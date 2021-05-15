import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PageNavComponent } from './shared/components/page-nav/page-nav.component';
import { HomeComponent } from './shared/components/home/home.component';
import {RedirectService} from './shared/services/redirect.service';
import {MaterialSharedModule} from './shared/components/material-shared.module';
import {BreadcrumbsComponent} from './shared/components/breadcrumbs/breadcrumbs.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    BreadcrumbsComponent,
    PageNavComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AppRoutingModule,
    MaterialSharedModule
  ],
  providers: [RedirectService],
  bootstrap: [AppComponent]
})
export class AppModule { }
