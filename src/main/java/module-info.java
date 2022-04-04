module dam2.jaf {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires org.controlsfx.controls;
	requires javafx.base;
	requires javafx.graphics;

    opens dam2.jaf to javafx.fxml;
    exports dam2.jaf;
    exports model;

}