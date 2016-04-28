package utility;

/**
 * Konstanten
 * @author Anton Huber, Dennis Koerner, Eike Diekmann
 *
 */
public interface Konstanten 
{
    public double EPS = 0.0000001;
    //Maximale Anzahl an Koordianten pro Vertex
    public final static int ANZAHL_VERTEX_KOORDS = 3;         
     //Maximale Anzahl an Vertices pro Facet (Dreieck)
    public final static int ANZAHL_VERTEX = 3;                
    public final static int ANZAHL_EDGE_TRIANGLE = 3;
    
    public final static String APPPLICATION_START_HINWEIS= 
            "Um ein 3D Objekt anzuzeigen, muss vorher eine STL Datei geladen "
            + "werden\n über Datei -> Öffnen kann eine Datei ausgewählt "
            + "und geladen werden!";
    
    //DATEI einlesen
    public final static String FILE_READING = "Datei wird geladen. Dies kann"
            + " einige Sekunden bis Minuten dauern!";
    public final static String FILE_READING_ERROR = "Beim einlesen der Datei ist"
            + " ein Fehler aufgetreten!";
    
    public final int ROTATION = 0;
    public final int TRANSLATION = 1;
    public final int X_ACHSE = 0;
    public final int Y_ACHSE = 1;
    public final int Z_ACHSE = 2;
    
    public final String FILE_EXTENSION_DESCRIPTION = "STL-Dateien (*.stl)";
    public final String FILE_EXTENSION = "*.stl";        
    
    public final boolean DEBUG = false;
    
    public final static String DATEIPFAD = 
            "C:\\Users\\Eike\\Documents\\NetBeansProjects\\"
            + "STLVerwaltung\\stl\\bottle.stl";
    public final String FXML_ROOT_PATH = "../view/rootPane.fxml";
}
