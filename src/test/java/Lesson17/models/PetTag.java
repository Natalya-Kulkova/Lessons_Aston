package Lesson17.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PetTag {
    @JsonProperty
    private Long id;

    @JsonProperty
    private String name;

    public PetTag() {}

    public PetTag(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}