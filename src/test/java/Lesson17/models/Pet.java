package Lesson17.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {
    @JsonProperty
    private Long id;

    @JsonProperty
    private Category category;

    @JsonProperty
    private String name;

    @JsonProperty
    private String[] photoUrls;

    @JsonProperty
    private PetTag[] tags;

    @JsonProperty
    private String status;

    public Pet() {}

    public Pet(Long id, Category category, String name, String[] photoUrls, PetTag[] tags, String status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String[] getPhotoUrls() {
        return photoUrls;
    }

    public PetTag[] getTags() {
        return tags;
    }

    public String getStatus() {
        return status;
    }
}