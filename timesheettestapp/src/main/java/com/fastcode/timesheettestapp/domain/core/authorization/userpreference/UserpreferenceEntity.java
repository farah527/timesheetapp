package com.fastcode.timesheettestapp.domain.core.authorization.userpreference;

import javax.persistence.*;
import java.time.*;
import com.fastcode.timesheettestapp.domain.core.authorization.user.UserEntity;
import com.fastcode.timesheettestapp.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "userpreference")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class UserpreferenceEntity extends AbstractEntity {

    @Basic
    @Column(name = "theme", nullable = false,length =256)
    private String theme;

    @Basic
    @Column(name = "language", nullable = false,length =256)
    private String language;

    @Id
    @EqualsAndHashCode.Include()
    @Column(name = "id", nullable = false)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "id")
    private UserEntity user;


}



