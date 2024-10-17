package ovh.gabrielhuav.escom.compartir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan(basePackages = "ovh.gabrielhuav.escom.compartir")
@EnableJpaRepositories(basePackages = "ovh.gabrielhuav.escom.compartir.auth.repository")
@EntityScan(basePackages = "ovh.gabrielhuav.escom.compartir.auth.model")
public class CompartirApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompartirApplication.class, args);
    }
}
