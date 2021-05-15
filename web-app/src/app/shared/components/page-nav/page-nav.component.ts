import { Component, OnInit } from '@angular/core';
import {RedirectService} from '../../services/redirect.service';

@Component({
  selector: 'app-page-nav',
  templateUrl: './page-nav.component.html',
  styleUrls: ['./page-nav.component.css']
})
export class PageNavComponent implements OnInit {

  constructor(private redirectService: RedirectService) { }

  ngOnInit(): void {
  }

  navigateTo(route: string): void {
    this.redirectService.navigateTo(route);
  }
}
