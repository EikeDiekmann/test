package utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Konsole 
{

    private InputStreamReader isr; // = new InputStreamReader(System.in);
    private BufferedReader br;// = new BufferedReader(isr);
    private String eingabe = null;

    public Konsole()
    {
    }
    
    private void initializeReader()
    {
        this.isr = new InputStreamReader(System.in);
        this.br = new BufferedReader(isr);
    }
    
    private boolean closeReader()
    {
        boolean success = false;
        try 
        {
            isr.close();
            success = true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            success = false;
        }
        return success;
    }
    public String getNutzereingabe()
    {

        initializeReader();
        
        try
        {
            this.eingabe  = this.br.readLine();
        } 
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            closeReader();
        }
        return eingabe;
        
    }
    
    
    public String getNutzereingabe(String aufforderungstext)
    {
        
        initializeReader();
        
        
        
        System.out.print(aufforderungstext + " ");
        try
        {
            this.eingabe  = this.br.readLine();
        } 
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            closeReader();
        }
        return eingabe;
        
    }
    
    
    /**
     * @deprecated  Verhaelt sich nicht wie erwartet. Muss �berarbeitet werden
     */
    /*
    private static int getNutzerEingabeGanzzahl()
    {
        
         //System.out.println("Du hast " + eingabe + " eingegeben.");    
        while(true)
        {
            try
            {
                eingabe = br.readLine();
                try
                {                
                    a = Integer.parseInt(eingabe);                    
                } catch (NumberFormatException e)
                {
                    System.out.print("Bitte eine positive Nat�rliche Zahl eingeben: \r");
                    continue;
                }
            }
            catch (IOException e1)
            {                
                e1.printStackTrace();
                continue;
            }    
            if ( a < 1)
            {
                System.out.println("Bitte eine positive Nat�rliche Zahl eingeben: \r");
                continue;
            }
                break;
        }
        
        try{
            isr.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();    //verfolgt den Fehler bis zum Ursprung und gibt diesen aus        
        }
        
        return a;
    }
    
    */
    
}
