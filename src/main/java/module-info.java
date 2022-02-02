module com.example.programming_assignment_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.programming_assignment_2 to javafx.fxml;
    exports com.example.programming_assignment_2;
}