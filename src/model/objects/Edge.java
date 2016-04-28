package model.objects;
import utility.Konstanten;

/**
 * Edge
 * @author Anton Huber, Dennis Koerner, Eike Diekmann
 *
 */
public class Edge implements Konstanten
{
    Vertex ortsvektor; //Startpunkt der Gerade
    Vertex zielpunkt; //Endpunkt der Geraden
    
    /**
     * Konstruktor erzeugt eine Kante mit dem Orstvektor 
     *                      zum Anfangspunkt und einem Zielpunkt
     * @param ortsvektor Ortsvektor der Kante
     * @param zielpunkt Ziel- bzw Endpukt der Geraden
     */
    public Edge (Vertex ortsvektor, Vertex zielpunkt)
    {
        this.ortsvektor = ortsvektor;
        this.zielpunkt = zielpunkt;
    }

    /**
     * Gibt den Ortsvektor der Kante zurück
     * @return Ortsvektor der Kante
     */
    public Vertex getOrtsvektor() 
    {
        return this.ortsvektor;
    }
    
    /**
     * Setzt den Ortsvektor
     * @param ortsvektor Ortsvektor der Kante
     */
    public void setOrtsvektor(Vertex ortsvektor)
    {
        this.ortsvektor = ortsvektor;
    }
    
    
    /**
     * gibt den Ziel- bzw Endpunkt der Kante zur�ck
     * @return Zielpunkt der Kante
     */
    public Vertex getZielpunkt() 
    {
        return this.zielpunkt;
    }
    
    
    /**
     * Setzt den Zielpunkt
     * @param zielpunkt Zielpunkt der Kante
     */
    public void setZielpunkt(Vertex zielpunkt)
    {
        this.zielpunkt = zielpunkt;
    }
    
    /**
     * gibt den Richtungsvektor der Geraden zur�ck
     * @return Richtungsvektor der Geraden
     */
    public Vertex getRichtungsvektor()
    {
            //richtungsvektor = Zielpunkt - Ortsvektor
        Vertex richtungsvektor = new Vertex(this.zielpunkt.getX() 
                - this.ortsvektor.getX(), this.zielpunkt.getY() 
                - this.ortsvektor.getY(), this.zielpunkt.getZ() 
                - this.ortsvektor.getZ());
        return richtungsvektor;
    }
	    
    /**
     * gibt den Betrag zwischen den, 
     *        durch den Orstvektor und dem Zielpunkt definierten, Punkten zurück
     * @return L�nge der Kante
     */
    public double getLaenge()
    {	
        return(Math.sqrt(getRichtungsvektor().getX() * getRichtungsvektor().getX()
                    + getRichtungsvektor().getY() * getRichtungsvektor().getY() 
                    + getRichtungsvektor().getZ() * getRichtungsvektor().getZ()));
    }
 
//##############################################################################     
    @Override
    public int hashCode()
    {
        return this.ortsvektor.hashCode() * 11 + this.zielpunkt.hashCode() * 2 + 
                (int) this.getLaenge();
    }
 //##############################################################################    
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        
        if (obj == this)
            return true;
    
        if (obj.getClass() != this.getClass())
            return false;
        
        Edge edge = (Edge) obj;
        
        //Unterscheiden sich die Hashcodes, so sind die Objekte ungleich.
        if (edge.hashCode() != this.hashCode())                       
            return false;
        
        if (!edge.getOrtsvektor().equals(this.getOrtsvektor()) 
                || !edge.getRichtungsvektor().equals(this.getRichtungsvektor()))
            return false;
   
        return true;         
    }
 
    
//##############################################################################
    @Override
    public String toString()
    {
       return ("(" +this.ortsvektor + " ; " + this.zielpunkt + ")"); 
    }
}