package com.heystyles.factura.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
        "com.heystyles.factura.api.async",
        "com.heystyles.factura.api.config",
        "com.heystyles.factura.api.controller",
        "com.heystyles.factura.api.converter",
        "com.heystyles.factura.api.dao",
        "com.heystyles.factura.api.entity",
        "com.heystyles.factura.api.service",
        "com.heystyles.factura.api.exception",
        "com.heystyles.factura.api.http",
        "com.heystyles.factura.api.message",
        "com.heystyles.factura.api.service",
        "com.heystyles.factura.api.validator",
})
@SpringBootApplication
public class FacturaApp extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FacturaApp.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(FacturaApp.class, args);
    }

}
