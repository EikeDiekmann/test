package model.objects;
/**
 * 
 * @author Anton Huber, Dennis Koerner, Eike Diekmann
 *
 */
public class Vertex
{
	private double x,y,z;
	
	/**
	 * Konstruktor erzeugt einen Vertex, mit den Koordinaten x,y,z
	 * @param x Koordinate auf X-Achse
	 * @param y Koordinate auf Y-Achse
	 * @param z Koordinate auf Z-Achse
	 */
	public Vertex(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

        
//##############################################################################  	
	/**
	 * Gibt die X-Koordinate zurueck
	 * @return Koordinate auf X-Achse
	 */
	public double getX()
	{
		return this.x;
	}

//##############################################################################        
    /**
     * Setzt die X-Koordinate
     * @param x Koordinate auf X-Achse
     */
    public void setX(double x)
    {
        this.x = x;
    }
	
//##############################################################################  	
    /**
    * Gibt die Y-Koordinate zurueck
    * @return Koordinate auf Y-Achse
    */
    public double getY()
    {
	return this.y;
    }
	
//##############################################################################  
    /**
     * Setzt die Y-Koordinate
     * @param y Koordinate auf Y-Achse
     */
    public void setY(double y)
    {
        this.y = y;
    }

//##############################################################################  
    /**
     * Gibt die Z-Koordinate zurueck 
     * @return Koordinate auf Z-Achse
     */
    public double getZ() 
    {
	return this.z;
    }
	
//##############################################################################  
    /**
    * Setzt die Z-Koordinate
    * @param z Koordinate auf Z-Achse
    */
    public void setZ(double z)
    {
    this.z = z;
    }


//##############################################################################      
    /**
    * Addiert zwei Vektoren
    * @param vektorSummandA Summand
    * @param vektorSummandB Summand
    * @return Ergebnis der Vektoraddition
     */
    public static Vertex addiere(Vertex vektorSummandA, Vertex vektorSummandB)
    {
        return new Vertex(vektorSummandA.getX() + vektorSummandB.getX(), 
                vektorSummandA.getY() + vektorSummandB.getY(), 
                vektorSummandA.getZ() + vektorSummandB.getZ()); 
    }
    
    
    /**
     * Subtrahiert zwei Vektoren
     * @param vektorMinuend     Minuend, von dem abgezogen wird
     * @param vektoSubtrahend   Subtrahend
     * @return Ergebnis der Subtraktion
     */
    public static Vertex subtrahiere(Vertex vektorMinuend, Vertex vektoSubtrahend)
    {
        return new Vertex(vektorMinuend.getX() - vektoSubtrahend.getX(), 
                vektorMinuend.getY() - vektoSubtrahend.getY(), 
                vektorMinuend.getZ() - vektoSubtrahend.getZ()); 
    }
	
//##############################################################################  
    /**
     * Bildet das Kreutzprodukt zweier Vekotoren
     * @param x Vektor x
     * @param y Vektor y
     * @return Kreutzprodukt
     */
    public static Vertex kreuzprodukt(Vertex x, Vertex y)
    {
        double z1, z2, z3;
        
        z1 = x.getY() * y.getZ() - x.getZ() * y.getY();
        z2 = x.getZ() * y.getX() - x.getX() * y.getZ();
        z3 = x.getX() * y.getY() - x.getY() * y.getX();
        
        return new Vertex(z1,z2,z3);
        
    }

//##############################################################################  
	@Override
	public int hashCode()
	{
	    return (int) (this.x * 5 + this.y*7 + this.z * 3);
	}
	
	@Override
	public boolean equals(Object obj)
	{
	    if (obj == null)
            return false;
        
        if (obj == this)
            return true;
 
	    if (obj.getClass() != this.getClass())
	        return false;
	    
	    Vertex vertex = (Vertex) obj;
	    
            //Unterscheiden sich die Hashcodes, so sind die Objekte ungleich.
	    if(vertex.hashCode() != this.hashCode())                                            
	        return false;
	    
	    if(vertex.getX() != this.getX() || vertex.getY() != this.getY() || 
                                                    vertex.getZ() != this.getZ())
	        return false;
	    	    
	    return true;
	}
	
//##############################################################################	
	@Override
	public String toString()
	{
	    return this.x + " " + this.y + " " + this.z;
	    
	}

	


	
}
