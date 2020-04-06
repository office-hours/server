package edu.cnm.deepdive.officehours.service;

import edu.cnm.deepdive.officehours.model.entity.Student;
import edu.cnm.deepdive.officehours.model.entity.Teacher;
import edu.cnm.deepdive.officehours.model.entity.User;
import edu.cnm.deepdive.officehours.model.repository.StudentRepository;
import edu.cnm.deepdive.officehours.model.repository.TeacherRepository;
import edu.cnm.deepdive.officehours.model.repository.UserRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final StudentRepository studentRepository;
  private final TeacherRepository teacherRepository;

  @Autowired
  public UserService(UserRepository userRepository,
      StudentRepository studentRepository,
      TeacherRepository teacherRepository) {
    this.userRepository = userRepository;
    this.studentRepository = studentRepository;
    this.teacherRepository = teacherRepository;
  }

  public User findOrCreate(String oauth, String nickname, String email) {
    return userRepository.findFirstByOauth(oauth)
        .orElseGet(() -> {
          User user = new User();
          user.setOauth(oauth);
          user.setNickname(nickname);
          user.setEmail(email);
          // FIXME Shouldn't create teacher automatically.
          Teacher teacher = new Teacher();
          teacher.setUser(user);
          teacher.setTeacherName(nickname);
          user.setTeacher(teacher);
          return userRepository.save(user);
        });
  }

  public User create(User user) {
    user.setStudent(resolveStudent(user.getStudent()));
    user.setTeacher(resolveTeacher(user.getTeacher()));
    return userRepository.save(user);
  }

  public Iterable<User> readAll(){
    return  userRepository.findAllByOrderByNickname();
  }

  public Optional<User> readOne(UUID id) {
    return userRepository.findById(id);
  }

  public Optional<User> update(UUID userId, User user) {
    return userRepository.findById(userId).map((s) -> {
      s.setEmail(user.getEmail());
      return userRepository.save(s);
    });
  }

  public Optional<String> update(UUID id, String email) {
    return userRepository.findById(id).map((s) -> {
      s.setEmail(email);
      return userRepository.save(s).getEmail();
    });
  }

  public void clearStudent(UUID userId) {
    userRepository.findById(userId).map((user) -> {
      user.setStudent(null);
      return userRepository.save(user);
    }).get();
  }


  public void clearTeacher(UUID userId) {
    userRepository.findById(userId).map((user) -> {
      user.setTeacher(null);
      return userRepository.save(user);
    }).get();
  }

  @SuppressWarnings("OptionalGetWithoutIsPresent")
  private Student resolveStudent(Student student) {
    UUID studentId;
    return (student != null && (studentId = student.getId()) != null)
        ? studentRepository.findById(studentId).get()
        : student;
  }

  @SuppressWarnings("OptionalGetWithoutIsPresent")
  private Teacher resolveTeacher(Teacher teacher) {
    UUID teacherId;
    return (teacher != null && (teacherId = teacher.getId()) != null)
        ? teacherRepository.findById(teacherId).get()
        : teacher;
  }

}
