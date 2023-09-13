import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import consoleTools.Input;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


class InputTests 
{

	private final String gruß = "Hi";
	LocalDateTime ancient;
	
	@BeforeEach
	void prepare()
	{
		
		ancient = LocalDateTime.of(LocalDate.of(0, 1, 1), LocalTime.of(0, 0));
	}
	
	@Test
	void testGetString() 
	{
		ByteArrayInputStream is = new ByteArrayInputStream(gruß.getBytes());

		String greetings = Input.getString(is, "Hi u");
		
		assert(greetings.equals(gruß));
	}
	
	@Test
	void testGetTime()
	{
		
		ByteArrayInputStream is = new ByteArrayInputStream("0\n1\n1\n0\n7\n".getBytes());
		
		LocalDateTime ldt;
		try
		{
			ldt = Input.getDateTime(is, "hi", ancient, ancient.plusDays(8));
			assert(ldt.isAfter(ancient)&&ldt.isBefore(ancient.plusDays(8)));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
