package dam2.jaf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class contracteController {

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
    private CheckBox cboxConductor;

    @FXML
    private ComboBox<?> cbxClient;

    @FXML
    private ComboBox<?> cbxConductor;

    @FXML
    private ComboBox<?> cbxEmpleat;

    @FXML
    private ComboBox<?> cbxEstat;

    @FXML
    private ComboBox<?> cbxVehicle;

    @FXML
    private TableColumn<?, ?> clmClient;

    @FXML
    private TableColumn<?, ?> clmCodi;

    @FXML
    private TableColumn<?, ?> clmConductor;

    @FXML
    private TableColumn<?, ?> clmDataFi;

    @FXML
    private TableColumn<?, ?> clmDataInici;

    @FXML
    private TableColumn<?, ?> clmEmpleat;

    @FXML
    private TableColumn<?, ?> clmEstat;

    @FXML
    private TableColumn<?, ?> clmVehicle;

    @FXML
    private DatePicker dateDataFi;

    @FXML
    private DatePicker dateDataInici;

    @FXML
    private TableView<?> tblViewContracte;

    @FXML
    private TextField textCerca;

    @FXML
    private TextField textCodi;

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
