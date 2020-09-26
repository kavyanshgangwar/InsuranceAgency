package anubahv.insuracne.insuranceagency;

import anubahv.insuracne.insuranceagency.models.User;
import anubahv.insuracne.insuranceagency.services.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InsuranceagencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(InsuranceagencyApplication.class, args);
    }

}
