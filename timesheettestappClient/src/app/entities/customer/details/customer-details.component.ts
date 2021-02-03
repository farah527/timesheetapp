import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { FormBuilder, Validators} from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

import { CustomerService } from '../customer.service';
import { ICustomer } from '../icustomer';
import { BaseDetailsComponent, Globals, PickerDialogService, ErrorService } from 'src/app/common/shared';
import { GlobalPermissionService } from 'src/app/core/global-permission.service';


@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.scss']
})
export class CustomerDetailsComponent extends BaseDetailsComponent<ICustomer> implements OnInit {
	title = 'Customer';
	parentUrl = 'customer';
	constructor(
		public formBuilder: FormBuilder,
		public router: Router,
		public route: ActivatedRoute,
		public dialog: MatDialog,
		public global: Globals,
		public customerService: CustomerService,
		public pickerDialogService: PickerDialogService,
		public errorService: ErrorService,
		public globalPermissionService: GlobalPermissionService,
	) {
		super(formBuilder, router, route, dialog, global, pickerDialogService, customerService, errorService);
  }

	ngOnInit() {
		this.entityName = 'Customer';
		this.setAssociations();
		super.ngOnInit();
		this.setForm();
    	this.getItem();
    	this.setPickerSearchListener();
	}
  
  setForm(){
    this.itemForm = this.formBuilder.group({
      customerid: [{value: '', disabled: true}, Validators.required],
      description: [''],
      isactive: [false, Validators.required],
      name: ['', Validators.required],
      
    });
    
    this.fields = [
			
			
        {
		  name: 'description',
		  label: 'description',
		  isRequired: false,
		  isAutoGenerated: false,
	      type: 'string',
	    },
			
        {
		  name: 'isactive',
		  label: 'isactive',
		  isRequired: true,
		  isAutoGenerated: false,
          type: 'boolean',
	    },
			
        {
		  name: 'name',
		  label: 'name',
		  isRequired: true,
		  isAutoGenerated: false,
	      type: 'string',
	    },
      ];
      
  }
  
  onItemFetched(item: ICustomer) {
    this.item = item;
    this.itemForm.patchValue(item);
  }
  
  setAssociations(){
    this.associations = [
      {
        column: [
	        {
	          key: 'customerid',
	          value: undefined,
	          referencedkey: 'customerid'
			},
		],
		isParent: true,
		table: 'project',
		type: 'OneToMany',
		label: 'projects',
		},
		];
		
		this.childAssociations = this.associations.filter(association => {
			return (association.isParent);
		});

		this.parentAssociations = this.associations.filter(association => {
			return (!association.isParent);
		});
	}
	
	onSubmit() {
		let customer = this.itemForm.getRawValue();
		super.onSubmit(customer);
		
	}
}
