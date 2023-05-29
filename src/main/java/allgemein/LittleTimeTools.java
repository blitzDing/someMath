package allgemein;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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
    	if(years!=0)output = "Years: " + years;
    	
    	int month = p.getMonths();
    	if((month!=0)&&(years>0))output = output + ", Month: " + month;
    	if((month!=0)&&(years==0))output = "Month: " + month;
    	
    	int days = p.getDays();
    	if((days!=0)&&(output.length()>0))output = output + ", Days: "+ days;
    	if((days!=0)&&(output.length()==0))output = "Days: " + days;
    	
    	if((years==0)&&(month==0)&&(days==0))output = "Today";
    	
    	return output;
    }

    public static String fullLDTBetweenLDTs(LocalDateTime fromDateTime, LocalDateTime toDateTime)
    {
    	/*
			Nikolai Shevchenko's Code. StackOverFlow.
			Little Modification. No Zero's.
		*/
    	
    	String output = "";
    	
    	LocalDateTime tempDateTime = LocalDateTime.from( fromDateTime );

    	long years = tempDateTime.until( toDateTime, ChronoUnit.YEARS );
    	tempDateTime = tempDateTime.plusYears( years );
    	if(years>0) output = output + "Years: " + years;
    	
    	long months = tempDateTime.until( toDateTime, ChronoUnit.MONTHS );
    	tempDateTime = tempDateTime.plusMonths( months );
    	if(months>0&&output.length()>0)output = output + ", Months: " + months;
    	if(months>0&&output.length()==0)output = output + "Months: " + months;
    	
    	long days = tempDateTime.until( toDateTime, ChronoUnit.DAYS );
    	tempDateTime = tempDateTime.plusDays( days );
    	if(days>0&&output.length()>0)output = output + ", Days: " + days;
    	if(days>0&&output.length()==0)output = output + "Days: " + days;


    	long hours = tempDateTime.until( toDateTime, ChronoUnit.HOURS );
    	tempDateTime = tempDateTime.plusHours( hours );
    	if(hours>0&&output.length()>0)output = output + ", Hours: " + hours;
    	if(hours>0&&output.length()==0)output = output + "Hours: " + hours;
    	
    	long minutes = tempDateTime.until( toDateTime, ChronoUnit.MINUTES );
    	tempDateTime = tempDateTime.plusMinutes( minutes );
    	if(minutes>0&&output.length()>0)output = output + ", Minutes: " + minutes;
    	if(minutes>0&&output.length()==0)output = output + "Minutes: " + minutes;

    	long seconds = tempDateTime.until( toDateTime, ChronoUnit.SECONDS );
    	if(seconds>0&&output.length()>0)output = output + ", Seconds: " + seconds;
    	if(seconds>0&&output.length()==0)output = output + "Seconds: " + seconds;

    	return output;
    }
}
