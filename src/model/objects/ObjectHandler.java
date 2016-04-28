package model.objects;

import control.ObjectViewController;
import javafx.scene.layout.Pane;

/**
 * ObjectHandler hat die Kontrolle über die 3DObjekt erzeugung
 * @author Eike
 */
public class ObjectHandler implements Runnable
{
    private static ObjectViewController ovC;
    private final Pane Object3DPane;
    private final String dateipfad;
    
    
//##############################################################################    
    public ObjectHandler(Pane Object3DPane, String dateipfad)
    {
        this.Object3DPane = Object3DPane;
        this.dateipfad = dateipfad;
    }
 
//##############################################################################  
    @Override
    public void run() 
    {
        ObjectHandler.ovC = new ObjectViewController (Object3DPane, dateipfad);
        Thread t = new Thread(ovC);
        t.start();
    }

//##############################################################################  
    public static ObjectViewController getOVC()
    {
        return ObjectHandler.ovC;
    }
    
}
