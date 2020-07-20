package com.example.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"featureName", "email"})})
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String featureName;
    private String email;
    private boolean enable;

    // The default constructor exists only for the sake of JPA. You do not use it directly, so it is designated as protected
    protected Feature() {
    }

    public Feature(String featureName, String email, boolean enable) {
        this.featureName = featureName;
        this.email = email;
        this.enable = enable;
    }

    @Override
    public String toString() {
        return String.format(
                "Feature[id=%d, featureName='%s', email='%s', enable='%b']",
                id, featureName, email, enable);
    }
}
