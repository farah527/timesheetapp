package com.fastcode.timesheettestapp.domain.core.authorization.userpermission;

import javax.persistence.*;
import java.time.*;
import com.fastcode.timesheettestapp.domain.core.authorization.permission.PermissionEntity;
import com.fastcode.timesheettestapp.domain.core.authorization.user.UserEntity;
import com.fastcode.timesheettestapp.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "userpermission")
@IdClass(UserpermissionId.class)
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class UserpermissionEntity extends AbstractEntity {

    @Id
    @EqualsAndHashCode.Include()
    @Column(name = "permission_id", nullable = false)
    private Long permissionId;
    
    @Basic
    @Column(name = "revoked", nullable = true)
    private Boolean revoked;
    
    @Id
    @EqualsAndHashCode.Include()
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @ManyToOne
    @JoinColumn(name = "permission_id", insertable=false, updatable=false)
    private PermissionEntity permission;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private UserEntity user;


}



