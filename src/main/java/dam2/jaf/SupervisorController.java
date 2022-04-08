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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.*;

public class SupervisorController implements Initializable{

	private ObservableList<Supervisor> llistaSupervisors;
	private ObservableList<Botiga> llistaBotiga;

	private FilteredList<Supervisor> llistaFiltrada;

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
	private ComboBox<Botiga> cbxBotiga;

	@FXML
	private TableColumn<Supervisor, String> clmCognom1;

	@FXML
	private TableColumn<Supervisor, String> clmCognom2;

	@FXML
	private TableColumn<Supervisor, String> clmDataNaixament;

	@FXML
	private TableColumn<Supervisor, String> clmDireccio;

	@FXML
	private TableColumn<Supervisor, String> clmDni;

	@FXML
	private TableColumn<Supervisor, String> clmMail;

	@FXML
	private TableColumn<Supervisor, String> clmNom;

	@FXML
	private TableColumn<Supervisor, String> clmTelefon;
	
	@FXML
	private TableColumn<Supervisor, Botiga> clmBotiga;
	
	@FXML
	private TableColumn<Supervisor, String> clmTelefonEmpresa;

	@FXML
	private DatePicker dateDataNaixament;

	@FXML
	private TableView<Supervisor> tblViewSupervisor;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		App.setTitol("Supervisor");
		
		llistaSupervisors=FXCollections.observableArrayList();
		llistaBotiga=FXCollections.observableArrayList();
		llistaFiltrada=new FilteredList<>(llistaSupervisors, p -> true);

		tblViewSupervisor.setItems(llistaFiltrada);
		cbxBotiga.setItems(llistaBotiga);

		clmCognom1.setCellValueFactory(new PropertyValueFactory<Supervisor, String>("cognom1"));
		clmCognom2.setCellValueFactory(new PropertyValueFactory<Supervisor, String>("cognom2"));
		clmDataNaixament.setCellValueFactory(new PropertyValueFactory<Supervisor, String>("dataNaixament"));
		clmDireccio.setCellValueFactory(new PropertyValueFactory<Supervisor, String>("direccio"));
		clmDni.setCellValueFactory(new PropertyValueFactory<Supervisor, String>("dni"));
		clmMail.setCellValueFactory(new PropertyValueFactory<Supervisor, String>("mail"));
		clmNom.setCellValueFactory(new PropertyValueFactory<Supervisor, String>("nom"));
		clmTelefon.setCellValueFactory(new PropertyValueFactory<Supervisor, String>("telefon"));
		clmBotiga.setCellValueFactory(new PropertyValueFactory<Supervisor, Botiga>("botiga"));
		clmTelefonEmpresa.setCellValueFactory(new PropertyValueFactory<Supervisor, String>("telefonEmpresa"));

		SupervisorDAOImpl.Tots(App.con, llistaSupervisors);
		
		BotigaDAOImpl.Tots(App.con, llistaBotiga);

