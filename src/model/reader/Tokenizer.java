package model.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.IncorrectStlFileException;
import utility.AusgabeTexte;
import utility.StlBnf;


/**
 * Überprüft und verarbeitet String mittels regulärerer Ausdrücke
 * @author Eike
 */
public class Tokenizer implements StlBnf, AusgabeTexte
{
    List<String> tokenizedString = new ArrayList<>();
    List<String> tokenizedFaces = new ArrayList<>();
    
   boolean correctFormat = false;
    
   /**
    * Konstruktor startet den Tokenizer-Prozess
    * @param str zu behandelnde String
    */
    public Tokenizer(String str)
    {
        try 
        {
            tokenize(str);
        } catch (IncorrectStlFileException e) 
        {
            e.printStackTrace();
        }
    }
    
    
 //#############################################################################  
    /**
     * überprüft den übergeben String, ob dieser im StlFormat vorleigt
     * Außerdem werden die Faces gespeichert, die in der StlDatei vorhanden sind
     * @param str zu überüprüfende String, aus dem die Objekt 
     *                                          Facets extrahiert werden
     * @throws IncorrectStlFileException wirft eine Ausnahme, wenn es sich bei 
     *                  der Datei nicht um eine korrekte StlDatei handelt
     */
    private void tokenize(String str) throws IncorrectStlFileException
    {
        String[] tmp = str.split(" ");
        List <String> result = new ArrayList <>();
        List <String> facesResult = new ArrayList <>();
        for(int i = 0; i < tmp.length; i++)
        {
            if(Pattern.compile("[a-zA-Z0-9]").matcher(tmp[i]).find())
            {
                //trim() entfernt die Whitespaces am Anfang u. am Ende des Strings
                tmp[i] = tmp[i].trim();            
                //Wandelt jeden Buchstaben des Strings in den entsprechenden 
                //                                              Grossbuchstaben 
                tmp[i] = tmp[i].toUpperCase();   
                //Fuegt isolierte Tokens in die Liste ein    
                result.add(tmp[i]);                                           
            }
        }
        
        result = filterTokens(result, GRMA_STL);
        this.tokenizedString = result;
        this.tokenizedFaces = filterTokens(facesResult, GRMA_FACET);
        
        
        if(result.size()<1)
        {
            throw new IncorrectStlFileException();
        }
        else
        {
            correctFormat = true;
        }
    }
    
    
 //#############################################################################
 /**
  * gibt die Liste der Faces zurück
  * @return Liste der Faces aus der StlDatei
  */
    public List<String> getTokenizedFaces()
    {
        return this.tokenizedFaces;
    }
    
    
 //#############################################################################   
    /**
     * gibt zurück, ob die Datei im richtigen Format vorliegt
     * @return true, falls Datei eine Stl Datei ist, sonst false
     */
    public boolean isCorrectFormat()
    {
        return this.correctFormat;
    }
    
    
 //#############################################################################
    /**
     * gibt den gesamten (gefilterten) Dateiinhalt zzurück
     * @return Dateiinhalt als String
     */
    public String getTokenizedString()
    {
        String tmp = "";
        for(int i = 0; i <this.tokenizedString.size(); i++)
        {
            tmp += this.tokenizedString.get(i); 
        }
        return tmp;
    }
    
    
 //#############################################################################
    /**
     * Filtert eine beliebige String Liste anhand des 
     *                      übergebenen regulären Ausdrucks
     * @param tokenList Liste mit zu tokenizen Strings
     * @param regex regluärer Ausdruck anhand dessen die gewünschtenn Stellen 
     *                      gefunden und zurückgegeben werden
     * @return gesuchte Passagen, die dem regulären Ausdruck entsprechen
     */
    public static List<String> filterTokens(List<String> tokenList, String regex)
    {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Tokenizer.toString(tokenList));

        while(matcher.find())                  
        {
            result.add(matcher.group(0));
        }
        return result;
    }
   
    
 //#############################################################################
    /**
     * gibt die Tokenized String Lister zurück
     * @return Tolkenized String Liste
     */
    public List<String> getTokenizedList()
    {
        return tokenizedString;
    }
    
 
 //#############################################################################
    /**
     * wandelt die StringListe in eineneinzelnen String um
     * @return 
     */
    @Override
    public String toString()
    {

        String tmp = "";
        for(int i = 0; i < this.tokenizedString.size(); i++)
        {
            tmp += this.tokenizedString.get(i) + " "; 
        }
        return tmp;
    }
    
 //#############################################################################
    /**
     * wandelt die StringListe in eineneinzelnen String um
     * @return 
     */
    private static String toString(List<String> tokenizedString)
    {
        String tmp = "";
        for(int i = 0; i < tokenizedString.size(); i++)
        {
            tmp += tokenizedString.get(i) + " "; 
        }
        return tmp;
    }
    
}
