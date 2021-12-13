package com.t.t.k.ims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Main class to run Spring Boot application.
 *
 * @author ttkien
 */

@SpringBootApplication
@EnableMongoRepositories("com.t.t.k.ims.repository")
public class Application {
    /**
     * Runs the Spring boot application
     *
     * @param args input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
