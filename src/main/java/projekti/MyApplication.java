package projekti;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableAutoConfiguration
//@EntityScan("models")
//@EnableJpaRepositories("repositories")
//@ComponentScan("projekti")
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class);
    }

}
