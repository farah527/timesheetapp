package com.fastcode.timesheettestapp.domain.core.timesheetstatus;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.time.*;
import com.fastcode.timesheettestapp.domain.core.timesheet.TimesheetEntity;
import com.fastcode.timesheettestapp.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "timesheetstatus")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class TimesheetstatusEntity extends AbstractEntity {

    @Basic
    @Column(name = "statusname", nullable = false,length =255)
    private String statusname;

    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @OneToMany(mappedBy = "timesheetstatus", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TimesheetEntity> timesheetsSet = new HashSet<TimesheetEntity>();
    
    public void addTimesheets(TimesheetEntity timesheets) {        
    	timesheetsSet.add(timesheets);
        timesheets.setTimesheetstatus(this);
    }
    public void removeTimesheets(TimesheetEntity timesheets) {
        timesheetsSet.remove(timesheets);
        timesheets.setTimesheetstatus(null);
    }

}



