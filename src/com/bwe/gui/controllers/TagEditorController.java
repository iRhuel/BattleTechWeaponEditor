package com.bwe.gui.controllers;

import com.bwe.pojo.weapon.Weapon;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

import java.util.List;

public class TagEditorController {
    public VBox vBox;
    private String[] tagList = new String[]{
            "component_type_variant",
            "component_type_variant1",
            "component_type_variant2",
            "component_type_variant3",
            "range_close",
            "range_standard",
            "range_long",
            "range_very-long",
            "range_extreme",
            "component_type_stock",
            "component_type_lostech",
            "component_type_debug",
    };

    void setup(Weapon wpn) {
        List<String> wpnTagList = wpn.getComponentTags().getItems();

        vBox.getChildren().addAll(new Label("ComponentTags"), new Separator());
        for (String str : tagList) {
            CheckBox checkBox = new CheckBox(str);
            checkBox.setSelected(wpnTagList.contains(str));
            checkBox.setOnAction(event -> {
                if(checkBox.isSelected())
                    wpnTagList.add(str);
                else
                    wpnTagList.remove(str);
            });
            vBox.getChildren().add(checkBox);
        }
        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> closeButton.getScene().getWindow().hide());
        vBox.getChildren().addAll(new Separator(), closeButton);
    }
}
