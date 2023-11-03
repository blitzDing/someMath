package allgemein;

import java.io.IOException;
import java.time.LocalDateTime;

import fileShortCuts.LoadAndSave;

public class SimpleLogger 
{
	
	private final String fileName;
	
	String sessionString = "";
	
	public SimpleLogger(String fileName)
	{
		this.fileName = fileName;
		logNow("Log of LocalDateTime");
	}
	
	public void log(String msg, LocalDateTime timeStamp)
	{
		sessionString = sessionString  + msg +": "+ LittleTimeTools.timeString(timeStamp) + '\n';
	}
	
	public void logNow(String msg)
	{
		log(msg, LocalDateTime.now());
	}
	
	public void saveLog(String filePath) throws IOException
	{
		LoadAndSave.saveText(filePath, fileName, sessionString);
	}
}
