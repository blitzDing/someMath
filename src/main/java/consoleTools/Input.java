package consoleTools;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import allgemein.LittleTimeTools;

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
	
	/*
	 * I do not close scanner in any input Method. Because
	 * it's tied to System.in. If i would System.in would
	 * be closed to and never be opened again in the 
	 * running JVM. 
	 */
	public static boolean getYesOrNo(String qPhrase) throws IOException, InputMismatchException
	{

		Scanner scanner = new Scanner(System.in);

		System.out.print(qPhrase);
		String answer =	scanner.nextLine();
		String s = answer.toLowerCase().trim();
		
		if(s.equals("y")||s.equals("yes")) return true;
		if(s.equals("n")||s.equals("no")) return false;
		
		throw new InputMismatchException("Don't understand your answer.");
	}
	
	public static String getString(String qPhrase)
	{
		
		Scanner scanner = new Scanner(System.in);
		
		
		System.out.print(qPhrase);
		String s =	scanner.nextLine();
		
		return s;
	}

	public static int getNrInput(String qPhrase, int startOfValideInput, int range)
	{
		
		Scanner scanner = new Scanner(System.in);
		int max = startOfValideInput+range;
		
		System.out.print(qPhrase);
		String s =	scanner.nextLine();
		int n = 0;
		if(s.trim().matches("[0-9]+"))n=Integer.parseInt(s.trim());
		else throw new IllegalArgumentException("That's no Number");
		
		if(n>=startOfValideInput&&n<=max) return n;
		else throw new IllegalArgumentException("Answer not inside Intervall.");
	}
	
	public static String getAnswerOutOfList(String phrase, List<String> answerList) throws InputMismatchException, IOException
	{

		if(answerList.isEmpty()||answerList.contains(null))throw new IllegalArgumentException("answer List Bad.");
		
		int size = answerList.size();
		
		System.out.println(phrase);
		for(int n=1;n<size+1;n++)
		{
			System.out.println(n + ".) " + answerList.get(n-1));
		}
		
		int n = getNrInput("Choose a Number: ", 1, size);
		
		return answerList.get(n-1);
	}
	
	public static LocalDate getDate(String qPhrase, LocalDate ld, int yearRange) throws IOException
	{
		return getDate(qPhrase, ld.getYear(), yearRange, ld.getMonthValue(), ld.getDayOfMonth());
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
	
	public static LocalTime getTime(String qPhrase, LocalTime lt) throws IOException
	{
		return getTime(qPhrase, lt.getHour(), lt.getMinute());
	}
	
	public static LocalTime getTime(String qPhrase, int hourOffset, int minuteOffset) throws IOException
	{
		if(hourOffset<minHour||hourOffset>maxHour)throw new IllegalArgumentException("hour isn't correct.");
		if(minuteOffset<minMinute||minuteOffset>maxMinute)throw new IllegalArgumentException("minute isn't correct.");
		
		System.out.println(qPhrase);
		
		LocalTime offsetTime = LocalTime.of(hourOffset, minuteOffset);
		
		int hour = getNrInput("Which hour?",minHour,maxHour);
		int minute = getNrInput("Which minute?",minMinute,maxMinute);
		
		LocalTime gatheredTime = LocalTime.of(hour, minute);
		
		if(gatheredTime.isBefore(offsetTime))throw new IllegalArgumentException("Gathered Time is before or at Offset Time.");
		return gatheredTime;
	}

	public static LocalDateTime getDateTime(String qPhrase, LocalDate ld, LocalTime lt, int yearRange) throws IOException
	{
		return getDateTime(qPhrase, LocalDateTime.of(ld, lt), yearRange);
	}
	
	public static LocalDateTime getDateTime(String qPhrase, LocalDateTime ldt, int yearRange) throws IOException
	{
		return getDateTime(qPhrase, ldt.getYear(), yearRange, ldt.getMonthValue(), ldt.getDayOfMonth(), ldt.getHour(), ldt.getMinute());
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
		
		System.out.println(qPhrase);
		LocalTime lt = getTime("Time please.", minHour, minMinute);//special Offset!!
		LocalDate ld = getDate("Date please.", yearOffset, yearRange, monthOffset, dayOffset);
		
		LocalDateTime ldt = LocalDateTime.of(ld, lt);
		if(ldtOffset.isBefore(ldt))return ldt;
		else throw new IllegalArgumentException("Date Time can't be before Offset DateTime.");
	}
}