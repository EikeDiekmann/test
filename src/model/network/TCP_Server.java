package model.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import utility.Konstanten;
import static utility.Konstanten.DEBUG;


/**
 *
 * @author Eike
 */
public class TCP_Server implements Konstanten 
{

    private final ExecutorService pool;
    private final ServerSocket serverSocket;
    private final int port;
    
//##############################################################################  
    public TCP_Server(int port)  throws IOException
    {
        this.port=port;
        
        String zusatz;
         pool = Executors.newCachedThreadPool();
        zusatz = "CachedThreadPool";
      
       serverSocket = new ServerSocket(port);
    
        Thread t1 = new Thread(new NetworkService(pool,serverSocket));
        if(DEBUG)
          System.out.println("Start NetworkService(Multiplikation), " + zusatz +
                       ", Thread: "+Thread.currentThread());
        //Start der run-Methode von NetworkService: warten auf Client-request
        t1.start();

        //reagiert auf Strg+C, der Thread(Parameter) darf nicht gestartet sein
        Runtime.getRuntime().addShutdownHook(new Thread() 
        {
            @Override
            public void run() 
            {
            if(DEBUG)
                System.out.println("Strg+C, pool.shutdown");
            pool.shutdown();  //keine Annahme von neuen Anforderungen
            try 
            {
                //warte maximal 4 Sekunden auf Beendigung aller Anforderungen
                pool.awaitTermination(4L, TimeUnit.SECONDS);
                if (!serverSocket.isClosed()) 
                {
                    if(DEBUG)
                    System.out.println("ServerSocket close");
                    serverSocket.close();
                }
            } 
            catch ( IOException e ) { }
            catch ( InterruptedException ei ) { }
            }
        });
    }
}
