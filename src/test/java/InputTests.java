

import org.junit.jupiter.api.Test;

import allgemein.LittleTimeTools;

import org.junit.jupiter.api.BeforeEach;

import consoleTools.InputArgumentException;
import consoleTools.InputStreamSession;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class InputTests 
{

	
	LocalDateTime ancient;
	
	@BeforeEach
	public void prepare()
	{
		
		ancient = LocalDateTime.of(LocalDate.of(2, 1, 1), LocalTime.of(0, 0));
		System.out.println("Ancient: " + LittleTimeTools.timeString(ancient));
		System.out.println("Ancient: " + LittleTimeTools.timeString(ancient.plusDays(8)));
		
	}
	
	@Test
	public void testGetString() 
	{
		
		String gruß = "Hi";
		ByteArrayInputStream is = new ByteArrayInputStream(gruß.getBytes());
		InputStreamSession inTaker = new InputStreamSession(is);

		String greetings = inTaker.getString("Hi u");
		
		assert(greetings.equals(gruß));
	}
	
	@Test
	public void testGetDateTime() throws InputArgumentException
	{
		int hour = 0;
		int minute = 0;
		int year = 2;
		int month = 1;
		int day = 6;
		String lines = hour+"\n"+minute+"\n"+year+"\n"+month+"\n"+day+"\n";
		
		ByteArrayInputStream is = new ByteArrayInputStream(lines.getBytes());
		InputStreamSession inTaker = new InputStreamSession(is);

		LocalDateTime ldt;
		try
		{
			ldt = inTaker.getDateTime("hi", ancient, ancient.plusDays(8));
			assert(ldt.isAfter(ancient)&&ldt.isBefore(ancient.plusDays(8)));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testGetDateTimeInOneLine() throws InputArgumentException
	{

		String year = "0002";
		String month = "JAN";
		int day = 6;
		String lines = "0" + day + month + year + "T" + "00" + ":" + "00"+ "\n";
		
		ByteArrayInputStream is = new ByteArrayInputStream(lines.getBytes());
		InputStreamSession inTaker = new InputStreamSession(is);

		LocalDateTime ldt;
		ldt = inTaker.getDateTimeInOneLine("hi", ancient, ancient.plusDays(8));
		assert(ldt.isAfter(ancient)&&ldt.isBefore(ancient.plusDays(8)));
	}

}