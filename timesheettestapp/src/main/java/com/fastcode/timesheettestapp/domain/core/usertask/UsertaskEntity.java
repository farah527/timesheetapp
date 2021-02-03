package com.fastcode.timesheettestapp.domain.core.usertask;

import javax.persistence.*;
import java.time.*;
import com.fastcode.timesheettestapp.domain.core.users.UsersEntity;
import com.fastcode.timesheettestapp.domain.core.task.TaskEntity;
import com.fastcode.timesheettestapp.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usertask")
@IdClass(UsertaskId.class)
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class UsertaskEntity extends AbstractEntity {

    @Id
    @EqualsAndHashCode.Include()
    @Column(name = "userid", nullable = false)
    private Long userid;
    
    @Id
    @EqualsAndHashCode.Include()
    @Column(name = "taskid", nullable = false)
    private Long taskid;
    
    @ManyToOne
    @JoinColumn(name = "taskid", insertable=false, updatable=false)
    private TaskEntity task;

    @ManyToOne
    @JoinColumn(name = "userid", insertable=false, updatable=false)
    private UsersEntity users;


}



