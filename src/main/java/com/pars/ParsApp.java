package com.pars;

import com.pars.system.db.ConnectionFactory;
import com.pars.system.db.Persistences;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class ParsApp {

    public static void main(String[] args) {
        if (args[0].equals("pars.local-connection"))
            ConnectionFactory.setPersistenceUnit(Persistences.Local);
        SpringApplication.run(ParsApp.class, args);
    }
}
