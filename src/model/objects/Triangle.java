package model.objects;



/**
 * Triangle
 * @author Anton Huber, Dennis Koerner, Eike Diekmann
 *
 */
public class Triangle extends Polygon implements Comparable<Triangle>
{
    Edge kanten[] = new Edge[3];

    /**
     * Konstruktor, legt ein dreieckiges Polygon an.
     * @param kante1 Kante 1 des Dreiecks
     * @param kante2 Kante 2 des Dreiecks
     * @param kante3 Kante 3 des Dreiecks
     */
    public Triangle(Edge kante1, Edge kante2, Edge kante3)
    {
        super(new Edge [] {kante1, kante2, kante3});
        this.kanten[0] = kante1;
        this.kanten[1] = kante2;
        this.kanten[2] = kante3;
    }
    
//##############################################################################      
    /**
     * getFlaecheninhalt berechnet den Flaecheninhalt des Dreiecks und 
     *          gibt diesen zurueck
     * @return Flaecheninhalt des Dreiecks
     */
    public double getFlaecheninhalt()
    {
        //Dreiecksurpsrung in den Koordinatenursprung legen, 
        //um mit hilfe des Kreuzproduktes die Flaeche des Dreieckes zu berechnen.
        
        //1. Korrekturvektor - legt einen Punkt des Dreiecks in den Ursprung 
        //      und berechnet alle anderen Punkte dementsprechend.
        Vertex korrekturvektor = this.kanten[0].getOrtsvektor();          //
        Edge kantenKorrigiert[] = translate(korrekturvektor);
        
        //2. Unique Punkte des Dreicks (3)        
        Vertex punkteDesDreiecks[] = 
                              berechneUniquePunkteDesDreiecks(kantenKorrigiert);
        
        //3. Kreutprodukt
        Vertex kreuzProdukt = Vertex.kreuzprodukt(
                                    punkteDesDreiecks[1], punkteDesDreiecks[2]);
        
        //4. Flaeche berechnen
        double flaeche = 0.5 * Math.sqrt(kreuzProdukt.getX() * kreuzProdukt.getX()
                                        + kreuzProdukt.getY() * kreuzProdukt.getY()
                                        + kreuzProdukt.getZ() * kreuzProdukt.getZ() 
                                         );     
        return flaeche;
    }  
    
//##############################################################################     
    /**
     * gibt den Normalenvektor des Polygons zurueck
     * @return Normalenvektor des Polygons
     */
    public Vertex getNormale()
    {        
        return Vertex.kreuzprodukt(this.kanten[0].getRichtungsvektor(), 
                                            this.kanten[1].getRichtungsvektor());
    }
    
//##############################################################################     
    /**
     * tranlsate verschiebt die Kanten des Dreiecks um den Verschiebungsvektor 
     *          "translationVektor"
     * @param translationVektor
     * @return verschobenen Kanten
     */
    public Edge[] translate(Vertex translationVektor)
    {
        Edge kantenKorrigiert[] = new Edge[3];        
        
        for(int i = 0; i < kanten.length; i++)
        {
            kantenKorrigiert[i] = new Edge ( 
              Vertex.subtrahiere(this.kanten[i].getOrtsvektor(),translationVektor),
              Vertex.subtrahiere(this.kanten[i].getZielpunkt(), translationVektor)
                                           );
        }
        return kantenKorrigiert;        
    }
    
    
//##############################################################################     
    /**
     * Berechnet aus den Kanten des Dreiecks die einzelnen Punkte, 
     *              die das Dreieck definieren
     * @param kantenEinesDreiecks
     * @return die drei Punkte des Dreiecks
     * 
     */
    public static Vertex[] berechneUniquePunkteDesDreiecks(
                                                    Edge kantenEinesDreiecks[]) 
    {
        if(kantenEinesDreiecks.length < 3)
            throw new IndexOutOfBoundsException();
        
        //Unique Punkte des Dreicks (3)        
        Vertex punkteDesDreiecks[]= new Vertex[3];

        //F�ge die Punkte der ersten Gerade zu der Liste der Punkte des 
        //              Dreiecks hinzu.
        punkteDesDreiecks [0] = kantenEinesDreiecks[0].getOrtsvektor();
        
            punkteDesDreiecks [1] = kantenEinesDreiecks[0].getZielpunkt();
        
            if (kantenEinesDreiecks[1].getOrtsvektor().equals(
                                        kantenEinesDreiecks[2].getOrtsvektor()))
            {
                punkteDesDreiecks[2] = kantenEinesDreiecks[1].getOrtsvektor();
            }
            else if (kantenEinesDreiecks[1].getZielpunkt().equals(
                                        kantenEinesDreiecks[2].getZielpunkt()))
            {
                punkteDesDreiecks[2] = kantenEinesDreiecks[1].getZielpunkt();
            }
            else if (kantenEinesDreiecks[1].getZielpunkt().equals(
                                        kantenEinesDreiecks[2].getOrtsvektor()))
            {
                punkteDesDreiecks[2] = kantenEinesDreiecks[2].getOrtsvektor();
            }
            else if (kantenEinesDreiecks[1].getOrtsvektor().equals(
                                        kantenEinesDreiecks[2].getZielpunkt()))
            {
                punkteDesDreiecks[2] = kantenEinesDreiecks[1].getOrtsvektor();
            }
            /*
            if (punkteDesDreiecks[2]==null)
            {
                throw new NotATriangleException();
            }*/
        return punkteDesDreiecks;
    }   
    
    @Override
    public int compareTo(Triangle tri) {
        if(this.getFlaecheninhalt() == tri.getFlaecheninhalt())
            return 0;
        else
            return this.getFlaecheninhalt() > tri.getFlaecheninhalt() ? 1 : -1;
    }
    
    @Override
    public String toString()
    {
     
        return ("" + this.getFlaecheninhalt());
    }
}
