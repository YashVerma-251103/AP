package com.assignment_4.Scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import com.assignment_4.App;

import com.Start.Classes.canteen;
import com.Start.Classes.menu_item;

import java.util.List;
import java.util.stream.Collectors;

public class MenuScene {

    private Scene menuScene;
    private VBox menuLayout;
    private ListView<String> menuListView;
    private canteen canteenInstance;

    public MenuScene() {
        canteenInstance = canteen.get_instance();
        setupLayout();
        setupViewOrdersButton();
        setupMenuListView();
        menuScene = new Scene(menuLayout, App.world_width, App.world_height);
    }

    private void setupLayout() {
        menuLayout = new VBox(App.v_box_spacing);
        Label menuLabel = new Label("Menu Page");
        menuLayout.getChildren().add(menuLabel);
    }

    private void setupMenuListView() {
        menuListView = new ListView<>();
        menuLayout.getChildren().add(menuListView);
        fetchAndDisplayMenuItems();
    }

    private void setupViewOrdersButton() {
        Button viewOrdersButton = new Button("View Orders");
        viewOrdersButton.setOnAction(event -> App.setScene(App.getOrderScene().getScene()));
        menuLayout.getChildren().add(viewOrdersButton);
    }

    private void fetchAndDisplayMenuItems() {
        List<String> menuItems = canteenInstance.get_menu().values().stream()
                .map(menu_item::toString) // Assuming menu_item class has a proper toString method
                .collect(Collectors.toList());
        menuListView.getItems().addAll(menuItems);
    }

    public Scene getScene() {
        return menuScene;
    }
}