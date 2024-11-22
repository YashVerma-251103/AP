package com.assignment_4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.assignment_4.Scenes.MenuScene;
import com.assignment_4.Scenes.OrderScene;
import com.Start.byte_me;
import com.Start.Planning.data_seeder;;

public class App extends Application {

    private static MenuScene menuScene;
    private static OrderScene orderScene;
    private static Stage app_stage;
    public static final int world_width = 1280, world_height = 720;
    public static final int v_box_spacing = 15;


    @Override
    public void start(Stage stage) {

        data_seeder.seed_data();

        

        // Create the menu page
        menuScene = new MenuScene();

        // Create the orders page
        orderScene = new OrderScene();

        app_stage = stage;

        // Configure the stage
        stage.setTitle("Byte_Me");
        // stage.setScene(menuScene.getScene());
        stage.setScene(orderScene.getScene());
        stage.show();
    }

    public static void setScene(Scene scene) {
        // Stage stage = (Stage) scene.getWindow();
        // stage.setScene(scene);
        app_stage.setScene(scene);
    }



    public static MenuScene getMenuScene() {
        return menuScene;
    }
    public static Scene get_ms_scene() {
        return menuScene.getScene();
    }

    public static OrderScene getOrderScene() {
        return orderScene;
    }

    public static void main(String[] args) {
        byte_me.main(args);
        // launch();
    }
}