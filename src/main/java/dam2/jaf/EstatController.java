package dam2.jaf;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.*;


public class EstatController implements Initializable{

	private ObservableList<Estat> llistaEstats;
	private FilteredList<Estat> llistaFiltrada;
	
	
    @FXML
    private AnchorPane anchor;

    @FXML
    private Button botoActualitzar;

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
    private TableColumn<Estat, Integer> clmCodi;

    @FXML
    private TableColumn<Estat, String> clmDescripcio;

    @FXML
    private TableView<Estat> tblViewEstat;

    @FXML
    private TextField textCerca;

    @FXML
    private TextField textCodi;

    @FXML
    private TextField textDescripcio;

    @FXML
    void Netejar(ActionEvent event) {
    	
    	textCodi.setText(null);
    	textDescripcio.setText(null);
    	
    	botoActualitzar.setDisable(true);
		botoEliminar.setDisable(true);
		botoGuardar.setDisable(false);
    }

    @FXML
    void actualizarRegistre(ActionEvent event) {

    	Estat estat = new Estat(Integer.valueOf(textCodi.getText()), textDescripcio.getText());
    	
    	EstatDAO estatDAO = new EstatDAOImpl();    	
    	int res = estatDAO.Update(App.con, estat);
    	
    	if(res>0) {
    		
    		if(tblViewEstat.getSelectionModel().getSelectedIndex()!=-1) {
    			llistaEstats.set(tblViewEstat.getSelectionModel().getSelectedIndex(),estat);
    		}
    		else {
    			llistaEstats.set(llistaEstats.size(), estat);
    		}
    		
    		Alert missatge = new Alert(AlertType.INFORMATION);
    		missatge.setTitle("Resgistre afegit");
    		missatge.setContentText("L'Estat s'ha actualitzat correctament");
    		missatge.setHeaderText("Resultat:");
    		missatge.show();
    		
    		Netejar(event);
    	}else {
    		
    		Alert missatge = new Alert(AlertType.ERROR);
    		missatge.setTitle("Error en actualitzar el registre");
    		missatge.setContentText("L'Estat no s'ha pogut actualitzar");
    		missatge.setHeaderText("Resultat:");
    		missatge.show(); 		
    	}  	
    }

    @FXML
    void buidar(ActionEvent event) {
    	textCerca.setText(null);
    }

    @FXML
    void eliminarRegistre(ActionEvent event) {
    	
    	EstatDAO estatDAO = new EstatDAOImpl();    	
    	int res = estatDAO.Delete(App.con, tblViewEstat.getSelectionModel().getSelectedItem().getIdEstat());
    	
    	if(res>0) {
    		llistaEstats.remove(tblViewEstat.getSelectionModel().getSelectedItem());
    		
    		Alert missatge = new Alert(AlertType.INFORMATION);
    		missatge.setTitle("El registre s'ha eliminat");
    		missatge.setContentText("L'Estat s'ha eliminat correctament");
    		missatge.setHeaderText("Resultat:");
    		missatge.show();
    		
    		Netejar(event);
    	}else {
    		
    		Alert missatge = new Alert(AlertType.ERROR);
    		missatge.setTitle("Error en eliminar el registre");
    		missatge.setContentText("L'Estat no s'ha pogut eliminar");
    		missatge.setHeaderText("Resultat:");
    		missatge.show(); 		
    	}   	
    }

    @FXML
    void guardarRegistre(ActionEvent event) {
    	
    	Estat estat = new Estat(textDescripcio.getText());
    	
    	EstatDAO estatDAO = new EstatDAOImpl();    	
    	int res = estatDAO.Create(App.con, estat);
    	
    	if(res>0) {
    		llistaEstats.add(estat);
    		
    		Alert missatge = new Alert(AlertType.INFORMATION);
    		missatge.setTitle("Resgistre afegit");
    		missatge.setContentText("L'Estat s'ha afegit correctament");
    		missatge.setHeaderText("Resultat:");
    		missatge.show();
    		
    		Netejar(event);
    	}else {
    		
    		Alert missatge = new Alert(AlertType.ERROR);
    		missatge.setTitle("Error en afegir el registre");
    		missatge.setContentText("L'Estat no s'ha pogut afegir");
    		missatge.setHeaderText("Resultat:");
    		missatge.show();
    		
    	}
    }

    @FXML
    void tornar(ActionEvent event) throws IOException {
    	App.setRoot("gestio");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		llistaEstats=FXCollections.observableArrayList();
		llistaFiltrada=new FilteredList<>(llistaEstats, p -> true);
		
		tblViewEstat.setItems(llistaFiltrada);
		
		clmCodi.setCellValueFactory(new PropertyValueFactory<Estat, Integer>("idEstat"));
		clmDescripcio.setCellValueFactory(new PropertyValueFactory<Estat, String>("descripcio"));

		EstatDAOImpl.Tots(App.con, llistaEstats);
		
		gestionarEvents();
	}

	void gestionarEvents() {

		tblViewEstat.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Estat>() {

			@Override
			public void changed(ObservableValue<? extends Estat> observable, Estat oldValue, Estat newValue) {

				if(newValue!=null) {
					
					textCodi.setText(String.valueOf(newValue.getIdEstat()));
					textDescripcio.setText(newValue.getDescripcio());
					
					botoActualitzar.setDisable(false);
					botoEliminar.setDisable(false);
					botoGuardar.setDisable(true);

				}
				
			}
			
		});
		
		textCerca.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				llistaFiltrada.setPredicate(stringCerca -> {
					if(newValue == null || newValue.isEmpty()) return true;
					
					if(stringCerca.getDescripcio().toLowerCase().contains(newValue.toLowerCase()))
							
							return true;
					
					return false;
				});										
			}			
		});		
	}
}
