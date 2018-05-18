package com.bwe.pojo.weapon;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Cost",
        "Rarity",
        "Purchasable",
        "Manufacturer",
        "Model",
        "UIName",
        "Id",
        "Name",
        "Details",
        "Icon"
})
public class Description {

    @JsonProperty("Cost")
    private Integer cost;
    @JsonProperty("Rarity")
    private Integer rarity;
    @JsonProperty("Purchasable")
    private Boolean purchasable;
    @JsonProperty("Manufacturer")
    private String manufacturer;
    @JsonProperty("Model")
    private String model;
    @JsonProperty("UIName")
    private String uIName;
    @JsonProperty("Id")
    private String id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Details")
    private String details;
    @JsonProperty("Icon")
    private String icon;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Cost")
    public Integer getCost() {
        return cost;
    }

    @JsonProperty("Cost")
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @JsonProperty("Rarity")
    public Integer getRarity() {
        return rarity;
    }

    @JsonProperty("Rarity")
    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    @JsonProperty("Purchasable")
    public Boolean getPurchasable() {
        return purchasable;
    }

    @JsonProperty("Purchasable")
    public void setPurchasable(Boolean purchasable) {
        this.purchasable = purchasable;
    }

    @JsonProperty("Manufacturer")
    public String getManufacturer() {
        return manufacturer;
    }

    @JsonProperty("Manufacturer")
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @JsonProperty("Model")
    public String getModel() {
        return model;
    }

    @JsonProperty("Model")
    public void setModel(String model) {
        this.model = model;
    }

    @JsonProperty("UIName")
    public String getUIName() {
        return uIName;
    }

    @JsonProperty("UIName")
    public void setUIName(String uIName) {
        this.uIName = uIName;
    }

    @JsonProperty("Id")
    public String getId() {
        return id;
    }

    @JsonProperty("Id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("Details")
    public String getDetails() {
        return details;
    }

    @JsonProperty("Details")
    public void setDetails(String details) {
        this.details = details;
    }

    @JsonProperty("Icon")
    public String getIcon() {
        return icon;
    }

    @JsonProperty("Icon")
    public void setIcon(String icon) {
        this.icon = icon;
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