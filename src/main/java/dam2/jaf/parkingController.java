package dam2.jaf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.CheckComboBox;

public class parkingController {

    @FXML
    private AnchorPane anchor;

    @FXML
    private Button botoActualizar;

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
    private ComboBox<?> cbxBotiga;

    @FXML
    private CheckComboBox<?> chcbxVehicles;

    @FXML
    private TableColumn<?, ?> clmBotiga;

    @FXML
    private TableColumn<?, ?> clmCapacitat;

    @FXML
    private TableColumn<?, ?> clmCodi;

    @FXML
    private TableColumn<?, ?> clmDescripcio;

    @FXML
    private TableColumn<?, ?> clmDireccio;

    @FXML
    private TableColumn<?, ?> clmTelefon;

    @FXML
    private TableView<?> tblViewParking;

    @FXML
    private TextField textCapacitat;

    @FXML
    private TextField textCerca;

    @FXML
    private TextField textCodi;

    @FXML
    private TextField textDescripcio;

    @FXML
    private TextField textDireccio1;

    @FXML
    private TextField textTelefon;

    @FXML
    void Netejar(ActionEvent event) {

    }

    @FXML
    void actualizarRegistre(ActionEvent event) {

    }

    @FXML
    void buidar(ActionEvent event) {

    }

    @FXML
    void eliminarRegistre(ActionEvent event) {

    }

    @FXML
    void guardarRegistre(ActionEvent event) {

    }

    @FXML
    void tornar(ActionEvent event) {

    }

}
