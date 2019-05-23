package com.leroy.ronan;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.Map;

@EnableWebMvc
@SpringBootApplication
public class App {

    public static void main(String...args) throws Exception {
        Map<String, Object> props = new HashMap<>();
        props.put("server.port", 8081);

        new SpringApplicationBuilder()
                .sources(App.class)
                .properties(props)
                .run(args);
    }

}
