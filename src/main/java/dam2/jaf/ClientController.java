package dam2.jaf;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.*;

public class ClientController implements Initializable {

	private ObservableList<Client> llistaClients;
	private ObservableList<Carnet> llistaCarnets;

	private FilteredList<Client> llistaFiltrada;
	
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
    private CheckComboBox<Carnet> chcbxCarnet;

	@FXML
	private TableColumn<Client, String> clmCognom1;

	@FXML
	private TableColumn<Client, String> clmCognom2;

	@FXML
	private TableColumn<Client, LocalDate> clmDataNaixament;

	@FXML
	private TableColumn<Client, String> clmDireccio;

	@FXML
	private TableColumn<Client, String> clmDni;

	@FXML
	private TableColumn<Client, String> clmMail;

	@FXML
	private TableColumn<Client, String> clmNom;

	@FXML
	private TableColumn<Client, String> clmTelefon;

	@FXML
	private DatePicker dateDataNaixament;

	@FXML
	private TableView<Client> tblViewClient;

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
		
//		chcbxCarnet
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
		
		llistaClients=FXCollections.observableArrayList();
    	llistaCarnets=FXCollections.observableArrayList();
    	llistaFiltrada = new FilteredList<>(llistaClients, p -> true);

    	tblViewClient.setItems(llistaFiltrada);
    	
    	clmDni.setCellValueFactory(new PropertyValueFactory<Client,String>("dni"));
    	clmNom.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
    	clmCognom1.setCellValueFactory(new PropertyValueFactory<Client,String>("cognom1"));
    	clmCognom2.setCellValueFactory(new PropertyValueFactory<Client,String>("cognom2"));
    	clmDataNaixament.setCellValueFactory(new PropertyValueFactory<Client,LocalDate>("dataNaixament"));
    	clmTelefon.setCellValueFactory(new PropertyValueFactory<Client,String>("telefon"));
    	clmDireccio.setCellValueFactory(new PropertyValueFactory<Client,String>("direccio"));
    	clmMail.setCellValueFactory(new PropertyValueFactory<Client,String>("mail"));
    	
    	//Mostar multiple

    	
    	ClientDAOImpl.Tots(App.con, llistaClients);
    	
    	CarnetDAOImpl.Tots(App.con, llistaCarnets);
    	
    	chcbxCarnet.getItems().addAll(llistaCarnets);//


    	gestionarEvents();

	}

	private void gestionarEvents() {
		// TODO Auto-generated method stub
		
	}

}
