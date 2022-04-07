package dam2.jaf;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.*;

public class movimentsController implements Initializable{
	
	private ObservableList<MovimentParking> llistaMoviments;
	private FilteredList<MovimentParking> llistaFiltrada;
	
    @FXML
    private AnchorPane anchor;

    @FXML
    private Button botoBuidar;

    @FXML
    private Button botoTornar;

    @FXML
    private TableColumn<MovimentParking, Integer> clmCodi;

    @FXML
    private TableColumn<MovimentParking, LocalDateTime> clmDataHora;

    @FXML
    private TableColumn<MovimentParking, Parking> clmDesti;

    @FXML
    private TableColumn<MovimentParking, Parking> clmOrigen;

    @FXML
    private TableColumn<MovimentParking, Vehicle> clmVehicle;

    @FXML
    private TableView<MovimentParking> tblViewMoviments;

    @FXML
    private TextField textCerca;

    @FXML
    void buidar(ActionEvent event) {
    	textCerca.setText(null);
    }

    @FXML
    void tornar(ActionEvent event) throws IOException {
    	App.setRoot("gestio");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		App.setTitol("Moviments");

		llistaMoviments=FXCollections.observableArrayList();
    	llistaFiltrada = new FilteredList<>(llistaMoviments, p -> true);

    	tblViewMoviments.setItems(llistaFiltrada);
    	
    	clmCodi.setCellValueFactory(new PropertyValueFactory<MovimentParking,Integer>("idMovimentParking"));
    	clmDataHora.setCellValueFactory(new PropertyValueFactory<MovimentParking,LocalDateTime>("dataHora"));
    	clmDesti.setCellValueFactory(new PropertyValueFactory<MovimentParking,Parking>("desti"));
    	clmOrigen.setCellValueFactory(new PropertyValueFactory<MovimentParking,Parking>("origen"));
    	clmVehicle.setCellValueFactory(new PropertyValueFactory<MovimentParking,Vehicle>("vehicle"));
    	
    	MovimentParkingDAOImpl.Tots(App.con, llistaMoviments);
    	
		gestionarEvents();
	}
	
    void gestionarEvents() {
		
		textCerca.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				llistaFiltrada.setPredicate(stringCerca -> {
					if(newValue == null || newValue.isEmpty()) return true;
					
					if(stringCerca.getDesti().toString().toLowerCase().contains(newValue.toLowerCase())||stringCerca.getOrigen().toString().toLowerCase().contains(newValue.toLowerCase())||stringCerca.getVehicle().getMatricula().toLowerCase().contains(newValue.toLowerCase()))
						return true;
					
					return false;
				});
			}
		});
    	
		
	}


}
