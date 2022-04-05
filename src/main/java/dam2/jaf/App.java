package dam2.jaf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.time.format.*;
import java.time.*;
import java.util.*;

/**
 * JavaFX App
 */
public class App extends Application {

	public static Connexio con = new Connexio("config.xml");

    private static Scene scene;
    private static Stage stage;


    @Override
    public void start(Stage stage) throws IOException {
    	this.stage = stage;
        scene = new Scene(loadFXML("Menu"),1010,700);
        stage.setScene(scene);
        stage.setMaximized(true);
//        stage.setMinHeight(700);
//        stage.setMinWidth(1010);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {

        launch();
    }
    
    static void setTitol(String titol){
        stage.setTitle(titol+" - JAFM");
    }

}