package com.fastcode.timesheettestapp.domain.core.authorization.role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.time.*;
import com.fastcode.timesheettestapp.domain.core.authorization.rolepermission.RolepermissionEntity;
import com.fastcode.timesheettestapp.domain.core.authorization.userrole.UserroleEntity;
import com.fastcode.timesheettestapp.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "role")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class RoleEntity extends AbstractEntity {

    @Basic
    @Column(name = "display_name", nullable = false,length =255)
    private String displayName;

    @Basic
    @Column(name = "name", nullable = false,length =255)
    private String name;

    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RolepermissionEntity> rolepermissionsSet = new HashSet<RolepermissionEntity>();
    
    public void addRolepermissions(RolepermissionEntity rolepermissions) {        
    	rolepermissionsSet.add(rolepermissions);
        rolepermissions.setRole(this);
    }
    public void removeRolepermissions(RolepermissionEntity rolepermissions) {
        rolepermissionsSet.remove(rolepermissions);
        rolepermissions.setRole(null);
    }
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserroleEntity> userrolesSet = new HashSet<UserroleEntity>();
    
    public void addUserroles(UserroleEntity userroles) {        
    	userrolesSet.add(userroles);
        userroles.setRole(this);
    }
    public void removeUserroles(UserroleEntity userroles) {
        userrolesSet.remove(userroles);
        userroles.setRole(null);
    }

}



