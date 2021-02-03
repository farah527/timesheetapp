package com.fastcode.timesheettestapp.application.core.timesheetdetails.dto;

import java.time.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter @Setter
public class UpdateTimesheetdetailsOutput {

  	private BigDecimal hours;
  	private Long id;
  	private String notes;
  	private LocalDate workdate;
  	private Long taskid;
	private String taskDescriptiveField;
  	private Long timeofftypeid;
	private String timeofftypeDescriptiveField;
  	private Long timesheetid;
	private LocalDate timesheetDescriptiveField;

}
