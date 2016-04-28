package model.reader;


import java.util.List;
import javafx.scene.shape.TriangleMesh;
import model.objects.Triangle;
import model.objects.Vertex;
import static utility.Konstanten.ANZAHL_VERTEX;


/**
 *MeshConverter konvertiert eine Liste aus Dreiecken (Triangles) 
 *              in eine TriangleMesh von JavaFX
 * @author Eike
 */
public class MeshConverter 
{
    private TriangleMesh triangleMesh;
        
    /**
     * DefaultKonstruktor erzeug ein neues leeres TriangleMesh
     */
    public MeshConverter()
    {
        triangleMesh =   new TriangleMesh();
    }
    
    
//##############################################################################  
    /**
     *  convertFromTriangles bekommt eine Liste mit Dreiecke übergeben und 
     *                      erzeugt daraus ein TriangleMesh
     * @param triangles Liste aus Driecken (Triangles)
     * @return TriangleMesh erzeugt aus den triangles
     */
    public TriangleMesh convertFromTriangles(List <Triangle> triangles)
    {
        for(int i = 0; i < triangles.size(); i++)
        {
            for(int j = 0; j < ANZAHL_VERTEX; j++)
                this.addVertexToMesh(getUniqueVertices(triangles.get(i))[j]);
            this.addTexToMesh(i);
            this.addFacesToMesh(i);
        }
        
       return this.triangleMesh;
    }
    
//##############################################################################  
    /**
     * Aus den Kanten, die ein Drieck definieren werden 
     *                  die drei Eckpunkte zurückgegeben
     * @param triangle Dreick, von dem die drei Eckpunkte gesucht sind
     * @return die drei Eckpunkte (Vertex[])
     */
    private Vertex[] getUniqueVertices(Triangle triangle)
    {
        //Vertex punkte[];
        //Edge kanten[] = triangle.getKanten();
        return Triangle.berechneUniquePunkteDesDreiecks(triangle.getKanten());        
        
    }
    
//##############################################################################   
    /**
     * fügt der Mesh einen Punkt hinzu
     * @param vertex Punkt, der hinzugefüügt werden soll
     */
    private void addVertexToMesh(Vertex vertex)
    {
         triangleMesh.getPoints().addAll( (float)vertex.getX(), 
                            (float) vertex.getY(),  (float) vertex.getZ());
    }
    
    
//##############################################################################    
    /**
     * fügt der Mesh Facets hinzu
     * @param facenumber 
     */
    private void addFacesToMesh(int facenumber)
    {
        int first = facenumber * ANZAHL_VERTEX;
        triangleMesh.getFaces().addAll(first, first, first + 2, 
                                            first + 2, first + 1, first + 1 );
    }
    
 
//##############################################################################
    /**
     * fügt der Mesh Texturkoordinaten hinzu
     * @param facenumber 
     */
    private void addTexToMesh(int facenumber)
    {
        triangleMesh.getTexCoords().addAll(1,1,0,1,1,0);
    }
    
//##############################################################################    
    /**
     * NYD
     * @param vertex
     * @return 
     */
    private boolean isVertexInMesh(Vertex vertex)
    {
        return false;
    }
    
}