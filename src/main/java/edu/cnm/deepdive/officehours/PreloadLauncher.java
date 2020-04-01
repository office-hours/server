package edu.cnm.deepdive.officehours;

import org.springframework.boot.builder.SpringApplicationBuilder;

public class PreloadLauncher {

  public static void main(String[] args) {
    new SpringApplicationBuilder()
        .sources(OfficeHoursApplication.class)
        .profiles("preload")
        .run(args);
  }

}
