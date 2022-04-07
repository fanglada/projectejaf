package dam2.jaf;

import java.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Botiga;
import model.Carnet;
import model.Empleat;
import model.TipusVehicle;
import model.Vehicle;

public class vehicleController {

	private ObservableList<Vehicle> llistaVehicles;
	private ObservableList<Botiga> llistaBotiga;
	
	private FilteredList<Vehicle> llistaFiltrada;
	
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
    private ComboBox<?> cbxCanvi;

    @FXML
    private ComboBox<?> cbxCarnet;

    @FXML
    private ComboBox<?> cbxTipusVehicle;

    @FXML
    private TableColumn<Vehicle, Integer> clmCV;

    @FXML
    private TableColumn<Vehicle, String> clmCanvi;

    @FXML
    private TableColumn<Vehicle, Integer> clmCapacitat;

    @FXML
    private TableColumn<Vehicle, Carnet> clmCarnet;

    @FXML
    private TableColumn<Vehicle, LocalDate> clmDataMatriculacio;

    @FXML
    private TableColumn<Vehicle, String> clmMarca;

    @FXML
    private TableColumn<Vehicle, String> clmMatricula;

    @FXML
    private TableColumn<Vehicle, String> clmModel;

    @FXML
    private TableColumn<Vehicle, Integer> clmNumPortes;

    @FXML
    private TableColumn<Vehicle, Integer> clmNumRodes;

    @FXML
    private TableColumn<Vehicle, TipusVehicle> clmTipusVehicle;

    @FXML
    private TableView<Vehicle> tblViewVehicle;

    @FXML
    private TextField textCapacitat;

    @FXML
    private TextField textCerca;

    @FXML
    private TextField textCodi;

    @FXML
    private TextField textDescripcio;

    @FXML
    private TextField textDescripcio1;

    @FXML
    private TextField textMarca;

    @FXML
    private TextField textModel;

    @FXML
    private TextField textTelefon;

   

    

    

    

    @FXML
    void guardarRegistre(ActionEvent event) {

    }
    
    @FXML
    void actualizarRegistre(ActionEvent event) {

    }
    
    @FXML
    void eliminarRegistre(ActionEvent event) {

    }

    @FXML
    void tornar(ActionEvent event) {

    }

    @FXML
    void buidar(ActionEvent event) {

    }
    
    @FXML
    void Netejar(ActionEvent event) {

    }
}
