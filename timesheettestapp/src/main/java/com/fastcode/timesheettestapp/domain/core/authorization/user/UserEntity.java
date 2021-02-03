package com.fastcode.timesheettestapp.domain.core.authorization.user;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.time.*;
import com.fastcode.timesheettestapp.domain.core.authorization.userpermission.UserpermissionEntity;
import com.fastcode.timesheettestapp.domain.core.authorization.userpreference.UserpreferenceEntity;
import com.fastcode.timesheettestapp.domain.core.authorization.userrole.UserroleEntity;
import com.fastcode.timesheettestapp.domain.core.authorization.tokenverification.TokenverificationEntity;
import com.fastcode.timesheettestapp.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "f_user")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class UserEntity extends AbstractEntity {

    @Basic
    @Column(name = "last_name", nullable = false,length =32)
    private String lastName;

    @Basic
    @Column(name = "user_name", nullable = false,length =32)
    private String userName;

    @Basic
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
    
    @Basic
    @Column(name = "first_name", nullable = false,length =32)
    private String firstName;

    @Basic
    @Column(name = "password", nullable = false,length =128)
    private String password;

    @Basic
    @Column(name = "email_address", nullable = false,length =256)
    private String emailAddress;

    @Basic
    @Column(name = "phone_number", nullable = true,length =32)
    private String phoneNumber;

    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Basic
    @Column(name = "is_email_confirmed", nullable = true)
    private Boolean isEmailConfirmed;
    
    @OneToOne(mappedBy = "user", cascade=CascadeType.MERGE)
    private UserpreferenceEntity userpreference;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserpermissionEntity> userpermissionsSet = new HashSet<UserpermissionEntity>();
    
    public void addUserpermissions(UserpermissionEntity userpermissions) {        
    	userpermissionsSet.add(userpermissions);
        userpermissions.setUser(this);
    }
    public void removeUserpermissions(UserpermissionEntity userpermissions) {
        userpermissionsSet.remove(userpermissions);
        userpermissions.setUser(null);
    }
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TokenverificationEntity> tokenverificationsSet = new HashSet<TokenverificationEntity>();
    
    public void addTokenverifications(TokenverificationEntity tokenverifications) {        
    	tokenverificationsSet.add(tokenverifications);
        tokenverifications.setUser(this);
    }
    public void removeTokenverifications(TokenverificationEntity tokenverifications) {
        tokenverificationsSet.remove(tokenverifications);
        tokenverifications.setUser(null);
    }
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserroleEntity> userrolesSet = new HashSet<UserroleEntity>();
    
    public void addUserroles(UserroleEntity userroles) {        
    	userrolesSet.add(userroles);
        userroles.setUser(this);
    }
    public void removeUserroles(UserroleEntity userroles) {
        userrolesSet.remove(userroles);
        userroles.setUser(null);
    }

}



