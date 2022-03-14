package com.yohan.imageupload;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ImageUploadApplication {

    public static void main(String[] args) {
        System.out.println("Working Directory = " + System.getProperty("user.dir")); // get working directory
        SpringApplication.run(ImageUploadApplication.class, args);
    }
}
