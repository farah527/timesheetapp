package com.fastcode.timesheettestapp.domain.core.authorization.rolepermission;

import javax.persistence.*;
import java.time.*;
import com.fastcode.timesheettestapp.domain.core.authorization.permission.PermissionEntity;
import com.fastcode.timesheettestapp.domain.core.authorization.role.RoleEntity;
import com.fastcode.timesheettestapp.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rolepermission")
@IdClass(RolepermissionId.class)
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class RolepermissionEntity extends AbstractEntity {

    @Id
    @EqualsAndHashCode.Include()
    @Column(name = "permission_id", nullable = false)
    private Long permissionId;
    
    @Id
    @EqualsAndHashCode.Include()
    @Column(name = "role_id", nullable = false)
    private Long roleId;
    
    @ManyToOne
    @JoinColumn(name = "role_id", insertable=false, updatable=false)
    private RoleEntity role;

    @ManyToOne
    @JoinColumn(name = "permission_id", insertable=false, updatable=false)
    private PermissionEntity permission;


}



