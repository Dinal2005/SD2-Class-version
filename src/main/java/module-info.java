module com.example.class_version_sd2_cw {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.class_version_sd2_cw to javafx.fxml;
    exports com.example.class_version_sd2_cw;
}