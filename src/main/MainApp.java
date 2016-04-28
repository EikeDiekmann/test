package main;

import java.io.IOException;

import javafx.application.Application;
import static javafx.application.Platform.exit;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utility.AusgabeTexte;
import utility.Konstanten;

/**
 * lädt die GUI 
 * @author Eike
 */
public class MainApp extends Application implements AusgabeTexte, Konstanten
{
    private static Stage primaryStage;
    private AnchorPane rootPane;
//##############################################################################  
    /**
     * Default Konstruktor
     */
    public MainApp()
    {    }

//##############################################################################  
    /**
     * Einstiegspunkt des Programmsm 
     * Lädt da Fenster und initialisiert den Controller
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) 
    {
        MainApp.primaryStage = primaryStage;
        MainApp.primaryStage.setTitle(WINDOW_TITLE);

        initRootLayout();
        
        MainApp.primaryStage.setOnCloseRequest((WindowEvent we) -> 
        {
            exit();
        }); 
    }

//##############################################################################     
    /**
     * Intialisiert das RootLayout
     * lädt die FXML 
     */
    private void initRootLayout() 
    {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(FXML_ROOT_PATH));
            rootPane = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootPane);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
           if(DEBUG)
               e.printStackTrace();
        }
    }

//############################################################################## 
    /**
     * Returns the main stage.
     * @return PrimaryStage der Main Stage
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
 
//############################################################################## 
    public static void main(String[] args) 
    {
        launch(args);
    }
}
