package dam2.jaf;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class UsuarisController {

    @FXML
    private AnchorPane anchor;

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
    
    @FXML
    void tornar(ActionEvent event) throws IOException {
    	
    	App.setRoot("menu");

    }

}
