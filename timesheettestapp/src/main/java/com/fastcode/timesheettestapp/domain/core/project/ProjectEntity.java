package com.fastcode.timesheettestapp.domain.core.project;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.time.*;
import com.fastcode.timesheettestapp.domain.core.task.TaskEntity;
import com.fastcode.timesheettestapp.domain.core.customer.CustomerEntity;
import com.fastcode.timesheettestapp.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ProjectEntity extends AbstractEntity {

    @Basic
    @Column(name = "enddate", nullable = false)
    private LocalDate enddate;

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
    
    @Basic
    @Column(name = "startdate", nullable = false)
    private LocalDate startdate;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TaskEntity> tasksSet = new HashSet<TaskEntity>();
    
    public void addTasks(TaskEntity tasks) {        
    	tasksSet.add(tasks);
        tasks.setProject(this);
    }
    public void removeTasks(TaskEntity tasks) {
        tasksSet.remove(tasks);
        tasks.setProject(null);
    }
    @ManyToOne
    @JoinColumn(name = "customerid")
    private CustomerEntity customer;


}



