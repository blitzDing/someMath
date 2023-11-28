package allgemein;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import someMath.*;

/**
 * ExactPeriode is relative it depends on two LocalDateTime's (ldt's)
 * fromLDT and toLDT. Because a year is not always 365 Days. And a 
 * Month is variable, in days, too. I'm trying to make this class 
 * immutable. I don't think it makes sense to have a Method that 
 * gives you an exact or otherwise Periode if you don't have to 
 * ldt's to relate to. So i don't have it. In The Periode class of 
 * the SDK there are such Methods like ofXXX(params).
 */
public class ExactPeriode 
{

	private final NaturalNumber years;
	private final NaturalNumber months;
	private final NaturalNumber days;
	private final NaturalNumber hours;
	private final NaturalNumber minutes;
	private final NaturalNumber seconds;
	
	private final LocalDateTime fromLDT;
	private final LocalDateTime toLDT;
	private final boolean isNegative;
	
	public ExactPeriode(LocalDateTime fromLDT, LocalDateTime toLDT) throws InterfaceNumberException
	{

		this.fromLDT = fromLDT;
		this.toLDT = toLDT;
		
    	/*
			Nikolai Shevchenko's Code. StackOverFlow.
			Little Modification. No Zero's.
		*/
    	
    	LocalDateTime fromLDTTemp;
    	LocalDateTime toLDTTemp;
    	
    	if(fromLDT.isBefore(toLDT))
    	{
    		isNegative = false;
    		fromLDTTemp = LocalDateTime.from( fromLDT );
    		toLDTTemp = LocalDateTime.from( toLDT );
    	}
    	else 
    	{
    		isNegative = true;    		
    		fromLDTTemp = LocalDateTime.from( toLDT );
    		toLDTTemp = LocalDateTime.from( fromLDT );
    	}

    	years = new NaturalNumber((int) fromLDTTemp.until( toLDTTemp, ChronoUnit.YEARS ));
    	fromLDTTemp = fromLDTTemp.plusYears( years.getNumberCore() );
    	
    	months = new NaturalNumber((int) fromLDTTemp.until( toLDTTemp, ChronoUnit.MONTHS ));
    	fromLDTTemp = fromLDTTemp.plusMonths( months.getNumberCore() );
    	
    	days = new NaturalNumber((int) fromLDTTemp.until( toLDTTemp, ChronoUnit.DAYS ));
    	fromLDTTemp = fromLDTTemp.plusDays( days.getNumberCore() );

    	hours = new NaturalNumber((int) fromLDTTemp.until( toLDTTemp, ChronoUnit.HOURS ));
    	fromLDTTemp = fromLDTTemp.plusHours( hours.getNumberCore() );
    	
    	minutes = new NaturalNumber((int) fromLDTTemp.until( toLDTTemp, ChronoUnit.MINUTES ));
    	fromLDTTemp = fromLDTTemp.plusMinutes( minutes.getNumberCore() );

    	seconds = new NaturalNumber((int) fromLDTTemp.until( toLDTTemp, ChronoUnit.SECONDS ));
	}

	public ExactPeriode plusYears(NaturalNumber yearsPlus) throws InterfaceNumberException
	{
		
		LocalDateTime newToLDT = this.toLDT.plusYears(yearsPlus.getNumberCore());
		return new ExactPeriode(fromLDT, newToLDT);
	}

	public ExactPeriode plusMonth(NaturalNumber monthPlus) throws InterfaceNumberException
	{

		LocalDateTime newToLDT = this.toLDT.plusMonths(monthPlus.getNumberCore());
		return new ExactPeriode(fromLDT, newToLDT);
	}
	
	public ExactPeriode plusDays(NaturalNumber daysPlus) throws InterfaceNumberException
	{
		
		LocalDateTime newToLDT = this.toLDT.plusDays(daysPlus.getNumberCore());
		return new ExactPeriode(fromLDT, newToLDT);
	}
	
	public ExactPeriode plusHours(NaturalNumber hoursPlus) throws InterfaceNumberException
	{

		LocalDateTime newToLDT = this.toLDT.plusHours(hoursPlus.getNumberCore());
		return new ExactPeriode(fromLDT, newToLDT);
	}
	
	public ExactPeriode plusMinutes(NaturalNumber minutesPlus) throws InterfaceNumberException
	{

		LocalDateTime newToLDT = this.toLDT.plusMinutes(minutesPlus.getNumberCore());
		return new ExactPeriode(fromLDT, newToLDT);
	}
	
	public ExactPeriode plusSeconds(NaturalNumber secondsPlus) throws InterfaceNumberException
	{

		LocalDateTime newToLDT = this.toLDT.plusSeconds(secondsPlus.getNumberCore());
		return new ExactPeriode(fromLDT, newToLDT);
	}

