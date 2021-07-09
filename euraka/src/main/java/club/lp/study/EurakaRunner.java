package club.lp.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurakaRunner {

    public static void main(String[] args) {
        SpringApplication.run(EurakaRunner.class, args);
    }

}
