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
import javafx.scene.layout.AnchorPane;
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

    @FXML private MenuItem fileOpen;
    @FXML private MenuItem fileExit;
    @FXML private MenuItem editSave;
    @FXML private MenuItem editBackup;
    @FXML private MenuItem editRestore;
    @FXML private MenuItem viewColumns;
    @FXML private MenuItem helpAbout;

    @FXML private ComboBox<String> filterComboBox;
    @FXML private CheckBox toggleStock;
    @FXML private ListView<String> filterListBox;
    @FXML private Button filterButton;

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

    private TableColumn[] tableColumns;

    @FXML private AnchorPane columnToggles;
    @FXML private ColumnTogglesController columnTogglesController;

    private Stage mainStage;

    private Prefs prefs;
    private WeaponCollection list;

    @FXML
    private void initialize() {
        prefs = new Prefs();
        list = new WeaponCollection(prefs.getWorkingDir());
        tableColumns = new TableColumn[]{
                colName, colMfr, colDmg, colStb, colHeatDmg,
                colAccMod, colCrit, colHeat, colTons, colBonusA,
                colBOnusB, colDmgPerTon, colDmgPerHeat, colStabPerTon, colStabPerHeat,
                colMinRange, colMaxRange, colRefireMod, colShotsWhenFired, colAttackRecoil,
                colCost, colRarity, colPurchasable, colISize
        };

        filterComboBox.getItems().addAll("All", "Category", "Type", "WeaponSubType");

        setCellValueFactories();
        setCellFactories();
        setVisibleColumns();
        populateTable();
    }

    private void setCellValueFactories() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMfr.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        colDmg.setCellValueFactory(new PropertyValueFactory<>("dmgAdjusted"));
        colStb.setCellValueFactory(new PropertyValueFactory<>("stabAdjusted"));
        colHeatDmg.setCellValueFactory(new PropertyValueFactory<>("heatDamage"));
        colAccMod.setCellValueFactory(new PropertyValueFactory<>("accuracyModifier"));
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
        for (int i = 0; i < tableColumns.length; i++)
            tableColumns[i].setVisible(prefs.getShowCol(i));
    }

    public void fileOpen(ActionEvent actionEvent) {
        File initDir = new File(prefs.getWorkingDir());
        if (!initDir.exists()) {
            prefs.setWorkingDir("C://");
            initDir = new File(prefs.getWorkingDir());
        }

        DirectoryChooser dChooser = new DirectoryChooser();
        dChooser.setTitle("Select Directory");
        dChooser.setInitialDirectory(initDir);
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

        File initDir = new File(prefs.getBackupDir());
        if (!initDir.exists()) {
            prefs.setBackupDir("C://");
            initDir = new File(prefs.getBackupDir());
        }

        FileChooser fChooser = new FileChooser();
        fChooser.setTitle("Select Save Location");
        fChooser.setInitialDirectory(initDir);
        fChooser.setInitialFileName(sampleFileName);
        fChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json", "*.json"));
        File file = fChooser.showSaveDialog(mainStage);
        if (file != null) {
            prefs.setBackupDir(file.getParent());
            list.backup(file);
        }
    }

    public void editRestoreBackup(ActionEvent actionEvent) {

        File initDir = new File(prefs.getBackupDir());
        if (!initDir.exists()) {
            prefs.setBackupDir("C://");
            initDir = new File(prefs.getBackupDir());
        }

        FileChooser fChooser = new FileChooser();
        fChooser.setTitle("Select Save Location");
        fChooser.setInitialDirectory(initDir);
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

    public void viewColumns(ActionEvent actionEvent) {

        TableColumn[] tableColumns = {
                colName, colMfr, colDmg, colStb, colHeatDmg,
                colAccMod, colCrit, colHeat, colTons, colBonusA,
                colBOnusB, colDmgPerTon, colDmgPerHeat, colStabPerTon, colStabPerHeat,
                colMinRange, colMaxRange, colRefireMod, colShotsWhenFired, colAttackRecoil,
                colCost, colRarity, colPurchasable, colISize
        };

        try {
            boolean[] flags = new boolean[24];
            for (int i = 0; i < flags.length; i++)
                flags[i] = tableColumns[i].isVisible();

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/fxml/columntoggles.fxml"));
            Parent root = loader.load();
            ColumnTogglesController columnTogglesController = loader.getController();
            columnTogglesController.setNameColVisibility(flags);
            Scene columnsScene = new Scene(root);
            Stage columnsStage = new Stage();
            columnsStage.setScene(columnsScene);
            columnsStage.initOwner(colName.getTableView().getScene().getWindow());
            columnsStage.setOnHidden(event -> setColumnVisibility(columnTogglesController.getSelections()));
            columnsStage.setTitle("Column Toggles");
            columnsStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setColumnVisibility(boolean[] flags) {
        TableColumn[] tableColumns = {
                colName, colMfr, colDmg, colStb, colHeatDmg,
                colAccMod, colCrit, colHeat, colTons, colBonusA,
                colBOnusB, colDmgPerTon, colDmgPerHeat, colStabPerTon, colStabPerHeat,
                colMinRange, colMaxRange, colRefireMod, colShotsWhenFired, colAttackRecoil,
                colCost, colRarity, colPurchasable, colISize
        };
        for (int i = 0; i < tableColumns.length; i++) {
            tableColumns[i].setVisible(flags[i]);
            prefs.setShowCol(i, flags[i]);
        }
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

    private void populateTable() {
        list.filter(filterComboBox.getValue(), filterListBox.getSelectionModel().getSelectedItem(), toggleStock.isSelected());
        ObservableList<Weapon> observableList = FXCollections.observableList(list.getWeaponSubList());
        displayTable.setItems(observableList);
    }

    public void nameCellCommit(TableColumn.CellEditEvent<Weapon, String> cellEditEvent) {
        displayTable.getSelectionModel().getSelectedItem().getDescription().setName(cellEditEvent.getNewValue());
        displayTable.getSelectionModel().getSelectedItem().getDescription().setUIName(cellEditEvent.getNewValue());
        displayTable.refresh();
    }

    public void mfrCellCommit(TableColumn.CellEditEvent<Weapon, String> cellEditEvent) {
        displayTable.getSelectionModel().getSelectedItem().getDescription().setManufacturer(cellEditEvent.getNewValue());
        displayTable.refresh();
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

    public void costCellCommit(TableColumn.CellEditEvent<Weapon, Integer> cellEditEvent) {
        displayTable.getSelectionModel().getSelectedItem().getDescription().setCost(cellEditEvent.getNewValue());
        displayTable.refresh();
    }

    public void rarityCellCommit(TableColumn.CellEditEvent<Weapon, Integer> cellEditEvent) {
        displayTable.getSelectionModel().getSelectedItem().getDescription().setRarity(cellEditEvent.getNewValue());
        displayTable.refresh();
    }

    public void purchasableCellCommit(TableColumn.CellEditEvent<Weapon, Boolean> cellEditEvent) {
        displayTable.getSelectionModel().getSelectedItem().getDescription().setPurchasable(cellEditEvent.getNewValue());
        displayTable.refresh();
    }

    public Prefs getPrefs() {
        return prefs;
    }
}