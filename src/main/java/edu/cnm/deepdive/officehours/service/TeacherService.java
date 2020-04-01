package edu.cnm.deepdive.officehours.service;

import edu.cnm.deepdive.officehours.model.entity.Teacher;
import edu.cnm.deepdive.officehours.model.repository.TeacherRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
    this.teacherRepository = teacherRepository;
  }

  public Teacher create(Teacher teacher) {
      return teacherRepository.save(teacher);
    }

  public Iterable<Teacher> readAll() {
    return teacherRepository.getAllByOrderByTeacherName();
  }

  public Optional<Teacher> readOne(UUID id) {
      return teacherRepository.findById(id);
    }

    public Optional<Teacher> update(UUID id, Teacher teacher) {
      return teacherRepository.findById(id).map((s) -> {
        s.setTeacherName(teacher.getTeacherName());
        return teacherRepository.save(s);
      });
    }

    public Optional<String> update(UUID id, String teacherName) {
      return teacherRepository.findById(id).map((s) -> {
        s.setTeacherName(teacherName);
        return teacherRepository.save(s).getTeacherName();
      });
    }

  }
