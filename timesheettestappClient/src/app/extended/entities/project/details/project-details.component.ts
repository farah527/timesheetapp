import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

import { ProjectExtendedService } from '../project.service';
import { Globals, PickerDialogService, ErrorService } from 'src/app/common/shared';

import { CustomerExtendedService } from 'src/app/extended/entities/customer/customer.service';

import { GlobalPermissionService } from 'src/app/core/global-permission.service';
import { ProjectDetailsComponent } from 'src/app/entities/project/index';

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.scss']
})
export class ProjectDetailsExtendedComponent extends ProjectDetailsComponent implements OnInit {
	title:string='Project';
	parentUrl:string='project';
	//roles: IRole[];  
	constructor(
		public formBuilder: FormBuilder,
		public router: Router,
		public route: ActivatedRoute,
		public dialog: MatDialog,
		public global: Globals,
		public projectExtendedService: ProjectExtendedService,
		public pickerDialogService: PickerDialogService,
		public errorService: ErrorService,
		public customerExtendedService: CustomerExtendedService,
		public globalPermissionService: GlobalPermissionService,
	) {
		super(formBuilder, router, route, dialog, global, projectExtendedService, pickerDialogService, errorService,
		customerExtendedService,
		globalPermissionService,
		);
  }

	ngOnInit() {
		super.ngOnInit();
  }
  
}
