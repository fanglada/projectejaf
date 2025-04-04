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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.*;

public class EmpleatController implements Initializable{

	private ObservableList<Empleat> llistaEmpleats;
	private ObservableList<Botiga> llistaBotiga;

	private FilteredList<Empleat> llistaFiltrada;

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
	private TableColumn<Empleat, Botiga> clmBotiga;

	@FXML
	private DatePicker dateDataNaixament;

	@FXML
	private TableView<Empleat> tblViewEmpleat;

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
	
	private boolean data = true;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		App.setTitol("Empleat");

		llistaEmpleats=FXCollections.observableArrayList();
		llistaBotiga=FXCollections.observableArrayList();

		llistaFiltrada=new FilteredList<>(llistaEmpleats, p -> true);

		tblViewEmpleat.setItems(llistaFiltrada);
		cbxBotiga.setItems(llistaBotiga);


		clmCognom1.setCellValueFactory(new PropertyValueFactory<Empleat, String>("cognom1"));
		clmCognom2.setCellValueFactory(new PropertyValueFactory<Empleat, String>("cognom2"));
		clmDataNaixament.setCellValueFactory(new PropertyValueFactory<Empleat, String>("dataNaixament"));
		clmDireccio.setCellValueFactory(new PropertyValueFactory<Empleat, String>("direccio"));
		clmDni.setCellValueFactory(new PropertyValueFactory<Empleat, String>("dni"));
		clmMail.setCellValueFactory(new PropertyValueFactory<Empleat, String>("mail"));
		clmNom.setCellValueFactory(new PropertyValueFactory<Empleat, String>("nom"));
		clmTelefon.setCellValueFactory(new PropertyValueFactory<Empleat, String>("telefon"));
		clmBotiga.setCellValueFactory(new PropertyValueFactory<Empleat,Botiga>("botiga"));

		EmpleatDAOImpl.Tots(App.con, llistaEmpleats);

		BotigaDAOImpl.Tots(App.con, llistaBotiga);

		gestionarEvents();
	}

	private void gestionarEvents() {

		tblViewEmpleat.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Empleat>() {

			@Override
			public void changed(ObservableValue<? extends Empleat> observable, Empleat oldValue, Empleat newValue) {

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
			    		missatge.setTitle("Hi ha un problema, el empleat ha de ser major d'edat");
						missatge.setContentText("Hi ha un problema, el empleat ha de ser major d'edat");
						missatge.setHeaderText("Alerta:");
						missatge.show();
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

		if(!textDni.getText().isEmpty() && !textNom.getText().isEmpty() && !textCognom1.getText().isEmpty() && !textCognom2.getText().isEmpty() && dateDataNaixament.getValue() !=null && !textTelefon.getText().isEmpty() && !textDireccio.getText().isEmpty() && !textMail.getText().isEmpty() && cbxBotiga.getValue() !=null) {

			Empleat empleat = new Empleat(textDni.getText(), textNom.getText(), textCognom1.getText(), textCognom2.getText(), dateDataNaixament.getValue(), textTelefon.getText(), textDireccio.getText(), textMail.getText(), cbxBotiga.getValue());

			EmpleatDAO empleatDAO = new EmpleatDAOImpl();    	
			int res = empleatDAO.create(App.con, empleat);

			if(res>0) {
				llistaEmpleats.add(empleat);

				Alert missatge = new Alert(AlertType.INFORMATION);
				missatge.setTitle("Resgistre afegit");
				missatge.setContentText("L'Empleat s'ha afegit correctament");
				missatge.setHeaderText("Resultat:");
				missatge.show();

				Netejar(event);
			}else {

				Alert missatge = new Alert(AlertType.ERROR);
				missatge.setTitle("Error en afegir el registre");
				if(res == -4) 
				{
					missatge.setContentText("Hi ha un problema, no s'ha pogut donar d'alta, l'empleat ja existeix");
				}else 
				{
					missatge.setContentText("L'Empleat no s'ha pogut afegir");
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
	void actualitzarRegistre(ActionEvent event) {

		Empleat empleat = new Empleat(textDni.getText(), textNom.getText(), textCognom1.getText(), textCognom2.getText(), dateDataNaixament.getValue(), textTelefon.getText(), textDireccio.getText(), textMail.getText(), cbxBotiga.getValue());

		EmpleatDAO empleatDAO = new EmpleatDAOImpl();    	
		int res = empleatDAO.update(App.con, empleat);

		if(res>0) {

			if(tblViewEmpleat.getSelectionModel().getSelectedIndex()!=-1) {
				llistaEmpleats.set(tblViewEmpleat.getSelectionModel().getSelectedIndex(),empleat);
			}
			else {
				llistaEmpleats.set(llistaEmpleats.size(), empleat);
			}

			Alert missatge = new Alert(AlertType.INFORMATION);
			missatge.setTitle("Resgistre afegit");
			missatge.setContentText("L'Empleat s'ha actualitzat correctament");
			missatge.setHeaderText("Resultat:");
			missatge.show();

			Netejar(event);
		}else {

			Alert missatge = new Alert(AlertType.ERROR);
			missatge.setTitle("Error en actualitzar el registre");
			missatge.setContentText("L'Empleat no s'ha pogut actualitzar");
			missatge.setHeaderText("Resultat:");
			missatge.show(); 		
		}  	
	}


	@FXML
	void eliminarRegistre(ActionEvent event) {

		EmpleatDAO empleatDAO = new EmpleatDAOImpl();    	
		int res = empleatDAO.delete(App.con, tblViewEmpleat.getSelectionModel().getSelectedItem().getDni());

		if(res>0) {
			llistaEmpleats.remove(tblViewEmpleat.getSelectionModel().getSelectedItem());

			Alert missatge = new Alert(AlertType.INFORMATION);
			missatge.setTitle("El registre s'ha eliminat");
			missatge.setContentText("L'Empleat s'ha eliminat correctament");
			missatge.setHeaderText("Resultat:");
			missatge.show();

			Netejar(event);
		}else {

			Alert missatge = new Alert(AlertType.ERROR);
			missatge.setTitle("Error en eliminar el registre");
			missatge.setContentText("L'Empleat no s'ha pogut eliminar");
			missatge.setHeaderText("Resultat:");
			missatge.show(); 		
		}   	
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

		textDni.setEditable(true);

		botoActualitzar.setDisable(true);
		botoEliminar.setDisable(true);
		botoGuardar.setDisable(false);
		
    	data = true;
	}
}
