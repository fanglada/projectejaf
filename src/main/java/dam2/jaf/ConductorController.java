package dam2.jaf;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;

public class ConductorController implements Initializable{

	private ObservableList<Conductor> llistaConductor;
	private ObservableList<Carnet> llistaCarnets;

	private FilteredList<Conductor> llistaFiltrada;
	
	private ObservableList<Carnet> llistaCarnetsTaula;
	
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
    private TableColumn<Conductor, String> clmCognom1;

    @FXML
    private TableColumn<Conductor, String> clmCognom2;

    @FXML
    private TableColumn<Conductor, LocalDate> clmDataNaixament;

    @FXML
    private TableColumn<Conductor, String> clmDireccio;

    @FXML
    private TableColumn<Conductor, String> clmDni;

    @FXML
    private TableColumn<Conductor, String> clmMail;

    @FXML
    private TableColumn<Conductor, String> clmNom;

    @FXML
    private TableColumn<Conductor, String> clmTelefon;
    
    @FXML
    private TableColumn<Conductor, Void> clmCarnets;

    @FXML
    private DatePicker dateDataNaixament;

    @FXML
    private TableView<Conductor> tblViewConductor;

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
	
	private boolean data = true;

    
	@Override
	public void initialize(URL location, ResourceBundle resources) {


		if (!taula) {
			App.setTitol("Conductor");

			llistaConductor=FXCollections.observableArrayList();
	    	llistaCarnets=FXCollections.observableArrayList();
	    	llistaFiltrada = new FilteredList<>(llistaConductor, p -> true);
	
	    	tblViewConductor.setItems(llistaFiltrada);
	    	
	    	clmDni.setCellValueFactory(new PropertyValueFactory<Conductor,String>("dni"));
	    	clmNom.setCellValueFactory(new PropertyValueFactory<Conductor,String>("nom"));
	    	clmCognom1.setCellValueFactory(new PropertyValueFactory<Conductor,String>("cognom1"));
	    	clmCognom2.setCellValueFactory(new PropertyValueFactory<Conductor,String>("cognom2"));
	    	clmDataNaixament.setCellValueFactory(new PropertyValueFactory<Conductor,LocalDate>("dataNaixament"));
	    	clmTelefon.setCellValueFactory(new PropertyValueFactory<Conductor,String>("telefon"));
	    	clmDireccio.setCellValueFactory(new PropertyValueFactory<Conductor,String>("direccio"));
	    	clmMail.setCellValueFactory(new PropertyValueFactory<Conductor,String>("mail"));
	    	clmCarnets.setCellFactory(new Callback<TableColumn<Conductor, Void>, TableCell<Conductor, Void>>() {
	            @Override
	            public TableCell<Conductor, Void> call(final TableColumn<Conductor, Void> param) {
	                final TableCell<Conductor, Void> cell = new TableCell<Conductor, Void>() {
	
	                    private final Button btn = new Button("Carnets");
	
	                    {
	                        btn.setOnAction((ActionEvent event) -> {
	                        	taula = true;
	                        	Conductor conductor = getTableView().getItems().get(getIndex());
	                        	obrirTaula(conductor);
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
	
	    	ConductorDAOImpl.Tots(App.con, llistaConductor);
	    	
	    	CarnetDAOImpl.Tots(App.con, llistaCarnets);
	    	
	    	chcbxCarnet.getItems().addAll(llistaCarnets);
	
	
	    	gestionarEvents();
	    	
		}
		else if(taula) 
		{

			taulaCarnets.setItems(llistaCarnetsTaula);

			clmTaulaCarnets.setCellValueFactory(new PropertyValueFactory<Carnet,String>("descripcio"));

		}

	}

	private void gestionarEvents() {
		
		textTelefon.textProperty().addListener(new ChangeListener<>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
		    	if (!newValue.matches("-?([0-9]*)?") && newValue!=null) {
		    		textTelefon.setText(oldValue);
		        }
			}});
		
		dateDataNaixament.valueProperty().addListener(new ChangeListener<>() {

			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
				// TODO Auto-generated method stub
				if(newValue!=null)
				{
				   	if (newValue.compareTo(LocalDate.now().minusYears(18)) > 0) {
			    		dateDataNaixament.setValue(oldValue);
			    		
			    		Alert missatge=new Alert(AlertType.ERROR);
			    		missatge.setTitle("Hi ha un problema, el conductor ha de ser major d'edat");
						missatge.setContentText("Hi ha un problema, el conductor ha de ser major d'edat");
						missatge.setHeaderText("Alerta:");
						missatge.show();
			        }
				   	
				}
		 
			}});
		
		tblViewConductor.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Conductor>() {

			@Override
			public void changed(ObservableValue<? extends Conductor> observable, Conductor oldValue, Conductor newValue) {
				// TODO Auto-generated method stub
				if(newValue!=null)
				{
					textDni.setText(newValue.getDni());
			    	textNom.setText(newValue.getNom());
			    	textCognom1.setText(newValue.getCognom1());
			    	textCognom2.setText(newValue.getCognom2());
			    	dateDataNaixament.setValue(newValue.getDataNaixament());
			    	textTelefon.setText(newValue.getTelefon());
			    	textMail.setText(newValue.getMail());
			    	textDireccio.setText(newValue.getDireccio());
			    	chcbxCarnet.getCheckModel().clearChecks();
			    	newValue.getCarnet().stream().forEach((carnet)->{chcbxCarnet.getCheckModel().check(trobarCarnet(carnet));});
			    	textDni.setEditable(false);
					
					botoActualitzar.setDisable(false);
					botoEliminar.setDisable(false);
					botoGuardar.setDisable(true);
				}
			}
			
		});
		textCerca.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				llistaFiltrada.setPredicate(stringCerca -> {
					if(newValue == null || newValue.isEmpty()) return true;
					
					if(stringCerca.getNom().toLowerCase().contains(newValue.toLowerCase())||stringCerca.getCognom1().toLowerCase().contains(newValue.toLowerCase())||stringCerca.getCognom2().toLowerCase().contains(newValue.toLowerCase())||stringCerca.getDireccio().toLowerCase().contains(newValue.toLowerCase())||stringCerca.getDni().toLowerCase().contains(newValue.toLowerCase())||stringCerca.getMail().toLowerCase().contains(newValue.toLowerCase())||stringCerca.getTelefon().toLowerCase().contains(newValue.toLowerCase()))
						return true;
					
					return false;
				});
			}
		});
		
	}
	
