package allgemein;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import someMath.*;

public class ExactPeriode 
{

	private final NaturalNumber years;
	private final NaturalNumber months;
	private final NaturalNumber days;
	private final NaturalNumber hours;
	private final NaturalNumber minutes;
	private final NaturalNumber seconds;
	
	public ExactPeriode(LocalDateTime fromLDT, LocalDateTime toLDT) throws InterfaceNumberException
	{

    	/*
			Nikolai Shevchenko's Code. StackOverFlow.
			Little Modification. No Zero's.
		*/
    	
    	LocalDateTime tempDateTime = LocalDateTime.from( fromLDT );

    	years = new NaturalNumber((int) tempDateTime.until( toLDT, ChronoUnit.YEARS ));
    	tempDateTime = tempDateTime.plusYears( years.getNumberCore() );
    	
    	months = new NaturalNumber((int) tempDateTime.until( toLDT, ChronoUnit.MONTHS ));
    	tempDateTime = tempDateTime.plusMonths( months.getNumberCore() );
    	
    	days = new NaturalNumber((int) tempDateTime.until( toLDT, ChronoUnit.DAYS ));
    	tempDateTime = tempDateTime.plusDays( days.getNumberCore() );

    	hours = new NaturalNumber((int) tempDateTime.until( toLDT, ChronoUnit.HOURS ));
    	tempDateTime = tempDateTime.plusHours( hours.getNumberCore() );
    	
    	minutes = new NaturalNumber((int) tempDateTime.until( toLDT, ChronoUnit.MINUTES ));
    	tempDateTime = tempDateTime.plusMinutes( minutes.getNumberCore() );

    	seconds = new NaturalNumber((int) tempDateTime.until( toLDT, ChronoUnit.SECONDS ));
	}

	public static ExactPeriode of(NaturalNumber years, NaturalNumber months, NaturalNumber days, NaturalNumber hours, NaturalNumber minutes, NaturalNumber seconds) throws InterfaceNumberException
	{

		LocalDateTime zero = LocalDateTime.of(0, 0, 0, 0, 0, 0);
		int intYears = years.getNumberCore();
		int intMonths = months.getNumberCore();
		int intDays = days.getNumberCore();
		int intHours = hours.getNumberCore();
		int intMinutes = minutes.getNumberCore();
		int intSeconds = seconds.getNumberCore();
		
		LocalDateTime toLDT = LocalDateTime.of(intYears, intMonths, intDays, intHours, intMinutes, intSeconds);
		
		return new ExactPeriode(zero, toLDT);
	}
	
	public static ExactPeriode ofYears(int years) throws InterfaceNumberException
	{
		
		LocalDateTime zero = LocalDateTime.of(0, 0, 0, 0, 0, 0);
		LocalDateTime toLDT = LocalDateTime.of(years, 0, 0, 0, 0, 0);
		
		return new ExactPeriode(zero, toLDT);
	}

	public static ExactPeriode ofMonth(int month) throws InterfaceNumberException
	{
		
		LocalDateTime zero = LocalDateTime.of(0, 0, 0, 0, 0, 0);
		LocalDateTime toLDT = LocalDateTime.of(0, month, 0, 0, 0, 0);
		
		return new ExactPeriode(zero, toLDT);
	}

	public static ExactPeriode ofDays(int days) throws InterfaceNumberException
	{
		
		LocalDateTime zero = LocalDateTime.of(0, 0, 0, 0, 0, 0);
		LocalDateTime toLDT = LocalDateTime.of(0, 0, days, 0, 0, 0);
		
		return new ExactPeriode(zero, toLDT);
	}

	public static ExactPeriode ofHours(int hours) throws InterfaceNumberException
	{

		LocalDateTime zero = LocalDateTime.of(0, 0, 0, 0, 0, 0);
		LocalDateTime toLDT = LocalDateTime.of(0, 0, 0, hours, 0, 0);

		return new ExactPeriode(zero, toLDT);
	}
	
	public static ExactPeriode ofMinutes(int minutes) throws InterfaceNumberException
	{

		LocalDateTime zero = LocalDateTime.of(0, 0, 0, 0, 0, 0);
		LocalDateTime toLDT = LocalDateTime.of(0, 0, 0, 0, minutes, 0);

		return new ExactPeriode(zero, toLDT);
	}

	public static ExactPeriode ofSeconds(int seconds) throws InterfaceNumberException
	{
		LocalDateTime zero = LocalDateTime.of(0, 0, 0, 0, 0, 0);
		LocalDateTime toLDT = LocalDateTime.of(0, 0, 0, 0, 0, seconds);
		
		return new ExactPeriode(zero, toLDT);
	}

	public ExactPeriode plusYears(NaturalNumber yearsPlus) throws InterfaceNumberException
	{
		return of(years, months, days, hours, months, seconds);
	}

	public ExactPeriode plusMonth(NaturalNumber monthPlus) throws InterfaceNumberException
	{
		NaturalNumber month = this.months.addWith(monthPlus);
		
		return of(years, month, days, hours, minutes, seconds);
	}
	
	public ExactPeriode plusDays(NaturalNumber daysPlus) throws InterfaceNumberException
	{
		NaturalNumber days = this.days.addWith(daysPlus);
		
		return of(years, months, days, hours, minutes, seconds);
	}
	
	public ExactPeriode plusHours(NaturalNumber hoursPlus) throws InterfaceNumberException
	{
		NaturalNumber hours = this.hours.addWith(hoursPlus);
		
		return of(years, months, days, hours, minutes, seconds);
	}
	
	public ExactPeriode plusMinutes(NaturalNumber minutesPlus) throws InterfaceNumberException
	{
		NaturalNumber minutes = this.minutes.addWith(minutesPlus);
		
		return of(years, months, days, hours, minutes, seconds);
	}
	
	public ExactPeriode plusSeconds(NaturalNumber secondsPlus) throws InterfaceNumberException
	{
		NaturalNumber seconds = this.seconds.addWith(secondsPlus);
		
		return of(years, months, days, hours, minutes, seconds);
	}

	public NaturalNumber getYears()
	{
		return years;
	}
	
	public NaturalNumber getMonths()
	{
		return months;
	}
	
	public NaturalNumber getDays()
	{
		return days;
	}
	
	public NaturalNumber getHours()
	{
		return hours;
	}
	
	public NaturalNumber getMinutes()
	{
		return minutes;
	}
	
	public NaturalNumber getSeconds()
	{
		return seconds;
	}
	
	public String toString()
	{
		String output = 	"Years: " + years
							+ " Months: "+ months
							+ " Days: " + days
							+ " Hours: " + hours
							+ " Minutes: " + minutes
							+ " Seconds: " + seconds;
		
		return output;
	}
}