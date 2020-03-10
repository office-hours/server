package edu.cnm.deepdive.officehours.controller.rest.html;

import edu.cnm.deepdive.officehours.service.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/appointments")
public class AppointmentPageController {

  private final AppointmentRepository repository;

  @Autowired
  public AppointmentPageController(AppointmentRepository repository) {
    this.repository = repository;
  }

  @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
  public String getAll(Model model) {
    model.addAttribute("appointment", repository.getAllByOrderByCreatedDesc());
    return "list";
  }

}
