package org.srijan.incomeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "org.srijan.incomeservice",     // current package
        "org.srijan.commonutils"        // explicitly including
})
public class IncomeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IncomeServiceApplication.class, args);
    }

}
