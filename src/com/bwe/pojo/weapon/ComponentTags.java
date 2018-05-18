package com.bwe.pojo.weapon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "items",
        "tagSetSourceFile"
})
public class ComponentTags {

    @JsonProperty("items")
    private List<String> items = null;
    @JsonProperty("tagSetSourceFile")
    private String tagSetSourceFile;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("items")
    public List<String> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<String> items) {
        this.items = items;
    }

    @JsonProperty("tagSetSourceFile")
    public String getTagSetSourceFile() {
        return tagSetSourceFile;
    }

    @JsonProperty("tagSetSourceFile")
    public void setTagSetSourceFile(String tagSetSourceFile) {
        this.tagSetSourceFile = tagSetSourceFile;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}