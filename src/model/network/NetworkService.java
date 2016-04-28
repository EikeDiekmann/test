package model.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Eike
 */
class NetworkService implements Runnable 
{ 
  private final ServerSocket serverSocket;
  private final ExecutorService pool;
  public NetworkService(ExecutorService pool, ServerSocket serverSocket) 
  {
    this.serverSocket = serverSocket;
    this.pool = pool;
  }

//##############################################################################  
  @Override
  public void run() 
  { // run the service
    
    try 
    {     
      while ( true ) 
      {
        Socket cs = serverSocket.accept();  //warten auf Client-Anforderung
       
        //starte den Handler-Thread zur Realisierung der Client-Anforderung
        pool.execute(new Handler(serverSocket,cs));
      }
    
    } catch (IOException ex) 
    {
      System.out.println("--- Interrupt NetworkService-run");
    }
    finally 
    {
      System.out.println("--- Ende NetworkService(pool.shutdown)");
      pool.shutdown();  //keine Annahme von neuen Anforderungen
      try 
      {
      	//warte maximal 4 Sekunden auf Beendigung aller Anforderungen
        pool.awaitTermination(4L, TimeUnit.SECONDS);
        if ( !serverSocket.isClosed() ) 
        {
          System.out.println("--- Ende NetworkService:ServerSocket close");
          serverSocket.close();
        }
      } catch ( IOException e ) { }
      catch ( InterruptedException ei ) { }
    }
  }
  
}


