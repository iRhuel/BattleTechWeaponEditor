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
        "duration",
        "ticksOnActivations",
        "useActivationsOfTarget",
        "ticksOnEndOfRound",
        "ticksOnMovements",
        "stackLimit",
        "clearedWhenAttacked"
})
public class DurationData {

    @JsonProperty("duration")
    private Integer duration;
    @JsonProperty("ticksOnActivations")
    private Boolean ticksOnActivations;
    @JsonProperty("useActivationsOfTarget")
    private Boolean useActivationsOfTarget;
    @JsonProperty("ticksOnEndOfRound")
    private Boolean ticksOnEndOfRound;
    @JsonProperty("ticksOnMovements")
    private Boolean ticksOnMovements;
    @JsonProperty("stackLimit")
    private Integer stackLimit;
    @JsonProperty("clearedWhenAttacked")
    private Boolean clearedWhenAttacked;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("duration")
    public Integer getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @JsonProperty("ticksOnActivations")
    public Boolean getTicksOnActivations() {
        return ticksOnActivations;
    }

    @JsonProperty("ticksOnActivations")
    public void setTicksOnActivations(Boolean ticksOnActivations) {
        this.ticksOnActivations = ticksOnActivations;
    }

    @JsonProperty("useActivationsOfTarget")
    public Boolean getUseActivationsOfTarget() {
        return useActivationsOfTarget;
    }

    @JsonProperty("useActivationsOfTarget")
    public void setUseActivationsOfTarget(Boolean useActivationsOfTarget) {
        this.useActivationsOfTarget = useActivationsOfTarget;
    }

    @JsonProperty("ticksOnEndOfRound")
    public Boolean getTicksOnEndOfRound() {
        return ticksOnEndOfRound;
    }

    @JsonProperty("ticksOnEndOfRound")
    public void setTicksOnEndOfRound(Boolean ticksOnEndOfRound) {
        this.ticksOnEndOfRound = ticksOnEndOfRound;
    }

    @JsonProperty("ticksOnMovements")
    public Boolean getTicksOnMovements() {
        return ticksOnMovements;
    }

    @JsonProperty("ticksOnMovements")
    public void setTicksOnMovements(Boolean ticksOnMovements) {
        this.ticksOnMovements = ticksOnMovements;
    }

    @JsonProperty("stackLimit")
    public Integer getStackLimit() {
        return stackLimit;
    }

    @JsonProperty("stackLimit")
    public void setStackLimit(Integer stackLimit) {
        this.stackLimit = stackLimit;
    }

    @JsonProperty("clearedWhenAttacked")
    public Boolean getClearedWhenAttacked() {
        return clearedWhenAttacked;
    }

    @JsonProperty("clearedWhenAttacked")
    public void setClearedWhenAttacked(Boolean clearedWhenAttacked) {
        this.clearedWhenAttacked = clearedWhenAttacked;
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