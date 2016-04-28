package model.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import exceptions.IncorrectStlFileException;
import java.util.List;
import utility.AusgabeTexte;


/**
 * Stlreader liest die StlDatei ein
 * @author Eike
 */
public class StlReader implements AusgabeTexte
{
    
    private BufferedReader br = null;
    private final String dateipfad;
    private Tokenizer tokenizer;
    String stlFile ="";

    
//##############################################################################
/**
 * Konstruktor
 * @param dateipfad
 */
    public StlReader(String dateipfad)
    {        
        this.dateipfad = dateipfad;
        
        System.out.println(TXT_BEGIN_READING_FILE + ": " + this.dateipfad);
        // TODO Auto-generated method stub
        if (readStlFile(this.dateipfad))
        {   
           System.out.println(TXT_END_READING_FILE + ": " + this.dateipfad);        
           
        }  
    }
 
//##############################################################################

    public String getStlString() throws IncorrectStlFileException
    {
        if (this.isCorrectStlFormat())
        {
            System.out.println(TXT_PARSING_SUCCESS);
        }
        else
        {
            System.out.println(TXT_PARSING_UNSUCCESSFULL);
            throw new IncorrectStlFileException();
        }
        return this.stlFile;
    }
    
//##############################################################################
    /**
     * liest die StlDatei mit dem Dateipfad "dateipfad" ein
     * @param dateipfad Pfad zur StlDatei
     * @return  true, falls die Datei eingelesen wurde, sonst false
     */
    private boolean readStlFile(String dateipfad)
    {
        boolean konnteDateiEinlesen = false;
        String line="";
        
        try 
        {
            String tmpLine="";
 
            br = new BufferedReader(new FileReader(dateipfad)); 

            while( (tmpLine = br.readLine()) != null ) 
            {
                //fuegt nach jeder Zeile ein Leerzeichen als Trenner ein.
                line += tmpLine.trim() + " ";    
                konnteDateiEinlesen = true;
            }
        }
        
        catch (FileNotFoundException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        finally
        {
            try 
            {
                br.close();
            } catch (IOException e) 
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
       this.stlFile = line;
      // System.out.println(line);
       return konnteDateiEinlesen;
    
    }
  
//##############################################################################   
    /**
     * Prueft, ob die eingelesene Datei im STL-Format vorliegt
     * @return true, wenn es eingelesene Datei im STL-Format vorliegt, sonst false
     */
    private boolean isCorrectStlFormat()
    {
        //Tokenizer checkedStlFile = new Tokenizer(this.stlFile);
        this.tokenizer = new Tokenizer(this.stlFile);
        return tokenizer.isCorrectFormat();
        
    }
    
    
//##############################################################################
    /**
     * gibt die verarbeiteten Faces zurück
     * @return tokenized Faces
     */
    public List<String> getTokenizedFaces()
    {
        return this.tokenizer.getTokenizedFaces();
    }
    
}
