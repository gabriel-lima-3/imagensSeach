package gabriel_lima_3.imagelitapi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ImagelitapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImagelitapiApplication.class, args);
	}


}
