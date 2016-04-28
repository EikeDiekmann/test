package utility;

public interface AusgabeTexte 
{
    public static final String TXT_NO_STL_FILE = new String("kein korrekte STL-Datei");
    public static final String TXT_NO_TRIANGLE = new String("Dreieck ist nicht eindeutig und korrekt definiert");
    
    
    public static final String TXT_PARSING = new String("Verarbeite Dateiinhalt");
    public static final String TXT_PARSING_SUCCESS = new String("Datei entspricht dem STL-Format");
    public static final String TXT_PARSING_UNSUCCESSFULL = new String("Datei ist nicht im STL-Format");
    public static final String TXT_BEGIN_READING_FILE = new String ("STL File wird eingelesen");
    public static final String TXT_END_READING_FILE = new String ("STL File wurde eingelesen");
    
    public static final String TXT_FILENAME_PROMPT = new String("Bitte Dateiname angeben:");
    
    public static final String WINDOW_TITLE = "STL FIle Viwer";
}
