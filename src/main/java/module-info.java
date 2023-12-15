module com.example.projetofinalpoo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.projetofinalpoo to javafx.fxml;
    exports com.example.projetofinalpoo;
}