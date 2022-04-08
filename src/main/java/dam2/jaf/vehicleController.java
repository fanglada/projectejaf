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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.*;

public class vehicleController implements Initializable{

	private ObservableList<Vehicle> llistaVehicles;
	private ObservableList<TipusVehicle> llistaTipusVehicles;
	private ObservableList<Carnet> llistaCarnets;
	private ObservableList<Parking> llistaParkings;


	private FilteredList<Vehicle> llistaFiltrada;

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
	private ComboBox<String> cbxCanvi;

	@FXML
	private ComboBox<Carnet> cbxCarnet;

	@FXML
	private ComboBox<TipusVehicle> cbxTipusVehicle;

	@FXML
	private ComboBox<Parking> cbxParking;

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
	private TableColumn<Vehicle, Parking> clmParking;

	@FXML
	private TableView<Vehicle> tblViewVehicle;

	@FXML
	private TextField textCapacitat;

	@FXML
	private TextField textCerca;

	@FXML
	private TextField textCodi;

	@FXML
	private TextField textNumRodes;

	@FXML
	private TextField textNumPortes;

	@FXML
	private TextField textMarca;

	@FXML
	private TextField textModel;

	@FXML
	private TextField textCv;

	@FXML
	private DatePicker textDataMatriculacio;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		App.setTitol("Vehicles");

		llistaVehicles=FXCollections.observableArrayList();
		llistaTipusVehicles=FXCollections.observableArrayList();
		llistaCarnets=FXCollections.observableArrayList();
		llistaParkings=FXCollections.observableArrayList();


		llistaFiltrada=new FilteredList<>(llistaVehicles, p -> true);

		tblViewVehicle.setItems(llistaFiltrada);
		cbxTipusVehicle.setItems(llistaTipusVehicles);
		cbxCarnet.setItems(llistaCarnets);
		cbxParking.setItems(llistaParkings);
		cbxCanvi.getItems().addAll("Manual", "Automatic");


