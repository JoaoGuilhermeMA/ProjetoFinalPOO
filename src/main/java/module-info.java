module com.example.projetofinalpoo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.projetofinalpoo to javafx.fxml;
    opens dominio to javafx.base;
    exports com.example.projetofinalpoo;
    opens persistencia to javafx.base;
}
