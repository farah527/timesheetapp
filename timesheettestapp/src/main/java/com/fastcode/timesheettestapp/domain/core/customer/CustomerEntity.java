package com.fastcode.timesheettestapp.domain.core.customer;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.time.*;
import com.fastcode.timesheettestapp.domain.core.project.ProjectEntity;
import com.fastcode.timesheettestapp.domain.core.abstractentity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class CustomerEntity extends AbstractEntity {

    @Id
    @EqualsAndHashCode.Include()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerid", nullable = false)
    private Long customerid;
    
    @Basic
    @Column(name = "name", nullable = false,length =255)
    private String name;

    @Basic
    @Column(name = "description", nullable = true)
    private String description;

    @Basic
    @Column(name = "isactive", nullable = false)
    private Boolean isactive;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProjectEntity> projectsSet = new HashSet<ProjectEntity>();
    
    public void addProjects(ProjectEntity projects) {        
    	projectsSet.add(projects);
        projects.setCustomer(this);
    }
    public void removeProjects(ProjectEntity projects) {
        projectsSet.remove(projects);
        projects.setCustomer(null);
    }

}