	public ExactPeriode minusYears(NaturalNumber yearsMinus) throws InterfaceNumberException
	{
		
		LocalDateTime newToLDT = this.toLDT.minusYears(yearsMinus.getNumberCore());
		return new ExactPeriode(fromLDT, newToLDT);
	}

	public ExactPeriode minusMonth(NaturalNumber monthMinus) throws InterfaceNumberException
	{

		LocalDateTime newToLDT = this.toLDT.minusMonths(monthMinus.getNumberCore());
		return new ExactPeriode(fromLDT, newToLDT);
	}
	
	public ExactPeriode minusDays(NaturalNumber daysMinus) throws InterfaceNumberException
	{
		
		LocalDateTime newToLDT = this.toLDT.minusDays(daysMinus.getNumberCore());
		return new ExactPeriode(fromLDT, newToLDT);
	}
	
	public ExactPeriode minusHours(NaturalNumber hoursMinus) throws InterfaceNumberException
	{

		LocalDateTime newToLDT = this.toLDT.minusHours(hoursMinus.getNumberCore());
		return new ExactPeriode(fromLDT, newToLDT);
	}
	
	public ExactPeriode minusMinutes(NaturalNumber minutesMinus) throws InterfaceNumberException
	{

		LocalDateTime newToLDT = this.toLDT.minusMinutes(minutesMinus.getNumberCore());
		return new ExactPeriode(fromLDT, newToLDT);
	}
	
	public ExactPeriode minusSeconds(NaturalNumber secondsMinus) throws InterfaceNumberException
	{

		LocalDateTime newToLDT = this.toLDT.minusSeconds(secondsMinus.getNumberCore());
		return new ExactPeriode(fromLDT, newToLDT);
	}
		
	public int getAbsoluteDays()
	{
		LocalDateTime fromLDTTemp;
		LocalDateTime toLDTTemp;
		
		if(fromLDT.isBefore(toLDT)) 
		{
			fromLDTTemp = fromLDT;
			toLDTTemp = toLDT;
		}
		else
		{
			
			toLDTTemp = fromLDT;
			fromLDTTemp = toLDT;
		}
			
		return (int) fromLDTTemp.until( toLDTTemp, ChronoUnit.DAYS );

	}
	
	public int getAbsoluteHours()
	{

		LocalDateTime fromLDTTemp;
		LocalDateTime toLDTTemp;
		
		if(fromLDT.isBefore(toLDT)) 
		{
			fromLDTTemp = fromLDT;
			toLDTTemp = toLDT;
		}
		else
		{
			
			toLDTTemp = fromLDT;
			fromLDTTemp = toLDT;
		}

		
		return (int) fromLDTTemp.until( toLDTTemp, ChronoUnit.HOURS );
	}
	
	public int getAbsoluteMinutes()
	{
		LocalDateTime fromLDTTemp;
		LocalDateTime toLDTTemp;
		
		if(fromLDT.isBefore(toLDT)) 
		{
			fromLDTTemp = fromLDT;
			toLDTTemp = toLDT;
		}
		else
		{
			
			toLDTTemp = fromLDT;
			fromLDTTemp = toLDT;
		}

		
		return (int) fromLDTTemp.until( toLDTTemp, ChronoUnit.MINUTES );
	}
	
	public int getAbsoluteSeconds()
	{
		LocalDateTime fromLDTTemp;
		LocalDateTime toLDTTemp;
		
		if(fromLDT.isBefore(toLDT)) 
		{
			fromLDTTemp = fromLDT;
			toLDTTemp = toLDT;
		}
		else
		{
			
			toLDTTemp = fromLDT;
			fromLDTTemp = toLDT;
		}

		return (int) fromLDTTemp.until( toLDTTemp, ChronoUnit.SECONDS );
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
	
	public LocalDateTime getFromLDT()
	{
		
		LocalDate ld = fromLDT.toLocalDate();
		LocalTime lt = fromLDT.toLocalTime();
		
		return LocalDateTime.of(ld, lt);//Giving out a copy in hope fromLDT can't
										//be changed from the outside.
	}
	
	public LocalDateTime getToLDT()
	{
		
		LocalDate ld = toLDT.toLocalDate();
		LocalTime lt = toLDT.toLocalTime();
		
		return LocalDateTime.of(ld, lt);//Giving out a copy in hope fromLDT can't
		//be changed from the outside.
	}
	
	public boolean getSign()
	{
		return !isNegative;
	}

	public String toString()
	{
		
		String output;
		
		if(isNegative)output = "*";
		else output = 	"";
			
		output = output	+ "Years: " + years + "."
						+ " Months: "+ months + "."
						+ " Days: " + days + "."
						+ " Hours: " + hours + "."
						+ " Minutes: " + minutes + "."
						+ " Seconds: " + seconds + ".";
		
		return output;
	}
}