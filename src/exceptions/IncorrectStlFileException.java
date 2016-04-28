package exceptions;
import utility.AusgabeTexte;


public class IncorrectStlFileException extends Exception implements AusgabeTexte
{

    /**
     * Exception wenn eine Datei geladen wird, 
     *              die nicht dem *.stl format entspricht
     */
    private static final long serialVersionUID = 1L;

    public IncorrectStlFileException()
    {
        super(TXT_NO_STL_FILE);
    }
}
