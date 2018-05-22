package com.bwe.gui.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class ColumnTogglesController {

    public CheckBox name;
    public CheckBox mfr;
    public CheckBox dmg;
    public CheckBox stb;
    public CheckBox heatDmg;
    public CheckBox accMod;
    public CheckBox crit;
    public CheckBox heatGen;
    public CheckBox tons;
    public CheckBox bonusA;
    public CheckBox bonusB;
    public CheckBox dmgPerTon;
    public CheckBox dmgPerHeat;
    public CheckBox stbPerTon;
    public CheckBox stbPerHeat;
    public CheckBox minRange;
    public CheckBox maxRange;
    public CheckBox refireMod;
    public CheckBox shotsWhenFired;
    public CheckBox attackRecoil;
    public CheckBox cost;
    public CheckBox rarity;
    public CheckBox purchasable;
    public CheckBox iSize;

    public Button okButton;

    boolean[] getSelections() {
        CheckBox[] buttons = {
                name, mfr, dmg, stb, heatDmg,
                accMod, crit, heatGen, tons, bonusA,
                bonusB, dmgPerTon, dmgPerHeat, stbPerTon, stbPerHeat,
                minRange, maxRange, refireMod, shotsWhenFired, attackRecoil,
                cost, rarity, purchasable, iSize
        };
        boolean[] result = new boolean[buttons.length];
        for (int i = 0; i < buttons.length; i++)
            result[i] = buttons[i].isSelected();
        return result;
    }

    void setNameColVisibility(boolean[] flags) {
        CheckBox[] buttons = {
                name, mfr, dmg, stb, heatDmg,
                accMod, crit, heatGen, tons, bonusA,
                bonusB, dmgPerTon, dmgPerHeat, stbPerTon, stbPerHeat,
                minRange, maxRange, refireMod, shotsWhenFired, attackRecoil,
                cost, rarity, purchasable, iSize
        };

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setSelected(flags[i]);
        }
    }

    @FXML
    private void okButtonPress() {
        okButton.getScene().getWindow().hide();
    }
}
