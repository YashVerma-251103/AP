package com.assignment_4.Scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import com.assignment_4.App;

import com.Start.Classes.canteen;
import com.Start.Classes.order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderScene {

    private Scene ordersScene;
    private VBox ordersLayout;
    private ListView<String> ordersListView;
    private canteen canteenInstance;

    public OrderScene() {
        canteenInstance = canteen.get_instance();
        setupLayout();
        setupBackButton();
        setupOrdersListView();
        ordersScene = new Scene(ordersLayout, 640, 480);
    }

    private void setupLayout() {
        ordersLayout = new VBox(10);
        Label ordersLabel = new Label("Orders Page");
        ordersLayout.getChildren().add(ordersLabel);
    }

    private void setupOrdersListView() {
        ordersListView = new ListView<>();
        ordersLayout.getChildren().add(ordersListView);
        fetchAndDisplayOrders();
    }

    private void setupBackButton() {
        Button viewMenuButton = new Button("View Menu");
        viewMenuButton.setOnAction(event -> App.setScene(App.get_ms_scene()));
        ordersLayout.getChildren().add(viewMenuButton);
    }

    private void fetchAndDisplayOrders() {
        List<String> orders = canteenInstance.get_current_orders().values().stream()
                .map(order::order_string)
                .map(StringBuilder::toString)
                .collect(Collectors.toList());
        ordersListView.getItems().addAll(orders);
    }

    public Scene getScene() {
        return ordersScene;
    }
}