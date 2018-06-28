package com.bwe.pojo.weapon;

import com.bwe.enums.WeaponSubType;
import com.fasterxml.jackson.annotation.*;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Category",
        "Type",
        "WeaponSubType",
        "MinRange",
        "MaxRange",
        "RangeSplit",
        "AmmoCategory",
        "StartingAmmoCapacity",
        "HeatGenerated",
        "Damage",
        "OverheatedDamageMultiplier",
        "EvasiveDamageMultiplier",
        "EvasivePipsIgnored",
        "DamageVariance",
        "HeatDamage",
        "AccuracyModifier",
        "CriticalChanceMultiplier",
        "AOECapable",
        "IndirectFireCapable",
        "RefireModifier",
        "ShotsWhenFired",
        "ProjectilesPerShot",
        "AttackRecoil",
        "Instability",
        "WeaponEffectID",
        "Description",
        "BonusValueA",
        "BonusValueB",
        "ComponentType",
        "ComponentSubType",
        "PrefabIdentifier",
        "BattleValue",
        "InventorySize",
        "Tonnage",
        "AllowedLocations",
        "DisallowedLocations",
        "CriticalComponent",
        "statusEffects",
        "ComponentTags"
})
public class Weapon {

    @JsonProperty("Category")
    private String category;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("WeaponSubType")
    private String weaponSubType;
    @JsonProperty("MinRange")
    private Integer minRange;
    @JsonProperty("MaxRange")
    private Integer maxRange;
    @JsonProperty("RangeSplit")
    private List<Integer> rangeSplit = null;
    @JsonProperty("AmmoCategory")
    private String ammoCategory;
    @JsonProperty("StartingAmmoCapacity")
    private Integer startingAmmoCapacity;
    @JsonProperty("HeatGenerated")
    private Integer heatGenerated;
    @JsonProperty("Damage")
    private Integer damage;
    @JsonProperty("OverheatedDamageMultiplier")
    private Integer overheatedDamageMultiplier;
    @JsonProperty("EvasiveDamageMultiplier")
    private Integer evasiveDamageMultiplier;
    @JsonProperty("EvasivePipsIgnored")
    private Integer evasivePipsIgnored;
    @JsonProperty("DamageVariance")
    private Integer damageVariance;
    @JsonProperty("HeatDamage")
    private Integer heatDamage;
    @JsonProperty("AccuracyModifier")
    private Integer accuracyModifier;
    @JsonProperty("CriticalChanceMultiplier")
    private Double criticalChanceMultiplier;
    @JsonProperty("AOECapable")
    private Boolean aOECapable;
    @JsonProperty("IndirectFireCapable")
    private Boolean indirectFireCapable;
    @JsonProperty("RefireModifier")
    private Integer refireModifier;
    @JsonProperty("ShotsWhenFired")
    private Integer shotsWhenFired;
    @JsonProperty("ProjectilesPerShot")
    private Integer projectilesPerShot;
    @JsonProperty("AttackRecoil")
    private Integer attackRecoil;
    @JsonProperty("Instability")
    private Integer instability;
    @JsonProperty("WeaponEffectID")
    private String weaponEffectID;
    @JsonProperty("Description")
    private Description description;
    @JsonProperty("BonusValueA")
    private String bonusValueA;
    @JsonProperty("BonusValueB")
    private String bonusValueB;
    @JsonProperty("ComponentType")
    private String componentType;
    @JsonProperty("ComponentSubType")
    private String componentSubType;
    @JsonProperty("PrefabIdentifier")
    private String prefabIdentifier;
    @JsonProperty("BattleValue")
    private Integer battleValue;
    @JsonProperty("InventorySize")
    private Integer inventorySize;
    @JsonProperty("Tonnage")
    private Double tonnage;
    @JsonProperty("AllowedLocations")
    private String allowedLocations;
    @JsonProperty("DisallowedLocations")
    private String disallowedLocations;
    @JsonProperty("CriticalComponent")
    private Boolean criticalComponent;
    @JsonProperty("statusEffects")
    private List<Object> statusEffects = null;
    @JsonProperty("ComponentTags")
    private ComponentTags componentTags;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonIgnore
    public void applyBonuses() {
        for (String subString : bonusValueA.split("\\|"))
            applyBonus(subString);

        for (String subString : bonusValueB.split("\\|"))
            applyBonus(subString);
    }

