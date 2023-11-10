package allgemein;

import java.io.IOException;
import java.time.LocalDateTime;
import java.nio.file.Files;
import java.nio.file.Path;

import fileShortCuts.TextAndObjSaveAndLoad;

public class SimpleLogger 
{
	
	private final Path path;
	
	private String sessionString = "";
	
	public SimpleLogger(Path path, String msgHeadOfFile) throws IOException
	{
		
		Path p = path.getParent().toAbsolutePath();
		if(p==null)throw new IOException("Argument got no Parent.");
		if(!Files.exists(p))throw new IOException("No Folder of that Path");
		
		this.path = path;
		logNow(msgHeadOfFile + "\nLog of LocalDateTime");
	}
	
	public SimpleLogger(String fileNameAndPathStr, String msgHeadOfFile) throws IOException
	{
		this(Path.of(fileNameAndPathStr), msgHeadOfFile);
	}
	
	public void log(String msg, LocalDateTime timeStamp)
	{
		sessionString = sessionString  + msg +": "+ LittleTimeTools.timeString(timeStamp) + '\n';
	}
	
	public void logNow(String msg)
	{
		log(msg, LocalDateTime.now());
	}
	
	public void saveLog() throws IOException
	{
		TextAndObjSaveAndLoad.saveText(path, sessionString);;
	}
	
	public String getSessionString()
	{
		return sessionString;
	}
}
