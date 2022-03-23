package dam2.jaf;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Client;

public class ClientController implements Initializable{

    @FXML
    private SplitPane SplitPane;

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
    private TableColumn<Client, String> clmCognom1;

    @FXML
    private TableColumn<?, ?> clmCognom2;

    @FXML
    private TableColumn<?, ?> clmDataNaixament;

    @FXML
    private TableColumn<?, ?> clmDireccio;

    @FXML
    private TableColumn<?, ?> clmDni;

    @FXML
    private TableColumn<?, ?> clmMail;

    @FXML
    private TableColumn<?, ?> clmNom;

    @FXML
    private TableColumn<?, ?> clmTelefon;

    @FXML
    private DatePicker dateDataNaixament;

    @FXML
    private TableView<?> tblViewClient;

    @FXML
    private TextField textCerca;

    @FXML
    private TextField textCognom1;

    @FXML
    private TextField textCognom2;

    @FXML
    private TextField textDireccio;

    @FXML
    private TextField textDni;

    @FXML
    private TextField textMail;

    @FXML
    private TextField textNom;

    @FXML
    private TextField textTelefon;

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
    void netejar(ActionEvent event) {

    }

    @FXML
    void tornar(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
