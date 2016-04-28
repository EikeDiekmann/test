package control;

import model.objects.Xform;
import model.reader.MeshConverter;
import model.reader.StlParser;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.PerspectiveCamera;
import javafx.scene.paint.PhongMaterial;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.SubScene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;



import model.objects.Triangle;
import utility.Konstanten;



import utility.MaterialColors;

/**
* ObjectViewController
 * @author Eike
 *  
 */
public class ObjectViewController implements MaterialColors, Runnable, Konstanten {
    
    @FXML 
    private final Pane rootpane;    
    private final Group root = new Group();
    private final Xform world = new Xform();        //von Oracle bereitgestellte Klasse zum Anzeigen von Objekten
    private final PerspectiveCamera camera = new PerspectiveCamera(true);
    private final Xform cameraXform = new Xform();
    private final Xform cameraXform2 = new Xform();
    private final Xform cameraXform3 = new Xform();
    private final double cameraDistance = 450;
    private final Xform objectGroup = new Xform();
    private double mousePosX;
    private double mousePosY;
    private double mouseOldX;
    private double mouseOldY;
    private double mouseDeltaX;
    private double mouseDeltaY;
    
    private boolean built = false;
    
    private MeshConverter meshConverter = new MeshConverter();    
    private TriangleMesh triangleMesh = new TriangleMesh();
    private MeshView meshView;
    private final StlParser stlParser;  
    List<Triangle> triangleList;

    
    /**
     * Konstruktor
     * @param rootpane In dieses Pane wird das Objekt geladen
     * @param dateipfad Dateipfad zur Datei mit dem 3D Obkjekt im stl Format
     */
     public ObjectViewController(Pane rootpane, String dateipfad) 
     {
        this.stlParser = new StlParser(dateipfad); 
        this.rootpane=rootpane;
    }
     
    /**
     * erzeugt die Szene
     */
    private void buildScene() 
    {
        root.getChildren().add(world);
    }

    /**
     * setzt die kamera in die Szene
     */
    private void buildCamera() 
    {
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraXform3.setRotateZ(180.0);

        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-cameraDistance);
        cameraXform.ry.setAngle(320.0);
        cameraXform.rx.setAngle(40);
    }

    /**
     * erstellt aus den eingelesenen Stl Daten ein 3DObjekt und fügt es der Szene hinzu
     */
    private void build3DObjectView()
    {
        final PhongMaterial redMaterial =     MaterialColors.RED_MATERIAL();
        
        Xform triangleXform = new Xform();
        
        
         //aus der eingelesenen Trianglelist wird mit dem Meshkonverter ein TriangleMesh erzeugt
        this.meshConverter = new MeshConverter();
        this.triangleMesh = new TriangleMesh( );
        this.triangleMesh = meshConverter.convertFromTriangles(triangleList);   
 
        meshView = new MeshView(triangleMesh);
        meshView.setMaterial(redMaterial);

        
        triangleXform.getChildren().add(meshView);
        objectGroup.getChildren().add(triangleXform);
        world.getChildren().addAll(objectGroup);
    }
    
    /**
     * rotateObject ermöglicht es, das Objekt um eine Achse mit beliebigem Winkel in Grad zu drehen
     * @param achse Achse, um die gedreht wird
     * @param winkel Winlkel mit dem das Objekt gedreht wird
     */
    public void rotateObject(int achse, int winkel)
    {        
        if (achse == X_ACHSE)
        {
            cameraXform.rx.setAngle(cameraXform.rx.getAngle() + winkel);
            if(DEBUG){ System.out.println("x: " + winkel); }
        }
        else if (achse == Y_ACHSE)
        {
            cameraXform.ry.setAngle(cameraXform.ry.getAngle() + winkel);
            if(DEBUG){  System.out.println("y: " + winkel);}
        }
          else if (achse == Z_ACHSE)
        {
            cameraXform.rz.setAngle(cameraXform.rz.getAngle() + winkel);
            if(DEBUG){  System.out.println("z: " + winkel);}
        }
    }
    
        
    /**
     * Versettzt das Objekt auf einer Achse um einen bestimmten Veratz
     * @param achse Achse auf der das Objekt versetzt werden soll
     * @param versatz Strecke, um die das Objekt versetzt werden soll
     */
    public void translateObject(int achse, int versatz)
    {        
        if (achse == X_ACHSE)
        {
            cameraXform.setTranslate(versatz + cameraXform.getXTranslation() ,0,0);
            if(DEBUG){ System.out.println("x: " + versatz); }
        }
        else if (achse == Y_ACHSE)
        {
              cameraXform.setTranslate(0,versatz + cameraXform.getYTranslation(),0);
            if(DEBUG){ System.out.println("y: " + versatz); }
        }
          else if (achse == Z_ACHSE)
        {
              cameraXform.setTranslate(0,0,versatz + + cameraXform.getZTranslation());
            if(DEBUG){ System.out.println("z: " + versatz); }
        }
    }

    /**
     * handleMouse ermöglicht es, das Objekt mit der Maus zu drehen
     * @param scene
     * @param root 
     */
    private void handleMouse(SubScene scene, final Node root) {
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();
            }
        });
        
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseDeltaX = (mousePosX - mouseOldX);
                mouseDeltaY = (mousePosY - mouseOldY);

                double modifier = 1.0;
                double modifierFactor = 0.1;

                if (me.isControlDown()) {
                    modifier = 0.1;
                }
                if (me.isShiftDown()) {
                    modifier = 10.0;
                }
                if (me.isPrimaryButtonDown()) {
                    cameraXform.ry.setAngle(cameraXform.ry.getAngle() - mouseDeltaX * modifierFactor * modifier * 2.0);  // +
                    cameraXform.rx.setAngle(cameraXform.rx.getAngle() + mouseDeltaY * modifierFactor * modifier * 2.0);  // -
                } else if (me.isSecondaryButtonDown()) {
                    double z = camera.getTranslateZ();
                    double newZ = z + mouseDeltaX * modifierFactor * modifier;
                    camera.setTranslateZ(newZ);
                } else if (me.isMiddleButtonDown()) {
                    cameraXform2.t.setX(cameraXform2.t.getX() + mouseDeltaX * modifierFactor * modifier * 0.3);  // -
                    cameraXform2.t.setY(cameraXform2.t.getY() + mouseDeltaY * modifierFactor * modifier * 0.3);  // -
                }
            }
        });
    }

    
    /**
     * gibt an, ob die Szene schon erstellt wurde
     * @return true, falls die Szene schon existiert, sons false
     */
    public boolean isBuilt()
    {
        return this.built;
    }
  
     
   /**
    * das 3D Objekt wird in einem eigenen Thread erstellt
    */
    @Override
    public void run() 
    {         
         Platform.runLater(() -> {
             stlParser.run();
             /*Thread stlParserThread = new Thread(stlParser);
             stlParserThread.start();
             while (stlParserThread.isAlive())
             {}*/
             
             triangleList = stlParser.parseTriangles();
             buildScene();
             buildCamera();
             build3DObjectView();
             
             
             SubScene scene = new SubScene(root, 1024,768);
             scene.setFill(Color.WHITE);
             handleMouse(scene, world);
             scene.setCamera(camera);
             rootpane.getChildren().add(scene);
             built=true;
         });
    }
    
    
    /**
     * gibt den genutzten StlParser zurück
     * @return genutzter StlParser
     */
    public StlParser getStlParser()
    {
        return this.stlParser;
    }
}