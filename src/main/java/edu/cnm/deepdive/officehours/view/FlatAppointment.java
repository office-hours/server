package edu.cnm.deepdive.officehours.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import edu.cnm.deepdive.officehours.model.entity.Appointment.Status;
import edu.cnm.deepdive.officehours.model.entity.Appointment.Subject;
import java.net.URI;
import java.util.Date;
import java.util.UUID;
import org.springframework.lang.NonNull;

/**
 * Declares the getters of appointment for serialization, excluding
 * references to other objects that could result in stack overflow on serialization.
 */
@JsonPropertyOrder({"id", "created", "updated", "status", "subject", "starTime", "endTime", "href"})
public interface FlatAppointment {

    /**
     * returns the Id (UUID) of a appointment resource.
     *
     * @return appointment UUID
     */
    @NonNull
     UUID getId();

    /**
     * Returns the date-time stamp recorded when a appointment resource is first written to the database.
     *
     * @return creation timestamp
     */
    @NonNull
    Date getCreated();

    /**
     * Returns the date-time stamp recorded when a appointment resource is updated in the database.
     *
     * @return updated timestamp
     */
    @NonNull
    Date getUpdated();

    /**
     * Returns the selected status of an appointment that comes from an enum.
     *
     * @return appointment status
     */
    @NonNull
    Status getStatus();

    /**
     * Returns the selected subject of an appointment that comes from an enum.
     *
     * @return appointment subject
     */
    @NonNull
    Subject getSubject();

    /**
     * returns an ISO for the start of an appointment
     *
     * @return appointment startTime
     */
    @NonNull
    Date getStartTime();

    /**
     * returns an ISO for the end of an appointment
     *
     * @return appointment endTime
     */
    @NonNull
    Date getEndTime();

    /**
     * Returns a URL referring to the appointment resource.
     *
     * @return appointment URL
     */
    @NonNull
    URI getHref();
  }

