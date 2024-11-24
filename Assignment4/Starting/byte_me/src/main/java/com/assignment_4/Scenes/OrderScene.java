package com.assignment_4.Scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import com.assignment_4.App;

import com.Start.Classes.canteen;
import com.Start.Classes.order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderScene {

    private Scene ordersScene;
    private VBox ordersLayout;
    
    private canteen canteenInstance;

    private ListView<String> pendingOrdersListView;
    // private ListView<String> deliveredOrdersListView;
    // private ListView<String> deniedOrdersListView;

    public OrderScene() {
        canteenInstance = canteen.get_instance();
        setupLayout();
        setupBackButton();
        setupOrdersListView();
        ordersScene = new Scene(ordersLayout, App.world_width, App.world_height);
    }

    private void setupLayout() {
        ordersLayout = new VBox(App.v_box_spacing);
        Label ordersLabel = new Label("Orders Page");
        ordersLayout.getChildren().add(ordersLabel);
    }

    private void setupOrdersListView() {

        pendingOrdersListView = new ListView<>();
        // deliveredOrdersListView = new ListView<>();
        // deniedOrdersListView = new ListView<>();

        Label pendingLabel = new Label("Pending Orders");
        // Label deliveredLabel = new Label("Delivered Orders");
        // Label deniedLabel = new Label("Denied Orders");

        // ordersLayout.getChildren().addAll(pendingLabel, pendingOrdersListView, deliveredLabel, deliveredOrdersListView,
        ordersLayout.getChildren().addAll(pendingLabel, pendingOrdersListView);
        fetchAndDisplayOrders();

    }

    private void setupBackButton() {
        Button viewMenuButton = new Button("View Menu");
        viewMenuButton.setOnAction(event -> App.setScene(App.get_ms_scene()));
        ordersLayout.getChildren().add(viewMenuButton);
    }

    private void fetchAndDisplayOrders() {

        List<order> vip_orders = new ArrayList<>(canteenInstance.get_priority_orders().values());
        List<order> orders = new ArrayList<>(canteenInstance.get_current_orders().values());


        List<String> vipPendingOrders = vip_orders.stream()
                .filter(order -> order.get_order_status_string().equals("Pending"))
                .map(order::order_string)
                .map(StringBuilder::toString)
                .collect(Collectors.toList());

        List<String> pendingOrders = orders.stream()
                .filter(order -> order.get_order_status_string().equals("Pending"))
                .map(order::order_string)
                .map(StringBuilder::toString)
                .collect(Collectors.toList());

        // List<String> deliveredOrders = orders.stream()
        //         .filter(order -> order.get_order_status_string().equals("Delivered"))
        //         .map(order::order_string)
        //         .map(StringBuilder::toString)
        //         .collect(Collectors.toList());
                


        // List<String> deniedOrders = orders.stream()
        //         .filter(order -> order.get_order_status_string().equals("Denied"))
        //         .map(order::order_string)
        //         .map(StringBuilder::toString)
        //         .collect(Collectors.toList());

        pendingOrdersListView.getItems().addAll(vipPendingOrders);
        pendingOrdersListView.getItems().addAll(pendingOrders);
        // deliveredOrdersListView.getItems().addAll(deliveredOrders);
        // deniedOrdersListView.getItems().addAll(deniedOrders);
    }

    public Scene getScene() {
        return ordersScene;
    }
}