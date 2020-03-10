package edu.cnm.deepdive.officehours.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.cnm.deepdive.officehours.view.FlatStudent;
import edu.cnm.deepdive.officehours.view.FlatTeacher;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;
import javax.persistence.AttributeConverter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
  @Column(name = "appointment_id", columnDefinition = "CHAR(16) FOR BIT DATA",
      nullable = false, updatable = false)
  private UUID id;

  @NonNull
  @ManyToOne(
      cascade = {
          CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
      })
  @JoinColumn(name = "student_id")
  @JsonSerialize(as = FlatStudent.class)
  private Student student;

  @NonNull
  @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
      CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "teacher_id")
  @JsonSerialize(as = FlatTeacher.class)
  private Teacher teacher;

  @NonNull
  @Enumerated(EnumType.ORDINAL)
  @Column(nullable = false)
  private Status status;

  @Enumerated(EnumType.ORDINAL)
  private Subject subject;

  @NonNull
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "start_time", nullable = false)
  private Date startTime;

  @NonNull
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "end_time", nullable = false)
  private Date endTime;

  @NonNull
  @Temporal(TemporalType.DATE)
  @Column(name = "appointment_date", nullable = false)
  private Date appointmentDate;

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


  @NonNull
  public Date getAppointmentDate() {
    return appointmentDate;
  }

  public void setAppointmentDate(@NonNull Date appointmentDate) {
    this.appointmentDate = appointmentDate;
  }

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

  public enum Status {
    NO_SHOW("N"),
    PENDING("P"),
    LATE("L"),
    CANCELLED("X"),
    ARRIVED("K");

    private String code;


    private Status(String code) {
      this.code = code;
    }

    public String getCode() {
      return code;
    }
  }

  public enum Subject {
    ANDROID_PROJECT("A"),
    CAPSTONE_PROJECT("C"),
    TUTORING("T"),
    GRADES("G"),
    OTHER("O");

    private String code;

    private Subject(String code) {
      this.code = code;
    }

    public String getCode() {
      return code;
    }
  }
//  @Converter(autoApply = true)
//  public class StatusConverter implements AttributeConverter<Status, String> {
//
//    @Override
//    public String convertToDatabaseColumn(Status status) {
//      if (status == null) {
//        return null;
//      }
//      return status.getCode();
//    }
//
//    @Override
//    public Status convertToEntityAttribute(String code) {
//      if (code == null) {
//        return null;
//      }
//      return Stream.of(Status.values())
//          .filter(c -> c.getCode().equals(code))
//          .findFirst()
//          .orElseThrow(IllegalArgumentException::new);
//    }
//  }
//
//  @Converter(autoApply = true)
//  public class SubjectConverter implements AttributeConverter<Subject, String> {
//
//    @Override
//    public String convertToDatabaseColumn(Subject subject) {
//      if(subject == null) {
//        return null;
//      }
//      return subject.getCode();
//    }
//
//    @Override
//    public Subject convertToEntityAttribute(String code) {
//      if(code == null) {
//        return null;
//      }
//      return Stream.of(Subject.values())
//          .filter(c -> c.getCode().equals(code))
//          .findFirst()
//          .orElseThrow(IllegalArgumentException::new);
//
//    }
//  }

}
