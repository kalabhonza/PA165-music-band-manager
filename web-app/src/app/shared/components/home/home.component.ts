import { Component, OnInit } from '@angular/core';
import {RedirectService} from '../../services/redirect.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private redirectService: RedirectService) { }

  ngOnInit(): void {
  }

  navigateTo(route: string): void {
    this.redirectService.navigateTo(route);
  }

}
