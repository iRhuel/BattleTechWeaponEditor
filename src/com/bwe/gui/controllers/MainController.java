package com.bwe.gui.controllers;

import com.bwe.Main;
import com.bwe.Prefs;
import com.bwe.WeaponCollection;
import com.bwe.enums.Category;
import com.bwe.enums.Type;
import com.bwe.enums.WeaponSubType;
import com.bwe.pojo.weapon.Weapon;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainController {

    public MenuItem fileOpen;
    public MenuItem fileExit;
    public MenuItem editSave;
    public MenuItem editBackup;
    public MenuItem editRestore;
    public MenuItem helpAbout;

    public ComboBox<String> filterComboBox;
    public CheckBox toggleStock;
    public ListView<String> filterListBox;
    public Button filterButton;

    public TableView<Weapon> displayTable;
    public TableColumn<Weapon, String> colName;
    public TableColumn<Weapon, String> colMnf;
    public TableColumn<Weapon, Integer> colDmg;
    public TableColumn<Weapon, Integer> colStab;
    public TableColumn<Weapon, Integer> colHeatDmg;
    public TableColumn<Weapon, Integer> colAcc;
    public TableColumn<Weapon, Double> colCrit;
    public TableColumn<Weapon, Integer> colHeat;
    public TableColumn<Weapon, Double> colTons;
    public TableColumn<Weapon, String> colBonusA;
    public TableColumn<Weapon, String> colBOnusB;
    public TableColumn<Weapon, String> colDmgPerTon;
    public TableColumn<Weapon, String> colDmgPerHeat;
    public TableColumn<Weapon, String> colStabPerTon;
    public TableColumn<Weapon, String> colStabPerHeat;
    public TableColumn<Weapon, Integer> colMinRange;
    public TableColumn<Weapon, Integer> colMaxRange;
    public TableColumn<Weapon, Integer> colRefireMod;
    public TableColumn<Weapon, Integer> colShotsWhenFired;
    public TableColumn<Weapon, Integer> colAttackRecoil;
    public TableColumn<Weapon, Integer> colCost;
    public TableColumn<Weapon, Integer> colRarity;
    public TableColumn<Weapon, Boolean> colPurchasable;
    public TableColumn<Weapon, Integer> colISize;

    private Stage mainStage;

    private Prefs prefs;
    private WeaponCollection list;

    @FXML
    private void initialize() {
        prefs = new Prefs();
        list = new WeaponCollection(prefs.getWorkingDir());

        filterComboBox.getItems().addAll("All", "Category", "Type", "WeaponSubType");
        setCellValueFactories();
        setCellFactories();
        populateTable();
    }

    private void setCellValueFactories() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMnf.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        colDmg.setCellValueFactory(new PropertyValueFactory<>("dmgAdjusted"));
        colStab.setCellValueFactory(new PropertyValueFactory<>("stabAdjusted"));
        colHeatDmg.setCellValueFactory(new PropertyValueFactory<>("heatDamage"));
        colAcc.setCellValueFactory(new PropertyValueFactory<>("accuracyModifier"));
        colCrit.setCellValueFactory(new PropertyValueFactory<>("criticalChanceMultiplier"));
        colHeat.setCellValueFactory(new PropertyValueFactory<>("heatGenerated"));
        colTons.setCellValueFactory(new PropertyValueFactory<>("tonnage"));
        colBonusA.setCellValueFactory(new PropertyValueFactory<>("bonusValueA"));
        colBOnusB.setCellValueFactory(new PropertyValueFactory<>("bonusValueB"));
        colDmgPerTon.setCellValueFactory(new PropertyValueFactory<>("dmgPerTon"));
        colDmgPerHeat.setCellValueFactory(new PropertyValueFactory<>("dmgPerHeat"));
        colStabPerTon.setCellValueFactory(new PropertyValueFactory<>("stabPerTon"));
        colStabPerHeat.setCellValueFactory(new PropertyValueFactory<>("stabPerHeat"));
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
        colMnf.setCellFactory(TextFieldTableCell.forTableColumn());
        colDmg.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colStab.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colHeatDmg.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colAcc.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
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

    public void fileOpen(ActionEvent actionEvent) {
        DirectoryChooser dChooser = new DirectoryChooser();
        dChooser.setTitle("Select Directory");
        dChooser.setInitialDirectory(new File(prefs.getWorkingDir()));
        File dir = dChooser.showDialog(mainStage);
        if (dir != null) {
            prefs.setWorkingDir(dir.getAbsolutePath());
            list = new WeaponCollection(dir.getAbsolutePath());
        }
        populateTable();
    }

    public void fileExit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void editSave(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Save changes to Jsons?", ButtonType.APPLY, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.APPLY)
            list.write();
    }

    public void editCreateBackup(ActionEvent actionEvent) {
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy-HH.mm").format(new Date());
        String sampleFileName = "BWEBackup-" + timeStamp;

        FileChooser fChooser = new FileChooser();
        fChooser.setTitle("Select Save Location");
        fChooser.setInitialDirectory(new File(prefs.getBackupDir()));
        fChooser.setInitialFileName(sampleFileName);
        fChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json", "*.json"));
        File file = fChooser.showSaveDialog(mainStage);
        if (file != null) {
            prefs.setBackupDir(file.getParent());
            list.backup(file);
        }
    }

    public void editRestoreBackup(ActionEvent actionEvent) {
        FileChooser fChooser = new FileChooser();
        fChooser.setTitle("Select Save Location");
        fChooser.setInitialDirectory(new File(prefs.getBackupDir()));
        fChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json", "*.json"));
        File file = fChooser.showOpenDialog(mainStage);

        if (file != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Restore Jsons from backup file?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES)
                list.restore(file);
        }
        list = new WeaponCollection(prefs.getWorkingDir());
        populateTable();
    }

    public void helpAbout(ActionEvent actionEvent) {
        try {
            Parent aboutRoot = new FXMLLoader(Main.class.getResource("gui/fxml/about.fxml")).load();
            Stage stage = new Stage();
            stage.setScene(new Scene(aboutRoot));
            stage.setTitle("About");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void filterButton(ActionEvent actionEvent) {
        populateTable();
    }

    public void nameCellCommit(TableColumn.CellEditEvent<Weapon,String> cellEditEvent) {
        displayTable.getSelectionModel().getSelectedItem().getDescription().setName(cellEditEvent.getNewValue());
        displayTable.getSelectionModel().getSelectedItem().getDescription().setUIName(cellEditEvent.getNewValue());
        displayTable.refresh();
    }

    public void mfrCellCommit(TableColumn.CellEditEvent<Weapon,String> cellEditEvent) {
        displayTable.getSelectionModel().getSelectedItem().getDescription().setManufacturer(cellEditEvent.getNewValue());
        displayTable.refresh();
    }

    private void populateTable() {
        list.filter(filterComboBox.getValue(), filterListBox.getSelectionModel().getSelectedItem(), toggleStock.isSelected());
        ObservableList<Weapon> observableList = FXCollections.observableList(list.getWeaponSubList());
        displayTable.setItems(observableList);
    }

    public void bonusACellCommit(TableColumn.CellEditEvent cellEditEvent) {
        displayTable.getSelectionModel().getSelectedItem().adjustBonus(cellEditEvent.getNewValue().toString(), 1);
        displayTable.refresh();
    }

    public void bonusBCellCommit(TableColumn.CellEditEvent cellEditEvent) {
        displayTable.getSelectionModel().getSelectedItem().adjustBonus(cellEditEvent.getNewValue().toString(), 2);
        displayTable.refresh();
    }

    public void integerBatchEdit(TableColumn.CellEditEvent<Weapon, Integer> cellEditEvent) {
        Weapon wpn = displayTable.getSelectionModel().getSelectedItem();
        list.batchEdit(wpn.getWeaponSubType(), cellEditEvent.getTableColumn().getText(), cellEditEvent.getNewValue());
        displayTable.refresh();
    }

    public void doubleBatchEdit(TableColumn.CellEditEvent<Weapon, Double> cellEditEvent) {
        Weapon wpn = displayTable.getSelectionModel().getSelectedItem();
        list.batchEdit(wpn.getWeaponSubType(), cellEditEvent.getTableColumn().getText(), cellEditEvent.getNewValue());
        displayTable.refresh();
    }

    public void costCellCommit(TableColumn.CellEditEvent<Weapon,Integer> cellEditEvent) {
        displayTable.getSelectionModel().getSelectedItem().getDescription().setCost(cellEditEvent.getNewValue());
        displayTable.refresh();
    }

    public void rarityCellCommit(TableColumn.CellEditEvent<Weapon,Integer> cellEditEvent) {
        displayTable.getSelectionModel().getSelectedItem().getDescription().setRarity(cellEditEvent.getNewValue());
        displayTable.refresh();
    }

    public void iSizeCellCommit(TableColumn.CellEditEvent<Weapon,Integer> cellEditEvent) {
        displayTable.getSelectionModel().getSelectedItem().setInventorySize(cellEditEvent.getNewValue());
        displayTable.refresh();
    }

    public void purchasableCellCommit(TableColumn.CellEditEvent<Weapon, Boolean> cellEditEvent) {
        displayTable.getSelectionModel().getSelectedItem().getDescription().setPurchasable(cellEditEvent.getNewValue());
        displayTable.refresh();
    }
}