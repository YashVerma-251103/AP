module com.assignment_4 {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires com.google.gson;
    opens com.Start.Classes to com.google.gson;
    opens com.Start.Structures to com.google.gson;
    exports com.assignment_4;
    exports com.assignment_4.Scenes;
    exports com.Start;
    exports com.Start.Classes;
    exports com.Start.Planning;
    exports com.Start.save_files;

}