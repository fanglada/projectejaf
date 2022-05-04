package dam2.jaf;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;

public class ClientController implements Initializable {

	private ObservableList<Client> llistaClients;
	private ObservableList<Carnet> llistaCarnets;

	private FilteredList<Client> llistaFiltrada;
	
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
	private TableColumn<Client, String> clmCognom1;

	@FXML
	private TableColumn<Client, String> clmCognom2;

	@FXML
	private TableColumn<Client, LocalDate> clmDataNaixament;

	@FXML
	private TableColumn<Client, String> clmDireccio;

	@FXML
	private TableColumn<Client, String> clmDni;

	@FXML
	private TableColumn<Client, String> clmMail;

	@FXML
	private TableColumn<Client, String> clmNom;

	@FXML
	private TableColumn<Client, String> clmTelefon;
	
    @FXML
    private TableColumn<Client, Void> clmCarnets;

	@FXML
	private DatePicker dateDataNaixament;

	@FXML
	private TableView<Client> tblViewClient;

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
		// TODO Auto-generated method stub
		
		
		if (!taula) {
			App.setTitol("Client");

			llistaClients=FXCollections.observableArrayList();
	    	llistaCarnets=FXCollections.observableArrayList();
	    	llistaFiltrada = new FilteredList<>(llistaClients, p -> true);
	
	    	tblViewClient.setItems(llistaFiltrada);
	    	
	    	clmDni.setCellValueFactory(new PropertyValueFactory<Client,String>("dni"));
	    	clmNom.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
	    	clmCognom1.setCellValueFactory(new PropertyValueFactory<Client,String>("cognom1"));
	    	clmCognom2.setCellValueFactory(new PropertyValueFactory<Client,String>("cognom2"));
	    	clmDataNaixament.setCellValueFactory(new PropertyValueFactory<Client,LocalDate>("dataNaixament"));
	    	clmTelefon.setCellValueFactory(new PropertyValueFactory<Client,String>("telefon"));
	    	clmDireccio.setCellValueFactory(new PropertyValueFactory<Client,String>("direccio"));
	    	clmMail.setCellValueFactory(new PropertyValueFactory<Client,String>("mail"));
	    	clmCarnets.setCellFactory(new Callback<TableColumn<Client, Void>, TableCell<Client, Void>>() {
	            @Override
	            public TableCell<Client, Void> call(final TableColumn<Client, Void> param) {
	                final TableCell<Client, Void> cell = new TableCell<Client, Void>() {
	
	                    private final Button btn = new Button("Carnets");
	
	                    {
	                        btn.setOnAction((ActionEvent event) -> {
	                        	taula = true;
	                            Client client = getTableView().getItems().get(getIndex());
	                        	obrirTaula(client);
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
	
	    	
	    	ClientDAOImpl.Tots(App.con, llistaClients);
	    	
	    	CarnetDAOImpl.Tots(App.con, llistaCarnets);
	    	
	    	chcbxCarnet.getItems().addAll(llistaCarnets);
	
	
	    	gestionarEvents();
		}else if(taula) 
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
			    		missatge.setTitle("Hi ha un problema, el client ha de ser major d'edat");
						missatge.setContentText("Hi ha un problema, el client ha de ser major d'edat");
						missatge.setHeaderText("Alerta:");
						missatge.show();
			        }
				}
			}});
	
			
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
		
		if(!textDni.getText().isEmpty() && !textNom.getText().isEmpty() && !textCognom1.getText().isEmpty() && !textCognom2.getText().isEmpty() && dateDataNaixament.getValue()!=null && !textTelefon.getText().isEmpty() && !textDireccio.getText().isEmpty() && !textMail.getText().isEmpty())
		{
		
			ArrayList<Carnet> carnets = new ArrayList<Carnet>();
			for(int i = 0; i < chcbxCarnet.getCheckModel().getCheckedItems().size(); i++) 
			{
				carnets.add(chcbxCarnet.getCheckModel().getCheckedItems().get(i));
			}
	    	Client client =  new Client(textDni.getText(), textNom.getText(), textCognom1.getText(), textCognom2.getText(), dateDataNaixament.getValue(), textTelefon.getText(), textDireccio.getText(), textMail.getText(), carnets);
	    	ClientDAO ClientDAO = new ClientDAOImpl();
	    	int resultat = ClientDAO.create(App.con, client);
	    	
	    	if (resultat>0)
	    	{
				llistaClients.add(client);
	    		Alert missatge=new Alert(AlertType.INFORMATION);
				missatge.setTitle("Client donat d'alta");
				missatge.setContentText("S'ha pujat correctament, però sempre va bé comprovar");
				missatge.setHeaderText("Alerta:");
				missatge.show();
				Netejar(null);
				
	    	}else 
	    	{
	    		Alert missatge=new Alert(AlertType.ERROR);
				missatge.setTitle("Hi ha un problema, client no s'ha pogut donar d'alta");
				if(resultat == -4) 
				{
					missatge.setContentText("Hi ha un problema, no s'ha pogut donar d'alta, el client ja existeix");
				}else 
				{
					missatge.setContentText("Hi ha un problema, client no s'ha pogut donar d'alta");
				}
				missatge.setHeaderText("Alerta:");
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
		
		
		Client client = tblViewClient.getSelectionModel().getSelectedItem();
		client.setNom(textNom.getText());
		client.setCognom1(textCognom1.getText());
		client.setCognom2(textCognom2.getText());
		client.setfNacimiento(dateDataNaixament.getValue());
		client.setTelefon(textTelefon.getText());
		client.setMail(textMail.getText());
		client.setDireccio(textDireccio.getText());
		ArrayList<Carnet> carnets = new ArrayList<Carnet>();
		for(int i = 0; i < chcbxCarnet.getCheckModel().getCheckedItems().size(); i++) 
		{
			carnets.add(chcbxCarnet.getCheckModel().getCheckedItems().get(i));
		}
		client.setCarnet(carnets);
		tblViewClient.refresh();
		
		
		ClientDAO ClientDAO = new ClientDAOImpl();
    	int resultat = ClientDAO.update(App.con, client);
    	if (resultat > 0)
    	{
    		Alert missatge=new Alert(AlertType.INFORMATION);
    		missatge.setTitle("Client actuaizat");
			missatge.setContentText("S'ha actualitzat correctament, però sempre va bé comprovar");
			missatge.setHeaderText("Alerta:");
			missatge.show();
			Netejar(null);
			
    	}else 
    	{
    		Alert missatge=new Alert(AlertType.ERROR);
    		missatge.setTitle("Hi ha un problema, el client no s'ha pogut actualitzar");
			missatge.setContentText("Hi ha un problema, el client no s'ha pogut actialitzar");
			missatge.setHeaderText("Alerta:");
			missatge.show();
    	}

	}

	@FXML
	void eliminarRegistre(ActionEvent event) {
		
    	Alert confirmacio=new Alert(AlertType.CONFIRMATION);
    	confirmacio.initModality(Modality.WINDOW_MODAL);
    	confirmacio.setTitle("Estas segur que vols esborrar el client?");
    	confirmacio.setContentText("Un cop fet no es pot desfer");
    	
        	
    	Optional<ButtonType> result = confirmacio.showAndWait();
    	if(result.isPresent() && result.get() == ButtonType.OK) {
        	ClientDAO ClientDAO = new ClientDAOImpl();
        	int resultat = ClientDAO.delete(App.con, tblViewClient.getSelectionModel().getSelectedItem());
        	if (resultat>0)
        	{
        		llistaClients.remove(tblViewClient.getSelectionModel().getSelectedItem());
        		tblViewClient.refresh();
        		Alert missatge=new Alert(AlertType.INFORMATION);
    			missatge.setTitle("El client s'ha donat de baixa");
    			missatge.setContentText("S'ha esborrat correctament, però sempre va bé comprovar");
    			missatge.setHeaderText("Alerta:");
    			missatge.show();
    			Netejar(null);
        	}else 
        	{
        		Alert missatge=new Alert(AlertType.ERROR);
    			missatge.setTitle("Hi ha un problema, client no s'ha pogut donar de baixa");
    			missatge.setContentText("Hi ha un problema, client no s'ha pogut donar de baixa");
    			missatge.setHeaderText("Alerta:");
    			missatge.show();
        		
        	}
    	}

	}
	
	@FXML
    void seleccionarClient(MouseEvent event) {
    	
    	botoGuardar.setDisable(true);
    	textDni.setEditable(false);
    	botoActualitzar.setDisable(false);
    	botoEliminar.setDisable(false);
		Client aux = tblViewClient.getSelectionModel().getSelectedItem();
		if(aux != null) 
		{
			carregarClient(aux); 
		}
		

    }
    
    private void carregarClient(Client client) 
    {
    	textDni.setText(client.getDni());
    	textNom.setText(client.getNom());
    	textCognom1.setText(client.getCognom1());
    	textCognom2.setText(client.getCognom2());
    	dateDataNaixament.setValue(client.getDataNaixament());
    	textTelefon.setText(client.getTelefon());
    	textMail.setText(client.getMail());
    	textDireccio.setText(client.getDireccio());
    	chcbxCarnet.getCheckModel().clearChecks();
    	client.getCarnet().stream().forEach((carnet)->{chcbxCarnet.getCheckModel().check(trobarCarnet(carnet));});
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
	
    void obrirTaula(Client client) 
    {
    	llistaCarnetsTaula=FXCollections.observableArrayList();

    	llistaCarnetsTaula.addAll(client.getCarnet());
    	try {	    		
    		FXMLLoader loader = new FXMLLoader(App.class.getResource("carnetsTaula.fxml"));
       		loader.setController(this);
    		Parent root = loader.load();
    		stageTaula = new Stage();
    		stageTaula.initModality(Modality.APPLICATION_MODAL);
    		stageTaula.getIcons().add(new Image(App.class.getResourceAsStream("jafmlogo.png")));
    		stageTaula.setTitle("Carnets de: " + client.getNom() + " " + client.getCognom1() + " " + client.getCognom2());
    		stageTaula.setScene(new Scene(root));
    		stageTaula.show();
  
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No s'ha pogit obrir la finestra amb els carnets");
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    
    @FXML
    void tancarCarnets(ActionEvent event) {
    	stageTaula.close();
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