    @JsonIgnore
    public void removeBonuses() {
        for (String subString : bonusValueA.split("\\|"))
            removeBonus(subString);

        for (String subString : bonusValueB.split("\\|"))
            removeBonus(subString);
    }

    @JsonIgnore
    public void adjustBonus(String newBonus, char whichBonus) {
        if (whichBonus == 'A') {
            for (String bonus : bonusValueA.split("\\|"))
                removeBonus(bonus);
            bonusValueA = newBonus;
        }

        if (whichBonus == 'B') {
            for (String bonus : bonusValueB.split("\\|"))
                removeBonus(bonus);
            bonusValueB = newBonus;
        }

        for (String bonus : newBonus.split("\\|"))
            applyBonus(bonus);
    }

    @JsonIgnore
    private void applyBonus(String bonus) {
        if (bonus.equals(""))
            return;

        if (bonus.charAt(0) == '-')
            subtractBonusValue(bonus);

        else
            addBonusValue(bonus);
    }

    @JsonIgnore
    private void removeBonus(String bonus) {
        if (bonus.equals(""))
            return;

        if (bonus.charAt(0) == '-')
            addBonusValue(bonus);

        else
            subtractBonusValue(bonus);
    }

    @JsonIgnore
    private void addBonusValue(String bonus) {
        if (bonus.contains(" Dmg.")) {
            damage += bonusValue(bonus);
        }
        if (bonus.contains(" Stb.Dmg.")) {
            instability += bonusValue(bonus);
        }
        if (bonus.contains(" Acc.")) {
            accuracyModifier -= bonusValue(bonus);
        }
        if (bonus.contains(" Crit.")) {
            criticalChanceMultiplier += bonusValue(bonus) / 100D;
        }
        if (bonus.contains(" Heat.")) {
            heatGenerated += bonusValue(bonus);
        }
        if (bonus.contains(" Dmg. (H)"))
            heatDamage += bonusValue(bonus);
    }

    @JsonIgnore
    private void subtractBonusValue(String bonus) {
        if (bonus.contains(" Dmg.")) {
            damage -= bonusValue(bonus);
        }
        if (bonus.contains(" Stb.Dmg.")) {
            instability -= bonusValue(bonus);
        }
        if (bonus.contains(" Acc.")) {
            accuracyModifier += bonusValue(bonus);
        }
        if (bonus.contains(" Crit.")) {
            criticalChanceMultiplier -= bonusValue(bonus) / 100D;
        }
        if (bonus.contains(" Heat.")) {
            heatGenerated -= bonusValue(bonus);
        }
        if (bonus.contains(" Dmg. (H)"))
            heatDamage -= bonusValue(bonus);
    }

    @JsonIgnore
    private int bonusValue(String bonus) {
        int i = 0;
        while (i < bonus.length() && !Character.isDigit(bonus.charAt(i)))
            i++;
        int j = i;
        while (j < bonus.length() && Character.isDigit(bonus.charAt(j))) {
            j++;
        }
        return Integer.parseInt(bonus.substring(i, j));
    }

    @JsonIgnore
    private String round(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(value);
    }

    @JsonIgnore
    public Integer getDamagePerVolley() {
        if (damage == null || shotsWhenFired == null)
            return null;
        return damage * shotsWhenFired;
    }

    @JsonIgnore
    public Integer getInstabilityPerVolley() {
        if (instability == null || shotsWhenFired == null)
            return null;
        return instability * shotsWhenFired;
    }

    @JsonIgnore
    public String getDmgPerTon() {
        if (getDamagePerVolley() == null || tonnage == null || tonnage <= 0)
            return "N/A";
        switch (weaponSubType) {
            case "AC2":
            case "AC5":
            case "SRM2":
            case "SRM4":
            case "SRM6":
            case "LRM5":
            case "LRM10":
            case "MachineGun":
                return round((double)getDamagePerVolley() / (tonnage + 1D));
            case "AC10":
            case "AC20":
            case "Gauss":
            case "LRM15":
            case "LRM20":
                return round((double)getDamagePerVolley() / (tonnage + 2D));
        }
        return round((double)getDamagePerVolley() / tonnage);
    }

