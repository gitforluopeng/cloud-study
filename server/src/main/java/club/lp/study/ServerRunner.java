package club.lp.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
public class ServerRunner {
    public static void main(String[] args) {
        SpringApplication.run(ServerRunner.class, args);
    }
}
