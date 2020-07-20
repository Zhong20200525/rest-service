package com.example.service;

import com.example.model.Feature;
import com.example.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeatureService {

    @Autowired
    private FeatureRepository repository;

    public List<Feature> findAll() {

        Iterable<Feature> it = repository.findAll();

        List<Feature> features = new ArrayList<Feature>();
        it.forEach(e -> {
            features.add(e);
        });

        return features;
    }

    public Feature findByFeatureNameAndEmail(String featureName, String email) {
        return repository.findFirstByFeatureNameAndEmail(featureName, email);
    }

    public Long count() {
        return repository.count();
    }

    public void deleteById(Long featureId) {
        repository.deleteById(featureId);
    }

    // update if exist, create if not exist
    public Feature save(Feature feature) {
        Feature feature1 = findByFeatureNameAndEmail(feature.getFeatureName(), feature.getEmail());
        if (feature1 != null) {
            feature.setId(feature1.getId());
        }
        return repository.save(feature);
    }
}
