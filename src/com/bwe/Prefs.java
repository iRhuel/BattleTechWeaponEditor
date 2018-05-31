package com.bwe;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Prefs {

    private Preferences preferences;
    private LinkedList<String> columnVisDefaults = new LinkedList<>();
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
        return preferences.getBoolean(key, columnVisDefaults.contains(key));
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
        columnVisDefaults.add("showName");
        columnVisDefaults.add("showManufacturer");
        columnVisDefaults.add("showDamage");
        columnVisDefaults.add("showInstability");
        columnVisDefaults.add("showAccuracyModifier");
        columnVisDefaults.add("showCriticalChangeMultiplier");
        columnVisDefaults.add("showHeatGenerated");
        columnVisDefaults.add("showBonusValueA");
        columnVisDefaults.add("showBonusValueB");
        columnVisDefaults.add("showDmgPerTon");
        columnVisDefaults.add("showDmgPerHeat");
        columnVisDefaults.add("showStbPerTon");
        columnVisDefaults.add("showStbPerHeat");
        columnVisDefaults.add("showCost");
        columnVisDefaults.add("showRarity");
        columnVisDefaults.add("showPurchasable");
    }

    private void setColWidthDefaults() {
        columnWidthDefaults.put("NameWidth", 90);
        columnWidthDefaults.put("ManufacturerWidth", 90);
        columnWidthDefaults.put("BonusValueAWidth", 85);
        columnWidthDefaults.put("BonusValueBWidth", 85);
    }

    public void clear() throws BackingStoreException {
        preferences.clear();
    }
}
