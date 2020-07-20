package com.example.repository;

import com.example.model.Feature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends CrudRepository<Feature, Long> {
    Feature findById(long id);
    Feature findFirstByFeatureNameAndEmail(String featureName, String email);
}
