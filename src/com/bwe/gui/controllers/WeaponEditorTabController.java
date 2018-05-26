package com.bwe.gui.controllers;

import com.bwe.Prefs;
import com.bwe.WeaponCollection;
import com.bwe.enums.Category;
import com.bwe.enums.Type;
import com.bwe.enums.WeaponSubType;
import com.bwe.pojo.weapon.Weapon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class WeaponEditorTabController {
    @FXML private ComboBox<String> filterComboBox;
    @FXML private CheckBox toggleStock;
    @FXML private CheckBox toggleBatch;
    @FXML private ListView<String> filterListBox;

    @FXML private TableView<Weapon> displayTable;
    @FXML private TableColumn<Weapon, String> colName;
    @FXML private TableColumn<Weapon, String> colMfr;
    @FXML private TableColumn<Weapon, Integer> colDmg;
    @FXML private TableColumn<Weapon, Integer> colStb;
    @FXML private TableColumn<Weapon, Integer> colHeatDmg;
    @FXML private TableColumn<Weapon, Integer> colAccMod;
    @FXML private TableColumn<Weapon, Double> colCrit;
    @FXML private TableColumn<Weapon, Integer> colHeat;
    @FXML private TableColumn<Weapon, Double> colTons;
    @FXML private TableColumn<Weapon, String> colBonusA;
    @FXML private TableColumn<Weapon, String> colBOnusB;
    @FXML private TableColumn<Weapon, String> colDmgPerTon;
    @FXML private TableColumn<Weapon, String> colDmgPerHeat;
    @FXML private TableColumn<Weapon, String> colStabPerTon;
    @FXML private TableColumn<Weapon, String> colStabPerHeat;
    @FXML private TableColumn<Weapon, Integer> colMinRange;
    @FXML private TableColumn<Weapon, Integer> colMaxRange;
    @FXML private TableColumn<Weapon, Integer> colRefireMod;
    @FXML private TableColumn<Weapon, Integer> colShotsWhenFired;
    @FXML private TableColumn<Weapon, Integer> colAttackRecoil;
    @FXML private TableColumn<Weapon, Integer> colCost;
    @FXML private TableColumn<Weapon, Integer> colRarity;
    @FXML private TableColumn<Weapon, Boolean> colPurchasable;
    @FXML private TableColumn<Weapon, Integer> colISize;
    @FXML private TableColumn[] tableColumns;

    private Prefs prefs;
    private WeaponCollection list;

    @FXML private void initialize() {
        tableColumns = new TableColumn[]{
                colName, colMfr, colDmg, colStb, colHeatDmg,
                colAccMod, colCrit, colHeat, colTons, colBonusA,
                colBOnusB, colDmgPerTon, colDmgPerHeat, colStabPerTon, colStabPerHeat,
                colMinRange, colMaxRange, colRefireMod, colShotsWhenFired, colAttackRecoil,
                colCost, colRarity, colPurchasable, colISize
        };
        filterComboBox.getItems().addAll("All", "Category", "Type", "WeaponSubType");
        toggleStock.setOnAction(event -> populateTable());
        setCellValueFactories();
        setCellFactories();
    }

    void postInitSetup(MainController mainController) {
        prefs = mainController.getPrefs();
        list = mainController.getList();
        setVisibleColumns();
        populateTable();
    }

    private void setCellValueFactories() {
//        for (TableColumn column : tableColumns)
//            column.setCellValueFactory(new PropertyValueFactory<>(column.getText()));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMfr.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        colDmg.setCellValueFactory(new PropertyValueFactory<>("dmgAdjusted"));
        colStb.setCellValueFactory(new PropertyValueFactory<>("stbAdjusted"));
        colHeatDmg.setCellValueFactory(new PropertyValueFactory<>("heatDamage"));
        colAccMod.setCellValueFactory(new PropertyValueFactory<>("accuracyModifier"));
        colCrit.setCellValueFactory(new PropertyValueFactory<>("criticalChanceMultiplier"));
        colHeat.setCellValueFactory(new PropertyValueFactory<>("heatGenerated"));
        colTons.setCellValueFactory(new PropertyValueFactory<>("tonnage"));
        colBonusA.setCellValueFactory(new PropertyValueFactory<>("bonusValueA"));
        colBOnusB.setCellValueFactory(new PropertyValueFactory<>("bonusValueB"));
        colDmgPerTon.setCellValueFactory(new PropertyValueFactory<>("dmgPerTon"));
        colDmgPerHeat.setCellValueFactory(new PropertyValueFactory<>("dmgPerHeat"));
        colStabPerTon.setCellValueFactory(new PropertyValueFactory<>("stbPerTon"));
        colStabPerHeat.setCellValueFactory(new PropertyValueFactory<>("stbPerHeat"));
        colMinRange.setCellValueFactory(new PropertyValueFactory<>("minRange"));
        colMaxRange.setCellValueFactory(new PropertyValueFactory<>("maxRange"));
        colRefireMod.setCellValueFactory(new PropertyValueFactory<>("refireModifier"));
        colShotsWhenFired.setCellValueFactory(new PropertyValueFactory<>("shotsWhenFired"));
        colAttackRecoil.setCellValueFactory(new PropertyValueFactory<>("attackRecoil"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colRarity.setCellValueFactory(new PropertyValueFactory<>("rarity"));
        colPurchasable.setCellValueFactory(new PropertyValueFactory<>("purchasable"));
        colISize.setCellValueFactory(new PropertyValueFactory<>("inventorySize"));
    }

    private void setCellFactories() {
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colMfr.setCellFactory(TextFieldTableCell.forTableColumn());
        colDmg.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colStb.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colHeatDmg.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colAccMod.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colCrit.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        colTons.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        colHeat.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colBonusA.setCellFactory(TextFieldTableCell.forTableColumn());
        colBOnusB.setCellFactory(TextFieldTableCell.forTableColumn());
        colRefireMod.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colShotsWhenFired.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colAttackRecoil.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colCost.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colRarity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colPurchasable.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
        colISize.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }

    private void setVisibleColumns() {
        for (TableColumn tableColumn : tableColumns)
            tableColumn.setVisible(prefs.getShowCol(tableColumn.getText()));
    }

    void populateTable() {
        list.filter(filterComboBox.getValue(), filterListBox.getSelectionModel().getSelectedItem(), toggleStock.isSelected());
        ObservableList<Weapon> observableList = FXCollections.observableList(list.getWeaponSubList());
        displayTable.setItems(observableList);
    }

    public void filterTypeSelected(ActionEvent actionEvent) {
        filterListBox.getItems().clear();

        switch (filterComboBox.getValue()) {

            case "All":
                filterListBox.getItems().add("All");
                break;

            case "Category":
                for (Category category : Category.values())
                    filterListBox.getItems().add(category.name());
                break;

            case "Type":
                for (Type type : Type.values())
                    filterListBox.getItems().add(type.name());
                break;

            case "WeaponSubType":
                for (WeaponSubType subType : WeaponSubType.values())
                    filterListBox.getItems().add(subType.name());
                break;
        }
    }

    public void subFilterClicked(MouseEvent mouseEvent) {
        populateTable();
    }

    public void strCellCommit(TableColumn.CellEditEvent<Weapon, String> cellEditEvent) {
        Weapon wpn = displayTable.getSelectionModel().getSelectedItem();
        if (toggleBatch.isSelected())
            list.batchEdit(wpn, cellEditEvent.getTableColumn().getText(), cellEditEvent.getNewValue());
        else
            list.singleEdit(wpn, cellEditEvent.getTableColumn().getText(), cellEditEvent.getNewValue());
        displayTable.refresh();
    }

    public void intCellCommit(TableColumn.CellEditEvent<Weapon, Integer> cellEditEvent) {
        Weapon wpn = displayTable.getSelectionModel().getSelectedItem();
        if (toggleBatch.isSelected())
            list.batchEdit(wpn, cellEditEvent.getTableColumn().getText(), cellEditEvent.getNewValue());
        else
            list.singleEdit(wpn,cellEditEvent.getTableColumn().getText(), cellEditEvent.getNewValue());
        displayTable.refresh();
    }

    public void dblCellCommit(TableColumn.CellEditEvent<Weapon, Double> cellEditEvent) {
        Weapon wpn = displayTable.getSelectionModel().getSelectedItem();
        if (toggleBatch.isSelected())
            list.batchEdit(wpn, cellEditEvent.getTableColumn().getText(), cellEditEvent.getNewValue());
        else
            list.singleEdit(wpn, cellEditEvent.getTableColumn().getText(), cellEditEvent.getNewValue());
        displayTable.refresh();
    }

    public void boolCellCommit(TableColumn.CellEditEvent<Weapon, Boolean> cellEditEvent) {
        Weapon wpn = displayTable.getSelectionModel().getSelectedItem();
        if (toggleBatch.isSelected())
            list.batchEdit(wpn, cellEditEvent.getTableColumn().getText(), cellEditEvent.getNewValue());
        else
            list.singleEdit(wpn, cellEditEvent.getTableColumn().getText(), cellEditEvent.getNewValue());
        displayTable.refresh();
    }

    TableColumn[] getTableColumns() {
        return tableColumns;
    }
}
