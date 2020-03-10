package edu.cnm.deepdive.officehours.model.entity;

import edu.cnm.deepdive.officehours.Status;
import edu.cnm.deepdive.officehours.Subject;
import java.util.Date;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;


@Entity
@Table(
    indexes = {
        @Index(columnList = "status"),
        @Index(columnList = "start_time"),
        @Index(columnList = "end_time"),
        @Index(columnList = "created")
    }
)
public class Policy {

    @NonNull
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
        CascadeType.PERSIST, CascadeType.REFRESH} )
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @NonNull
    @Column(nullable = false)
    private Status status;

    private Subject subject;

    @NonNull
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date created;

    @NonNull
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updated;

    private


}
