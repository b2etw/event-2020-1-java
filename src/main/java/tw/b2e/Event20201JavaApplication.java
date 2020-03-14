package tw.b2e;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Event20201JavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Event20201JavaApplication.class, args);
    }

}
