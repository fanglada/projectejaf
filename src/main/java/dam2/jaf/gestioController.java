package dam2.jaf;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class gestioController implements Initializable{

    @FXML
    private AnchorPane anchor;

    @FXML
    private Button botoTornar;

    @FXML
    void Botiga(ActionEvent event) throws IOException {
    	App.setRoot("botiga");
    }

    @FXML
    void Carnet(ActionEvent event) throws IOException {
    	App.setRoot("carnet");
    }

    @FXML
    void Estat(ActionEvent event) throws IOException {
    	App.setRoot("estat");
    }

    @FXML
    void Parking(ActionEvent event) throws IOException {
    	App.setRoot("parking");
    }

    @FXML
    void TipusVehicle(ActionEvent event) throws IOException {
    	App.setRoot("tipusVehicle");
    }

    @FXML
    void Vehicle(ActionEvent event) throws IOException {
    	App.setRoot("vehicle");
    }

    @FXML
    void tornar(ActionEvent event) throws IOException {
    	App.setRoot("menu");
    }
    
    @FXML
    void Moviments(ActionEvent event) throws IOException {
    	App.setRoot("moviments");
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		App.setTitol("Gestió");

	}
	

}
