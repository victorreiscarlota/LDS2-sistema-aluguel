package aracar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AracarApplication {

    @GetMapping("/teste")
    public String home() {
        return "Hello, 33333333!";
    }

    public static void main(String[] args) {
        SpringApplication.run(AracarApplication.class, args);
    }
}
