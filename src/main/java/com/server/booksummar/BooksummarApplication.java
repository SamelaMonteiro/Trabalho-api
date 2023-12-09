package com.server.booksummar;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class BooksummarApplication {

    public static void main(String[] args) {
        SpringApplication.run(BooksummarApplication.class, args);
    }

}
