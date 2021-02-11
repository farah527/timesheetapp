import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { FormBuilder } from '@angular/forms';
import * as Moment from 'moment';
import { extendMoment } from 'moment-range';

const moment = extendMoment(Moment);
@Component({
  selector: 'app-timesheet-details',
  templateUrl: './timesheet-details.component.html',
  styleUrls: ['./timesheet-details.component.scss'],
})
export class TimesheetDetailsComponent implements OnInit {
  title = 'Timesheet Details';
  parentUrl = 'timesheet-details';
  isLoadingResults = false;

  weekday = [ 'SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'];
  Months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
  month: any;

  notes: any;
  totalhours: any = 0;
  timesheetDate: any;
  timesheetday;
  timesheettilldate;
  dateList = [];
  customerProjects = [];
  customers = [];
  projects = [];

  timesheetDetails = [
    {
      taskId: 'tas1',
      taskDescriptiveField: 'task 1',
      projectId: 'pro1',
      projectDescriptiveField: 'project 1',
      timesheetId: 'tim1',
      timesheetDescriptiveField: ' timesheet 1',
      customerId: 'cust1',
      customerDescriptiveField: 'customer 1',
      workdate: '02/11/2020',
      hours: '2'
    },
    {
      taskId: 'tas1',
      taskDescriptiveField: 'task 1',
      projectId: 'pro3',
      projectDescriptiveField: 'project 3',
      timesheetId: 'tim1',
      timesheetDescriptiveField: ' timesheet 1',
      customerId: 'cust1',
      customerDescriptiveField: 'customer 1',
      workdate: '02/11/2020',
      hours: '2'
    },
    {
      taskId: 'tas2',
      taskDescriptiveField: 'task 1',
      projectId: 'pro1',
      projectDescriptiveField: 'project 1',
      timesheetId: 'tim1',
      timesheetDescriptiveField: ' timesheet 1',
      customerId: 'cust1',
      customerDescriptiveField: 'customer 1',
      workdate: '02/11/2020',
      hours: '2'
    },
    { taskId: 'tas1project2',
      taskDescriptiveField: 'task 1 project2',
      projectId: 'pro2',
      projectDescriptiveField: 'project 2',
      timesheetId: 'tim2',
      timesheetDescriptiveField: ' timesheet 2',
      customerId: 'cust2',
      customerDescriptiveField: 'customer 2',
      workdate: '02/11/2020',
      hours: '2'
    },
    { taskId: 'tas2tas2project2',
      taskDescriptiveField: 'tas2 project2',
      projectId: 'pro2',
      projectDescriptiveField: 'project 2',
      timesheetId: 'tim2',
      timesheetDescriptiveField: ' timesheet 2',
      customerId: 'cust2',
      customerDescriptiveField: 'customer 2',
      workdate: '02/11/2020',
      hours: '2'
    }
  ];
 constructor(
		public formBuilder: FormBuilder,
		public router: Router,
		public route: ActivatedRoute,
	) { }

	ngOnInit() {
    this.timesheetDate = new Date();
    this.timesheet('current');

    this.projects = this.timesheetDetails.reduce(function (r, timesheetobj) {
      r[timesheetobj.projectDescriptiveField] = r[timesheetobj.projectDescriptiveField] || [];
      r[timesheetobj.projectDescriptiveField].push({ timesheetobj });
      // r[a.pipeline_id].push({ project_name: a.project_name });
      return r;
    }, Object.create(null));
    console.log(this.projects);

    this.customerProjects = this.timesheetDetails.reduce(function (r, timesheetobj) {

        r[timesheetobj.customerDescriptiveField] = r[timesheetobj.customerDescriptiveField] || [] ;
        r[timesheetobj.customerDescriptiveField].push({ timesheetobj });
      return r;
    }, Object.create(null));
    console.log(this.customerProjects);
  }


  timesheet(state) {
    this.dateList = [];
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
        this.getdateList(this.timesheetDate, lastDate);
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
        this.getdateList(this.timesheetDate, this.timesheettilldate);
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
        this.getdateList(this.timesheetDate, lastDay);
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
        this.getdateList(this.timesheetDate, this.timesheettilldate);
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
          this.timesheetDate = fd;

          this.getdateList(this.timesheetDate, this.timesheettilldate);
        } else {
          var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
          var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);

          this.timesheetDate = firstDay;
          lastDate.setDate(firstDay.getDate() + 14);
          this.timesheettilldate = lastDate;
          this.getdateList(this.timesheetDate, this.timesheettilldate);
        }
    }
  }

 getdateList(startdate, enddate) {
   this.dateList = [];
    const start = new Date(startdate), end = new Date(enddate);
    const range = moment.range(moment(start), moment(end));
    var datearr= Array.from(range.by('day'));
    var mon=  datearr[0]._d.getMonth();
    this.month = this.Months[mon];
    console.log(datearr);
    for(var i=0; i< datearr.length; i++){
     var day= datearr[i]._d.getDay();
     var date=  datearr[i]._d.getDate();
    //  console.log(this.weekday[day]+' '+date);
      this.dateList.push(this.weekday[day]+' '+date);
    }
    console.log(range);
  }
}
