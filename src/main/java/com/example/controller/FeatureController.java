package com.example.controller;

import com.example.RestServiceApplication;
import com.example.model.Feature;
import com.example.service.FeatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class FeatureController {

    private static final Logger log = LoggerFactory.getLogger(RestServiceApplication.class);

    @Autowired
    private FeatureService featureService;

    @GetMapping("/feature/list")
    public ResponseEntity<?> get() {
        return ResponseEntity.status(HttpStatus.OK).body(featureService.findAll());
    }

    @GetMapping("/feature")
    public ResponseEntity<?> get(@RequestParam(value = "featureName", required = false) String featureName, @RequestParam(value = "email", required = false) String email) {
        try {
            log.info("featureName " + featureName);
            log.info("email " + email);
            if (featureName == null || email == null) {
                return ResponseEntity
                        .status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body("Error >>> Expected parameters: featureName and email");
            }
            else {
                Feature feature = featureService.findByFeatureNameAndEmail(featureName, email);
                HashMap<String, Boolean> map = new HashMap<String, Boolean>();
                map.put("canAccess", feature != null ? feature.isEnable() : false);
                log.info("feature != null " + String.valueOf(feature != null));
                if (feature != null) {
                    log.info("feature " + feature.toString());
                    log.info("enable " + String.valueOf(feature.isEnable()));
                }
                return ResponseEntity.status(HttpStatus.OK).body(map);
            }
        }
        catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PostMapping("/feature")
    public ResponseEntity<?> create(@RequestBody Feature feature) {
        try {
            log.info("Saved: " + featureService.save(feature).toString());
            return ResponseEntity.ok(null);
        }
        catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(exception.getMessage());
        }
    }

}