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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.*;

public class TipusVehicleController implements Initializable{

	private ObservableList<TipusVehicle> llistaTipusVehicles;
	private FilteredList<TipusVehicle> llistaFiltrada;

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
	private TableColumn<TipusVehicle, Integer> clmCodi;

	@FXML
	private TableColumn<TipusVehicle, String> clmDescripcio;

	@FXML
	private TableView<TipusVehicle> tblViewTipusVehicle;

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

		TipusVehicle tipusVehicle = new TipusVehicle(Integer.valueOf(textCodi.getText()), textDescripcio.getText());
    	
		TipusVehicleDAO tipusVehicleDAO = new TipusVehicleDAOImpl();    	
    	int res = tipusVehicleDAO.Update(App.con, tipusVehicle);
    	
    	if(res>0) {
    		
    		if(tblViewTipusVehicle.getSelectionModel().getSelectedIndex()!=-1) {
    			llistaTipusVehicles.set(tblViewTipusVehicle.getSelectionModel().getSelectedIndex(),tipusVehicle);
    		}
    		else {
    			llistaTipusVehicles.set(llistaTipusVehicles.size(), tipusVehicle);
    		}
    		
    		Alert missatge = new Alert(AlertType.INFORMATION);
    		missatge.setTitle("Resgistre afegit");
    		missatge.setContentText("El tipus de vehicle s'ha actualitzat correctament");
    		missatge.setHeaderText("Resultat:");
    		missatge.show();
    		
    		Netejar(event);
    	}else {
    		
    		Alert missatge = new Alert(AlertType.ERROR);
    		missatge.setTitle("Error en actualitzar el registre");
    		missatge.setContentText("El tipus de vehicle no s'ha pogut actualitzar");
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
		
		TipusVehicleDAO tipusVehicleDAO = new TipusVehicleDAOImpl();    	
    	int res = tipusVehicleDAO.Delete(App.con, tblViewTipusVehicle.getSelectionModel().getSelectedItem().getIdTipusVehicle());
    	
    	if(res>0) {
    		llistaTipusVehicles.remove(tblViewTipusVehicle.getSelectionModel().getSelectedItem());
    		
    		Alert missatge = new Alert(AlertType.INFORMATION);
    		missatge.setTitle("El tipus de vehicle s'ha eliminat");
    		missatge.setContentText("El tipus de vehicle s'ha eliminat correctament");
    		missatge.setHeaderText("Resultat:");
    		missatge.show();
    		
    		Netejar(event);
    	}else {
    		
    		Alert missatge = new Alert(AlertType.ERROR);
    		missatge.setTitle("Error en eliminar el registre");
    		missatge.setContentText("El tipus de vehicle no s'ha pogut eliminar");
    		missatge.setHeaderText("Resultat:");
    		missatge.show(); 		
    	}   	
    }

	@FXML
	void guardarRegistre(ActionEvent event) {
		
		TipusVehicle tipusVehicle = new TipusVehicle(textDescripcio.getText());
    	
		TipusVehicleDAO tipusVehicleDAO = new TipusVehicleDAOImpl();    	
    	int res = tipusVehicleDAO.Create(App.con, tipusVehicle);
    	
    	if(res>0) {
    		llistaTipusVehicles.add(tipusVehicle);
    		
    		Alert missatge = new Alert(AlertType.INFORMATION);
    		missatge.setTitle("Resgistre afegit");
    		missatge.setContentText("El tipus de vehicle s'ha afegit correctament");
    		missatge.setHeaderText("Resultat:");
    		missatge.show();
    		
    		Netejar(event);
    	}else {
    		
    		Alert missatge = new Alert(AlertType.ERROR);
    		missatge.setTitle("Error en afegir el tipus de vehicle");
    		missatge.setContentText("El tipus de vehicle no s'ha pogut afegir");
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


		App.setTitol("Tipus de Vehicle");
		

		llistaTipusVehicles=FXCollections.observableArrayList();
		llistaFiltrada=new FilteredList<>(llistaTipusVehicles, p -> true);

		tblViewTipusVehicle.setItems(llistaFiltrada);

		clmCodi.setCellValueFactory(new PropertyValueFactory<TipusVehicle, Integer>("idTipusVehicle"));
		clmDescripcio.setCellValueFactory(new PropertyValueFactory<TipusVehicle, String>("descripcio"));

		TipusVehicleDAOImpl.Tots(App.con, llistaTipusVehicles);

		gestionarEvents();
	}


	void gestionarEvents() {


		tblViewTipusVehicle.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TipusVehicle>() {

			public void changed(ObservableValue<? extends TipusVehicle> observable, TipusVehicle oldValue, TipusVehicle newValue) {

				if(newValue!=null) {

					textCodi.setText(String.valueOf(newValue.getIdTipusVehicle()));
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
