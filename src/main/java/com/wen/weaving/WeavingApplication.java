package com.wen.weaving;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwaggerBootstrapUI
@EnableSwagger2
@SpringBootApplication
public class WeavingApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeavingApplication.class, args);
    }

}
