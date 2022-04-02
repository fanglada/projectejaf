package dam2.jaf;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.*;

public class EmpleatSupervisorController implements Initializable{

	@FXML
    private AnchorPane anchor;

    @FXML
    private Button botoActualizar;

    @FXML
    private Button botoBuidar;
    
	@FXML
	private Button botoNetejar;

    @FXML
    private Button botoEliminar;

    @FXML
    private Button botoGuardar;

    @FXML
    private Button botoTornar;

    @FXML
    private CheckBox cboxSupervisor;

    @FXML
    private TableColumn<Empleat, String> clmCognom1;

    @FXML
    private TableColumn<Empleat, String> clmCognom2;

    @FXML
    private TableColumn<Empleat, String> clmDataNaixament;

    @FXML
    private TableColumn<Empleat, String> clmDireccio;

    @FXML
    private TableColumn<Empleat, String> clmDni;

    @FXML
    private TableColumn<Empleat, String> clmMail;

    @FXML
    private TableColumn<Empleat, String> clmNom;

    @FXML
    private TableColumn<Empleat, String> clmTelefon;

    @FXML
    private TableColumn<Empleat, String> clmTelefonEmpresa;

    @FXML
    private DatePicker dateDataNaixament;

    @FXML
    private TableView<Empleat> tblViewEmpleatSupervior;

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
    private TextField textTelefonEmpresa;

    @FXML
    void activarSupervisor(ActionEvent event) {
    	
    	textTelefonEmpresa.setDisable(!cboxSupervisor.isSelected());
    }

    @FXML
    void actualizarRegistre(ActionEvent event) {

    }

    @FXML
    void buidar(ActionEvent event) {
    	textCerca.setText(null);
    }
    
	@FXML
	void Netejar(ActionEvent event) {
		textDni.setText(null);
		textNom.setText(null);
		textCognom1.setText(null);
		textCognom2.setText(null);
		textDireccio.setText(null);
		textMail.setText(null);
		textTelefon.setText(null);
		dateDataNaixament.setValue(null);

		textTelefonEmpresa.setText(null);
	}

    @FXML
    void eliminarRegistre(ActionEvent event) {

    }

    @FXML
    void guardarRegistre(ActionEvent event) {

    }

    @FXML
    void tornar(ActionEvent event) throws IOException {
    	App.setRoot("usuaris");

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
   		gestionarEvents();

	}

	private void gestionarEvents() {
		// TODO Auto-generated method stub
		
	}
}
