module com.example.ryndersvitu_enzo_tpjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ryndersvitu_enzo_tpjavafx to javafx.fxml;
    exports com.example.ryndersvitu_enzo_tpjavafx;
}