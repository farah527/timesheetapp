import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-timesheet',
  templateUrl: './timesheet.component.html',
  styleUrls: ['./timesheet.component.scss']
})
export class TimesheetComponent implements OnInit {
  title:string='Timesheet';
  parentUrl:string='timesheet';

  isLoadingResults = false;
  customers = ['customer1', 'customer2', 'customer3'];
  projects = ['project1', 'project2', 'project3'];
  tasks = ['task1', 'task2', 'task3'];
  timeOffType = ['OTP', 'PO'];
  notesindex: any;
  totalhours: any = 0;
  timesheetDate: any;
  daysheet = [{
    customer: 'customer1',
    project: 'project1',
    task: 'task1',
    notes: 'notes about a task',
    hours: '3'
  },
  {
    notes: 'notes about a task',
    hours: '5',
    timeoff: 'PO'
  }];

	constructor(
		public formBuilder: FormBuilder,
		public router: Router,
		public route: ActivatedRoute,
	) { }

	ngOnInit() {
    this.totalDayHours();
  }

  nextDate() {
    var day = new Date(this.timesheetDate);
    console.log( this.timesheetDate);

    var nextDay = new Date( this.timesheetDate);
    nextDay.setDate(day.getDate() + 1);
    console.log(nextDay);
    this.timesheetDate = nextDay;
  }

  backDate() {
    var day = new Date(this.timesheetDate);
    console.log( this.timesheetDate);

    var backDay = new Date( this.timesheetDate);
    backDay.setDate(day.getDate() - 1);
    console.log(backDay);
    this.timesheetDate = backDay;
  }

  addCustomer() {
    let customer = { customer: '',
    project: '',
    task: '',
    notes: '',
    hours: ''};
    this.daysheet.push(customer);
  }

  addTimeOff() {
    let timeoff = {
      timeoff: 'OP',
      notes: '',
      hours: ''
    };
    this.daysheet.push(timeoff);
  }

  deleteRow(idx) {
    this.totalDayHours();
    this.daysheet.splice(idx, 1);
  }

  addNotes(idx) {
    if (this.notesindex != idx) {
      this.notesindex = idx;
    } else {
      this.notesindex = -1;
    }
  }

  totalDayHours() {
    this.totalhours = 0;
    for (let i=0; i < this.daysheet.length; i++ ) {
      this.totalhours = this.totalhours + parseInt(this.daysheet[i].hours);
    }
  }

  save() {

  }

}
