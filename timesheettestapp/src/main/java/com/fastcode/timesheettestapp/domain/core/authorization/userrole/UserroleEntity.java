package com.fastcode.timesheettestapp.domain.core.authorization.userrole;

import javax.persistence.*;
import java.time.*;
import com.fastcode.timesheettestapp.domain.core.authorization.role.RoleEntity;
import com.fastcode.timesheettestapp.domain.core.authorization.user.UserEntity;
import com.fastcode.timesheettestapp.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "userrole")
@IdClass(UserroleId.class)
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class UserroleEntity extends AbstractEntity {

    @Id
    @EqualsAndHashCode.Include()
    @Column(name = "role_id", nullable = false)
    private Long roleId;
    
    @Id
    @EqualsAndHashCode.Include()
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @ManyToOne
    @JoinColumn(name = "role_id", insertable=false, updatable=false)
    private RoleEntity role;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private UserEntity user;


}



