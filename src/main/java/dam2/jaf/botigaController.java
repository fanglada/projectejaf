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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.*;

public class botigaController implements Initializable {

	private ObservableList<Botiga> llistaBotigues;
	private FilteredList<Botiga> llistaFiltrada;
	
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
    private TableColumn<Botiga, Integer> clmCodi;

    @FXML
    private TableColumn<Botiga, String> clmDescripcio;

    @FXML
    private TableColumn<Botiga, String> clmDireccio;

    @FXML
    private TableColumn<Botiga, String> clmTelefon;

    @FXML
    private TableView<Botiga> tblViewBotiga;

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

    @FXML
    void Netejar(ActionEvent event) {
    	textCodi.setText(null);
    	textDescripcio.setText(null);
    	textDireccio.setText(null);
    	textTelefon.setText(null);
    	
		botoActualitzar.setDisable(true);
		botoEliminar.setDisable(true);
		botoGuardar.setDisable(false);
    }

    @FXML
    void actualizarRegistre(ActionEvent event) {

    }

    @FXML
    void buidar(ActionEvent event) {

    	textCerca.setText(null);
    	
    }

    @FXML
    void eliminarRegistre(ActionEvent event) {

    }

    @FXML
    void guardarRegistre(ActionEvent event) {

    }

    @FXML
    void tornar(ActionEvent event) throws IOException {
		App.setRoot("gestio");

	}
    


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		llistaBotigues=FXCollections.observableArrayList();
    	llistaFiltrada = new FilteredList<>(llistaBotigues, p -> true);

    	tblViewBotiga.setItems(llistaFiltrada);
    	
    	clmCodi.setCellValueFactory(new PropertyValueFactory<Botiga,Integer>("idBotiga"));
    	clmDescripcio.setCellValueFactory(new PropertyValueFactory<Botiga,String>("descripcio"));
    	clmDireccio.setCellValueFactory(new PropertyValueFactory<Botiga,String>("direccio"));
    	clmTelefon.setCellValueFactory(new PropertyValueFactory<Botiga,String>("telefon"));
    	
    	BotigaDAOImpl.Tots(App.con, llistaBotigues);
    	
		gestionarEvents();
	}
	
    void gestionarEvents() {
		
    	tblViewBotiga.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Botiga>() {

			@Override
			public void changed(ObservableValue<? extends Botiga> observable, Botiga oldValue, Botiga newValue) {
				// TODO Auto-generated method stub
				if(newValue!=null)
				{
					textCodi.setText(String.valueOf(newValue.getIdBotiga()));
					textDescripcio.setText(newValue.getDescripcio());
					textDireccio.setText(newValue.getDireccio());
					textTelefon.setText(newValue.getTelefon());
					
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
					
					if(stringCerca.getDescripcio().toLowerCase().contains(newValue.toLowerCase()))
						return true;
					
					return false;
				});
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
		
	}

}
