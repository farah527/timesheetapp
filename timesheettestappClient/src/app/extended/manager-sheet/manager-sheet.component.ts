import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { FormBuilder } from '@angular/forms';
import * as Moment from 'moment';
import { extendMoment } from 'moment-range';

const moment = extendMoment(Moment);
@Component({
  selector: 'app-manager-sheet',
  templateUrl: './manager-sheet.component.html',
  styleUrls: ['./manager-sheet.component.scss'],
})
export class ManagerSheetComponent implements OnInit {
  title = 'Manager Sheet';
  parentUrl = 'manager-sheet';
  isLoadingResults = false;

  // weekday = [ 'SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'];
  // Months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
  // month: any;

  timesheetDate: any;
  timesheetday;
  timesheettilldate;
  employees: any = [
    {
      employeeId:"emp1",
      employee:"Muhammad",
      totalhours:"80",
      timesheetDate: new Date()
    },
    {
      employeeId:"emp2",
      employee:"Usman",
      totalhours:"80",
      timesheetDate: new Date()
    }
  ];
  selectAll = false;

 constructor(
		public formBuilder: FormBuilder,
		public router: Router,
		public route: ActivatedRoute,
	) { }

	ngOnInit() {
    this.timesheetDate = new Date();
    this.timesheet('current');
    this.employees.forEach(emp => {
      emp['selected'] = 'false';
    });
    console.log(this.employees);
  }

  selectall() {
    console.log(this.selectAll);
    this.employees.forEach(emp => {
      emp.selected = this.selectAll;
    });
    console.log(this.employees);
  }

  accept() {}

  reject() {}


  timesheet(state) {
    var date = new Date(this.timesheetDate);
    var day = date.getDate();
    var nextDay = new Date( this.timesheetDate);
    console.log(day);

    var firstDate = new Date (this.timesheetDate);
    var lastDate = new Date (this.timesheetDate);
    if (state == 'current') {
      if (day < 15 ) {
        console.log('<15');
        var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
        var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);

        console.log('firstdate of month',firstDay);
        console.log('lastdate of month',lastDay);
        this.timesheetDate = firstDay;
        lastDate.setDate(new Date(firstDay).getDate() + 14);
        this.timesheettilldate = lastDate;
      } else {
        console.log('>15');
        var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
        var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
        console.log('firstdate of month',firstDay);
        console.log('lastdate of month',lastDay);
        firstDate.setDate(firstDay.getDate() + 15);
        this.timesheetDate = firstDate;
        console.log(this.timesheetDate);
        this.timesheettilldate = lastDay;
      }
    } else if (state == 'next') {
      console.log('next');
      if (day < 15 ) {
        console.log('<15');
        var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
        var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);

        console.log('firstdate of month',firstDay);
        console.log('lastdate of month',lastDay);
        firstDate.setDate(firstDay.getDate() + 15);
        this.timesheetDate = firstDate;
        this.timesheettilldate = lastDay;

      } else {
        console.log('>15');
        var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
        var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
        console.log('firstdate of month',firstDay);
        console.log('lastdate of month',lastDay);
        firstDate.setDate(new Date(lastDay).getDate() + 1);
        this.timesheetDate = firstDate;
        console.log(this.timesheetDate);
        lastDate.setDate(new Date(lastDay).getDate() + 15);
        this.timesheettilldate = lastDate;

      }
    } else {
        console.log('back');
        if (day < 15 ) {
          var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
          var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);

          console.log('firstdate of month',firstDay);
          console.log('lastdate of month',lastDay);
          lastDate.setDate(firstDay.getDate() - 1 );
          this.timesheettilldate = lastDate;
          var firstDayofLM = new Date(lastDate.getFullYear(), lastDate.getMonth(), 1);
        var fd =new Date(lastDate);
          fd.setDate(firstDayofLM.getDate() + 15);
          // setTimeout(function(){this.timesheetDate = firstDate; }, 2000);
          // firstDate.setDate(firstDate.getDate() - 16 );
          this.timesheetDate = fd;

        } else {
          var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
          var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);

          this.timesheetDate = firstDay;
          lastDate.setDate(firstDay.getDate() + 14);
          this.timesheettilldate = lastDate;

        }
    }

  }

}
