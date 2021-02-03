package com.fastcode.timesheettestapp.domain.core.timesheetdetails;

import javax.persistence.*;
import java.time.*;
import java.math.BigDecimal;
import com.fastcode.timesheettestapp.domain.core.task.TaskEntity;
import com.fastcode.timesheettestapp.domain.core.timesheet.TimesheetEntity;
import com.fastcode.timesheettestapp.domain.core.timeofftype.TimeofftypeEntity;
import com.fastcode.timesheettestapp.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "timesheetdetails")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class TimesheetdetailsEntity extends AbstractEntity {

    @Basic
    @Column(name = "hours", nullable = true)
    private BigDecimal hours;
    
    @Basic
    @Column(name = "notes", nullable = true)
    private String notes;

    @Basic
    @Column(name = "workdate", nullable = false)
    private LocalDate workdate;

    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "timesheetid")
    private TimesheetEntity timesheet;

    @ManyToOne
    @JoinColumn(name = "taskid")
    private TaskEntity task;

    @ManyToOne
    @JoinColumn(name = "timeofftypeid")
    private TimeofftypeEntity timeofftype;


}



