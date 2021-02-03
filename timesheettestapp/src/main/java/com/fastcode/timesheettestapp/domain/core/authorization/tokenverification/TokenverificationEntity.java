package com.fastcode.timesheettestapp.domain.core.authorization.tokenverification;

import javax.persistence.*;
import java.time.*;
import java.util.Date;
import com.fastcode.timesheettestapp.domain.core.authorization.user.UserEntity;
import com.fastcode.timesheettestapp.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tokenverification")
@IdClass(TokenverificationId.class)
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class TokenverificationEntity extends AbstractEntity {

    @Basic
    @Column(name = "expiration_time", nullable = true)
    private Date expirationTime;

    @Id
    @EqualsAndHashCode.Include()
    @Column(name = "token_type", nullable = false,length =256)
    private String tokenType;

    @Id
    @EqualsAndHashCode.Include()
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Basic
    @Column(name = "token", nullable = true,length =512)
    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private UserEntity user;


}



