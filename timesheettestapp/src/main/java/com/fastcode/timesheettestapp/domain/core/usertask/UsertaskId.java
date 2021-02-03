package com.fastcode.timesheettestapp.domain.core.usertask;

import java.time.*;
import javax.persistence.*;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter @Setter
@NoArgsConstructor
public class UsertaskId implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private Long userid;
    private Long taskid;
    
    public UsertaskId(Long taskid,Long userid) {
 	this.userid = userid;
 	this.taskid = taskid;
    }
    
}
