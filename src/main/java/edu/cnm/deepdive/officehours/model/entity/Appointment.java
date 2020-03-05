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
import javax.persistence.OneToOne;
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
public class Appointment {

  @NonNull
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column( name = "appointment_id", columnDefinition = "CHAR(16) FOR BIT DATA",
        nullable = false, updatable = false)
  private UUID id;

  @NonNull
  @ManyToOne(
      cascade = {
          CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })
  @JoinColumn(name = "student_id")
  private Student student;

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
  @Column(name = "start_time", nullable = false)
  private Date startTime;

  @NonNull
  @Column(name = "end_time", nullable = false)
  private Date endTime;

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

  public void setStudent(@NonNull Student student) {
    this.student = student;
  }

  public void setTeacher(@NonNull Teacher teacher) {
    this.teacher = teacher;
  }

  @NonNull
  public Date getEnd() {
    return endTime;
  }

  @NonNull
  public UUID getId() {
    return id;
  }

  @NonNull
  public Status getStatus() {
    return status;
  }

  public void setStatus(@NonNull Status status) {
    this.status = status;
  }

  public Subject getSubject() {
    return subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }

  @NonNull
  public Date getStart() {
    return startTime;
  }

  @NonNull
  public Date getCreated() {
    return created;
  }

  @NonNull
  public Date getUpdated() {
    return updated;
  }

  @NonNull
  public Student getStudent() {
    return student;
  }

  @NonNull
  public Teacher getTeacher() {
    return teacher;
  }

  @NonNull
  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(@NonNull Date startTime) {
    this.startTime = startTime;
  }

  @NonNull
  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(@NonNull Date endTime) {
    this.endTime = endTime;
  }

}
