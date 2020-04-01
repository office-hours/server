package edu.cnm.deepdive.officehours;

import org.springframework.boot.builder.SpringApplicationBuilder;

public class ServerLauncher {

  public static void main(String[] args) {
    new SpringApplicationBuilder()
        .sources(OfficeHoursApplication.class)
        .profiles("server")
        .run(args);
  }

}
