package model.reader;

import java.util.ArrayList;
import java.util.List;

import utility.StlBnf;
import model.objects.Edge;
import model.objects.Triangle;
import model.objects.Vertex;


/**
 * erzeugt aus Strings, die Punkte eines Dreiecks enthalten, Dreiecke
 * @author Eike
 */
public class TriangleFromStlString implements StlBnf
{

    private final List<Triangle> dreiecksListe = new ArrayList<>();
    private List<String> tokenizedFacet;
    
    
//##############################################################################
    /**
     * Konstruktor
     * Wandelt String, in dem Dreickskoordinaten enthalten sind, 
     *                                          in Dreicke (Triangles) um
     * @param stlString String, der die Koordinaten eines Dreiecks enthält
     */
    public TriangleFromStlString (String stlString)
    {
   
    List<String>vertexList = getVertexString(stlString);
   
    List<Vertex> vertices = this.getVertexList(vertexList);
    Edge kanten[] = new Edge[3];
    
        for(int i = 0; i < vertices.size(); i++)
        {
            if(i%3 == (3-1))
            {
                kanten[i%3] = new Edge(vertices.get(i), vertices.get(i-(3-1)));

                this.dreiecksListe.add(new Triangle(kanten[0], 
                                                    kanten[1], 
                                                    kanten[2]));
            }
            else
            {
                kanten[i%3] = new Edge(vertices.get(i), vertices.get(i+1));
            }
        }
    }
    
    
//##############################################################################
    /**
     * gibt die geparsten Dreicke zurück
     * @return Triangle List, die aus dem String geparsten Dreicke
     */
    public List<Triangle> getTriangles()
    {
        return this.dreiecksListe;
    }

    
//##############################################################################
    /**
     * 
     * @param STLFile
     * @return 
     */
    public List<String> getVertexString(String STLFile)
    {   // STL-Filter-Vertexes
        Tokenizer checkedStlFile = new Tokenizer(STLFile);

        List<String> tokenizedList = checkedStlFile.getTokenizedList();
        this.tokenizedFacet = Tokenizer.filterTokens(tokenizedList, GRMA_FACET);
        List<String> tokenizedVertex = Tokenizer.filterTokens(
                                                   tokenizedFacet, GRMA_VERTEX);
        List<String> vertexList = Tokenizer.filterTokens(
                                                  tokenizedVertex, GRMA_DOUBLE);
        return vertexList;
    }
    
    
//##############################################################################
    /**
     * gibt die Tokenized faces zurück
     * @return Tokenized Faces als String liste
     */
    public List<String> getTokenizedFaces()
    {
        return this.tokenizedFacet;
    }
    
    
//##############################################################################
    /**
     * gibt die Liste mit Vertices zurück
     * @param tokenList 
     * @return 
     */
    public List<Vertex> getVertexList(List<String> tokenList) //Vertex
    { 
        List<String> vertexStringList = new ArrayList<String>();
        List<Vertex> vertexList = new ArrayList<Vertex>();
        Double koordinate[] = new Double[3];
        vertexStringList = tokenList; 
        
        for(int i = 0; i < vertexStringList.size(); i++)
        {
            koordinate[i%3] = Double.parseDouble(
                                    vertexStringList.get(i).replace(",", "."));
      
            if(i%3 == 2)
            {
                vertexList.add(new Vertex(  koordinate[0], 
                                            koordinate[1], 
                                            koordinate[2]));
            }
        }
        return vertexList;
    }
}

