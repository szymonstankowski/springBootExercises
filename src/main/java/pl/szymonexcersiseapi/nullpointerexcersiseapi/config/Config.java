package pl.szymonexcersiseapi.nullpointerexcersiseapi.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

@Configuration
@EnableSwagger2
public class Config {

    //wyexcludowanie z ui swaggera basic-error-controller
    @Bean
    public Docket api(){
        return new Docket((DocumentationType.SWAGGER_2))
                .select()
                .paths(PathSelectors.regex("^(?!/(error).*$).*$"))
                .build();
    }



//    @Autowired
//    private ObjectMapper objectMapper;
//
//    void customiseObjectMapper(){
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//    }
}
