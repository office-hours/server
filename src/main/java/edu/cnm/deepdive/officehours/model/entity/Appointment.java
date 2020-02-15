package edu.cnm.deepdive.officehours.model.entity;

import java.util.Date;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

@Entity
public class Appointment {

  @NonNull
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column( name = "appointment_id", columnDefinition = "CHAR(16) FOR BIT DATA",
        nullable = false, updatable = false)
  private UUID id;


//  @NonNull
//  @OneToOne ( cascade = {CascadeType.DETACH, CascadeType.MERGE,
//      CascadeType.PERSIST, CascadeType.REFRESH})
//  @JoinColumn(name = "student_id", referencedColumnName = ap)
//  private Set<Student> studentId;

  @NonNull
  private UUID teacherId;


  @NonNull
  @Column(nullable = false)
  private String status;

  @NonNull

  private Date start;

  @NonNull
  private Date end;

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


  public void setStatus(@NonNull String status) {
    this.status = status;
  }

  @NonNull
  public Date getEnd() {
    return end;
  }

  @NonNull
  public UUID getId() {
    return id;
  }

  @NonNull
  public UUID getStudentId() {
    return studentId;
  }

  @NonNull
  public UUID getTeacherId() {
    return teacherId;
  }

  @NonNull
  public String getStatus() {
    return status;
  }

  @NonNull
  public Date getStart() {
    return start;
  }

  @NonNull
  public Date getCreated() {
    return created;
  }

  @NonNull
  public Date getUpdated() {
    return updated;
  }
}
