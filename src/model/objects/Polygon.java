package model.objects;

/**
 * Polygon
 * @author Anton Huber, Dennis Koerner, Eike Diekmann
 *
 */
public class Polygon
{
    Edge kanten[];
   
    /**
     * Konstruktor erzeugt ein Polygon aus Kanten
     * @param kanten
     */
    public Polygon(Edge kanten[])
    {
        this.kanten = kanten;
    }
    
    
    /**
     * Gibt die Kanten zurueck, die das Polygon definieren
     * @return Kanten des Polygons
     */
    public Edge [] getKanten()
    {
        return this.kanten;
    }
    
    
    /**
     * gibt den Normalenvektor des Polygons zurueck
     * @return Normalenvektor des Polygons
     */
    public Vertex getNormale()
    {        
        return Vertex.kreuzprodukt(this.kanten[0].getRichtungsvektor(), this.kanten[1].getRichtungsvektor());
    }
    
        
    /** TO DO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * Berechnet den Flaecheninhalt
     * @return Fl�cheninhalt
     */
    public double getFlaecheninhalt()
    {        
        // 0. Sichserstellen, das das Polygon eben ist 
        // 1. Polygon in den Ursprung legen
        /* 2. Polygon so drehen, das es in einer Ebene liegt, z.B. in der Ebene aufgespannt durch die X- und Y-Achsen.
              um eine Koordinate zu eliminieren. */
        // 3. Flaeche berechnen mit der Gaussschen Trapezformel.
        return 0;
    }
       
    
    @Override
    public int hashCode()
    {
        int hash = (int)getFlaecheninhalt();
        
        for (int i = 0; i < this.kanten.length; i++)
        {
            hash += kanten[i].hashCode();
        }
        return hash;
    }
      
    
    @Override
    public boolean equals(Object obj)           //fuers erste Hinreichend gut. Ich kann nicht ausschliessen, dass es Ausnahmen gibt, bei denen gleiche Polygone als ungleich markiert wreden oder umgekehrt 
    {
        boolean gleicheKanteFlag = false;
 
        if (obj == null)                    //Existiert das Objekt?
            return false;
        
        if (obj == this)                    //Sind die zu vergleichenden Objekte die Selben?
            return true;
        
        if(obj.getClass() != this.getClass())   // Besitzen die beiden Objekte die gleiche Klasse
            return false;         
            
        Polygon polygon = (Polygon) obj;
        
        if (polygon.hashCode() != this.hashCode())                           //Unterscheiden sich die Hashcodes, so sind die Objekte ungleich.
            return false;
        
        if (polygon.getFlaecheninhalt() != this.getFlaecheninhalt())        //Gleiche Polygone haben die gleiche Flaeche
            return false;
        
        if (this.kanten.length !=  polygon.getKanten().length)          //Gleiche Polygone haben die selbe Anzahl an Kanten
            return false;
        
        for(int i = 0; i < kanten.length; i++)                      //Jede Kante im ersten Polygon muss im zweiten enthalten sein
        {
            for (int j = 0; j < polygon.getKanten().length; j++)
            {
                if (polygon.getKanten()[j].equals(this.kanten[i]))
                {
                    gleicheKanteFlag = true;
                    break;
                }
            }
            
            if(gleicheKanteFlag==false)
                return false;                            
        }
        return true;
    }


    public int compareTo(Polygon poly) {
        // TODO Auto-generated method stub
        return 0;
    }


    public int compareTo(Triangle tri) {
        // TODO Auto-generated method stub
        return 0;
    }

}
