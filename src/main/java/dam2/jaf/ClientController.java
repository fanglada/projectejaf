package dam2.jaf;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;

public class ClientController implements Initializable {

	private ObservableList<Client> llistaClients;
	private ObservableList<Carnet> llistaCarnets;

	private FilteredList<Client> llistaFiltrada;
	
	@FXML
	private AnchorPane anchor;

	@FXML
	private Button botoActualitzar;

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
    private TableColumn<Client, Void> clmCarnets;

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
    private Button botoTornarCarnets;

    @FXML
    private TableColumn<Carnet, String> clmTaulaCarnets;

    @FXML
    private TableView<Carnet> taulaCarnets;
	
	
	
	private Stage stageTaula;
	
	private boolean taula = false;

	@FXML
	void actualizarRegistre(ActionEvent event) {

	}

	@FXML
	void buidar(ActionEvent event) {
    	textCerca.setText(null);
	}
	
	@FXML
	void Netejar(ActionEvent event) {
		
		botoGuardar.setDisable(false);
    	botoActualitzar.setDisable(true);
    	botoEliminar.setDisable(true);
    	
		textDni.setText(null);
		textNom.setText(null);
		textCognom1.setText(null);
		textCognom2.setText(null);
		textDireccio.setText(null);
		textMail.setText(null);
		textTelefon.setText("");
		dateDataNaixament.setValue(null);
		chcbxCarnet.getCheckModel().clearChecks();

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

	//@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		if (!taula) {
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
	    	clmCarnets.setCellFactory(new Callback<TableColumn<Client, Void>, TableCell<Client, Void>>() {
	            @Override
	            public TableCell<Client, Void> call(final TableColumn<Client, Void> param) {
	                final TableCell<Client, Void> cell = new TableCell<Client, Void>() {
	
	                    private final Button btn = new Button("Carnets");
	
	                    {
	                        btn.setOnAction((ActionEvent event) -> {
	                        	taula = true;
	                            Client client = getTableView().getItems().get(getIndex());
	                        	System.out.println(client.getNom());
	                        	obrirTaula(client);
	                        });
	                    }
	
	                    @Override
	                    public void updateItem(Void item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (empty) {
	                            setGraphic(null);
	                        } else {
	                            setGraphic(btn);
	                        }
	                    }
	                };
	                return cell;
	            }
	        });
	
	    	    	
	    	//Mostar multiple
	
	    	
	    	ClientDAOImpl.Tots(App.con, llistaClients);
	    	
	    	CarnetDAOImpl.Tots(App.con, llistaCarnets);
	    	
	    	chcbxCarnet.getItems().addAll(llistaCarnets);//
	
	
	    	gestionarEvents();
		}else if(taula) 
		{
			clmTaulaCarnets.setCellValueFactory(new PropertyValueFactory<Carnet,String>("descripcio"));
		}

	}
	
    @FXML
    void seleccionarClient(MouseEvent event) {
    	
    	botoGuardar.setDisable(true);
    	botoActualitzar.setDisable(false);
    	botoEliminar.setDisable(false);
		Client aux = tblViewClient.getSelectionModel().getSelectedItem();
		carregarClient(aux); 

    }
    
    private void carregarClient(Client client) 
    {
    	textDni.setText(client.getDni());
    	textNom.setText(client.getNom());
    	textCognom1.setText(client.getCognom1());
    	textCognom2.setText(client.getCognom2());
    	dateDataNaixament.setValue(client.getDataNaixament());
    	textTelefon.setText(client.getTelefon());
    	textMail.setText(client.getMail());
    	textDireccio.setText(client.getDireccio());
    	client.getCarnet().stream().forEach((carnet)->{chcbxCarnet.getCheckModel().check(trobarCarnet(carnet));});
    	
    }
    
    private Carnet trobarCarnet(Carnet carnet) 
    {
    	int i = 0;
    	while(llistaCarnets.get(i).getIdCarnet() != carnet.getIdCarnet())
    	{
    		i++;
    	}
    	return llistaCarnets.get(i);
    }

	private void gestionarEvents() {
		
		textTelefon.textProperty().addListener(new ChangeListener<>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
		    	if (!newValue.matches("-?([0-9]*)?") && newValue!=null) {
		    		textTelefon.setText(oldValue);
		        }
			}});;
		
	}
	
    void obrirTaula(Client client) 
    {
    	try {	    		
    		FXMLLoader loader = new FXMLLoader(App.class.getResource("carnetsTaula" + ".fxml"));
    		loader.setController(this);
    		Parent root = loader.load();
    		stageTaula = new Stage();
    		stageTaula.initModality(Modality.APPLICATION_MODAL);
    		stageTaula.setTitle("Carents de: " + client.getNom() + " " + client.getCognom1() + " " + client.getCognom2());
    		stageTaula.setScene(new Scene(root));
    		stageTaula.show(); 
  
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No s'ha pogit obrir la finestra de filtratge");
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    
    @FXML
    void tancarCarnets(MouseEvent event) {
    	stageTaula.close();
    }

}
