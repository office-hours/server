package edu.cnm.deepdive.officehours.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.cnm.deepdive.officehours.view.FlatPolicy;
import edu.cnm.deepdive.officehours.view.FlatTeacher;
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
import org.springframework.stereotype.Component;

/**
 * {@link Policy} database entity with an index of created and it's relationship with one {@link
 * Teacher} to zero or many {@link Policy}.
 */
@Component
@Entity
@Table(
    indexes = {
        @Index(columnList = "created")
    }
)
public class Policy implements FlatPolicy {

  @SuppressWarnings("JpaDataSourceORMInspection")
  @NonNull
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "policy_id", columnDefinition = "CHAR(16) FOR BIT DATA",
      nullable = false, updatable = false)
  private UUID id;

  @NonNull
  @ManyToOne(cascade = {})
  @JoinColumn(name = "teacher_id")
  @JsonSerialize(as = FlatTeacher.class)
  private Teacher teacher;

  @NonNull
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date startAvailable;

  @NonNull
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date endAvailable;

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
  @Column(nullable = false, updatable = true)
  private int blockTime;

  @NonNull
  public Teacher getTeacher() {
    return teacher;
  }

  /**
   * Sets a {@link Teacher} to a {@link Policy}.
   *
   * @param teacher
   */
  public void setTeacher(@NonNull Teacher teacher) {
    this.teacher = teacher;
  }

  @NonNull
  public Date getStartAvailable() {
    return startAvailable;
  }

  /**
   * Sets an available time {@link Policy} for the {@link Appointment} set by the {@link Teacher}.
   *
   * @param startAvailable time for the policy of teacher.
   */
  public void setStartAvailable(@NonNull Date startAvailable) {
    this.startAvailable = startAvailable;
  }

  @NonNull
  public Date getEndAvailable() {
    return endAvailable;
  }

  /**
   * Sets a end time {@link Policy} for set {@link Appointment} by {@link Teacher}.
   *
   * @param endAvailable time for the policy of teacher.
   */
  public void setEndAvailable(@NonNull Date endAvailable) {
    this.endAvailable = endAvailable;
  }

  @NonNull
  public int getBlockTime() {
    return blockTime;
  }

  /**
   * Sets a block-time for available times within the {@link Policy} of the {@link Teacher}s {@link
   * Appointment}.
   *
   * @param blockTime of teachers availability.
   */
  public void setBlockTime(@NonNull int blockTime) {
    this.blockTime = blockTime;
  }

  @NonNull
  public UUID getId() {
    return id;
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
