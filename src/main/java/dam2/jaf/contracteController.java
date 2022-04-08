package dam2.jaf;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import model.*;

public class contracteController implements Initializable{

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
    private CheckBox cboxConductor;

    @FXML
    private ComboBox<Client> cbxClient;

    @FXML
    private ComboBox<Conductor> cbxConductor;

    @FXML
    private ComboBox<Empleat> cbxEmpleat;

    @FXML
    private ComboBox<Estat> cbxEstat;

    @FXML
    private ComboBox<Vehicle> cbxVehicle;

    @FXML
    private TableColumn<Contracte, String> clmClient;

    @FXML
    private TableColumn<Contracte, String> clmCodi;

    @FXML
    private TableColumn<Contracte, String> clmConductor;

    @FXML
    private TableColumn<Contracte, String> clmDataFi;

    @FXML
    private TableColumn<Contracte, String> clmDataInici;

    @FXML
    private TableColumn<Contracte, String> clmEmpleat;

    @FXML
    private TableColumn<Contracte, String> clmEstat;

    @FXML
    private TableColumn<Contracte, String> clmVehicle;

    @FXML
    private DatePicker dateDataFi;

    @FXML
    private DatePicker dateDataInici;

    @FXML
    private TableView<Contracte> tblViewContracte;

    @FXML
    private TextField textCerca;

    @FXML
    private TextField textCodi;

    private ObservableList<Client> llistaClients;
    private ObservableList<Empleat> llistaEmpleats;
    private ObservableList<Vehicle> llistaVheicles;
    private ObservableList<Estat> llistaEstats;
    private ObservableList<Conductor> llistaConductors;
    private ObservableList<Contracte> llistaContractes;
    private ObservableList<Contracte> llistaFiltrada;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
    	llistaClients = FXCollections.observableArrayList();
    	llistaEmpleats = FXCollections.observableArrayList();
    	llistaVheicles = FXCollections.observableArrayList();
    	llistaEstats = FXCollections.observableArrayList();
    	llistaConductors = FXCollections.observableArrayList();
    	llistaContractes = FXCollections.observableArrayList();
    	llistaFiltrada = new FilteredList<>(llistaContractes, p -> true);
    	
    	ClientDAOImpl.Tots(App.con, llistaClients);
    	EmpleatDAOImpl.Tots(App.con, llistaEmpleats);
    	VehicleDAOImpl.Tots(App.con, llistaVheicles);
    	EstatDAOImpl.Tots(App.con, llistaEstats);
    	ConductorDAOImpl.Tots(App.con, llistaConductors);
    	ContracteDAOImpl.Tots(App.con, llistaContractes);
    	
    	cbxClient.setItems(llistaClients);
    	cbxEmpleat.setItems(llistaEmpleats);
    	cbxVehicle.setItems(llistaVheicles);
    	cbxEstat.setItems(llistaEstats);
    	cbxConductor.setItems(llistaConductors);
    	tblViewContracte.setItems(llistaFiltrada);
    	
