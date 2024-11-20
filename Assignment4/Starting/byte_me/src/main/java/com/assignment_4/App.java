// // package com.assignment_4;

// // import javafx.application.Application;
// // import javafx.fxml.FXMLLoader;
// // import javafx.scene.Parent;
// // import javafx.scene.Scene;
// // import javafx.stage.Stage;

// // import java.io.IOException;

// // /**
// //  * JavaFX App
// //  */
// // public class App extends Application {

// //     private static Scene scene;

// //     @Override
// //     public void start(Stage stage) throws IOException {
// //         scene = new Scene(loadFXML("primary"), 640, 480);
// //         stage.setScene(scene);
// //         stage.show();
// //     }

// //     static void setRoot(String fxml) throws IOException {
// //         scene.setRoot(loadFXML(fxml));
// //     }

// //     private static Parent loadFXML(String fxml) throws IOException {
// //         FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
// //         return fxmlLoader.load();
// //     }

// //     public static void main(String[] args) {
// //         launch();
// //     }

// // }

// package com.assignment_4;

// import javafx.application.Application;
// import javafx.scene.Scene;
// import javafx.scene.control.Label;
// import javafx.scene.layout.StackPane;
// import javafx.stage.Stage;

// public class App extends Application {
//     public static void main(String[] args) {
//         launch(args); // Launch the JavaFX application
//     }

//     @Override
//     public void start(Stage primaryStage) {
//         // Create the UI components
//         StackPane root = new StackPane();
//         root.getChildren().add(new Label("Hello, JavaFX without FXML!"));

//         // Set the scene
//         Scene scene = new Scene(root, 400, 300);

//         // Set up the stage
//         primaryStage.setTitle("JavaFX Maven App");
//         primaryStage.setScene(scene);
//         primaryStage.show();
//     }
// }

package com.assignment_4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import com.Start.*;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) {
        // Create the root layout
        StackPane root = new StackPane();

        // Add a button to the layout
        Button button = new Button("Click Me");
        button.setOnAction(event -> System.out.println("Button clicked!"));

        root.getChildren().add(button);

        // Create a scene
        scene = new Scene(root, 640, 480);

        // Configure the stage
        stage.setTitle("JavaFX Application without FXML");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();

    }
}
