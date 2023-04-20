import java.io.IOException;

import org.junit.jupiter.api.Test;

import fileShortCuts.LoadAndSave;

public class IOTest 
{
	@Test
	public void testIO() throws IOException
	{
		
		String path = "/home/bernstein/";
		String fileName = "dispendable.newTest";
		String oldText = "hi \n U Fool!";

		System.out.println("Old Text: "+oldText);
		
		LoadAndSave.saveText(path, fileName, oldText);
		String newText = LoadAndSave.loadText(path, fileName);
		
		System.out.println("New Text: "+newText);
		
		assert(oldText.equals(newText));
	}
}
