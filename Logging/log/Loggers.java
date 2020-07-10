package log;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import dataframe.DataFrame;
import scorer.ConfusionMatrix;
import scorer.CrossValidation;

public class Loggers {
    
    public static Logger df_Logger = Logger.getLogger(DataFrame.class.getName());
    
    public static Logger cv_Logger = Logger.getLogger(CrossValidation.class.getName());
    
    public static Logger cm_Logger = Logger.getLogger(ConfusionMatrix.class.getName());
    //public static Logger model_Logger ... maybe KNN Logger, misc loggers. 
    
    public static void changeLoggerSettings(LoggerSettings settings) {
        
    }
    
    public static void changeLoggerSettings(Logger logger, LoggerSettings settings ) { 
        
    }
    public static ConsoleHandler ch = new ConsoleHandler();
    
}
