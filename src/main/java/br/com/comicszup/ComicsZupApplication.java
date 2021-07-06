package br.com.comicszup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableFeignClients
public class ComicsZupApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComicsZupApplication.class, args);
    }

}