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
                    if (!isOnIgnoreList(file) && file.getName().contains("Weapon_")) {
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
                "tonnage", "slots", "getDmgPerTon", "getDmgPerHeat", "getStabPerTon",
                "getStabPerHeat");

        for (Weapon wpn : weaponSubList) {
            System.out.println();
            System.out.format("%15s%7d%7d%7d%10s%7d%15s%15s%15s%15s", wpn.getDescription().getName(),
                    wpn.getDmgAdjusted(), wpn.getStabAdjusted(), wpn.getHeatGenerated(), wpn.getTonnage(),
                    wpn.getInventorySize(), df.format(wpn.getDmgPerTon()), df.format(wpn.getDmgPerHeat()),
                    df.format(wpn.getStabPerTon()), df.format(wpn.getStabPerHeat()));
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

    public void batchEdit(String weaponSubType, String valName, int newInt) {
        for (Weapon wpn : weaponArrayList) {
            if (wpn.getWeaponSubType().equals(weaponSubType)) {
                switch (valName) {
                    case "Dmg":
                        wpn.adjustDmg(newInt);
                        break;

                    case "Stb":
                        wpn.adjustStab(newInt);
                        break;

                    case "AccMod":
                        wpn.adjustAcc(newInt);
                        break;

                    case "Heat":
                        wpn.adjustHeat(newInt);
                        break;
                }
            }
        }
    }

    public void batchEdit(String weaponSubType, String valName, double newDbl) {
        for (Weapon wpn : weaponArrayList) {
            if (wpn.getWeaponSubType().equals(weaponSubType)) {
                switch (valName) {
                    case "Crit":
                        wpn.adjustCrit(newDbl);
                        break;

                    case "Tons":
                        wpn.setTonnage(newDbl);
                        break;
                }
            }
        }
    }

    public ArrayList<Weapon> getWeaponSubList() {
        return weaponSubList;
    }
}
