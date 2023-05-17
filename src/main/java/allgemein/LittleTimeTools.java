package allgemein;

import java.time.LocalDateTime;
import java.time.Period;
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
    
    public static String LDbetweenLDTs(LocalDateTime ldt1, LocalDateTime ldt2)
    {
    	
    	String output = "";
    	
    	Period p = Period.between(ldt1.toLocalDate(), ldt2.toLocalDate());
    	
    	int years = p.getYears();
    	if(years>0)output = "Years: " + years;
    	
    	int month = p.getMonths();
    	if((month>0)&&(years==0))output = "Month: " + month;
    	if((month>0)&&(years>0))output = output + ", Month: " + month;
    	
    	int days = p.getDays();
    	if((days>0)&&(output.length()==0))output = "Days: " + days;
    	if((days>0)&&(output.length()>0))output = output + ", Days: "+ days;
    	
    	
    	if((years==0)&&(month==0)&&(days==0))output = "Today";
    	
    	return output;
    }

}
