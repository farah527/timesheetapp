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
  weekday = ['mon', 'tue', 'wed', 'thu', 'fri', 'sat','sun'];

  days = ['mon', 'tue'];
  time: any;
  constructor(
    public formBuilder: FormBuilder,
    public router: Router,
    public route: ActivatedRoute,
  ) { }

  ngOnInit() {

  }

  isActive(day) {
    if (this.days.includes(day)) {
      // this.days.push(day);
      return true;
    } else {
      // const index = this.days.indexOf(day);
      // if (index > -1) {
      //   this.days.splice(index, 1);
      // }
      // console.log(this.days);
      return false;
    }
  }

  selectDay(day) {
    console.log(day);
    console.log(this.days);
    if (this.days.includes(day)) {
      const index = this.days.indexOf(day);
      if (index > -1) {
        this.days.splice(index, 1);
      }
      console.log(this.days);
    } else {
      this.days.push(day);
    }
    console.log(this.days);
  }

  update() {
    var data= {days: this.days, time: this.time}
    console.log(data);
  }

  cancel() {}


}
