package com.bwe;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Prefs {

    private Preferences preferences;

    private String workingDir = "workingDir";
    private String backupDir = "backupDir";

    private String[] shouldShowCol = {
            "showNameCol", "showMfrCol", "showDmgCol", "showStbCol", "showHDmgCol",
            "showAccModCol", "showCritCol", "showHeatCol", "showTonsCol", "showBonusACol",
            "showBonusBCol", "showDmgPerTonCol", "showDmgPerHeatCol", "showStbPerTonCol", "showStbPerHeatCol",
            "showMinRangeCol", "showMaxRangeCol", "showRefireModCol", "showShotsWhenFiredCol", "showAttackRecoilCol",
            "showCostCol", "showRarityCol", "showPurchasableCol", "showInventorySizeCol"
    };

    public Prefs() {
        preferences = Preferences.userRoot().node(this.getClass().getName());
    }

    public void clear() throws BackingStoreException {
            preferences.clear();
    }

    public String getWorkingDir() {
        return preferences.get(workingDir, "C:\\");
    }

    public String getBackupDir() {
        return preferences.get(backupDir, "C:\\");
    }

    public boolean getShowCol(int colNum) {
        return preferences.getBoolean(shouldShowCol[colNum], true);
    }

    public void setWorkingDir(String path) {
        preferences.put(workingDir, path);
    }

    public void setBackupDir(String path) {
        preferences.put(backupDir, path);
    }

    public void setShowCol(int colNum, boolean showCol) {
        preferences.putBoolean(shouldShowCol[colNum], showCol);
    }
}