    @FXML
    void guardarRegistre(ActionEvent event) {
    	
		if(!textDni.getText().isEmpty() && !textNom.getText().isEmpty() && !textCognom1.getText().isEmpty() && !textCognom2.getText().isEmpty() && dateDataNaixament.getValue()!=null && !textTelefon.getText().isEmpty() && !textDireccio.getText().isEmpty() && !textMail.getText().isEmpty() && chcbxCarnet.getCheckModel().getCheckedItems().size()!=0)
		{
	    	
	    	ArrayList<Carnet> carnets = new ArrayList<Carnet>();
			for(int i = 0; i < chcbxCarnet.getCheckModel().getCheckedItems().size(); i++) 
			{
				carnets.add(chcbxCarnet.getCheckModel().getCheckedItems().get(i));
			}
	
	    	Conductor conductor = new Conductor(textDni.getText(), textNom.getText(), textCognom1.getText(), textCognom2.getText(), dateDataNaixament.getValue(), textTelefon.getText(), textDireccio.getText(), textMail.getText(), carnets);
	    	
	    	ConductorDAO conductorDAO = new ConductorDAOImpl();    	
	    	int res = conductorDAO.create(App.con, conductor);
	    	
	    	if(res>0) {
	    		llistaConductor.add(conductor);
	    		
	    		Alert missatge = new Alert(AlertType.INFORMATION);
	    		missatge.setTitle("Resgistre afegit");
	    		missatge.setContentText("El conductor s'ha afegit correctament");
	    		missatge.setHeaderText("Resultat:");
	    		missatge.show();
	    		
	    		Netejar(event);
	    	}else {
	    		
	    		Alert missatge = new Alert(AlertType.ERROR);
	    		missatge.setTitle("Error en afegir el registre");
				if(res == -4) 
				{
					missatge.setContentText("Hi ha un problema, no s'ha pogut donar d'alta, el conductor ja existeix");
				}else 
				{
					missatge.setContentText("El conductor no s'ha pogut afegir");
				}
	    		missatge.setHeaderText("Resultat:");
	    		missatge.show();   		
	    	}
    	}
	 	else
    	{
    	
	    	Alert missatge=new Alert(AlertType.ERROR);
			missatge.setTitle("Hi ha un problema, alguns camps estan buits");
			missatge.setContentText("Hi ha un problema, alguns camps estan buits");
			missatge.setHeaderText("Alerta:");
			missatge.show();
    	}
    }

