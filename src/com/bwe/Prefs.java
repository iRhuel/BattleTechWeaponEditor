package com.bwe;

import java.util.prefs.Preferences;

public class Prefs {

    private Preferences preferences;

    private String ID1 = "workingDir";
    private String ID2 = "backupDir";

    public Prefs() {
        preferences = Preferences.userRoot().node(this.getClass().getName());
        preferences.get(ID1, "C:\\");
        preferences.get(ID2, "C:\\");
    }

    public void setPrefs(String dirPath, String bakPath) {
        preferences.put(ID1, dirPath);
        preferences.put(ID2, bakPath);
    }

    public void clear() {
        try {
            preferences.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getWorkingDir() {
        return preferences.get(ID1, "C:\\");
    }

    public String getBackupDir() {
        return preferences.get(ID2, "C:\\");
    }

    public void setWorkingDir(String path) {
        preferences.put(ID1, path);
    }

    public void setBackupDir(String path) {
        preferences.put(ID2, path);
    }
}
