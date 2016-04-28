package model.objects;

/**
 * Polyeder
 * @author Anton Huber, Dennis Koerner, Eike Diekmann
 * HINWEIS: Diese Polyeder ist bisher noch nicht sicher ein geschlossenes 3D-Objekt.
 */
public class Polyeder 
{
    Polygon polygone[];
    Vertex bezugspunkt;
    
    
    /**
     * Konstruktor erzeugt einen Polyeder, der aus Polygonen besteht
     * @param polygone Polygonsammlung
     */
    public Polyeder(Polygon polygone[])
    {
        this.polygone = polygone; 
        bezugspunkt = polygone[0].getKanten()[0].getOrtsvektor();
    }
    
    
    /**
     * Gibt die Polygone zur�ck, die den Polyeder definieren
     * @return Polygone
     */
    public Polygon[] getPolygone()
    {
        return this.polygone;
    }
    
    
    /** 
     * Versetzt den Polyeder auf einer der drei Achsen um den Versatz
     * @param achse 0: x, 1: y, 2: z
     * @param versatz Um diesen Wert wird der polyeder im Raum verschoben
     */
    public void translate(int achse, double versatz)
    {
        switch  (achse)
        {
                //X-Achse
            case 0: 
                for(int i = 0; i < this.polygone.length; i++)
                {
                    for(int j = 0; j < this.polygone[i].getKanten().length; j ++)
                    {
                       Vertex ortsvektor =  this.polygone[i].getKanten()[j].getOrtsvektor();
                       ortsvektor.setX(ortsvektor.getX() - versatz);
                     //  this.polygone[i].getKanten()[j].setOrtsvektor(ortsvektor);
                      
                       Vertex zielpunkt =  this.polygone[i].getKanten()[j].getZielpunkt();
                       zielpunkt.setX(zielpunkt.getX() - versatz);
                   //    this.polygone[i].getKanten()[j].setZielpunkt(zielpunkt);
                    }
                }     
            
            break;
            
                //Y-Achse
            case 1: 
                for(int i = 0; i < this.polygone.length; i++)
                {
                    for(int j = 0; j < this.polygone[i].getKanten().length; j ++)
                    {
                        Vertex ortsvektor =  this.polygone[i].getKanten()[j].getOrtsvektor();
                        ortsvektor.setY(ortsvektor.getY() - versatz);
                     //  this.polygone[i].getKanten()[j].setOrtsvektor(ortsvektor);
                      
                        Vertex zielpunkt =  this.polygone[i].getKanten()[j].getZielpunkt();
                        zielpunkt.setY(zielpunkt.getY() - versatz);
                   //    this.polygone[i].getKanten()[j].setZielpunkt(zielpunkt);
                    }
                } 
            
            break;
                
                //Z-Achse
            case 2: 
                for(int i = 0; i < this.polygone.length; i++)
                {
                    for(int j = 0; j < this.polygone[i].getKanten().length; j ++)
                    {
                        Vertex ortsvektor =  this.polygone[i].getKanten()[j].getOrtsvektor();
                        ortsvektor.setZ(ortsvektor.getZ() - versatz);
                     //  this.polygone[i].getKanten()[j].setOrtsvektor(ortsvektor);
                      
                        Vertex zielpunkt =  this.polygone[i].getKanten()[j].getZielpunkt();
                        zielpunkt.setZ(zielpunkt.getZ() - versatz);
                   //    this.polygone[i].getKanten()[j].setZielpunkt(zielpunkt);
                    }
                } 
                
            break;
        
            default: ;
            break;
        }
    }
    
    
    /** TO DO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * Rotiert den Polyeder um eine Achse
     * @param achse 0: x, 1: y, 2: z
     * @param winkelInGrad gibt den Rotationswinkel in Grad an
     */
    public void rotate(int achse, double winkelInGrad)
    {
        
    }
    
    
    /**
     * gibt den bezugspunkt zurueck
     * @return Bezugspunkt des Polyeders
     */
    public Vertex getBezugspunkt()
    {
        return this.bezugspunkt;
    }
    
    /**
     * Berechnet den Oberfl�cheninhalt des Polyeders
     * @return Obefl�cheninhalt des Polyeders
     */
    public double getOberflaechenInhalt()
    {
        double flaeche = 0;
        for(int i = 0; i < this.polygone.length; i++)
        {
            flaeche += this.polygone[i].getFlaecheninhalt();
        }
        
        return flaeche;
    }
    
    
    /**   TO DO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * Berechnet das Volumen des Polyeders
     * @return Volumen des Polyeders
     */
    public double getVolumen()
    {
        return 2;
    }
}
