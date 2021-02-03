package com.fastcode.timesheettestapp.domain.core.timesheet;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.time.*;
import com.fastcode.timesheettestapp.domain.core.users.UsersEntity;
import com.fastcode.timesheettestapp.domain.core.timesheetdetails.TimesheetdetailsEntity;
import com.fastcode.timesheettestapp.domain.core.timesheetstatus.TimesheetstatusEntity;
import com.fastcode.timesheettestapp.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "timesheet")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class TimesheetEntity extends AbstractEntity {

    @Basic
    @Column(name = "periodendingdate", nullable = false)
    private LocalDate periodendingdate;

    @Basic
    @Column(name = "notes", nullable = true)
    private String notes;

    @Basic
    @Column(name = "periodstartingdate", nullable = false)
    private LocalDate periodstartingdate;

    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @OneToMany(mappedBy = "timesheet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TimesheetdetailsEntity> timesheetdetailsSet = new HashSet<TimesheetdetailsEntity>();
    
    public void addTimesheetdetails(TimesheetdetailsEntity timesheetdetails) {        
    	timesheetdetailsSet.add(timesheetdetails);
        timesheetdetails.setTimesheet(this);
    }
    public void removeTimesheetdetails(TimesheetdetailsEntity timesheetdetails) {
        timesheetdetailsSet.remove(timesheetdetails);
        timesheetdetails.setTimesheet(null);
    }
    @ManyToOne
    @JoinColumn(name = "timesheetstatusid")
    private TimesheetstatusEntity timesheetstatus;

    @ManyToOne
    @JoinColumn(name = "userid")
    private UsersEntity users;


}