		clmCV.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("cv"));
		clmCanvi.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("canvi"));
		clmCapacitat.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("capacitat"));
		clmCarnet.setCellValueFactory(new PropertyValueFactory<Vehicle, Carnet>("carnet"));
		clmDataMatriculacio.setCellValueFactory(new PropertyValueFactory<Vehicle, LocalDate>("dataMatriculacio"));
		clmMarca.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("marca"));
		clmMatricula.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("matricula"));
		clmModel.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("model"));
		clmNumPortes.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("numPortes"));
		clmNumRodes.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("numRodes"));
		clmTipusVehicle.setCellValueFactory(new PropertyValueFactory<Vehicle, TipusVehicle>("tipus"));
		clmParking.setCellValueFactory(new PropertyValueFactory<Vehicle, Parking>("parking"));



		VehicleDAOImpl.Tots(App.con, llistaVehicles);
		TipusVehicleDAOImpl.Tots(App.con, llistaTipusVehicles);
		CarnetDAOImpl.Tots(App.con, llistaCarnets);
		ParkingDAOImpl.Tots(App.con, llistaParkings);

		gestionarEvents();
	}


	private void gestionarEvents() {

		tblViewVehicle.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Vehicle>() {

			@Override
			public void changed(ObservableValue<? extends Vehicle> observable, Vehicle oldValue, Vehicle newValue) {

				if(newValue!=null) {

					textCapacitat.setText(String.valueOf(newValue.getCapacitat()));
					textCodi.setText(newValue.getMatricula());
					textMarca.setText(newValue.getMarca());
					textModel.setText(newValue.getModel());
					textNumRodes.setText(String.valueOf(newValue.getNumRodes()));
					textNumPortes.setText(String.valueOf(newValue.getNumPortes()));
					textCv.setText(String.valueOf(newValue.getCv()));
					textDataMatriculacio.setValue(newValue.getDataMatriculacio());
					cbxCanvi.setValue(newValue.getCanvi());
					cbxCarnet.setValue(newValue.getCarnet());
					cbxTipusVehicle.setValue(newValue.getTipus());
					cbxParking.setValue(newValue.getParking());

					textCodi.setEditable(false);
					botoActualitzar.setDisable(false);
					botoEliminar.setDisable(false);
					botoGuardar.setDisable(true);
				}
			}
		});

		textDataMatriculacio.valueProperty().addListener(new ChangeListener<>() {

			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
				// TODO Auto-generated method stub
				if(newValue!=null)
				{
					if (newValue.compareTo(LocalDate.now()) > 0) {
						textDataMatriculacio.setValue(oldValue);
					}
				}

			}});

		textCerca.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				llistaFiltrada.setPredicate(stringCerca -> {
					if(newValue == null || newValue.isEmpty()) return true;

					if(stringCerca.getCanvi().toLowerCase().contains(newValue.toLowerCase()) || stringCerca.getMarca().toLowerCase().contains(newValue.toLowerCase()) || stringCerca.getMatricula().toLowerCase().contains(newValue.toLowerCase()) || stringCerca.getModel().toLowerCase().contains(newValue.toLowerCase()))						

						return true;

					return false;
				});										
			}			
		});		
	}


	@FXML
	void guardarRegistre(ActionEvent event) {

		if(!textCodi.getText().isEmpty() && !textMarca.getText().isEmpty() && !textModel.getText().isEmpty() && cbxTipusVehicle.getValue() !=null && cbxCanvi.getValue() !=null && !textCv.getText().isEmpty() && !textNumRodes.getText().isEmpty() && !textNumPortes.getText().isEmpty() && textDataMatriculacio.getValue() !=null && !textCapacitat.getText().isEmpty() && cbxCarnet.getValue() !=null && cbxParking.getValue() !=null) {

			Vehicle vehicle = new Vehicle(textCodi.getText(), textMarca.getText(), textModel.getText(), cbxTipusVehicle.getValue(), cbxCanvi.getValue(), Integer.valueOf(textCv.getText()), Integer.valueOf(textNumRodes.getText()), Integer.valueOf(textNumPortes.getText()), textDataMatriculacio.getValue(), Integer.valueOf(textCapacitat.getText()), cbxCarnet.getValue(), cbxParking.getValue());
			System.out.println(cbxParking.getValue());
			VehicleDAO vehicleDAO = new VehicleDAOImpl();    	
			int res = vehicleDAO.create(App.con, vehicle);

			if(res>0) {
				llistaVehicles.add(vehicle);

				Alert missatge = new Alert(AlertType.INFORMATION);
				missatge.setTitle("Resgistre afegit");
				missatge.setContentText("El vehicle s'ha afegit correctament");
				missatge.setHeaderText("Resultat:");
				missatge.show();

				Netejar(event);
			}else {

				Alert missatge = new Alert(AlertType.ERROR);
				missatge.setTitle("Error en afegir el registre");
				missatge.setContentText("El vehicle no s'ha pogut afegir");
				missatge.setHeaderText("Resultat:");
				missatge.show();   		
			}
		}
	}

	@FXML
	void actualizarRegistre(ActionEvent event) {

		Vehicle vehicle = new Vehicle(textCodi.getText(), textMarca.getText(), textModel.getText(), cbxTipusVehicle.getValue(), cbxCanvi.getValue(), Integer.valueOf(textCv.getText()), Integer.valueOf(textNumRodes.getText()), Integer.valueOf(textNumPortes.getText()), textDataMatriculacio.getValue(), Integer.valueOf(textCapacitat.getText()), cbxCarnet.getValue(), cbxParking.getValue());

		VehicleDAO vehicleDAO = new VehicleDAOImpl();    	
		int res = vehicleDAO.update(App.con, vehicle);

		if(res>0) {

			if(tblViewVehicle.getSelectionModel().getSelectedIndex()!=-1) {
				llistaVehicles.set(tblViewVehicle.getSelectionModel().getSelectedIndex(),vehicle);
			}
			else {
				llistaVehicles.set(llistaVehicles.size(), vehicle);
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

		VehicleDAO vehicleDAO = new VehicleDAOImpl();    	
		int res = vehicleDAO.delete(App.con, tblViewVehicle.getSelectionModel().getSelectedItem().getMatricula());

		if(res>0) {
			llistaVehicles.remove(tblViewVehicle.getSelectionModel().getSelectedItem());

			Alert missatge = new Alert(AlertType.INFORMATION);
			missatge.setTitle("El registre s'ha eliminat");
			missatge.setContentText("El vehicle s'ha eliminat correctament");
			missatge.setHeaderText("Resultat:");
			missatge.show();

			Netejar(event);
		}else {

			Alert missatge = new Alert(AlertType.ERROR);
			missatge.setTitle("Error en eliminar el registre");
			missatge.setContentText("El vehicle no s'ha pogut eliminar");
			missatge.setHeaderText("Resultat:");
			missatge.show(); 		
		}   	
	}

	@FXML
	void tornar(ActionEvent event) throws IOException {
		App.setRoot("gestio");
	}

	@FXML
	void buidar(ActionEvent event) {
		textCerca.setText(null);
	}

	@FXML
	void Netejar(ActionEvent event) {
		textCapacitat.setText(null);
		textCodi.setText(null);
		textMarca.setText(null);
		textModel.setText(null);
		textNumRodes.setText(null);
		textNumPortes.setText(null);
		textCv.setText(null);
		textDataMatriculacio.setValue(null);
		cbxCanvi.setValue(null);		
		cbxCarnet.setValue(null);		
		cbxTipusVehicle.setValue(null);		
		cbxParking.setValue(null);		

		textCodi.setEditable(true);
		botoActualitzar.setDisable(true);
		botoEliminar.setDisable(true);
		botoGuardar.setDisable(false);
	}
}
