package dam2.jaf;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MenuController {

    @FXML
    private AnchorPane anchor;

    @FXML
    private Button botoProva;

    @FXML
    void Client(ActionEvent event) throws IOException {
    	
    	App.setRoot("client");

    }

    @FXML
    void Conductor(ActionEvent event) throws IOException {
    	
    	App.setRoot("conductor");

    }

    @FXML
    void EmpleatSupervisor(ActionEvent event) throws IOException {
    	
    	App.setRoot("empleatSupervisor");

    }

}
