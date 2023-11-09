package allgemein;

import java.io.IOException;
import java.time.LocalDateTime;

import java.nio.file.Path;

import fileShortCuts.FileCRUD;

public class SimpleLogger 
{
	
	private final Path fileNameAndPath;
	private final String fileName;
	private final String fileDir;
	
	private String sessionString = "";
	
	public SimpleLogger(Path fileNameAndPath) throws IOException
	{
		
		boolean isFile = fileNameAndPath.toFile().isFile();
		if(!isFile)throw new IOException("This is not a file Path.");
		
		Path p = fileNameAndPath.getParent().toAbsolutePath();
		if(p==null)throw new IOException("Argument got no Parent.");
		if(!FileCRUD.isThereThisFolder(p))throw new IOException("No Folder of that Path");
		
		this.fileNameAndPath = fileNameAndPath;
		this.fileName = fileNameAndPath.getFileName().toString();
		this.fileDir = fileNameAndPath.getParent().toString();
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
		FileCRUD.saveText(fileDir, fileName, sessionString);;
	}
}
