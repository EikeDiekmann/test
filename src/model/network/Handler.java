package model.network;

import model.objects.ObjectHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import utility.Konstanten;
import static utility.Konstanten.ROTATION;
import static utility.Konstanten.TRANSLATION;

/**
 * Handler als Thread ausgeführt
 * @author Eike
 */
class Handler implements Runnable, Konstanten
{  
  private final Socket client;
  private final ServerSocket serverSocket;
  
  private int achse;
  private double winkel;
  private double versatz;
  private int rotationOrTranslation;
  
//##############################################################################   
  /**
   * Server/Client-Server
   * @param serverSocket
   * @param client 
   */
  Handler(ServerSocket serverSocket,Socket client) 
  { //Server/Client-Socket
    this.client = client;
    this.serverSocket = serverSocket;
  }
  
//##############################################################################  
  /**
   * ermöglicht es das Objekt als Thread auszuführen
   */
  @Override
  public void run() 
  {
    StringBuffer sb = new StringBuffer();
    PrintWriter out = null;
    
    try {
      // read and service request on client
      System.out.println( "running service, " + Thread.currentThread() );
      out = new PrintWriter( client.getOutputStream(), true );
      BufferedReader bufferedReader = new BufferedReader(
                                new InputStreamReader(client.getInputStream()));
      char[] buffer = new char[100];
      // blockiert bis Nachricht empfangen
      int anzahlZeichen = bufferedReader.read(buffer, 0, 100); 
      String nachricht = new String(buffer, 0, anzahlZeichen);
      String[] werte = nachricht.split("\\s");  //Trennzeichen: whitespace
      if (werte[0].compareTo("Exit") == 0) 
      {
        out.println("Server ended");
        if ( !serverSocket.isClosed() ) 
        {
          System.out.println("--- Ende Handler:ServerSocket close");
          try 
          {
            serverSocket.close();
          } catch ( IOException e ) { }
        }
      }	
      else 
      {  
          
          String rt;
          if (werte.length == 3)    //prüft ob 3 parameter vorhanden sind 
          {
              
            if(DEBUG)
                System.out.println(werte[0] + " " + werte[1] + " " + werte[2]);
            try
            { //speichert die Art der manipulation (0: Rotation, 1: Translation)
                this.rotationOrTranslation = Integer.parseInt(werte[0]);
            } 
            catch (NumberFormatException e)
            { 
                this.rotationOrTranslation = 0;
            }
             
            try 
            {
                //speichert die Achse, um die rotiert, 
                //          bzw. auf der das Objekt verschoben wird
                this.achse = Integer.parseInt(werte[1]);
                rt = String.valueOf(this.achse);
                sb.append(rt + "\n");
            } 
            catch (NumberFormatException e){rt = "0";}

            sb.append(rt + "\n");
            //sollte der winkel kein Double sein, wird er auf default gesetzt(0)
            try 
            {
                //speichert den Winkel um den gedteht, 
                //          bzw den Veratz um den das Objekt verschoben wird
                this.winkel = Double.parseDouble(werte[2]);
                this.versatz  = this.winkel;
                rt = String.valueOf(this.winkel);
                sb.append(rt + "\n");
            } 
            catch (NumberFormatException e)
            { this.winkel = 0; }
            
            if (DEBUG)
            {   System.out.println(RotationOrTranslationToString());
                System.out.println(getAchseToString());
                System.out.println(WinkelToString());
            }
            
            //ruft die Funktion zur Rotation des Objekts auf
            if(this.rotationOrTranslation == ROTATION)  
              ObjectHandler.getOVC().rotateObject(this.achse, (int)this.winkel);
            //ruft die Funktion der Translaition des Objekts auf
            else if(this.rotationOrTranslation == TRANSLATION) 
             ObjectHandler.getOVC().translateObject(this.achse, (int)this.winkel);
            sb.deleteCharAt(sb.length()-1);
          }
          else 
          {
              this.achse = 0;
              this.winkel = 0;
          }
      }
    } 
    catch (IOException e) {System.out.println("IOException, Handler-run");}
    finally 
    { 
      out.println(sb);  //Rückgabe Ergebnis an den Client
      if ( !client.isClosed() ) 
      {
        System.out.println("****** Handler:Client close");
        try 
        {
          client.close();
        } 
        catch ( IOException e ) { }
      } 
    }
  }  //Ende run

//############################################################################## 
  /**
   * wandelt den Winkel für Testzwecke in eine String um
   * @return Winkel als String
   */
  private String WinkelToString() 
  {
      return "Winkel: "+ this.winkel;
  }
//##############################################################################   
  /**
   * wandelt den Versatz für Testzwecke in eine String um
   * @return Versatz als String
   */
   private String VersatzToString() 
    {
      return "Translationsversatz: " + this.versatz;
    }
  
//##############################################################################   
   /**
    * wandelt die Achse für Testzwecke in eine String um
    * @return Achse als String
    */
    public String getAchseToString() {  
        return "Achse: " + this.achse;
    }
 
//############################################################################## 
    /**
     * gibt als String zurück, ob es sich um eine Rotation 
     *                      oder eine Translation handelt
     * @return Rotation oder Translation als String
     */
     public String RotationOrTranslationToString() 
     {  
    
     if (this.rotationOrTranslation == ROTATION)
         return "Rotation";
     else if(this.rotationOrTranslation == TRANSLATION)
         return "Translation";
     else
         return "Keine Valide Option!";
    }
}
