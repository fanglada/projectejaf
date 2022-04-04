package dam2.jaf;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class EstatController implements Initializable{

    @FXML
    private AnchorPane anchor;

    @FXML
    private Button botoActualizar;

    @FXML
    private Button botoBuidar;

    @FXML
    private Button botoEliminar;

    @FXML
    private Button botoGuardar;

    @FXML
    private Button botoNetejar;

    @FXML
    private Button botoTornar;

    @FXML
    private TableColumn<?, ?> clmCodi;

    @FXML
    private TableColumn<?, ?> clmDescripcio;

    @FXML
    private TableView<?> tblViewEstat;

    @FXML
    private TextField textCerca;

    @FXML
    private TextField textCodi;

    @FXML
    private TextField textDescripcio;

    @FXML
    void Netejar(ActionEvent event) {

    }

    @FXML
    void actualizarRegistre(ActionEvent event) {

    }

    @FXML
    void buidar(ActionEvent event) {

    }

    @FXML
    void eliminarRegistre(ActionEvent event) {

    }

    @FXML
    void guardarRegistre(ActionEvent event) {

    }

    @FXML
    void tornar(ActionEvent event) throws IOException {
    	App.setRoot("gestio");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
