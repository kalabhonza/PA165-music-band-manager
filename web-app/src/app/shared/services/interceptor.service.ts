import { Injectable } from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthService} from './auth.service';
import {SessionService} from './session.service';

@Injectable()
export class InterceptorService implements HttpInterceptor {


  constructor(private auth: SessionService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const authToken = this.auth.getUserToken();
    const authReq = req.clone({
      headers: req.headers.append('Authorization', authToken)
    });
    return next.handle(req);
  }
}