    @FXML
    void actualizarRegistre(ActionEvent event) {
    	
    	ArrayList<Carnet> carnets = new ArrayList<Carnet>();
		for(int i = 0; i < chcbxCarnet.getCheckModel().getCheckedItems().size(); i++) 
		{
			carnets.add(chcbxCarnet.getCheckModel().getCheckedItems().get(i));
		}
		
    	Conductor conductor = new Conductor(textDni.getText(), textNom.getText(), textCognom1.getText(), textCognom2.getText(), dateDataNaixament.getValue(), textTelefon.getText(), textDireccio.getText(), textMail.getText(), carnets);

    	ConductorDAO conductorDAO = new ConductorDAOImpl();    	
    	int res = conductorDAO.update(App.con, conductor);
    	
    	if(res>0) {
    		
    		if(tblViewConductor.getSelectionModel().getSelectedIndex()!=-1) {
    			llistaConductor.set(tblViewConductor.getSelectionModel().getSelectedIndex(),conductor);
    		}
    		else {
    			llistaConductor.set(llistaConductor.size(), conductor);
    		}
    		
    		Alert missatge = new Alert(AlertType.INFORMATION);
    		missatge.setTitle("Resgistre afegit");
    		missatge.setContentText("El conductor s'ha actualitzat correctament");
    		missatge.setHeaderText("Resultat:");
    		missatge.show();
    		
    		Netejar(event);
    	}else {
    		
    		Alert missatge = new Alert(AlertType.ERROR);
    		missatge.setTitle("Error en actualitzar el registre");
    		missatge.setContentText("El conductor no s'ha pogut actualitzar");
    		missatge.setHeaderText("Resultat:");
    		missatge.show(); 		
    	}  	
    }

    @FXML
    void eliminarRegistre(ActionEvent event) {
    	
    	ConductorDAO conductorDAO = new ConductorDAOImpl();    	
    	int res = conductorDAO.delete(App.con, tblViewConductor.getSelectionModel().getSelectedItem().getDni());
    	
    	if(res>0) {
    		llistaConductor.remove(tblViewConductor.getSelectionModel().getSelectedItem());
    		Alert missatge = new Alert(AlertType.INFORMATION);
    		missatge.setTitle("El registre s'ha eliminat");
    		missatge.setContentText("El conductor s'ha eliminat correctament");
    		missatge.setHeaderText("Resultat:");
    		missatge.show();
    		
    		Netejar(event);
    	}else {
    		
    		Alert missatge = new Alert(AlertType.ERROR);
    		missatge.setTitle("Error en eliminar el registre");
    		missatge.setContentText("El conductor no s'ha pogut eliminar");
    		missatge.setHeaderText("Resultat:");
    		missatge.show(); 		
    	}   	
    }
    
	void obrirTaula(Conductor conductor) 
    {
    	llistaCarnetsTaula=FXCollections.observableArrayList();

    	llistaCarnetsTaula.addAll(conductor.getCarnet());
    	try {	    		
    		FXMLLoader loader = new FXMLLoader(App.class.getResource("carnetsTaula.fxml"));
       		loader.setController(this);
    		Parent root = loader.load();
    		stageTaula = new Stage();
    		stageTaula.initModality(Modality.APPLICATION_MODAL);
    		stageTaula.setTitle("Carnets de: " + conductor.getNom() + " " + conductor.getCognom1() + " " + conductor.getCognom2());
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
    void tancarCarnets(ActionEvent event) {
    	stageTaula.close();
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
    
    @FXML
    void tornar(ActionEvent event) throws IOException {
    	App.setRoot("usuaris");

    }
    
    @FXML
    void data(MouseEvent event) {
		if(data)
		{
			data=false;
			dateDataNaixament.setValue(LocalDate.now().minusYears(18));

		}
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
		
    	textDni.setEditable(true);
    	data = true;

	}

}
