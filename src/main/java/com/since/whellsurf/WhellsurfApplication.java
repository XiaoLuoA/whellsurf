package com.since.whellsurf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author luoxinyuan
 */
@SpringBootApplication
@ServletComponentScan
public class WhellsurfApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhellsurfApplication.class, args);
    }

}
