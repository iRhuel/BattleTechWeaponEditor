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
        "durationData",
        "targetingData",
        "effectType",
        "Description",
        "nature",
        "statisticData",
        "tagData",
        "floatieData",
        "actorBurningData",
        "vfxData",
        "instantModData",
        "poorlyMaintainedEffectData"
})
public class StatusEffect {

    @JsonProperty("durationData")
    private DurationData durationData;
    @JsonProperty("targetingData")
    private TargetingData targetingData;
    @JsonProperty("effectType")
    private String effectType;
    @JsonProperty("Description")
    private Description_ description;
    @JsonProperty("nature")
    private String nature;
    @JsonProperty("statisticData")
    private StatisticData statisticData;
    @JsonProperty("tagData")
    private Object tagData;
    @JsonProperty("floatieData")
    private Object floatieData;
    @JsonProperty("actorBurningData")
    private Object actorBurningData;
    @JsonProperty("vfxData")
    private Object vfxData;
    @JsonProperty("instantModData")
    private Object instantModData;
    @JsonProperty("poorlyMaintainedEffectData")
    private Object poorlyMaintainedEffectData;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("durationData")
    public DurationData getDurationData() {
        return durationData;
    }

    @JsonProperty("durationData")
    public void setDurationData(DurationData durationData) {
        this.durationData = durationData;
    }

    @JsonProperty("targetingData")
    public TargetingData getTargetingData() {
        return targetingData;
    }

    @JsonProperty("targetingData")
    public void setTargetingData(TargetingData targetingData) {
        this.targetingData = targetingData;
    }

    @JsonProperty("effectType")
    public String getEffectType() {
        return effectType;
    }

    @JsonProperty("effectType")
    public void setEffectType(String effectType) {
        this.effectType = effectType;
    }

    @JsonProperty("Description")
    public Description_ getDescription() {
        return description;
    }

    @JsonProperty("Description")
    public void setDescription(Description_ description) {
        this.description = description;
    }

    @JsonProperty("nature")
    public String getNature() {
        return nature;
    }

    @JsonProperty("nature")
    public void setNature(String nature) {
        this.nature = nature;
    }

    @JsonProperty("statisticData")
    public StatisticData getStatisticData() {
        return statisticData;
    }

    @JsonProperty("statisticData")
    public void setStatisticData(StatisticData statisticData) {
        this.statisticData = statisticData;
    }

    @JsonProperty("tagData")
    public Object getTagData() {
        return tagData;
    }

    @JsonProperty("tagData")
    public void setTagData(Object tagData) {
        this.tagData = tagData;
    }

    @JsonProperty("floatieData")
    public Object getFloatieData() {
        return floatieData;
    }

    @JsonProperty("floatieData")
    public void setFloatieData(Object floatieData) {
        this.floatieData = floatieData;
    }

    @JsonProperty("actorBurningData")
    public Object getActorBurningData() {
        return actorBurningData;
    }

    @JsonProperty("actorBurningData")
    public void setActorBurningData(Object actorBurningData) {
        this.actorBurningData = actorBurningData;
    }

    @JsonProperty("vfxData")
    public Object getVfxData() {
        return vfxData;
    }

    @JsonProperty("vfxData")
    public void setVfxData(Object vfxData) {
        this.vfxData = vfxData;
    }

    @JsonProperty("instantModData")
    public Object getInstantModData() {
        return instantModData;
    }

    @JsonProperty("instantModData")
    public void setInstantModData(Object instantModData) {
        this.instantModData = instantModData;
    }

    @JsonProperty("poorlyMaintainedEffectData")
    public Object getPoorlyMaintainedEffectData() {
        return poorlyMaintainedEffectData;
    }

    @JsonProperty("poorlyMaintainedEffectData")
    public void setPoorlyMaintainedEffectData(Object poorlyMaintainedEffectData) {
        this.poorlyMaintainedEffectData = poorlyMaintainedEffectData;
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