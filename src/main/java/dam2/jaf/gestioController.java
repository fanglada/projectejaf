package dam2.jaf;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class gestioController {

    @FXML
    private AnchorPane anchor;

    @FXML
    private Button botoTornar;

    @FXML
    void Botiga(ActionEvent event) {

    }

    @FXML
    void Carnet(ActionEvent event) {

    }

    @FXML
    void Estat(ActionEvent event) {

    }

    @FXML
    void Parking(ActionEvent event) {

    }

    @FXML
    void TipusVehicle(ActionEvent event) {

    }

    @FXML
    void Vehicle(ActionEvent event) {

    }

    @FXML
    void tornar(ActionEvent event) throws IOException {
    	App.setRoot("gestio");
    }

}
