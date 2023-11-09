package allgemein;

import java.io.IOException;
import java.time.LocalDateTime;

import java.nio.file.Path;

import fileShortCuts.FileCRUD;

public class SimpleLogger 
{
	
	private final Path path;
	
	private String sessionString = "";
	
	public SimpleLogger(Path path) throws IOException
	{
		
		boolean isFile = path.toFile().isFile();
		if(!isFile)throw new IOException("This is not a file Path.");
		
		Path p = path.getParent().toAbsolutePath();
		if(p==null)throw new IOException("Argument got no Parent.");
		if(!FileCRUD.isThereThisFolder(p))throw new IOException("No Folder of that Path");
		
		this.path = path;
	}
	
	public SimpleLogger(String fileNameAndPathStr) throws IOException
	{
		
		this(Path.of(fileNameAndPathStr));
		
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
	
	public void saveLog() throws IOException
	{
		FileCRUD.saveText(path, sessionString);;
	}
}