    @JsonIgnore
    public String getDmgPerHeat() {
            if (getDamagePerVolley() == null || heatGenerated == null)
                return "N/A";
            return round((double)getDamagePerVolley() / heatGenerated);
    }

    @JsonIgnore
    public String getStbPerTon() {
        if (getInstabilityPerVolley() == null || tonnage == null || tonnage <= 0)
            return "N/A";
        return round((double)getInstabilityPerVolley() / tonnage);
    }

    @JsonIgnore
    public String getStbPerHeat() {
        if (getInstabilityPerVolley() == null || heatGenerated == null)
            return "N/A";
        return round((double)getInstabilityPerVolley() / heatGenerated);
    }

    @JsonIgnore
    public String getName() {
        return description.getName();
    }

    @JsonIgnore
    public String getManufacturer() {
        return description.getManufacturer();
    }

    @JsonIgnore
    public Integer getCost() {
        return description.getCost();
    }

    @JsonIgnore
    public Integer getRarity() {
        return description.getRarity();
    }

    @JsonIgnore
    public Boolean getPurchasable() {
        return description.getPurchasable();
    }

    @JsonIgnore
    public Integer getShortRangeSplit() {
        return rangeSplit.get(0);
    }

    @JsonIgnore
    public Integer getMidRangeSplit() {
        return rangeSplit.get(1);
    }

    @JsonIgnore
    public Integer getLongRangeSplit() {
        return rangeSplit.get(2);
    }

    @JsonIgnore
    public void setShortRangeSplit(Integer integer) {
        rangeSplit.set(0, integer);
    }

    @JsonIgnore
    public void setMidRangeSplit(Integer integer) {
        rangeSplit.set(1, integer);
    }

    @JsonIgnore
    public void setLongRangeSplit(Integer integer) {
        rangeSplit.set(2, integer);
    }

