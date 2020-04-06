package edu.cnm.deepdive.officehours;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Main class of Office Hours application. The work of the application start-up is primarily
 * performed by the {@link SpringApplication} class here.
 */
@EnableWebSecurity
@EnableResourceServer
@EnableHypermediaSupport(type = HypermediaType.HAL)
@SpringBootApplication
public class OfficeHoursApplication extends ResourceServerConfigurerAdapter {


  @Value("${oauth.clientId}")
  private String clientId;

  /**
   * Entry point of the Office Hours Spring Boot application. Any command line will be forwarded to
   * {@link SpringApplication#run(Class, String...)}.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    SpringApplication.run(OfficeHoursApplication.class, args);
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.resourceId(clientId);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.authorizeRequests()
//        .antMatchers(HttpMethod.POST, "/**").hasRole("TEACHER")
//        .antMatchers(HttpMethod.PUT, "/**").hasRole("TEACHER")
//        .antMatchers(HttpMethod.DELETE, "/**").hasRole("TEACHER")
        .anyRequest().authenticated();
  }

}
