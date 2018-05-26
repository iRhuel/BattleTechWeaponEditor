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
        "effectTriggerType",
        "triggerLimit",
        "extendDurationOnTrigger",
        "specialRules",
        "effectTargetType",
        "range",
        "forcePathRebuild",
        "forceVisRebuild",
        "showInTargetPreview",
        "showInStatusPanel"
})
public class TargetingData {

    @JsonProperty("effectTriggerType")
    private String effectTriggerType;
    @JsonProperty("triggerLimit")
    private Integer triggerLimit;
    @JsonProperty("extendDurationOnTrigger")
    private Integer extendDurationOnTrigger;
    @JsonProperty("specialRules")
    private String specialRules;
    @JsonProperty("effectTargetType")
    private String effectTargetType;
    @JsonProperty("range")
    private Integer range;
    @JsonProperty("forcePathRebuild")
    private Boolean forcePathRebuild;
    @JsonProperty("forceVisRebuild")
    private Boolean forceVisRebuild;
    @JsonProperty("showInTargetPreview")
    private Boolean showInTargetPreview;
    @JsonProperty("showInStatusPanel")
    private Boolean showInStatusPanel;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("effectTriggerType")
    public String getEffectTriggerType() {
        return effectTriggerType;
    }

    @JsonProperty("effectTriggerType")
    public void setEffectTriggerType(String effectTriggerType) {
        this.effectTriggerType = effectTriggerType;
    }

    @JsonProperty("triggerLimit")
    public Integer getTriggerLimit() {
        return triggerLimit;
    }

    @JsonProperty("triggerLimit")
    public void setTriggerLimit(Integer triggerLimit) {
        this.triggerLimit = triggerLimit;
    }

    @JsonProperty("extendDurationOnTrigger")
    public Integer getExtendDurationOnTrigger() {
        return extendDurationOnTrigger;
    }

    @JsonProperty("extendDurationOnTrigger")
    public void setExtendDurationOnTrigger(Integer extendDurationOnTrigger) {
        this.extendDurationOnTrigger = extendDurationOnTrigger;
    }

    @JsonProperty("specialRules")
    public String getSpecialRules() {
        return specialRules;
    }

    @JsonProperty("specialRules")
    public void setSpecialRules(String specialRules) {
        this.specialRules = specialRules;
    }

    @JsonProperty("effectTargetType")
    public String getEffectTargetType() {
        return effectTargetType;
    }

    @JsonProperty("effectTargetType")
    public void setEffectTargetType(String effectTargetType) {
        this.effectTargetType = effectTargetType;
    }

    @JsonProperty("range")
    public Integer getRange() {
        return range;
    }

    @JsonProperty("range")
    public void setRange(Integer range) {
        this.range = range;
    }

    @JsonProperty("forcePathRebuild")
    public Boolean getForcePathRebuild() {
        return forcePathRebuild;
    }

    @JsonProperty("forcePathRebuild")
    public void setForcePathRebuild(Boolean forcePathRebuild) {
        this.forcePathRebuild = forcePathRebuild;
    }

    @JsonProperty("forceVisRebuild")
    public Boolean getForceVisRebuild() {
        return forceVisRebuild;
    }

    @JsonProperty("forceVisRebuild")
    public void setForceVisRebuild(Boolean forceVisRebuild) {
        this.forceVisRebuild = forceVisRebuild;
    }

    @JsonProperty("showInTargetPreview")
    public Boolean getShowInTargetPreview() {
        return showInTargetPreview;
    }

    @JsonProperty("showInTargetPreview")
    public void setShowInTargetPreview(Boolean showInTargetPreview) {
        this.showInTargetPreview = showInTargetPreview;
    }

    @JsonProperty("showInStatusPanel")
    public Boolean getShowInStatusPanel() {
        return showInStatusPanel;
    }

    @JsonProperty("showInStatusPanel")
    public void setShowInStatusPanel(Boolean showInStatusPanel) {
        this.showInStatusPanel = showInStatusPanel;
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