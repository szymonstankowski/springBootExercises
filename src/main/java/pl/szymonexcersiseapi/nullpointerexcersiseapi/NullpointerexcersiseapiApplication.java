package pl.szymonexcersiseapi.nullpointerexcersiseapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.szymonexcersiseapi.nullpointerexcersiseapi.service.PostService;

@SpringBootApplication
// jesli w pomie odkomentuje spring data a nie bedzie skonfigurowanego polaczenia z db to poznizsza linijka
// wylacza to polaczenie
//(exclude ={DataSourceAutoConfiguration.class})
public class NullpointerexcersiseapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NullpointerexcersiseapiApplication.class, args);

	}

}
