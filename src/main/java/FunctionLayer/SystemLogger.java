/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author emilv
 */
public class SystemLogger
{

    /**
     * @param args the command line arguments
     */
    private final static Logger logger = Logger.getLogger(SystemLogger.class.getName());
    private static FileHandler fh = null;

    public static void init() throws IOException
    {
        try
        {
            fh = new FileHandler("/home/sebastian/carportlogs/SystemLogger.txt", true); // sidste parameter appende til eksisterende hvis true 
        } catch (SecurityException | IOException e)
        {
            e.printStackTrace();
        }
        Logger l = Logger.getLogger("");
        fh.setFormatter(new SimpleFormatter());
        l.addHandler(fh);
    }
}
