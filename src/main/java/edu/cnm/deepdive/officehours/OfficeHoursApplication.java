package edu.cnm.deepdive.officehours;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

/**
 * Main class of Office Hours application. The work of the application start-up is primarily
 * performed by the {@link SpringApplication} class here.
 */
@EnableHypermediaSupport(type = HypermediaType.HAL)
@SpringBootApplication
public class OfficeHoursApplication {

  /**
   * Entry point of the Office Hours Spring Boot application. Any command line will be forwarded to
   * {@link SpringApplication#run(Class, String...)}.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    SpringApplication.run(OfficeHoursApplication.class, args);
  }


}
