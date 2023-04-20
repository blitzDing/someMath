package allgemein;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LittleTimeTools 
{

    public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    
    public static String timeString(LocalDateTime ldt)
    {
    	
    	return ldt.format(dtf);
    }
    
    public static LocalDateTime LDTfromTimeString(String timeString)
    {	
    	return LocalDateTime.parse(timeString, dtf);
    }

}
