package edu.cnm.deepdive.officehours;

import java.util.stream.Stream;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class SubjectConverter implements AttributeConverter<Subject, String> {

  @Override
  public String convertToDatabaseColumn(Subject subject) {
    if(subject == null) {
      return null;
    }
    return subject.getCode();
  }

  @Override
  public Subject convertToEntityAttribute(String code) {
    if(code == null) {
      return null;
    }
    return Stream.of(Subject.values())
        .filter(c -> c.getCode().equals(code))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);

  }
}
