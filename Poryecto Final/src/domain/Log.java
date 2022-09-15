package domain;
 
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;

/**
 * Constructor de la clase Log, esta se encarga de escribir en un archivo de texto
 * siempre que ocurra un error o una excepcion en POOBchis.
 * @author Dell
 *
 */
public class Log{
    public static String nombre="POOBchis";
    private static Logger logger;
    private static FileHandler file;
 
    /**
     * Record an exception in the developer log
     * @param e an exception to record
     */
    public static void record(Exception e){
        try{
            logger = Logger.getLogger(nombre);
            logger.setUseParentHandlers(true);
            file=new FileHandler(nombre+".log",true);
            file.setFormatter(new SimpleFormatter());
            file.setLevel(Level.WARNING);
            logger.addHandler(file);
            logger.log(Level.WARNING,e.toString(),e);
            file.close();
        }catch (Exception oe){
            oe.printStackTrace();
            System.exit(0);
        }
    }
}