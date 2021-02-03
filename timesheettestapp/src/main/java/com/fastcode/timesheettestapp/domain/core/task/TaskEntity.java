package com.fastcode.timesheettestapp.domain.core.task;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.time.*;
import com.fastcode.timesheettestapp.domain.core.timesheetdetails.TimesheetdetailsEntity;
import com.fastcode.timesheettestapp.domain.core.project.ProjectEntity;
import com.fastcode.timesheettestapp.domain.core.usertask.UsertaskEntity;
import com.fastcode.timesheettestapp.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class TaskEntity extends AbstractEntity {

    @Basic
    @Column(name = "isactive", nullable = false)
    private Boolean isactive;
    
    @Basic
    @Column(name = "name", nullable = false,length =255)
    private String name;

    @Basic
    @Column(name = "description", nullable = true)
    private String description;

    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TimesheetdetailsEntity> timesheetdetailsSet = new HashSet<TimesheetdetailsEntity>();
    
    public void addTimesheetdetails(TimesheetdetailsEntity timesheetdetails) {        
    	timesheetdetailsSet.add(timesheetdetails);
        timesheetdetails.setTask(this);
    }
    public void removeTimesheetdetails(TimesheetdetailsEntity timesheetdetails) {
        timesheetdetailsSet.remove(timesheetdetails);
        timesheetdetails.setTask(null);
    }
    @ManyToOne
    @JoinColumn(name = "projectid")
    private ProjectEntity project;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsertaskEntity> usertasksSet = new HashSet<UsertaskEntity>();
    
    public void addUsertasks(UsertaskEntity usertasks) {        
    	usertasksSet.add(usertasks);
        usertasks.setTask(this);
    }
    public void removeUsertasks(UsertaskEntity usertasks) {
        usertasksSet.remove(usertasks);
        usertasks.setTask(null);
    }

}



