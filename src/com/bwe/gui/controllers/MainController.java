package com.bwe.gui.controllers;

import com.bwe.Main;
import com.bwe.Prefs;
import com.bwe.WeaponCollection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainController {
    @FXML private Prefs prefs;
    @FXML private WeaponCollection list;

    @FXML private AnchorPane weaponEditorTab;
    @FXML private MenuItem fileOpen;
    @FXML private MenuItem fileExit;
    @FXML private MenuItem editSave;
    @FXML private MenuItem editBackup;
    @FXML private MenuItem editRestore;
    @FXML private Menu viewColumns;
    @FXML private MenuItem helpAbout;
    @FXML private Label workingDirDisplay;

    private Stage mainStage;

    @FXML private WeaponEditorTabController weaponEditorTabController;

    @FXML
    private void initialize() {
        prefs = new Prefs();
        list = new WeaponCollection(prefs.getWorkingDir());

        loadWeaponEditorTab();

        workingDirDisplay.setText(prefs.getWorkingDir());
        populateViewColumns();
    }

    private void loadWeaponEditorTab() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/fxml/weaponeditortab.fxml"));
//            weaponEditorTabController = new WeaponEditorTabController(this);
            weaponEditorTab.getChildren().setAll((AnchorPane)loader.load());
            weaponEditorTabController = loader.getController();
            weaponEditorTabController.postInitSetup(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            workingDirDisplay.setText(prefs.getWorkingDir());
        }

        weaponEditorTabController.populateTable();
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
        weaponEditorTabController.populateTable();
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

    private void populateViewColumns() {
        TableColumn[] tableColumns = weaponEditorTabController.getTableColumns();
        for (TableColumn column : tableColumns) {
            ToggleButton tb = new ToggleButton(column.getText());
            tb.setSelected(column.isVisible());
            tb.setOnAction(event -> column.setVisible(tb.isSelected()));
            CustomMenuItem cmi = new CustomMenuItem(tb);
            cmi.setHideOnClick(false);
            viewColumns.getItems().add(cmi);
        }
    }

    Prefs getPrefs() {
        return prefs;
    }

    WeaponCollection getList() {
        return list;
    }
}