import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import fileShortCuts.FileCRUD;



class IOTest 
{

	String dir = "someResources/";
	
	String fileName1 = "Hello Filesystem" + '1';
	
	String oldText = "hi \n U Fool!";

	@Test
	public void testIO() throws IOException
	{


		System.out.println("Old Text: "+oldText);
			
		FileCRUD.saveText(dir + fileName1, oldText);
		String newText = FileCRUD.loadText(dir + fileName1);
			
		System.out.println("New Text: "+newText);
			
		assert(oldText.equals(newText));//Loaded Text equals the saved one?
	
		Path path = Path.of(dir);
		boolean folderExists = Files.exists(path)&&Files.isDirectory(path);
			
		assert(folderExists);
		
		assert(new File(dir + fileName1).delete());//deletion must be successful.
	}		
}
