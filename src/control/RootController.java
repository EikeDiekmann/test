/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.objects.ObjectHandler;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import model.network.TCP_Server;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import main.MainApp;
import utility.Konstanten;
import static utility.Konstanten.APPPLICATION_START_HINWEIS;
import static utility.Konstanten.FILE_READING;
import utility.Meldungen;

/**
 *RootController steuert die Gui
 * @author Eike
 */
public class RootController implements Initializable, Konstanten, Meldungen
{
    
    
    static ObjectViewController ovC;
    private List<String> stlFaces ;
    private TCP_Server tcpServer;
    private int numberOfFaces;
    private int startIndex;
    private boolean facetNumbers;
    private boolean facetsInLine;
    
    @FXML
    private TextArea txa_StlCode; 
        
     @FXML
    private Label lbl_explanation;
     
    @FXML
    private Tab tab_network;
    
    @FXML
    private Pane Object3DPane;

    @FXML
    private Tab StlCodeTab;

    @FXML
    private Slider sld_y;

    @FXML
    private Slider sld_x;

    @FXML
    private TextField txt_port;

    @FXML
    private TextField txt_ipAddress;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button btn_button;

    @FXML
    private Button btn_verbinden;
    
    @FXML
    private MenuItem mni_open;

      @FXML
    private TextField txt_numberOfFaces;

    @FXML
    private TextField txt_startIndex;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_further;
    
    @FXML
    private Button btn_set;

    @FXML
    private CheckBox cbx_facetNumbers;
  
    @FXML
    private CheckBox cbx_facetsInLine;
    
    @FXML
    private MenuItem mnu_close;
      
    /**
     * mit Diesem Button werden die Einstellungen zum Anzeigen des 
     * StlCodes übernommen
     * @param event 
     */
    @FXML
    void btn_set_OnAction(ActionEvent event) 
    {
        //prüft die Index-Eingabe auf ganzzahligkeit
        if(!txt_startIndex.getText().matches("\\d+$"))
        {
           falscherIndex();
        }
        //prüft, ob die Anzahl der angezeigten Faces eine positive Ganzzahl ist
        else if(!txt_numberOfFaces.getText().matches("\\d+$"))
        {
           falscheAnzahl();
        }//wenn der index und die Anzahl eine Ganzahl sind..
        else
        {
            //werden die einstellungen in Instanzvariablen gespeichert
            this.numberOfFaces = Integer.parseInt(txt_numberOfFaces.getText());
            this.startIndex = Integer.parseInt(txt_startIndex.getText());
            this.facetNumbers=cbx_facetNumbers.isSelected();
            this.facetsInLine=cbx_facetsInLine.isSelected();
            this.showStl(this.startIndex, this.numberOfFaces);
            if(DEBUG)
                System.out.println(this.startIndex + " , " + 
                                                           this.numberOfFaces );
        }
        //ist der Index gleich 0 wird dier "zurück" Button deaktiviert
        if(this.startIndex==0)
            btn_back.setDisable(true);
    }
    
    
//##############################################################################   
    /**
     * reduziert den Startindex der angeziegten Facets
     * und zeigt diese neuen dann an
     * @param event 
     */
     @FXML
    void btn_back_OnAction(ActionEvent event) 
    {

            if (this.startIndex > 0)
            {     
                this.startIndex--;
                txt_startIndex.setText(String.valueOf(this.startIndex));
                this.showStl(this.startIndex, this.numberOfFaces);
                
                 btn_further.setDisable(false);
                 if(this.startIndex == 0)
                     btn_back.setDisable(true);
            }
            else
            {
                btn_back.setDisable(true);
            }
        
    }

//##############################################################################       
     /**
     * erhöht den Startindex der angeziegten Facets
     * und zeigt diese neuen dann an
     * @param event 
     */
    @FXML
    void btn_further_OnAction(ActionEvent event) 
    {   

        if (this.startIndex < stlFaces.size())
        {     
            this.startIndex++;      
            txt_startIndex.setText(String.valueOf(this.startIndex));
                
            this.showStl(this.startIndex, this.numberOfFaces);
            
            btn_back.setDisable(false);
            if(this.startIndex == this.stlFaces.size())
            btn_further.setDisable(true);
        }
        else
        {
            btn_further.setDisable(true);
        }
    }

//##############################################################################   
    /**
     * Menüknopf "Öffnen"
     * Öffnet den Datei Auswähl Dialog (File Chooser)
     * und übergibt den Dateipfad an den StlParser
     * @param event 
     */
    @FXML
    void mni_open_OnAction(ActionEvent event) 
    {
        boolean fileReadingMessage = false;
        
        tab_network.setDisable(true);

        //Es werden nur Datein mit der endund "*.stl" zugelassen
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter
                                   (FILE_EXTENSION_DESCRIPTION, FILE_EXTENSION);
        
        fileChooser.getExtensionFilters().add(extFilter);
        //öffnet den filechooser
        File file = fileChooser.showOpenDialog(MainApp.getPrimaryStage());     
        
        
        ObjectHandler objectHandler = new ObjectHandler(
                                                Object3DPane, file.getPath());
        Thread objectHandlerThread = new Thread(objectHandler);
        objectHandlerThread.start();
        
        while (objectHandlerThread.isAlive() && !fileReadingMessage)
        {
            
                 lbl_explanation.setText(FILE_READING);
                 fileReadingMessage = true;
        }
         tab_network.setDisable(false);
         StlCodeTab.setDisable(false);

    
    }
    
    
//##############################################################################       
    /**
     * wird der "Schließen" Button gedrückt, wird das Programm beendet
     * @param event 
     */
    @FXML
    void mnu_close_OnAction(ActionEvent event) 
    {
    exit(1);
    }
    
    
//##############################################################################   
    /**
     * Wird der Reiter "StlCode" gewählt, wird in das TextareaFeld die StlDatei 
     * mit den voreingestellten Einstellung geladen
     */
    @FXML
    void SltCodeTab_OnSelectionChanged() 
    {
        //besorgt sich das 3D Objekt
        RootController.ovC = ObjectHandler.getOVC();
        //holt gefilterte Faces
        this.stlFaces = RootController.ovC.getStlParser().getTokenizedFaces(); 
        
        this.startIndex = Integer.parseInt(txt_startIndex.getText());
        this.numberOfFaces = Integer.parseInt(txt_numberOfFaces.getText());
        //sollen die Facenummerierung zu sehen sein!?
        this.facetNumbers=cbx_facetNumbers.isSelected();   
        
        //sollen die einzelnen Faces in einer Reihe oder alles 
        //untereinander geschreiben sein?
        this.facetsInLine = cbx_facetsInLine.isSelected();  
                                        //
        if(DEBUG)
            System.out.println(this.startIndex + " , " + this.numberOfFaces );
        
        //ruftFunktion zunm Anzeigen der Faces auf
        this.showStl(this.startIndex, this.numberOfFaces);
    }
    
//##############################################################################       
    /**
     * erstellt einen neuen Server, über den das Objekt gesteuert werden kann
     * @param event
     * @throws IOException 
     */
    @FXML
    void btn_verbinden_OnAction(ActionEvent event) throws IOException 
    {
        tcpServer = new TCP_Server(Integer.parseInt(txt_port.getText()));
        btn_verbinden.setDisable(true);
    }
  
//##############################################################################
    /**
     * Initialisiert den Controller
     * @param url
     * @param resourceBundle 
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) 
    {
        //Hinweis, dass erst eine Datei geladen werden muss
        lbl_explanation.setText(APPPLICATION_START_HINWEIS); 
        
        //einige Startparameter
        btn_back.setDisable(true);  
        this.startIndex = 0;
        this.numberOfFaces = Integer.parseInt(txt_numberOfFaces.getText());
        this.facetNumbers = true;
    }
    
//##############################################################################   
    /**
     * gibt eine Errormeldung heraus, wenn der Startindex 
     * keine positive Ganzzahl ist
     */
    private void falscherIndex()
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(ERROR_INDEX_TITLE);
        alert.setHeaderText(ERROR_INDEX_HEADERTEXT);
        alert.setContentText(ERROR_INDEX_CONTENT);
        alert.show();
    }
    
