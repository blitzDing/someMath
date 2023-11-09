import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.junit.jupiter.api.Test;
import fileShortCuts.FileCRUD;

class IOTest 
{

	String dir = "someResources/";
	
	String fileNamePreFix = "dispensablenewTest";
	String fileName1 = fileNamePreFix + '1';
	String fileName2 = fileNamePreFix + '2';
	String fileName3 = fileNamePreFix + '3';
	String fileName4 = fileNamePreFix + '4';
	
	String imaginaryDir = "NotHere/";
	String imaginaryFileName = "Guguk";
	
	String tmpDir = "GhostDir/";
	String tmpFileName = "Ghost";
	
	String oldText = "hi \n U Fool!";

	@Test
	public void testIO() throws IOException
	{

		boolean imgFileExists = FileCRUD.isThereThisFile(dir+imaginaryFileName);
		assert(!imgFileExists);
		
		boolean imgDirExists = FileCRUD.isThereThisFolder(imaginaryDir);
		assert(!imgDirExists);

		System.out.println("Old Text: "+oldText);
			
		FileCRUD.saveText(dir, fileName1, oldText+'1');
		String newText = FileCRUD.loadText(dir, fileName1);
			
		System.out.println("New Text: "+newText);
			
		assert((oldText+'1').equals(newText));
			
		boolean folderExists = FileCRUD.isThereThisFolder(dir);
			
		assert(folderExists);
		
		boolean fileExists = FileCRUD.isThereThisFile(dir+fileName1);
		
		assert(fileExists);
		
		/* Testing erase Folder **************/
		/* */File file = new File(tmpDir);/* */
		/* */file.mkdir();                /* */
		/* */assert(file.exists());       /* */
		/* */FileCRUD.eraseFolder(tmpDir);/* */
		/*                                   */
		/* */assert(!file.exists());      /* */
		/* ***********************************/
		
		FileCRUD.saveText(dir, fileName2, oldText+'2');
		FileCRUD.saveText(dir, fileName3, oldText+'3');
		FileCRUD.saveText(dir, fileName4, oldText+'4');

		Set<String> someFileNames = FileCRUD.areThereFilesOfThisSchema(dir, "^(" + fileNamePreFix+").*$");
				
		assert(someFileNames.contains(fileName1));
		assert(someFileNames.contains(fileName2));
		assert(someFileNames.contains(fileName3));
		assert(someFileNames.contains(fileName4));
		
		FileCRUD.eraseFile(dir+fileName1);
		FileCRUD.eraseFile(dir+fileName2);
		FileCRUD.eraseFile(dir+fileName3);
		FileCRUD.eraseFile(dir+fileName4);
		
		fileExists = FileCRUD.isThereThisFile(dir+fileName1);
		
		assert(!fileExists);
	}		
}
