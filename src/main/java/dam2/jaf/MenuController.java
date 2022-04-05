package dam2.jaf;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class MenuController implements Initializable{

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		App.setTitol("Menu");

	}


}
