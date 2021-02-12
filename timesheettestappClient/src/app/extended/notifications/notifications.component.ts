import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.scss']
})
export class NotificationsComponent implements OnInit {
  title: string = 'Notifications';
  parentUrl: string = 'notification';

  isLoadingResults = false;
  weekday = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat','Sun'];
  bgColor: any;
  days =[];
  constructor(
    public formBuilder: FormBuilder,
    public router: Router,
    public route: ActivatedRoute,
  ) { }

  ngOnInit() {

  }

  selectDay(day) {
    if (day.includes(day)) {
      this.days.push(day);
      this.bgColor= "";
    }
  }

  update() {}

  cancel() {}


}