//##############################################################################    
    /**
     * gibt eine Errormeldung heraus, wenn die Reichweite 
     *          (Anzahl der angezeigten Facets) keine positive Ganzzahl ist
     */
    private void falscheAnzahl()
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(ERROR_REICHWEITE_TITLE);
        alert.setHeaderText(ERROR_REICHWEITE_HEADERTEXT);
        alert.setContentText(ERROR_REICHWEITE_CONTENT);
        alert.show();
        
    }
    
//##############################################################################   
    /**
     * zeigt die Facets in der Textarea an.
     * je nach Einstellungen in einer Zeile, mit Nummerierung etc
     * @param startIndex
     * @param range 
     */
    private void showStl(int startIndex, int range)
    {
      for(int i = startIndex; i < (startIndex + range); i++)
      {
        if((startIndex + i) < this.stlFaces.size() && startIndex >=0)  
        {
          if (this.facetNumbers)
          {
          if(i==startIndex)
          {
            if(this.facetsInLine) //erste Zeile mit Nummereireung in einer Zeile
            {
              txa_StlCode.setText("Facet Nummer: " + (i)+ "\n" + (
                                               (this.stlFaces.get(i)))  + "\n");
            }
            else    //erste Zeile mit Nummereireung und getrennten Zeilen
            { 
              txa_StlCode.setText("Facet Nummer: " + (i)+ "\n" + 
                         ((this.stlFaces.get(i)).replace(" ", "\n"))  + "\n");
            }
          }
          else 
          {
            //folgende Zeilen mit Nummereireung in einer Zeile
            if(this.facetsInLine) 
            {
               txa_StlCode.appendText("Facet Nummer: " + (i)+ "\n" + (
                                    (this.stlFaces.get(i)))  + "\n");
            }
            else    //folgende Zeilen mit Nummereireung und getrennten Zeilen
            {
              txa_StlCode.appendText("Facet Nummer: " + (i)+ "\n" + (
                            (this.stlFaces.get(i)).replace(" ", "\n"))  + "\n");
            }
          }
        } 
        else 
        {
          if(i==startIndex)
          {
            if(this.facetsInLine)//erste Zeile OHNE Nummereireung in einer Zeile
              txa_StlCode.setText(((this.stlFaces.get(i)))  + "\n");
            else    //erste Zeile OHNE Nummereireung und getrennten Zeilen
            {
              txa_StlCode.setText(((this.stlFaces.get(i)).replace(" ", "\n"))  
                                                                        + "\n");
            }
          }
          else
          {
            if(this.facetsInLine)   //w Zeile OHNE Nummereireung in einer Zeile
              txa_StlCode.appendText(((this.stlFaces.get(i)))  + "\n");
            else    //weitere Zeilen OHNE Nummereireung und getrennten Zeilen
            {
              txa_StlCode.appendText(((this.stlFaces.get(i)).replace(" ", "\n"))  
                                                                        + "\n");
            }
          }
        }
      }    
    }
  }
}
