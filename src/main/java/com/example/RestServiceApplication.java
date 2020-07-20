package com.example;

import com.example.model.Feature;
import com.example.service.FeatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(RestServiceApplication.class);

    @Autowired
    private FeatureService featureService;

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {

            final String email = "admin@gmail.com";
            featureService.save(new Feature("Storage", email, true));
            featureService.save(new Feature("Security", email, true));
            featureService.save(new Feature("Deploy", email, true));
            featureService.save(new Feature("Localization", email, true));
            featureService.save(new Feature("Query", email, true));

            log.info("");

            // list all features
            log.info("List all features");
            log.info("-------------------------------");
            for (Feature feature : featureService.findAll()) {
                log.info(feature.toString());
            }
            log.info("Total: {}\n", featureService.count());

            // update existing feature record
            log.info("Update (featureName: 'Query', email: 'admin@gmail.com') to enable = false");
            log.info("--------------------------------");
            Feature before = featureService.findByFeatureNameAndEmail("Query", email);
            log.info("Before: {}", before.toString());
            log.info("BEFORE isEnable: {}", before.isEnable());
            featureService.save(new Feature("Query", email, false));
            Feature updated = featureService.findByFeatureNameAndEmail("Query", email);
            log.info("Updated: {}", updated.toString());
            log.info("AFTER isEnable: {}", updated.isEnable());
            log.info("Total: {}\n", featureService.count());
        };
    }
}
