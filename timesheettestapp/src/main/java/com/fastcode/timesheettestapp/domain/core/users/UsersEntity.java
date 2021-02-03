package com.fastcode.timesheettestapp.domain.core.users;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.time.*;
import com.fastcode.timesheettestapp.domain.core.timesheet.TimesheetEntity;
import com.fastcode.timesheettestapp.domain.core.usertask.UsertaskEntity;
import com.fastcode.timesheettestapp.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class UsersEntity extends AbstractEntity {

    @Basic
    @Column(name = "firstname", nullable = false,length =255)
    private String firstname;

    @Basic
    @Column(name = "password", nullable = false,length =255)
    private String password;

    @Basic
    @Column(name = "join_date", nullable = true)
    private LocalDate joinDate;

    @Basic
    @Column(name = "isactive", nullable = false)
    private Boolean isactive;
    
    @Basic
    @Column(name = "isemailconfirmed", nullable = false)
    private Boolean isemailconfirmed;
    
    @Basic
    @Column(name = "emailaddress", nullable = false,length =255)
    private String emailaddress;

    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Basic
    @Column(name = "lastname", nullable = false,length =255)
    private String lastname;

    @Basic
    @Column(name = "username", nullable = false,length =255)
    private String username;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TimesheetEntity> timesheetsSet = new HashSet<TimesheetEntity>();
    
    public void addTimesheets(TimesheetEntity timesheets) {        
    	timesheetsSet.add(timesheets);
        timesheets.setUsers(this);
    }
    public void removeTimesheets(TimesheetEntity timesheets) {
        timesheetsSet.remove(timesheets);
        timesheets.setUsers(null);
    }
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsertaskEntity> usertasksSet = new HashSet<UsertaskEntity>();
    
    public void addUsertasks(UsertaskEntity usertasks) {        
    	usertasksSet.add(usertasks);
        usertasks.setUsers(this);
    }
    public void removeUsertasks(UsertaskEntity usertasks) {
        usertasksSet.remove(usertasks);
        usertasks.setUsers(null);
    }

}



