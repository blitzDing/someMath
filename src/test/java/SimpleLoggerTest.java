

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import allgemein.SimpleLogger;
import fileShortCuts.TextAndObjSaveAndLoad;


class SimpleLoggerTest 
{

	final String msgHeadOfFile = "Log Test File";
	final String pathStr = "someResources/logTestFile";
	
	@Test
	void test() throws IOException
	{
		SimpleLogger sl = new SimpleLogger(pathStr, msgHeadOfFile);
		sl.saveLog();
		
		Path path = Path.of(pathStr);
		assert(Files.exists(path));
		
		String text = TextAndObjSaveAndLoad.loadText(pathStr);		
		assert((text+'\n').equals(sl.getSessionString()));
		
		new File(pathStr).delete();
	}
}
