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
        "appliesEachTick",
        "effectsPersistAfterDestruction",
        "statName",
        "operation",
        "modValue",
        "modType",
        "additionalRules",
        "targetCollection",
        "targetWeaponCategory",
        "targetWeaponType",
        "targetAmmoCategory",
        "targetWeaponSubType"
})
public class StatisticData {

    @JsonProperty("appliesEachTick")
    private Boolean appliesEachTick;
    @JsonProperty("effectsPersistAfterDestruction")
    private Boolean effectsPersistAfterDestruction;
    @JsonProperty("statName")
    private String statName;
    @JsonProperty("operation")
    private String operation;
    @JsonProperty("modValue")
    private String modValue;
    @JsonProperty("modType")
    private String modType;
    @JsonProperty("additionalRules")
    private String additionalRules;
    @JsonProperty("targetCollection")
    private String targetCollection;
    @JsonProperty("targetWeaponCategory")
    private String targetWeaponCategory;
    @JsonProperty("targetWeaponType")
    private String targetWeaponType;
    @JsonProperty("targetAmmoCategory")
    private String targetAmmoCategory;
    @JsonProperty("targetWeaponSubType")
    private String targetWeaponSubType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("appliesEachTick")
    public Boolean getAppliesEachTick() {
        return appliesEachTick;
    }

    @JsonProperty("appliesEachTick")
    public void setAppliesEachTick(Boolean appliesEachTick) {
        this.appliesEachTick = appliesEachTick;
    }

    @JsonProperty("effectsPersistAfterDestruction")
    public Boolean getEffectsPersistAfterDestruction() {
        return effectsPersistAfterDestruction;
    }

    @JsonProperty("effectsPersistAfterDestruction")
    public void setEffectsPersistAfterDestruction(Boolean effectsPersistAfterDestruction) {
        this.effectsPersistAfterDestruction = effectsPersistAfterDestruction;
    }

    @JsonProperty("statName")
    public String getStatName() {
        return statName;
    }

    @JsonProperty("statName")
    public void setStatName(String statName) {
        this.statName = statName;
    }

    @JsonProperty("operation")
    public String getOperation() {
        return operation;
    }

    @JsonProperty("operation")
    public void setOperation(String operation) {
        this.operation = operation;
    }

    @JsonProperty("modValue")
    public String getModValue() {
        return modValue;
    }

    @JsonProperty("modValue")
    public void setModValue(String modValue) {
        this.modValue = modValue;
    }

    @JsonProperty("modType")
    public String getModType() {
        return modType;
    }

    @JsonProperty("modType")
    public void setModType(String modType) {
        this.modType = modType;
    }

    @JsonProperty("additionalRules")
    public String getAdditionalRules() {
        return additionalRules;
    }

    @JsonProperty("additionalRules")
    public void setAdditionalRules(String additionalRules) {
        this.additionalRules = additionalRules;
    }

    @JsonProperty("targetCollection")
    public String getTargetCollection() {
        return targetCollection;
    }

    @JsonProperty("targetCollection")
    public void setTargetCollection(String targetCollection) {
        this.targetCollection = targetCollection;
    }

    @JsonProperty("targetWeaponCategory")
    public String getTargetWeaponCategory() {
        return targetWeaponCategory;
    }

    @JsonProperty("targetWeaponCategory")
    public void setTargetWeaponCategory(String targetWeaponCategory) {
        this.targetWeaponCategory = targetWeaponCategory;
    }

    @JsonProperty("targetWeaponType")
    public String getTargetWeaponType() {
        return targetWeaponType;
    }

    @JsonProperty("targetWeaponType")
    public void setTargetWeaponType(String targetWeaponType) {
        this.targetWeaponType = targetWeaponType;
    }

    @JsonProperty("targetAmmoCategory")
    public String getTargetAmmoCategory() {
        return targetAmmoCategory;
    }

    @JsonProperty("targetAmmoCategory")
    public void setTargetAmmoCategory(String targetAmmoCategory) {
        this.targetAmmoCategory = targetAmmoCategory;
    }

    @JsonProperty("targetWeaponSubType")
    public String getTargetWeaponSubType() {
        return targetWeaponSubType;
    }

    @JsonProperty("targetWeaponSubType")
    public void setTargetWeaponSubType(String targetWeaponSubType) {
        this.targetWeaponSubType = targetWeaponSubType;
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