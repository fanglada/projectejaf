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


public class CarnetController  implements Initializable{

	private ObservableList<Carnet> llistaCarnets;
	private FilteredList<Carnet> llistaFiltrada;

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
    private TableColumn<Carnet, Integer> clmCodi;

    @FXML
    private TableColumn<Carnet, String> clmDescripcio;

    @FXML
    private TableView<Carnet> tblViewCarnet;

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
    	Carnet carnet = new Carnet(Integer.valueOf(textCodi.getText()),textDescripcio.getText());
    	
    	CarnetDAO carnetDAO = new CarnetDAOImpl();
    	int res = carnetDAO.Update(App.con, carnet);
    	
    	if(res>0)
    	{
    		if(tblViewCarnet.getSelectionModel().getSelectedIndex()!=-1)
    		{
    			llistaCarnets.set(tblViewCarnet.getSelectionModel().getSelectedIndex(), carnet);
    		}
    		else
    		{
    			llistaCarnets.set(llistaCarnets.size(),carnet);
    		}
    		
    		Alert missatge = new Alert(AlertType.INFORMATION);
    		missatge.setTitle("El registre s'ha actualitzat");
    		missatge.setContentText("El carnet s'ha actualitzat correctament");
    		missatge.setHeaderText("Resultat:");
    		missatge.show();
    		
    		Netejar(event);
    	}
    	else
    	{
      		Alert missatge = new Alert(AlertType.ERROR);
    		missatge.setTitle("Error en actualitzar un registre");
    		missatge.setContentText("El carnet no s'ha pogut actualitzat");
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

    	
    	CarnetDAO carnetDAO = new CarnetDAOImpl();
    	int res = carnetDAO.Delete(App.con, tblViewCarnet.getSelectionModel().getSelectedItem().getIdCarnet());
    	
    	if(res>0)
    	{
    		llistaCarnets.remove(tblViewCarnet.getSelectionModel().getSelectedItem());
    		
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
    void guardarRegistre(ActionEvent event) {
    	
    	Carnet carnet = new Carnet(textDescripcio.getText());
    	
    	CarnetDAO carnetDAO = new CarnetDAOImpl();
    	int res = carnetDAO.Create(App.con, carnet);
    	
    	if(res>0)
    	{
    		llistaCarnets.add(carnet);
    		
    		Alert missatge = new Alert(AlertType.INFORMATION);
    		missatge.setTitle("Registre afegit");
    		missatge.setContentText("El carnet s'ha afegit correctament");
    		missatge.setHeaderText("Resultat:");
    		missatge.show();
    		
    		Netejar(event);
    	}
    	else
    	{
    		
       		Alert missatge = new Alert(AlertType.ERROR);
    		missatge.setTitle("Registre afegit");
    		missatge.setContentText("El carnet no s'ha pogut afegir");
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
		// TODO Auto-generated method stub
		App.setTitol("Gestió");
		
    	llistaCarnets=FXCollections.observableArrayList();
    	llistaFiltrada = new FilteredList<>(llistaCarnets, p -> true);

    	tblViewCarnet.setItems(llistaFiltrada);
    	
    	clmCodi.setCellValueFactory(new PropertyValueFactory<Carnet,Integer>("idCarnet"));
    	clmDescripcio.setCellValueFactory(new PropertyValueFactory<Carnet,String>("descripcio"));

    	CarnetDAOImpl.Tots(App.con, llistaCarnets);
    	
    	gestionarEvents();

	}

	void gestionarEvents() {
		tblViewCarnet.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Carnet>() {

			@Override
			public void changed(ObservableValue<? extends Carnet> observable, Carnet oldValue, Carnet newValue) {
				// TODO Auto-generated method stub
				if(newValue!=null)
				{
					textCodi.setText(String.valueOf(newValue.getIdCarnet()));
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
				// TODO Auto-generated method stub
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
