package consoleTools;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.util.Scanner;

public class Input
{

	final static int minYear = 0;
	
	final static int minMonth = 1;
	final static int maxMonth = 12;
	
	final static int minDay = 1;
	final static int maxDay = 31;
	
	final static int minHour = 0;
	final static int maxHour = 23;
	
	final static int minMinute = 0;
	final static int maxMinute = 59;
	
	public static int getNrInput(String qPhrase, int startOfValideInput, int range) throws IOException
	{
		
		Scanner scanner = new Scanner(System.in);
		
		if(range<=0)
		{
			scanner.close();
			throw new IllegalArgumentException("Sorry range must be greater than Zero.");		
		}
		
		System.out.println(qPhrase+"("+startOfValideInput+"-" + (startOfValideInput+range) + ")");
		int n = scanner.nextInt();
		scanner.close();
		
		return n;
	}
	
	public static LocalDate getDate(String qPhrase, int yearOffset, int yearRange, int monthOffset, int dayOffset) throws IOException
	{
		if(yearOffset<minYear)throw new IllegalArgumentException("Year isn't correct.");		
		if(monthOffset<minMonth||monthOffset>maxMonth)throw new IllegalArgumentException("Month isn't correct.");
		if(dayOffset<minDay||dayOffset>maxDay)throw new IllegalArgumentException("Day isn't correct.");
		if(dayOffset>Month.of(monthOffset).length(Year.isLeap(yearOffset)))throw new IllegalArgumentException("Day isn't correct.");
		
		LocalDate offsetDate = LocalDate.of(yearOffset, monthOffset, dayOffset);
		
		System.out.println(qPhrase);
		
		int year = getNrInput("Which Year?", yearOffset, yearRange);
		int month = getNrInput("Which Month?",minMonth,maxMonth);
		int day = getNrInput("Which Day?",minDay,maxDay);
	
		boolean leapYear = Year.isLeap(year);
		Month m = Month.of(month);
		int maxDays = m.length(leapYear);
					
		if(day<=maxDays)
		{
			LocalDate ld = LocalDate.of(year, month, day);
			if(offsetDate.isBefore(ld))return ld;
			else throw new IllegalArgumentException("Date is befor or at Offset Date.");
		}
		else throw new IllegalArgumentException("This month doesn't have this day.");
	}
	
	public static LocalTime getTime(String qPhrase, int hourOffset, int minuteOffset) throws IOException
	{
		if(hourOffset<minHour||hourOffset>maxHour)throw new IllegalArgumentException("hour isn't correct.");
		if(minuteOffset<minMinute||minuteOffset>maxMinute)throw new IllegalArgumentException("minute isn't correct.");
		
		System.out.println(qPhrase);
		
		LocalTime offsetTime = LocalTime.of(hourOffset, minuteOffset);
		
		int hour = getNrInput("Which hour?",minHour,maxHour);
		int minute = getNrInput("Which minute?",minMinute,maxMinute);
		
		LocalTime lt = LocalTime.of(hour, minute);
		if(offsetTime.isBefore(lt))return lt;
		else throw new IllegalArgumentException("Time is before or at Offset Time.");
	}
	
	public static LocalDateTime getDateTime(String qPhrase, int yearOffset, int yearRange, int monthOffset, int dayOffset, int hourOffset, int minuteOffset) throws IOException
	{
		if(yearOffset<minYear)throw new IllegalArgumentException("Year isn't correct.");
		if(monthOffset<minMonth||monthOffset>maxMonth)throw new IllegalArgumentException("Month isn't correct.");
		if(dayOffset<minDay||dayOffset>maxDay)throw new IllegalArgumentException("Day isn't correct.");
		if(dayOffset>Month.of(monthOffset).length(Year.isLeap(yearOffset)))throw new IllegalArgumentException("Day isn't correct.");
		if(hourOffset<minHour||hourOffset>maxHour)throw new IllegalArgumentException("hour isn't correct.");
		if(minuteOffset<minMinute||minuteOffset>maxMinute)throw new IllegalArgumentException("minute isn't correct.");

		LocalDateTime ldtOffset = LocalDateTime.of(yearOffset, monthOffset, dayOffset, hourOffset, minuteOffset);
		
		LocalTime lt = getTime("Time please.", hourOffset, minuteOffset);
		LocalDate ld = getDate("Date please.", yearOffset, yearRange, monthOffset, dayOffset);
		
		LocalDateTime ldt = LocalDateTime.of(ld, lt);
		if(ldtOffset.isBefore(ldt))return ldt;
		else throw new IllegalArgumentException("Date Time can't be before Offset DateTime.");
	}
}