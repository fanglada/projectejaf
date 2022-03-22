module dam2.jaf {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;

    opens dam2.jaf to javafx.fxml;
    exports dam2.jaf;
}