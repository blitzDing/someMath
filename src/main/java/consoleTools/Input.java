package consoleTools;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.util.InputMismatchException;
import java.util.List;
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
	
	final static String yes = "yes";
	final static char ja = 'y';

	final static String no = "no";
	final static char nein = 'n';
	
	final static String dontUnderstandTheAnswer = "I' don't Understand the answer.";
	
	final static String noNumber = "That's no Number";

	final static String answerOutOfBounds = "Answer out of Bounds.";
	
	final static String answerListBad = "Answer List Bad.(null or Empty)";
	
	final static String chooseANumber = "Choose a Number: ";
	
	final static int minNrForOutOfList = 1;
	
	final static String whichYear = "Which Year?: "; 
	final static String whichMonth = "Which Month?: ";
	final static String whichDay = "Which Day?: ";
	
	final static String whichHour = "Which Hour?: ";
	final static String whichMinute = "Which Minute?: ";
	
	final static String gatheredTimeOutOfBounds = "Gathered out of Bounds.";
	
	final static String timePlease = "Time Please.";
	final static String datePlease = "Date Please.";
	
	final static String dateTimeOutOfBounds = "Date out of Bounds.";
	
	/*
	 * I do not close scanner in any input Method. Because
	 * it's tied to System.in. If i would System.in would
	 * be closed to and never be opened again in the 
	 * running JVM. 
	 */
	
	public static boolean getYesOrNo(InputStream is, String qPhrase) throws IOException, InputMismatchException
	{

		Scanner scanner = new Scanner(is);

		System.out.print(qPhrase);
		String answer = scanner.nextLine();
		String s = answer.toLowerCase().trim();
		
		if(s.equals(ja+"")||s.equals(yes)) return true;
		if(s.equals(nein+"")||s.equals(no)) return false;
		
		throw new InputMismatchException(dontUnderstandTheAnswer);
	}
	
	public static String getString(InputStream is, String qPhrase)
	{
		
		Scanner scanner = new Scanner(is);
		
		
		System.out.print(qPhrase);
		String s = scanner.nextLine();
		
		return s;
	}

	public static int getNrInput(InputStream is, String qPhrase, int startOfValideInput, int range)
	{
		
		Scanner scanner = new Scanner(is);
		int max = startOfValideInput+range;
		
		System.out.print(qPhrase);
		String s = scanner.nextLine();
		
		int n = 0;
		if(s.trim().matches("[0-9]+"))n=Integer.parseInt(s.trim());
		else throw new IllegalArgumentException(noNumber);
		
		if(n>=startOfValideInput&&n<=max) return n;
		else throw new IllegalArgumentException(answerOutOfBounds);
	}
	
	public static String getAnswerOutOfList(InputStream is, String phrase, List<String> answerList) throws InputMismatchException, IOException
	{

		if(answerList.isEmpty()||answerList.contains(null))throw new IllegalArgumentException(answerListBad);
		
		int size = answerList.size();
		
		System.out.println(phrase);
		for(int n=1;n<size+1;n++)
		{
			System.out.println(n + ".) " + answerList.get(n-1));
		}
		
		int n = getNrInput(is,chooseANumber, minNrForOutOfList, size);
		
		return answerList.get(n-1);
	}
	
	
	public static LocalDate getDate(InputStream is, String qPhrase, LocalDate begin, LocalDate end) throws IOException
	{
		
		
		System.out.println(qPhrase);
		
		int yearBegin = begin.getYear();
		int yearEnd = end.getYear();
		
		int year = getNrInput(is, whichYear, yearBegin, yearEnd);
		int month = getNrInput(is, whichMonth,minMonth,maxMonth);
	
		boolean leapYear = Year.isLeap(year);
		Month m = Month.of(month);
		int maxDays = m.length(leapYear);
					
		int day = getNrInput(is, whichDay, minDay, maxDays);

		LocalDate ld = LocalDate.of(year, month, day);
		
		return ld;
	}
	
	public static LocalTime getTime(InputStream is, String qPhrase, LocalTime begin, LocalTime end) throws IOException
	{
		
		
		System.out.println(qPhrase);
				
		int hour = getNrInput(is, whichHour, minHour, maxHour);
		int minute = getNrInput(is, whichMinute, minMinute, maxMinute);
		
		LocalTime gatheredTime = LocalTime.of(hour, minute);
		
		if(gatheredTime.isBefore(begin))throw new IllegalArgumentException(gatheredTimeOutOfBounds);
		
		if(gatheredTime.isAfter(end))throw new IllegalArgumentException(gatheredTimeOutOfBounds);
		
		return gatheredTime;
	}
			
	public static LocalDateTime getDateTime(InputStream is, String qPhrase, LocalDateTime begin, LocalDateTime end) throws IOException
	{

		
		System.out.println(qPhrase);
		LocalTime beginOfTheDay = LocalTime.of(minHour, minMinute);
		LocalTime endOfTheDay = LocalTime.of(maxHour, maxMinute);
		
		LocalTime lt = getTime(is, timePlease, beginOfTheDay, endOfTheDay);
		
		LocalDate dateBegin = begin.toLocalDate();
		LocalDate dateEnd = end.toLocalDate();
		
		LocalDate ld = getDate(is, datePlease, dateBegin, dateEnd);
		
		LocalDateTime ldt = LocalDateTime.of(ld, lt);
		if(ldt.isBefore(begin)|| ldt.isAfter(end))throw new IllegalArgumentException(dateTimeOutOfBounds);
	
		return ldt;
	}
}