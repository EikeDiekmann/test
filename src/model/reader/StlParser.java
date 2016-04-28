package model.reader;

import java.util.List;

import exceptions.IncorrectStlFileException;
import utility.AusgabeTexte;
import model.objects.Triangle;

/**
 * StlParser prüft die StlDatei und erzeugt entsprechende Dreiecke
 * @author Eike
 */
public class StlParser  implements AusgabeTexte, Runnable
{
    private final String dateipfad;
    private String stlString;
    private StlReader stlReader;
    TriangleFromStlString createdTriangle;
    
//##############################################################################    
    /**
     * Konstruktor speichert den Dateipfad der Stl-Datei
     * @param dateipfad Dateipfad zu StlDatei
     */
    public StlParser(String dateipfad)
    { 
       this.dateipfad = dateipfad;
    }
    
//##############################################################################    
    /**
     * soll parallel laufen
     */
    @Override
    public void run() 
    {
         System.out.println("Dateipfad: " + this.dateipfad);
        this.stlReader = new StlReader(this.dateipfad);
       
        System.out.println(TXT_PARSING);
                
        
        //Speichern des StlStrings aus der Datei
        try 
        {
            this.stlString = stlReader.getStlString();
        } 
        catch (IncorrectStlFileException e) 
        {        }
    }
    
//##############################################################################    
    /**
     * gibt den überprüften Dateiinhalt der StlDatei zurück
     * @return geprüfte StlDatei
     */
    public String getStlString()
    {
        return this.stlString;
    }
    
 //##############################################################################   
    /**
     * 
     * @return 
     */
    public List<String> getTokenizedFaces()
    {
                return this.createdTriangle.getTokenizedFaces();
    }
    
//##############################################################################   
    /**
     * liest die einzelnen Dreiecke in eine Liste ein
     * @return List<Triangle>: eine Liste mit Dreiecken aus der StlDatei
     */
    public List<Triangle> parseTriangles()
    {
     //   System.out.println(stlString);
        this.createdTriangle = new TriangleFromStlString(this.stlString);
        return createdTriangle.getTriangles();
    }

}
