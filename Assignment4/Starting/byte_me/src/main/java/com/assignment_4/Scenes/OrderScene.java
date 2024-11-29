package com.assignment_4.Scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import com.assignment_4.App;
import com.assignment_4.order_help;

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






// package com.assignment_4.Scenes;

// import com.assignment_4.App;
// import com.Start.Classes.order;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.core.type.TypeReference;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ListView;
// import javafx.scene.layout.VBox;

// import java.io.File;
// import java.io.IOException;
// import java.util.List;
// import java.util.stream.Collectors;

// public class OrderScene {

//     private Scene ordersScene;
//     private VBox ordersLayout;

//     private ListView<String> pendingOrdersListView;

//     public OrderScene() {
//         setupLayout();
//         setupBackButton();
//         setupOrdersListView();
//         ordersScene = new Scene(ordersLayout, App.world_width, App.world_height);
//     }

//     private void setupLayout() {
//         ordersLayout = new VBox(App.v_box_spacing);
//         Label ordersLabel = new Label("Orders Page");
//         ordersLayout.getChildren().add(ordersLabel);
//     }

//     private void setupOrdersListView() {
//         pendingOrdersListView = new ListView<>();
//         Label pendingLabel = new Label("Pending Orders");
//         ordersLayout.getChildren().addAll(pendingLabel, pendingOrdersListView);
//         fetchAndDisplayOrders();
//     }

//     private void setupBackButton() {
//         Button viewMenuButton = new Button("View Menu");
//         viewMenuButton.setOnAction(event -> App.setScene(App.get_ms_scene()));
//         ordersLayout.getChildren().add(viewMenuButton);
//     }

//     private void fetchAndDisplayOrders() {
//         try {
//             // Load current and priority orders from JSON files
//             List<order> currentOrders = loadOrdersFromJson("current_orders.json");
//             List<order> priorityOrders = loadOrdersFromJson("priority_orders.json");

//             // Filter for pending orders
//             List<String> pendingOrders = currentOrders.stream()
//                     .filter(order -> order.get_order_status_string().equals("Pending"))
//                     .map(order::order_string)
//                     .map(StringBuilder::toString)
//                     .collect(Collectors.toList());

//             List<String> vipPendingOrders = priorityOrders.stream()
//                     .filter(order -> order.get_order_status_string().equals("Pending"))
//                     .map(order::order_string)
//                     .map(StringBuilder::toString)
//                     .collect(Collectors.toList());

//             // Add pending orders to the ListView
//             pendingOrdersListView.getItems().addAll(vipPendingOrders);
//             pendingOrdersListView.getItems().addAll(pendingOrders);

//         } catch (IOException e) {
//             e.printStackTrace();
//             // Handle file reading error (e.g., show a message to the user)
//         }
//     }

//     private List<order> loadOrdersFromJson(String fileName) throws IOException {
//         // Create a Jackson ObjectMapper instance
//         ObjectMapper objectMapper = new ObjectMapper();

//         // Read the JSON file and map it to a list of 'order' objects
//         File file = new File(fileName);
//         List<order> orders = objectMapper.readValue(file, new TypeReference<List<order>>(){});

//         return orders;
//     }

//     public Scene getScene() {
//         return ordersScene;
//     }
// }
