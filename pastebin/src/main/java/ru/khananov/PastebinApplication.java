package ru.khananov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PastebinApplication {
    public static void main(String[] args) {
        SpringApplication.run(PastebinApplication.class);
    }
}