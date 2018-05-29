package com.bwe;

import java.util.HashMap;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Prefs {

    private Preferences preferences;
    private HashMap<String, Boolean> columnVisDefaults = new HashMap<>();
    private HashMap<String, Integer> columnWidthDefaults = new HashMap<>();

    public Prefs() {
        preferences = Preferences.userRoot().node(this.getClass().getName());
        setColVisDefaults();
        setColWidthDefaults();
    }

    public String getWorkingDir() {
        return preferences.get("workingDir", "C:\\");
    }

    public String getBackupDir() {
        return preferences.get("backupDir", "C:\\");
    }

    public boolean getShowCol(String key) {
        return preferences.getBoolean(key, columnVisDefaults.getOrDefault(key, true));
    }

    public int getColWidth(String key) {
        return preferences.getInt(key, columnWidthDefaults.getOrDefault(key, 50));
    }

    public void setWorkingDir(String path) {
        preferences.put("workingDir", path);
    }

    public void setBackupDir(String path) {
        preferences.put("backupDir", path);
    }

    public void setShowCol(String key, boolean flag) {
        preferences.putBoolean(key, flag);
    }

    public void setColWidth(String key, int width) {
        preferences.putInt(key, width);
    }

    private void setColVisDefaults() {
        columnVisDefaults.put("showName", true);
        columnVisDefaults.put("showId", false);
        columnVisDefaults.put("showIcon", false);
        columnVisDefaults.put("showManufacturer", true);
        columnVisDefaults.put("showModel", false);
        columnVisDefaults.put("showDetails", false);
        columnVisDefaults.put("showDamage", true);
        columnVisDefaults.put("showDamagePerVolley", false);
        columnVisDefaults.put("showInstability", true);
        columnVisDefaults.put("showInstabilityPerVolley", false);
        columnVisDefaults.put("showHeatDamage", false);
        columnVisDefaults.put("showStartingAmmoCapacity", false);
        columnVisDefaults.put("showOverheatedDamageMultiplier", false);
        columnVisDefaults.put("showEvasiveDamageMultiplier", false);
        columnVisDefaults.put("showEvasivePipsIgnored", false);
        columnVisDefaults.put("showDamageVariance", false);
        columnVisDefaults.put("showAccuracyModifier", true);
        columnVisDefaults.put("showCriticalChanceMultiplier", true);
        columnVisDefaults.put("showHeatGenerated", true);
        columnVisDefaults.put("showTonnage", false);
        columnVisDefaults.put("showBonusValueA", true);
        columnVisDefaults.put("showBonusValueB", true);
        columnVisDefaults.put("showAOECapable", false);
        columnVisDefaults.put("showIndirectFireCapable", false);
        columnVisDefaults.put("showDmgPerTon", true);
        columnVisDefaults.put("showDmgPerHeat", true);
        columnVisDefaults.put("showStbPerTon", true);
        columnVisDefaults.put("showStbPerHeat", true);
        columnVisDefaults.put("showMinRange", false);
        columnVisDefaults.put("showMaxRange", false);
        columnVisDefaults.put("showRangeSplit", false);
        columnVisDefaults.put("showRefireModifier", false);
        columnVisDefaults.put("showShotsWhenFired", false);
        columnVisDefaults.put("showAttackRecoil", false);
        columnVisDefaults.put("showWeaponEffectID", false);
        columnVisDefaults.put("showCost", true);
        columnVisDefaults.put("showRarity", true);
        columnVisDefaults.put("showPurchasable", true);
        columnVisDefaults.put("showInventorySize", false);
        columnVisDefaults.put("showComponentType", false);
        columnVisDefaults.put("showComponentSubType", false);
        columnVisDefaults.put("showPrefabIdentifier", false);
        columnVisDefaults.put("showBattleValue", false);
        columnVisDefaults.put("showAllowedLocations", false);
        columnVisDefaults.put("showDisallowedLocations", false);
        columnVisDefaults.put("showCriticalComponent", false);
        columnVisDefaults.put("showStatusEffects", false);
        columnVisDefaults.put("showComponentTags", false);
        columnVisDefaults.put("showCategory", false);
        columnVisDefaults.put("showType", false);
        columnVisDefaults.put("showWeaponSubType", false);
        columnVisDefaults.put("showAmmoCategory", false);
    }

    private void setColWidthDefaults() {
        columnWidthDefaults.put("NameWidth", 90);
        columnWidthDefaults.put("ManufacturerWidth", 100);
        columnWidthDefaults.put("BonusValueAWidth", 85);
        columnWidthDefaults.put("BonusValueBWidth", 85);
    }

    public void clear() throws BackingStoreException {
        preferences.clear();
    }
}