		gestionarEvents();
	}

	private void gestionarEvents() {

		tblViewSupervisor.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Supervisor>() {

			@Override
			public void changed(ObservableValue<? extends Supervisor> observable, Supervisor oldValue, Supervisor newValue) {

				if(newValue!=null) {

					textNom.setText(String.valueOf(newValue.getNom()));
					textCognom1.setText(String.valueOf(newValue.getCognom1()));
					textCognom2.setText(newValue.getCognom2());
					dateDataNaixament.setValue(newValue.getDataNaixament()); 
					textDireccio.setText(newValue.getDireccio());
					textDni.setText(newValue.getDni());
					textMail.setText(newValue.getMail());
					textTelefon.setText(newValue.getTelefon());
					cbxBotiga.setValue(newValue.getBotiga());
					textTelefonEmpresa.setText(newValue.getTelefonEmpresa());
					
					textDni.setEditable(false);

					botoActualitzar.setDisable(false);
					botoEliminar.setDisable(false);
					botoGuardar.setDisable(true);
				}
			}
		});
		
		textTelefon.textProperty().addListener(new ChangeListener<>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
		    	if (!newValue.matches("-?([0-9]*)?") && newValue!=null) {
		    		textTelefon.setText(oldValue);
		        }
			}});;
			
			textTelefonEmpresa.textProperty().addListener(new ChangeListener<>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					// TODO Auto-generated method stub
			    	if (!newValue.matches("-?([0-9]*)?") && newValue!=null) {
			    		textTelefonEmpresa.setText(oldValue);
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
				        }
					}
			 
				}});

		textCerca.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				llistaFiltrada.setPredicate(stringCerca -> {
					if(newValue == null || newValue.isEmpty()) return true;

					if(stringCerca.getNom().toLowerCase().contains(newValue.toLowerCase()) || stringCerca.getCognom1().toLowerCase().contains(newValue.toLowerCase()) || stringCerca.getCognom2().toLowerCase().contains(newValue.toLowerCase()) || stringCerca.getDireccio().toLowerCase().contains(newValue.toLowerCase()) || stringCerca.getDni().toLowerCase().contains(newValue.toLowerCase()) || stringCerca.getMail().toLowerCase().contains(newValue.toLowerCase()) || stringCerca.getTelefon().toLowerCase().contains(newValue.toLowerCase()))						
						
						return true;

					return false;
				});										
			}			
		});		
	}
	

	@FXML
	void guardarRegistre(ActionEvent event) {

		Supervisor supervisor = new Supervisor(textDni.getText(), textNom.getText(), textCognom1.getText(), textCognom2.getText(), dateDataNaixament.getValue(), textTelefon.getText(), textDireccio.getText(), textMail.getText(), textTelefonEmpresa.getText(), cbxBotiga.getValue());
    	
    	SupervisorDAO supervisorDAO = new SupervisorDAOImpl();    	
    	int res = supervisorDAO.create(App.con, supervisor);
    	
    	if(res>0) {
    		llistaSupervisors.add(supervisor);
    		
    		Alert missatge = new Alert(AlertType.INFORMATION);
    		missatge.setTitle("Resgistre afegit");
    		missatge.setContentText("El Supervisor s'ha afegit correctament");
    		missatge.setHeaderText("Resultat:");
    		missatge.show();
    		
    		Netejar(event);
    	}else {
    		
    		Alert missatge = new Alert(AlertType.ERROR);
    		missatge.setTitle("Error en afegir el registre");
    		missatge.setContentText("El Supervisor no s'ha pogut afegir");
    		missatge.setHeaderText("Resultat:");
    		missatge.show();   		
    	}
    }
	
	@FXML
	void actualitzarRegistre(ActionEvent event) {

		Supervisor supervisor = new Supervisor(textDni.getText(), textNom.getText(), textCognom1.getText(), textCognom2.getText(), dateDataNaixament.getValue(), textTelefon.getText(), textDireccio.getText(), textMail.getText(), textTelefonEmpresa.getText(), cbxBotiga.getValue());
    	
    	SupervisorDAO supervisorDAO = new SupervisorDAOImpl();    	
    	int res = supervisorDAO.update(App.con, supervisor);
    	
    	if(res>0) {
    		
    		if(tblViewSupervisor.getSelectionModel().getSelectedIndex()!=-1) {
    			llistaSupervisors.set(tblViewSupervisor.getSelectionModel().getSelectedIndex(),supervisor);
    		}
    		else {
    			llistaSupervisors.set(llistaSupervisors.size(), supervisor);
    		}
    		
    		Alert missatge = new Alert(AlertType.INFORMATION);
    		missatge.setTitle("Resgistre afegit");
    		missatge.setContentText("El Supervisor s'ha actualitzat correctament");
    		missatge.setHeaderText("Resultat:");
    		missatge.show();
    		
    		Netejar(event);
    	}else {
    		
    		Alert missatge = new Alert(AlertType.ERROR);
    		missatge.setTitle("Error en actualitzar el registre");
    		missatge.setContentText("El Supervisor no s'ha pogut actualitzar");
    		missatge.setHeaderText("Resultat:");
    		missatge.show(); 		
    	}  	
    }

	@FXML
	void eliminarRegistre(ActionEvent event) {

		SupervisorDAO supervisorDAO = new SupervisorDAOImpl();    	
    	int res = supervisorDAO.delete(App.con, tblViewSupervisor.getSelectionModel().getSelectedItem().getDni());
    	
    	if(res>0) {
    		llistaSupervisors.remove(tblViewSupervisor.getSelectionModel().getSelectedItem());
    		
    		Alert missatge = new Alert(AlertType.INFORMATION);
    		missatge.setTitle("El registre s'ha eliminat");
    		missatge.setContentText("El Supervisor s'ha eliminat correctament");
    		missatge.setHeaderText("Resultat:");
    		missatge.show();
    		
    		Netejar(event);
    	}else {
    		
    		Alert missatge = new Alert(AlertType.ERROR);
    		missatge.setTitle("Error en eliminar el registre");
    		missatge.setContentText("El Supervisor no s'ha pogut eliminar");
    		missatge.setHeaderText("Resultat:");
    		missatge.show(); 		
    	}   	
    }
	
	@FXML
	void tornar(ActionEvent event) throws IOException {
		App.setRoot("usuaris");
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
		textTelefon.setText("");
		dateDataNaixament.setValue(null);
		cbxBotiga.setValue(null);
		textTelefonEmpresa.setText("");
		
		textDni.setEditable(true);
		botoActualitzar.setDisable(true);
		botoEliminar.setDisable(true);
		botoGuardar.setDisable(false);
	}
}
