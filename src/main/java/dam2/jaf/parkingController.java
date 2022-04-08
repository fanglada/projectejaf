package dam2.jaf;

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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class parkingController implements Initializable {

	private ObservableList<Botiga> llistaBotigues;

	private ObservableList<Parking> llistaParkings;

	private FilteredList<Parking> llistaFiltrada;

	private ObservableList<Vehicle> llistaVehiclesTaula;

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
	private ComboBox<Botiga> cbxBotiga;

	@FXML
	private TableColumn<Parking, Botiga> clmBotiga;

	@FXML
	private TableColumn<Parking, Integer> clmCapacitat;

	@FXML
	private TableColumn<Parking, Integer> clmCodi;

	@FXML
	private TableColumn<Parking, String> clmDescripcio;

	@FXML
	private TableColumn<Parking, String> clmDireccio;

	@FXML
	private TableColumn<Parking, String> clmTelefon;

	@FXML
	private TableColumn<Parking, Void> clmVehicles;

	@FXML
	private TableView<Parking> tblViewParking;

	@FXML
	private TextField textCapacitat;

	@FXML
	private TextField textCerca;

	@FXML
	private TextField textCodi;

	@FXML
	private TextField textDescripcio;

	@FXML
	private TextField textDireccio;

	@FXML
	private TextField textTelefon;

	private Stage stageTaula;

	private boolean taula = false;  

	@FXML
	private Button botoTornarVehicles;

	@FXML
	private TableColumn<Vehicle, Integer> clmCV;

	@FXML
	private TableColumn<Vehicle, String> clmCanvi;

	@FXML
	private TableColumn<Vehicle, Integer> clmCapacitatV;

	@FXML
	private TableColumn<Vehicle, Carnet> clmCarnet;

	@FXML
	private TableColumn<Vehicle, LocalDateTime> clmDataMatriculacio;

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
	private TableView<Vehicle> taulaVehicles;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(!taula)
		{

			App.setTitol("Parking");

			llistaBotigues=FXCollections.observableArrayList();
			llistaParkings=FXCollections.observableArrayList();

			llistaVehiclesTaula=FXCollections.observableArrayList();
			llistaFiltrada = new FilteredList<>(llistaParkings, p -> true);

			tblViewParking.setItems(llistaFiltrada);
			cbxBotiga.setItems(llistaBotigues);

			clmCodi.setCellValueFactory(new PropertyValueFactory<Parking,Integer>("idParking"));
			clmBotiga.setCellValueFactory(new PropertyValueFactory<Parking,Botiga>("botiga"));
			clmDescripcio.setCellValueFactory(new PropertyValueFactory<Parking,String>("descripcio"));
			clmTelefon.setCellValueFactory(new PropertyValueFactory<Parking,String>("telefon"));
			clmDireccio.setCellValueFactory(new PropertyValueFactory<Parking,String>("direccio"));
			clmCapacitat.setCellValueFactory(new PropertyValueFactory<Parking,Integer>("capacitat"));
			clmVehicles.setCellFactory(new Callback<TableColumn<Parking, Void>, TableCell<Parking, Void>>() {
				@Override
				public TableCell<Parking, Void> call(final TableColumn<Parking, Void> param) {
					final TableCell<Parking, Void> cell = new TableCell<Parking, Void>() {

						private final Button btn = new Button("Vehicles");

						{
							btn.setOnAction((ActionEvent event) -> {
								taula = true;
								Parking parking = getTableView().getItems().get(getIndex());
								obrirTaula(parking);
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

			ParkingDAOImpl.Tots(App.con, llistaParkings);

			BotigaDAOImpl.Tots(App.con, llistaBotigues);


			gestionarEvents();
		}
		else if(taula) 
		{

			taulaVehicles.setItems(llistaVehiclesTaula);

			clmCV.setCellValueFactory(new PropertyValueFactory<Vehicle,Integer>("cv"));
			clmCanvi.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("canvi"));
			clmCapacitatV.setCellValueFactory(new PropertyValueFactory<Vehicle,Integer>("capacitat"));
			clmCarnet.setCellValueFactory(new PropertyValueFactory<Vehicle,Carnet>("carnet"));
			clmDataMatriculacio.setCellValueFactory(new PropertyValueFactory<Vehicle,LocalDateTime>("dataMatriculacio"));
			clmMarca.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("marca"));
			clmModel.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("model"));
			clmMatricula.setCellValueFactory(new PropertyValueFactory<Vehicle,String>("matricula"));
			clmNumPortes.setCellValueFactory(new PropertyValueFactory<Vehicle,Integer>("numPortes"));
			clmNumRodes.setCellValueFactory(new PropertyValueFactory<Vehicle,Integer>("numRodes"));
			clmTipusVehicle.setCellValueFactory(new PropertyValueFactory<Vehicle,TipusVehicle>("tipus"));
			clmParking.setCellValueFactory(new PropertyValueFactory<Vehicle,Parking>("parking"));

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

		textCerca.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				llistaFiltrada.setPredicate(stringCerca -> {
					if(newValue == null || newValue.isEmpty()) return true;

					if(stringCerca.getBotiga().toString().toLowerCase().contains(newValue.toLowerCase())||stringCerca.getDescripcio().toLowerCase().contains(newValue.toLowerCase())||stringCerca.getDireccio().toLowerCase().contains(newValue.toLowerCase())||stringCerca.getTelefon().toLowerCase().contains(newValue.toLowerCase()))
						return true;

					return false;
				});
			}
		});

		tblViewParking.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Parking>() {

			@Override
			public void changed(ObservableValue<? extends Parking> observable, Parking oldValue, Parking newValue) {
				// TODO Auto-generated method stub
				if(newValue!=null)
				{
					textCodi.setText(String.valueOf(newValue.getIdParking()));
					cbxBotiga.setValue(newValue.getBotiga());
					textDescripcio.setText(newValue.getDescripcio());
					textDireccio.setText(newValue.getDireccio());
					textTelefon.setText(newValue.getTelefon());
					textCapacitat.setText(String.valueOf(newValue.getCapacitat()));

					botoActualitzar.setDisable(false);
					botoEliminar.setDisable(false);
					botoGuardar.setDisable(true);
				}
			}

		});
		
		textCapacitat.textProperty().addListener(new ChangeListener<>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
		    	if (!newValue.matches("-?([0-9]*)?") && newValue!=null) {
		    		textCapacitat.setText(oldValue);
		        }
			}});

	}


	void obrirTaula(Parking parking) 
	{
		llistaVehiclesTaula=FXCollections.observableArrayList();

		llistaVehiclesTaula.addAll(VehicleDAOImpl.BuscarParking(App.con, parking.getIdParking()));

		try {	    		
			FXMLLoader loader = new FXMLLoader(App.class.getResource("vehiclesTaula.fxml"));
			loader.setController(this);
			Parent root = loader.load();
			stageTaula = new Stage();
			stageTaula.initModality(Modality.APPLICATION_MODAL);
			stageTaula.setTitle("Vehicles de: " + parking.getDireccio());
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
	void tancarVehicles(ActionEvent event) {
		stageTaula.close();
	}

	@FXML
	void guardarRegistre(ActionEvent event) {

		if(cbxBotiga.getValue() !=null && !textDireccio.getText().isEmpty() && !textTelefon.getText().isEmpty() && !textDescripcio.getText().isEmpty() && !textCapacitat.getText().isEmpty()) {

			Parking parking = new Parking(cbxBotiga.getValue(), textDireccio.getText(), textTelefon.getText(), textDescripcio.getText(), Integer.parseInt(textCapacitat.getText()));

			ParkingDAO ParkingDAO = new ParkingDAOImpl();
			int resultat = ParkingDAO.create(App.con, parking);

			if (resultat>0)
			{
				llistaParkings.add(parking);
				Alert missatge=new Alert(AlertType.INFORMATION);
				missatge.setTitle("Parking donat d'alta");
				missatge.setContentText("S'ha pujat correctament, però sempre va bé comprovar");
				missatge.setHeaderText("Alerta:");
				missatge.show();
				Netejar(null);

			}else 
			{
				Alert missatge=new Alert(AlertType.ERROR);
				missatge.setTitle("Hi ha un problema, parking no s'ha pogut donar d'alta");
				missatge.setContentText("Hi ha un problema, parking no s'ha pogut donar d'alta");
				missatge.setHeaderText("Alerta:");
				missatge.show();
			}
		}
	}

	@FXML
	void actualizarRegistre(ActionEvent event) {
		Parking parking = new Parking(Integer.valueOf(textCodi.getText()),cbxBotiga.getValue(), textDireccio.getText(), textTelefon.getText(), textDescripcio.getText(), Integer.parseInt(textCapacitat.getText()));

		ParkingDAO ParkingDAO = new ParkingDAOImpl();
		int resultat = ParkingDAO.update(App.con, parking);

		if (resultat>0)
		{
			if(tblViewParking.getSelectionModel().getSelectedIndex()!=-1)
			{
				llistaParkings.set(tblViewParking.getSelectionModel().getSelectedIndex(), parking);
			}
			else
			{
				llistaParkings.set(llistaParkings.size(),parking);
			}    		

			Alert missatge=new Alert(AlertType.INFORMATION);
			missatge.setTitle("El registre s'ha actualitzat");
			missatge.setContentText("El parking s'ha actualitzat correctament");
			missatge.setHeaderText("Resultat:");
			missatge.show();

			Netejar(event);
		}
		else
		{
			Alert missatge = new Alert(AlertType.ERROR);
			missatge.setTitle("Error en actualitzar un registre");
			missatge.setContentText("El parking no s'ha pogut actualitzat");
			missatge.setHeaderText("Resultat:");
			missatge.show();
		}
	}

	@FXML
	void eliminarRegistre(ActionEvent event) {
		ParkingDAO ParkingDAO = new ParkingDAOImpl();
		int resultat = ParkingDAO.delete(App.con, tblViewParking.getSelectionModel().getSelectedItem().getIdParking());

		if(resultat>0)
		{
			llistaParkings.remove(tblViewParking.getSelectionModel().getSelectedItem());

			Alert missatge = new Alert(AlertType.INFORMATION);
			missatge.setTitle("El registre s'ha eliminat");
			missatge.setContentText("El carnet s'ha eliminat correctament");
			missatge.setHeaderText("Resultat:");
			missatge.show();

			Netejar(event);
		}
		else
		{

			Alert missatge = new Alert(AlertType.ERROR);
			missatge.setTitle("Error en eliminar el registre ");
			missatge.setContentText("El carnet no s'ha pogut eliminar");
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
		textCodi.setText(null);
		cbxBotiga.setValue(null);
		textDescripcio.setText(null);
		textDireccio.setText(null);
		textTelefon.setText("");
		textCapacitat.setText("");

		botoActualitzar.setDisable(true);
		botoEliminar.setDisable(true);
		botoGuardar.setDisable(false);
	}

}
