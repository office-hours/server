package edu.cnm.deepdive.officehours.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cnm.deepdive.officehours.model.entity.Appointment;
import edu.cnm.deepdive.officehours.model.entity.Policy;
import edu.cnm.deepdive.officehours.model.entity.Student;
import edu.cnm.deepdive.officehours.model.entity.Teacher;
import edu.cnm.deepdive.officehours.model.entity.User;
import edu.cnm.deepdive.officehours.model.repository.AppointmentRepository;
import edu.cnm.deepdive.officehours.model.repository.PolicyRepository;
import edu.cnm.deepdive.officehours.model.repository.StudentRepository;
import edu.cnm.deepdive.officehours.model.repository.TeacherRepository;
import edu.cnm.deepdive.officehours.model.repository.UserRepository;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@Profile("preload")
public class Preloader implements CommandLineRunner {

  private static final  String USER_DATA = "preload/users.json";
  private static final String POLICY_DATA = "preload/policies.json";
  private static final String APPOINTMENT_DATA = "preload/appointments.json";

  private final UserRepository userRepository;
  private final TeacherRepository teacherRepository;
  private final PolicyRepository policyRepository;
  private final StudentRepository studentRepository;
  private final AppointmentRepository appointmentRepository;

  @Autowired
  public Preloader(UserRepository userRepository,
      TeacherRepository teacherRepository,
      PolicyRepository policyRepository,
      StudentRepository studentRepository,
      AppointmentRepository appointmentRepository){
    this.userRepository = userRepository;
    this.teacherRepository = teacherRepository;
    this.policyRepository = policyRepository;
    this.studentRepository = studentRepository;
    this.appointmentRepository = appointmentRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    loadUsers();
    loadPolicies();
  }

  private void loadUsers() throws IOException {
    ClassPathResource resource = new ClassPathResource(USER_DATA);
    try (InputStream input = resource.getInputStream()) {
      ObjectMapper mapper = new ObjectMapper();
      User[] users = mapper.readValue(input, User[].class);
      for (User user : users) {
        Student student = user.getStudent();
        Teacher teacher = user.getTeacher();
        if (student != null ) {
          student.setUser(user);
        } else if (teacher != null) {
          teacher.setUser(user);
        }
      }
      userRepository.saveAll(Arrays.asList(users));
    }
  }


  private void loadPolicies() throws IOException {
    ClassPathResource resource = new ClassPathResource(POLICY_DATA);
    try (InputStream input = resource.getInputStream()) {
      ObjectMapper mapper = new ObjectMapper();
      Policy[] policies = mapper.readValue(input, Policy[].class);
      for (Policy policy : policies) {
        Teacher teacher = policy.getTeacher();
        teacher = teacherRepository.findFirstByTeacherName(teacher.getTeacherName());
        policy.setTeacher(teacher);
      }
      policyRepository.saveAll(Arrays.asList(policies));
    }
  }


  private void loadAppointment() throws IOException {
    ClassPathResource resource = new ClassPathResource(POLICY_DATA);
    try (InputStream input = resource.getInputStream()) {
      ObjectMapper mapper = new ObjectMapper();
      Appointment[] appointments = mapper.readValue(input, Appointment[].class);
      for (Appointment appointment : appointments) {
        Teacher teacher = appointment.getTeacher();
        teacher = teacherRepository.findFirstByTeacherName(teacher.getTeacherName());
        Student student = appointment.getStudent();
        student = studentRepository.findFirstByStudentName(student.getStudentName());
        appointment.setTeacher(teacher);
        appointment.setStudent(student);
      }
      appointmentRepository.saveAll(Arrays.asList(appointments));
    }
  }
}

