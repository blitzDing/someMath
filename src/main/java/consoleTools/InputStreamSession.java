package consoleTools;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import allgemein.LittleTimeTools;

public class InputStreamSession
{

	public static final Map<String, Month> monthMap = Map.ofEntries(
			  new AbstractMap.SimpleEntry<String, Month>("JAN", Month.JANUARY),
			  new AbstractMap.SimpleEntry<String, Month>("FEB", Month.FEBRUARY),
			  new AbstractMap.SimpleEntry<String, Month>("MAR", Month.MARCH),
			  new AbstractMap.SimpleEntry<String, Month>("APR", Month.APRIL),
			  new AbstractMap.SimpleEntry<String, Month>("MAY", Month.MAY),
			  new AbstractMap.SimpleEntry<String, Month>("JUN", Month.JUNE),
			  new AbstractMap.SimpleEntry<String, Month>("JUL", Month.JULY),
			  new AbstractMap.SimpleEntry<String, Month>("AUG", Month.AUGUST),
			  new AbstractMap.SimpleEntry<String, Month>("SEP", Month.SEPTEMBER),
			  new AbstractMap.SimpleEntry<String, Month>("OCT", Month.OCTOBER),
			  new AbstractMap.SimpleEntry<String, Month>("NOV", Month.NOVEMBER),
			  new AbstractMap.SimpleEntry<String, Month>("DEC", Month.DECEMBER)				  
			);

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
	
	private final Scanner scanner; 

	public InputStreamSession(InputStream is)
	{
		scanner = new Scanner(is);
	}
	
	public boolean getYesOrNo(String qPhrase) throws IOException, InputArgumentException
	{

		System.out.println(qPhrase);
		String answer = scanner.nextLine();
		String s = answer.toLowerCase().trim();
		
		if(s.equals(ja+"")||s.equals(yes)) return true;
		if(s.equals(nein+"")||s.equals(no)) return false;
		
		throw new InputArgumentException(dontUnderstandTheAnswer);
	}
	
	public String getString(String qPhrase)
	{
	
		System.out.println(qPhrase);
		String s = scanner.nextLine();
		
		return s;
	}

	public int getNrInput(String qPhrase, int startOfValideInput, int range) throws InputArgumentException
	{
		
		int max = startOfValideInput+range;
		
		System.out.println(qPhrase);
		String s = scanner.nextLine();
		
		int n = 0;
		if(s.trim().matches("[0-9]+"))n=Integer.parseInt(s.trim());
		else throw new InputArgumentException(noNumber);
		
		if(n>=startOfValideInput&&n<=max) return n;
		else throw new InputArgumentException(answerOutOfBounds);
	}
	
	public String getAnswerOutOfList(String phrase, List<String> answerList) throws IOException, InputArgumentException
	{

		if(answerList.isEmpty()||answerList.contains(null))throw new IllegalArgumentException(answerListBad);
		
		int size = answerList.size();
		
		System.out.println(phrase);
		for(int n=1;n<size+1;n++)
		{
			System.out.println(n + ".) " + answerList.get(n-1));
		}
		
		int n = getNrInput(chooseANumber, minNrForOutOfList, size);
		
		return answerList.get(n-1);
	}
	
	
	public LocalDate getDate(String qPhrase, LocalDate begin, LocalDate end) throws IOException, InputArgumentException
	{
		
		
		System.out.println(qPhrase);
		
		int yearBegin = begin.getYear();
		int yearEnd = end.getYear();
		
		int year = getNrInput(whichYear, yearBegin, yearEnd);
		int month = getNrInput(whichMonth,minMonth,maxMonth);
	
		boolean leapYear = Year.isLeap(year);
		Month m = Month.of(month);
		int maxDays = m.length(leapYear);
					
		int day = getNrInput(whichDay, minDay, maxDays);

		LocalDate ld = LocalDate.of(year, month, day);
		
		return ld;
	}
	
	public LocalTime getTime(String qPhrase, LocalTime begin, LocalTime end) throws IOException, InputArgumentException
	{
		
		
		System.out.println(qPhrase);
				
		int hour = getNrInput(whichHour, minHour, maxHour);
		int minute = getNrInput(whichMinute, minMinute, maxMinute);
		
		LocalTime gatheredTime = LocalTime.of(hour, minute);
		
		if(gatheredTime.isBefore(begin))throw new InputArgumentException(gatheredTimeOutOfBounds);
		
		if(gatheredTime.isAfter(end))throw new InputArgumentException(gatheredTimeOutOfBounds);
		
		return gatheredTime;
	}
			
	public LocalDateTime getDateTime(String qPhrase, LocalDateTime begin, LocalDateTime end) throws IOException, InputArgumentException
	{

		
		System.out.println(qPhrase);
		LocalTime beginOfTheDay = LocalTime.of(minHour, minMinute);
		LocalTime endOfTheDay = LocalTime.of(maxHour, maxMinute);
		
		LocalTime lt = getTime(timePlease, beginOfTheDay, endOfTheDay);
		
		LocalDate dateBegin = begin.toLocalDate();
		LocalDate dateEnd = end.toLocalDate();
		
		LocalDate ld = getDate(datePlease, dateBegin, dateEnd);
		
		String beginStr = LittleTimeTools.timeString(begin);
		String endStr = LittleTimeTools.timeString(end);
		
		LocalDateTime ldt = LocalDateTime.of(ld, lt);
		if(ldt.isBefore(begin)|| ldt.isAfter(end))throw new InputArgumentException(beginStr + " to " + endStr + dateTimeOutOfBounds);
	
		return ldt;
	}
	
	public LocalDateTime getDateTimeInOneLine(String phrase, LocalDateTime begin, LocalDateTime end) throws InputArgumentException
	{
		
		
		LocalDateTime ldt = null;
		String answer = getString(phrase);
		answer = answer.trim();
		String pattern = "(\\d+){2}([A-Z]+){3}(\\d+){4}T(\\d+){2}\\:(\\d+){2}";
		if(!answer.matches(pattern))throw new InputArgumentException("That answer doesn't fit pattern: DDMMMYYYYTHH:MM");
		
		String dayNr = answer.substring(0, 2);
		if(dayNr.startsWith("0"))dayNr = dayNr.substring(1);
		
		int day = Integer.parseInt(dayNr);
		if(day>31)throw new InputArgumentException("UnknownDay of Month.");
		
		String month = answer.substring(2, 5);
		if(!monthMap.containsKey(month))throw new InputArgumentException("Unknown Month.");
		Month m = monthMap.get(month);
		
		String year = answer.substring(5, 9);
		while(year.startsWith("0"))year = year.substring(1);
		int jahr = Integer.parseInt(year);
		
		String hour = answer.substring(10, 12);
		if(hour.startsWith("0"))hour = hour.substring(1);
		int stunde = Integer.parseInt(hour);
		if(stunde>23)throw new InputArgumentException("Unknown Hour.");
		
		String minute = answer.substring(13, 15);
		if(minute.startsWith("0"))minute = minute.substring(1);
		int minuteMinute = Integer.parseInt(minute);
		if(minuteMinute>59)throw new InputArgumentException("Unknown Minute.");
		
		LocalDate ld = LocalDate.of(jahr, m, day);
		LocalTime lt = LocalTime.of(stunde, minuteMinute);
		ldt = LocalDateTime.of(ld, lt);
		
		return ldt;
	}
	
	
	
	
}