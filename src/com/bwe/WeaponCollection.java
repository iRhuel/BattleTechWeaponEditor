package com.bwe;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.bwe.pojo.weapon.Weapon;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class WeaponCollection {

    private ObjectMapper mapper;
    private ObjectWriter writer;
    private ArrayList<Weapon> weaponArrayList = new ArrayList<>();
    private ArrayList<File> fileArrayList = new ArrayList<>();
    private ArrayList<Weapon> weaponSubList = new ArrayList<>();

    public WeaponCollection(String path) {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        printer.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        mapper = new ObjectMapper();
        writer = mapper.writer(printer);

        File[] fileList = new File(path).listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                try {
                    if (!file.getName().contains("Template") && file.getName().contains("Weapon_")) {
                        fileArrayList.add(file);
                        Weapon wpn = mapper.readValue(file, Weapon.class);
                        weaponArrayList.add(wpn);
                    }
                } catch (Exception e) {
                    System.out.println("Error loading " + file.getName());
                    e.printStackTrace();
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
        System.out.println(saveFile.getAbsolutePath());
        JsonNodeFactory factory = JsonNodeFactory.instance;
        ObjectNode parent = factory.objectNode();
        try {
            for (File file : fileArrayList) {
                parent.set(file.getName(), mapper.readTree(file));
            }
            writer.writeValue(saveFile, parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void restore(File restoreFile) {
        try {
            JsonNode jsonNode = mapper.readTree(restoreFile);
            ObjectNode objectNode = (ObjectNode) jsonNode;

            for (File file : fileArrayList) {
                writer.writeValue(file, objectNode.get(file.getName()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public void print() {
        DecimalFormat df = new DecimalFormat("#.##");
        weaponSubList.sort(new IWeaponComparator());
        System.out.format("\n%15s%7s%7s%7s%10s%7s%15s%15s%15s%15s", "weapon", "dmg", "stab", "heat",
                "tonnage", "slots", "getDmgPerTon", "getDmgPerHeat", "getStbPerTon",
                "getStbPerHeat");

        for (Weapon wpn : weaponSubList) {
            System.out.println();
            System.out.format("%15s%7d%7d%7d%10s%7d%15s%15s%15s%15s", wpn.getDescription().getName(),
                    wpn.getDmgAdjusted(), wpn.getStbAdjusted(), wpn.getHeatGenerated(), wpn.getTonnage(),
                    wpn.getInventorySize(), df.format(wpn.getDmgPerTon()), df.format(wpn.getDmgPerHeat()),
                    df.format(wpn.getStbPerTon()), df.format(wpn.getStbPerHeat()));
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
                wpn.adjustBonus(newStr, 1);
                break;

            case "BonusValueB":
                wpn.adjustBonus(newStr, 2);
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

            case"DisallowedLocations":
                wpn.setDisallowedLocations(newStr);
                break;
        }
    }

    public void singleEdit(Weapon wpn, String field, Integer newInt) {
        switch(field) {
            case "Damage":
                wpn.adjustDmg(newInt);
                break;

            case "Instability":
                wpn.adjustStb(newInt);
                break;

            case "HeatDamage":
                wpn.setHeatDamage(newInt);
                break;

            case "AccuracyModifier":
                wpn.adjustAcc(newInt);
                break;

            case "HeatGenerated":
                wpn.adjustHeat(newInt);
                break;

            case "MinRange":
                wpn.setMinRange(newInt);
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
        }
    }

    public void singleEdit(Weapon wpn, String field, Double newDbl) {
        switch (field) {
            case "Crit":
                wpn.adjustCrit(newDbl);
                break;

            case "Tons":
                wpn.setTonnage(newDbl);
                break;
        }
    }

    public void singleEdit(Weapon wpn, String field, Boolean newBool) {
        switch (field) {
            case "Purchasable":
                wpn.getDescription().setPurchasable(newBool);
                break;
        }
    }

    public void batchEdit(Weapon item, String field, String newStr) {
        String name = item.getDescription().getName().replaceAll(" \\+", "");
        for (Weapon wpn : weaponArrayList) {
            if (wpn.getDescription().getName().contains(name)) {
                singleEdit(wpn, field, newStr);
            }
        }
    }

    public void batchEdit(Weapon item, String field, Integer newInt) {
        String name = item.getDescription().getName().replaceAll(" \\+", "");
        for (Weapon wpn : weaponArrayList) {
            if (wpn.getDescription().getName().contains(name)) {
                singleEdit(wpn, field, newInt);
            }
        }
    }

    public void batchEdit(Weapon item, String field, Double newDbl) {
        String name = item.getDescription().getName().replaceAll(" \\+", "");
        for (Weapon wpn : weaponArrayList) {
            if (wpn.getDescription().getName().contains(name)) {
                singleEdit(wpn, field, newDbl);
            }
        }
    }

    public void batchEdit(Weapon item, String field, Boolean newBool) {
        String name = item.getDescription().getName().replaceAll(" \\+", "");
        for (Weapon wpn : weaponArrayList) {
            if (wpn.getDescription().getName().contains(name)) {
                singleEdit(wpn, field, newBool);
            }
        }
    }

    public ArrayList<Weapon> getWeaponSubList() {
        return weaponSubList;
    }
}
