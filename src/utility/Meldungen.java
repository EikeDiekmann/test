package utility;

/**
 *
 * @author Eike
 */
public interface Meldungen 
{
     //ErrorMeldung, weil in das Index Feld des StlCodeViewers keine Zahl 
                            //                  eingetragen wurde
    public final String ERROR_INDEX_TITLE = "Index muss positive Ganzzahl sein!";
    public final String ERROR_INDEX_HEADERTEXT="Index Eingabeerror";
    public final String ERROR_INDEX_CONTENT = "In das Index Feld darf nur eine "
            + "positiven Ganzzahl geschrieben werden!";
       
    //Errormeldung, da f. die Reichweite keine positive Int Zahl eingetragen wurde
    public final String ERROR_REICHWEITE_TITLE = "Anzahl muss positive Ganzzahl "
            + "sein!";
    public final String ERROR_REICHWEITE_HEADERTEXT = "Anzahl Eingabeerror";
    public final String ERROR_REICHWEITE_CONTENT = "In das Anzahl Faces pro "
            + "Anzeige"
            + " Feld darf nur eine positiven Ganzzahl geschrieben werden!";
    
    
    
    
}