    	clmCodi.setCellValueFactory(new PropertyValueFactory<Contracte,String>("idContracte"));
    	clmClient.setCellValueFactory(new PropertyValueFactory<Contracte,String>("client"));
    	clmEmpleat.setCellValueFactory(new PropertyValueFactory<Contracte,String>("empleat"));
    	clmConductor.setCellValueFactory(new PropertyValueFactory<Contracte,String>("conductor"));
    	clmDataInici.setCellValueFactory(new PropertyValueFactory<Contracte,String>("dataInici"));
    	clmDataFi.setCellValueFactory(new PropertyValueFactory<Contracte,String>("dataFi"));
    	clmVehicle.setCellValueFactory(new PropertyValueFactory<Contracte,String>("estat"));
    	clmEstat.setCellValueFactory(new PropertyValueFactory<Contracte,String>("vehicle"));

	}
	
	private void gestionarEvents() {
		
		
	}
	
    @FXML
    void guardarRegistre(ActionEvent event) {
    	
    	if(cbxClient.getValue() != null && cbxEmpleat.getValue() != null && dateDataInici.getValue() != null && dateDataFi.getValue() != null && cbxVehicle.getValue() !=null && cbxEstat.getValue() != null) 
    	{
    		
    		Contracte contracte;
    		
    		if (cboxConductor.isSelected()) 
    		{
    			contracte =  new Contracte(cbxClient.getValue(), cbxEmpleat.getValue(), cbxConductor.getValue(), dateDataInici.getValue(), dateDataFi.getValue(), cbxEstat.getValue(), cbxVehicle.getValue());
    		}else 
    		{
    			contracte =  new Contracte(cbxClient.getValue(), cbxEmpleat.getValue(), dateDataInici.getValue(), dateDataFi.getValue(), cbxEstat.getValue(), cbxVehicle.getValue());
    		}
        	ContracteDAO ContracteDAO = new ContracteDAOImpl();
        	int resultat = ContracteDAO.create(App.con, contracte);
        	
        	if (resultat>0)
        	{
        		llistaContractes.add(contracte);
        		Alert missatge=new Alert(AlertType.INFORMATION);
    			missatge.setTitle("Contracte creat");
    			missatge.setContentText("S'ha creat correctament, però sempre va bé comprovar");
    			missatge.setHeaderText("Alerta:");
    			missatge.show();
    			Netejar(null);
    			
        	}else 
        	{
        		Alert missatge=new Alert(AlertType.ERROR);
    			missatge.setTitle("Hi ha un problema, no s'ha pogut crear el contracte");
    			missatge.setContentText("Hi ha un problema, no s'ha pogut crear el contracte");
    			missatge.setHeaderText("Alerta:");
    			missatge.show();
        		
        	}
    		
    	}

    }

    @FXML
    void actualizarRegistre(ActionEvent event) {

    }


    @FXML
    void eliminarRegistre(ActionEvent event) {

    }
    

    @FXML
    void gestionarConductor(ActionEvent event) {
    	cbxConductor.setDisable(!cbxConductor.isDisabled());
    }

	@FXML
    void seleccionarClient(MouseEvent event) {
    	
    	botoGuardar.setDisable(true);
    	textCodi.setEditable(false);
    	botoActualizar.setDisable(false);
    	botoEliminar.setDisable(false);
		Contracte aux = tblViewContracte.getSelectionModel().getSelectedItem();
		if(aux != null) 
		{
			carregarContracte(aux); 
		}
		

    }
    
    private void carregarContracte(Contracte contracte) 
    {
    	textCodi.setText(String.valueOf(contracte.getIdContracte()));
    	cbxClient.getSelectionModel().select(contracte.getClient());
    	cbxEmpleat.getSelectionModel().select(contracte.getEmpleat());
    	if(contracte.getConductor() != null) 
    	{
    		cboxConductor.setSelected(true);
    		cbxConductor.setDisable(false);
    		cbxConductor.getSelectionModel().select(contracte.getConductor());
    		
    	}else 
    	{
    		cboxConductor.setSelected(false);
    		cbxConductor.setDisable(true);
    		
    	}
    	dateDataInici.setValue(contracte.getDataInici());
    	dateDataFi.setValue(contracte.getDataFi());
    	cbxVehicle.getSelectionModel().select(contracte.getVehicle());
    	cbxEstat.getSelectionModel().select(contracte.getEstat());
	}

    @FXML
    void tornar(ActionEvent event) throws IOException {
    	
    	App.setRoot("Menu");

    }
    
    @FXML
    void buidar(ActionEvent event) {
    	
    }
    
    @FXML
    void Netejar(ActionEvent event) {
    	textCodi.setText(null);
    	cbxClient.getSelectionModel().select(null);
    	cbxEmpleat.getSelectionModel().select(null);
    	cboxConductor.setSelected(false);
    	cbxConductor.setDisable(true);
    	dateDataInici.setValue(null);
    	dateDataFi.setValue(null);
    	cbxVehicle.getSelectionModel().select(null);
    	cbxEstat.getSelectionModel().select(null);
    	botoGuardar.setDisable(false);
    	botoActualizar.setDisable(true);
    	botoEliminar.setDisable(true);
    	
    }

}