    @JsonProperty("Category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("Category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    @JsonProperty("Type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("WeaponSubType")
    public String getWeaponSubType() {
        return weaponSubType;
    }

    @JsonProperty("WeaponSubType")
    public void setWeaponSubType(String weaponSubType) {
        this.weaponSubType = weaponSubType;
    }

    @JsonProperty("MinRange")
    public Integer getMinRange() {
        return minRange;
    }

    @JsonProperty("MinRange")
    public void setMinRange(Integer minRange) {
        this.minRange = minRange;
    }

    @JsonProperty("MaxRange")
    public Integer getMaxRange() {
        return maxRange;
    }

    @JsonProperty("MaxRange")
    public void setMaxRange(Integer maxRange) {
        this.maxRange = maxRange;
    }

    @JsonProperty("RangeSplit")
    public List<Integer> getRangeSplit() {
        return rangeSplit;
    }

    @JsonProperty("RangeSplit")
    public void setRangeSplit(List<Integer> rangeSplit) {
        this.rangeSplit = rangeSplit;
    }

    @JsonProperty("AmmoCategory")
    public String getAmmoCategory() {
        return ammoCategory;
    }

    @JsonProperty("AmmoCategory")
    public void setAmmoCategory(String ammoCategory) {
        this.ammoCategory = ammoCategory;
    }

    @JsonProperty("StartingAmmoCapacity")
    public Integer getStartingAmmoCapacity() {
        return startingAmmoCapacity;
    }

    @JsonProperty("StartingAmmoCapacity")
    public void setStartingAmmoCapacity(Integer startingAmmoCapacity) {
        this.startingAmmoCapacity = startingAmmoCapacity;
    }

    @JsonProperty("HeatGenerated")
    public Integer getHeatGenerated() {
        return heatGenerated;
    }

    @JsonProperty("HeatGenerated")
    public void setHeatGenerated(Integer heatGenerated) {
        this.heatGenerated = heatGenerated;
    }

    @JsonProperty("Damage")
    public Integer getDamage() {
        return damage;
    }

    @JsonProperty("Damage")
    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    @JsonProperty("OverheatedDamageMultiplier")
    public Integer getOverheatedDamageMultiplier() {
        return overheatedDamageMultiplier;
    }

    @JsonProperty("OverheatedDamageMultiplier")
    public void setOverheatedDamageMultiplier(Integer overheatedDamageMultiplier) {
        this.overheatedDamageMultiplier = overheatedDamageMultiplier;
    }

    @JsonProperty("EvasiveDamageMultiplier")
    public Integer getEvasiveDamageMultiplier() {
        return evasiveDamageMultiplier;
    }

    @JsonProperty("EvasiveDamageMultiplier")
    public void setEvasiveDamageMultiplier(Integer evasiveDamageMultiplier) {
        this.evasiveDamageMultiplier = evasiveDamageMultiplier;
    }

    @JsonProperty("EvasivePipsIgnored")
    public Integer getEvasivePipsIgnored() {
        return evasivePipsIgnored;
    }

    @JsonProperty("EvasivePipsIgnored")
    public void setEvasivePipsIgnored(Integer evasivePipsIgnored) {
        this.evasivePipsIgnored = evasivePipsIgnored;
    }

    @JsonProperty("DamageVariance")
    public Integer getDamageVariance() {
        return damageVariance;
    }

    @JsonProperty("DamageVariance")
    public void setDamageVariance(Integer damageVariance) {
        this.damageVariance = damageVariance;
    }

    @JsonProperty("HeatDamage")
    public Integer getHeatDamage() {
        return heatDamage;
    }

    @JsonProperty("HeatDamage")
    public void setHeatDamage(Integer heatDamage) {
        this.heatDamage = heatDamage;
    }

    @JsonProperty("AccuracyModifier")
    public Integer getAccuracyModifier() {
        return accuracyModifier;
    }

    @JsonProperty("AccuracyModifier")
    public void setAccuracyModifier(Integer accuracyModifier) {
        this.accuracyModifier = accuracyModifier;
    }

    @JsonProperty("CriticalChanceMultiplier")
    public Double getCriticalChanceMultiplier() {
        return criticalChanceMultiplier;
    }

    @JsonProperty("CriticalChanceMultiplier")
    public void setCriticalChanceMultiplier(Double criticalChanceMultiplier) {
        this.criticalChanceMultiplier = criticalChanceMultiplier;
    }

    @JsonProperty("AOECapable")
    public Boolean getAOECapable() {
        return aOECapable;
    }

    @JsonProperty("AOECapable")
    public void setAOECapable(Boolean aOECapable) {
        this.aOECapable = aOECapable;
    }

    @JsonProperty("IndirectFireCapable")
    public Boolean getIndirectFireCapable() {
        return indirectFireCapable;
    }

    @JsonProperty("IndirectFireCapable")
    public void setIndirectFireCapable(Boolean indirectFireCapable) {
        this.indirectFireCapable = indirectFireCapable;
    }

    @JsonProperty("RefireModifier")
    public Integer getRefireModifier() {
        return refireModifier;
    }

    @JsonProperty("RefireModifier")
    public void setRefireModifier(Integer refireModifier) {
        this.refireModifier = refireModifier;
    }

    @JsonProperty("ShotsWhenFired")
    public Integer getShotsWhenFired() {
        return shotsWhenFired;
    }

    @JsonProperty("ShotsWhenFired")
    public void setShotsWhenFired(Integer shotsWhenFired) {
        this.shotsWhenFired = shotsWhenFired;
    }

    @JsonProperty("ProjectilesPerShot")
    public Integer getProjectilesPerShot() {
        return projectilesPerShot;
    }

    @JsonProperty("ProjectilesPerShot")
    public void setProjectilesPerShot(Integer projectilesPerShot) {
        this.projectilesPerShot = projectilesPerShot;
    }

    @JsonProperty("AttackRecoil")
    public Integer getAttackRecoil() {
        return attackRecoil;
    }

    @JsonProperty("AttackRecoil")
    public void setAttackRecoil(Integer attackRecoil) {
        this.attackRecoil = attackRecoil;
    }

    @JsonProperty("Instability")
    public Integer getInstability() {
        return instability;
    }

    @JsonProperty("Instability")
    public void setInstability(Integer instability) {
        this.instability = instability;
    }

    @JsonProperty("WeaponEffectID")
    public String getWeaponEffectID() {
        return weaponEffectID;
    }

    @JsonProperty("WeaponEffectID")
    public void setWeaponEffectID(String weaponEffectID) {
        this.weaponEffectID = weaponEffectID;
    }

    @JsonProperty("Description")
    public Description getDescription() {
        return description;
    }

    @JsonProperty("Description")
    public void setDescription(Description description) {
        this.description = description;
    }

    @JsonProperty("BonusValueA")
    public String getBonusValueA() {
        return bonusValueA;
    }

    @JsonProperty("BonusValueA")
    public void setBonusValueA(String bonusValueA) {
        this.bonusValueA = bonusValueA;
    }

    @JsonProperty("BonusValueB")
    public String getBonusValueB() {
        return bonusValueB;
    }

    @JsonProperty("BonusValueB")
    public void setBonusValueB(String bonusValueB) {
        this.bonusValueB = bonusValueB;
    }

    @JsonProperty("ComponentType")
    public String getComponentType() {
        return componentType;
    }

    @JsonProperty("ComponentType")
    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    @JsonProperty("ComponentSubType")
    public String getComponentSubType() {
        return componentSubType;
    }

    @JsonProperty("ComponentSubType")
    public void setComponentSubType(String componentSubType) {
        this.componentSubType = componentSubType;
    }

    @JsonProperty("PrefabIdentifier")
    public String getPrefabIdentifier() {
        return prefabIdentifier;
    }

    @JsonProperty("PrefabIdentifier")
    public void setPrefabIdentifier(String prefabIdentifier) {
        this.prefabIdentifier = prefabIdentifier;
    }

    @JsonProperty("BattleValue")
    public Integer getBattleValue() {
        return battleValue;
    }

    @JsonProperty("BattleValue")
    public void setBattleValue(Integer battleValue) {
        this.battleValue = battleValue;
    }

    @JsonProperty("InventorySize")
    public Integer getInventorySize() {
        return inventorySize;
    }

    @JsonProperty("InventorySize")
    public void setInventorySize(Integer inventorySize) {
        this.inventorySize = inventorySize;
    }

    @JsonProperty("Tonnage")
    public Double getTonnage() {
        return tonnage;
    }

    @JsonProperty("Tonnage")
    public void setTonnage(Double tonnage) {
        this.tonnage = tonnage;
    }

    @JsonProperty("AllowedLocations")
    public String getAllowedLocations() {
        return allowedLocations;
    }

    @JsonProperty("AllowedLocations")
    public void setAllowedLocations(String allowedLocations) {
        this.allowedLocations = allowedLocations;
    }

    @JsonProperty("DisallowedLocations")
    public String getDisallowedLocations() {
        return disallowedLocations;
    }

    @JsonProperty("DisallowedLocations")
    public void setDisallowedLocations(String disallowedLocations) {
        this.disallowedLocations = disallowedLocations;
    }

    @JsonProperty("CriticalComponent")
    public Boolean getCriticalComponent() {
        return criticalComponent;
    }

    @JsonProperty("CriticalComponent")
    public void setCriticalComponent(Boolean criticalComponent) {
        this.criticalComponent = criticalComponent;
    }

    @JsonProperty("statusEffects")
    public List<Object> getStatusEffects() {
        return statusEffects;
    }

    @JsonProperty("statusEffects")
    public void setStatusEffects(List<Object> statusEffects) {
        this.statusEffects = statusEffects;
    }

    @JsonProperty("ComponentTags")
    public ComponentTags getComponentTags() {
        return componentTags;
    }

    @JsonProperty("ComponentTags")
    public void setComponentTags(ComponentTags componentTags) {
        this.componentTags = componentTags;
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