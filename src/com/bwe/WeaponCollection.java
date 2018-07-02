package com.bwe;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.bwe.pojo.weapon.Weapon;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class WeaponCollection {
    private String workingDir;

    private ObjectMapper mapper;
    private ObjectWriter writer;
    private ArrayList<Weapon> weaponArrayList = new ArrayList<>();
    private ArrayList<File> fileArrayList = new ArrayList<>();
    private ArrayList<Weapon> weaponSubList = new ArrayList<>();

    public WeaponCollection(String path) {
        workingDir = path;
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        printer.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        mapper = new ObjectMapper();
        writer = mapper.writer(printer);

        search(new File(workingDir));

        for (File file : fileArrayList) {
            try {
                Weapon wpn = mapper.readValue(file, Weapon.class);
                wpn.setFileName(file.getName());
                wpn.setFilePath(getRelativeFilePath(workingDir, file.getCanonicalPath()).replace(file.getName(), ""));
                weaponArrayList.add(wpn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getRelativeFilePath(String rootString, String fileString) {
        return fileString.replace(rootString + "\\", "");
    }

    /**
     * Recursively populate fileList with all files in folder and subfolders
     *
     * @param baseFile working directory
     */
    private void search(File baseFile) {
        File[] fileList = baseFile.listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                if (file.isDirectory()) {
                    search(file);
                } else if (!file.getName().contains("Template") && file.getName().contains(".json") && file.getName().contains("Weapon_")) {
                    fileArrayList.add(file);
                }
            }
        }
    }

    @Deprecated
    private boolean isOnIgnoreList(File file) {
        String excludes[] = {
                "Weapon_DFAAttack.json",
                "Weapon_Laser_AI_Imaginary.json",
                "Weapon_MeleeAttack.json",
                "WeaponTemplate.json"};

        for (String exclude : excludes)
            if (file.getName().equals(exclude) || !file.getName().endsWith(".json"))
                return true;

        return false;
    }

    public void openFile(Weapon wpn) {
        try {
            Desktop.getDesktop().open(fileArrayList.get(weaponArrayList.indexOf(wpn)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write() {
        try {
            for (int i = 0; i < fileArrayList.size(); i++) {
                if (!fileArrayList.get(i).getName().contains(weaponArrayList.get(i).getDescription().getId()))
                    throw new FileNotFoundException();

                writer.writeValue(new FileOutputStream(fileArrayList.get(i)), weaponArrayList.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backup(File saveFile) {
        JsonNodeFactory factory = JsonNodeFactory.instance;
        ObjectNode parent = factory.objectNode();
        try {
            for (File file : fileArrayList) {
                parent.set(getRelativeFilePath(workingDir, file.getCanonicalPath()), mapper.readTree(file));
            }
            writer.writeValue(saveFile, parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void restore(File restoreFile, String workingDir) {
        try {
            JsonNode jsonNode = mapper.readTree(restoreFile);
            Iterator<String> iterator = jsonNode.fieldNames();
            while (iterator.hasNext()) {
                String name = iterator.next();
                if (jsonNode.get(name) != null) {

                    String[] temp = name.split("\\\\");
                    StringBuilder dirPath = new StringBuilder(workingDir);
                    dirPath.append("\\");
                    for (int i = 0; i < temp.length - 1; i++) {
                        dirPath.append(temp[i]).append("\\");
                    }

                    File dir = new File(dirPath.toString());
                    if (!dir.exists())
                        dir.mkdirs();

                    writer.writeValue(new File(workingDir + "\\" + name), jsonNode.get(name));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void filter(String filterType, String filterSubType, boolean stockOnlyFlag) {
        weaponSubList.clear();
        if (filterType == null)
            filterType = "All";
        switch (filterType) {
            case "All":
                for (Weapon wpn : weaponArrayList) {
                    if (!stockOnlyFlag || wpn.getDescription().getId().contains("STOCK")) {
                        weaponSubList.add(wpn);
                    }
                }
                break;

            case "Category":
                for (Weapon wpn : weaponArrayList) {
                    if (wpn.getCategory().equals(filterSubType) && (!stockOnlyFlag || wpn.getDescription().getId().contains("STOCK"))) {
                        weaponSubList.add(wpn);
                    }
                }
                break;

            case "Type":
                for (Weapon wpn : weaponArrayList) {
                    if (wpn.getType().equals(filterSubType) && (!stockOnlyFlag || wpn.getDescription().getId().contains("STOCK"))) {
                        weaponSubList.add(wpn);
                    }
                }
                break;

            case "WeaponSubType":
                for (Weapon wpn : weaponArrayList) {
                    if (wpn.getWeaponSubType().equals(filterSubType) && (!stockOnlyFlag || wpn.getDescription().getId().contains("STOCK"))) {
                        weaponSubList.add(wpn);
                    }
                }
                break;
        }
        weaponSubList.sort(new IWeaponComparator());
    }

    public void singleEdit(Weapon wpn, String field, String newStr) {
        switch (field) {
            case "Name":
                wpn.getDescription().setName(newStr);
                wpn.getDescription().setUIName(newStr);
                break;

            case "Manufacturer":
                wpn.getDescription().setManufacturer(newStr);
                break;

            case "BonusValueA":
                wpn.adjustBonus(newStr, 'A');
                break;

            case "BonusValueB":
                wpn.adjustBonus(newStr, 'B');
                break;

            case "Category":
                wpn.setCategory(newStr);
                break;

            case "Type":
                wpn.setType(newStr);
                break;

            case "WeaponSubType":
                wpn.setWeaponSubType(newStr);
                break;

            case "AmmoCategory":
                wpn.setAmmoCategory(newStr);
                break;

            case "WeaponEffectID":
                wpn.setWeaponEffectID(newStr);
                break;

            case "Model":
                wpn.getDescription().setModel(newStr);
                break;

            case "Id":
                wpn.getDescription().setId(newStr);
                break;

            case "Details":
                wpn.getDescription().setDetails(newStr);
                break;

            case "ComponentType":
                wpn.setComponentType(newStr);
                break;

            case "ComponentSubType":
                wpn.setComponentSubType(newStr);
                break;

            case "AllowedLocations":
                wpn.setAllowedLocations(newStr);
                break;

            case "DisallowedLocations":
                wpn.setDisallowedLocations(newStr);
                break;
        }
    }

    public void singleEdit(Weapon wpn, String field, Integer newInt) {
        switch (field) {
            case "Damage":
                wpn.removeBonuses();
                wpn.setDamage(newInt);
                wpn.applyBonuses();
                break;

            case "Instability":
                wpn.removeBonuses();
                wpn.setInstability(newInt);
                wpn.applyBonuses();
                break;

            case "HeatDamage":
                wpn.removeBonuses();
                wpn.setHeatDamage(newInt);
                wpn.applyBonuses();
                break;

            case "AccuracyModifier":
                wpn.removeBonuses();
                wpn.setAccuracyModifier(newInt);
                wpn.applyBonuses();
                break;

            case "HeatGenerated":
                wpn.removeBonuses();
                wpn.setHeatGenerated(newInt);
                wpn.applyBonuses();
                break;

            case "MinRange":
                wpn.setMinRange(newInt);
                break;

            case "ShortRangeSplit":
                wpn.setShortRangeSplit(newInt);
                break;

            case "MidRangeSplit":
                wpn.setMidRangeSplit(newInt);
                break;

            case "LongRangeSplit":
                wpn.setLongRangeSplit(newInt);
                break;

            case "MaxRange":
                wpn.setMaxRange(newInt);
                break;

            case "RefireModifier":
                wpn.setRefireModifier(newInt);
                break;

            case "ShotsWhenFired":
                wpn.setShotsWhenFired(newInt);
                break;

            case "AttackRecoil":
                wpn.setAttackRecoil(newInt);
                break;

            case "InventorySize":
                wpn.setInventorySize(newInt);
                break;

            case "StartingAmmoCapacity":
                wpn.setStartingAmmoCapacity(newInt);
                break;

            case "OverheatedDamageMultiplier":
                wpn.setOverheatedDamageMultiplier(newInt);
                break;

            case "EvasiveDamageMultiplier":
                wpn.setEvasiveDamageMultiplier(newInt);
                break;

            case "EvasivePipsIgnored":
                wpn.setEvasivePipsIgnored(newInt);
                break;

            case "DamageVariance":
                wpn.setDamageVariance(newInt);
                break;

            case "ProjectilesPerShot":
                wpn.setProjectilesPerShot(newInt);
                break;

            case "Cost":
                wpn.getDescription().setCost(newInt);
                break;

            case "Rarity":
                wpn.getDescription().setRarity(newInt);
                break;

            case "BattleValue":
                wpn.setBattleValue(newInt);
                break;
        }
    }

    public void singleEdit(Weapon wpn, String field, Double newDbl) {
        switch (field) {
            case "CriticalChanceMultiplier":
                wpn.removeBonuses();
                wpn.setCriticalChanceMultiplier(newDbl);
                wpn.applyBonuses();

            case "Tonnage":
                wpn.setTonnage(newDbl);
                break;
        }
    }

    public void singleEdit(Weapon wpn, String field, Boolean newBool) {
        switch (field) {
            case "Purchasable":
                wpn.getDescription().setPurchasable(newBool);
                break;

            case "AOECapable":
                wpn.setAOECapable(newBool);
                break;

            case "IndirectFireCapable":
                wpn.setIndirectFireCapable(newBool);
                break;

            case "CriticalComponent":
                wpn.setCriticalComponent(newBool);
                break;
        }
    }

    public void batchEdit(Weapon item, String field, String newStr) {
        String name = item.getDescription().getName().replaceAll(" \\+", "");
        for (Weapon wpn : weaponArrayList) {
            if (wpn.getDescription().getName().replaceAll(" \\+", "").equals(name)) {
                singleEdit(wpn, field, newStr);
            }
        }
    }

    public void batchEdit(Weapon item, String field, Integer newInt) {
        String name = item.getDescription().getName().replaceAll(" \\+", "");
        for (Weapon wpn : weaponArrayList) {
            if (wpn.getDescription().getName().replaceAll(" \\+", "").equals(name)) {
                singleEdit(wpn, field, newInt);
            }
        }
    }

    public void batchEdit(Weapon item, String field, Double newDbl) {
        String name = item.getDescription().getName().replaceAll(" \\+", "");
        for (Weapon wpn : weaponArrayList) {
            if (wpn.getDescription().getName().replaceAll(" \\+", "").equals(name)) {
                singleEdit(wpn, field, newDbl);
            }
        }
    }

    public void batchEdit(Weapon item, String field, Boolean newBool) {
        String name = item.getDescription().getName().replaceAll(" \\+", "");
        for (Weapon wpn : weaponArrayList) {
            if (wpn.getDescription().getName().replaceAll(" \\+", "").equals(name)) {
                singleEdit(wpn, field, newBool);
            }
        }
    }

    public ArrayList<Weapon> getWeaponSubList() {
        return weaponSubList;
    }
}
