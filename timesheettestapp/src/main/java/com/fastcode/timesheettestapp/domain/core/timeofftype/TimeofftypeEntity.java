package com.fastcode.timesheettestapp.domain.core.timeofftype;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.time.*;
import com.fastcode.timesheettestapp.domain.core.timesheetdetails.TimesheetdetailsEntity;
import com.fastcode.timesheettestapp.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "timeofftype")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class TimeofftypeEntity extends AbstractEntity {

    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Basic
    @Column(name = "typename", nullable = false,length =255)
    private String typename;

    @OneToMany(mappedBy = "timeofftype", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TimesheetdetailsEntity> timesheetdetailsSet = new HashSet<TimesheetdetailsEntity>();
    
    public void addTimesheetdetails(TimesheetdetailsEntity timesheetdetails) {        
    	timesheetdetailsSet.add(timesheetdetails);
        timesheetdetails.setTimeofftype(this);
    }
    public void removeTimesheetdetails(TimesheetdetailsEntity timesheetdetails) {
        timesheetdetailsSet.remove(timesheetdetails);
        timesheetdetails.setTimeofftype(null);
    }

}



