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
    void Usuaris(ActionEvent event) throws IOException {
    	
    	App.setRoot("usuaris");

    }
    
    @FXML
    void Gestio(ActionEvent event) throws IOException {
    	
    	App.setRoot("gestio");

    }
    
    @FXML
    void Contracte(ActionEvent event) throws IOException {
    	
    	App.setRoot("contracte");

    }


}
