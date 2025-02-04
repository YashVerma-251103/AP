module com.assignment_4 {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires com.fasterxml.jackson.databind;
    // requires com.google.gson;
//    requires org.junit.jupiter.api;


    opens com.Start.Classes to com.google.gson;
    opens com.Start.Structures to com.google.gson;


    exports com.assignment_4;
    exports com.assignment_4.Scenes;
    exports com.Start;
    exports com.Start.Classes;
    exports com.Start.Planning;
    exports com.Start.save_files;
    exports com.Start.Structures;

}