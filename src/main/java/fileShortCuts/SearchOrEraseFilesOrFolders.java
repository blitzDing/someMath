package fileShortCuts;

import java.io.IOException;
import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchOrEraseFilesOrFolders 
{

	public static boolean isThereThisFile(Path path)
	{
		if(Files.exists(path) && (!Files.isDirectory(path)))return true;
    	else return false;
	}
	
	public static boolean isThereThisFile(String folderPath)
    {
          Path path = Paths.get(folderPath);
          return isThereThisFile(path);
    }

	public static boolean isThereThisFolder(Path path)
    {
    	if(Files.exists(path) && Files.isDirectory(path))return true;
    	else return false;
    }
    
	public static boolean isThereThisFolder(String folderPath)
    {
		Path path = Paths.get(folderPath);
		return isThereThisFolder(path);
    }

    public static boolean eraseFile(Path path) throws IOException
    {
    	if(Files.exists(path) && (!Files.isDirectory(path)))
    	{
    		Files.delete(path);
    		return true;
    	}
    	else return false;
    }

    public static boolean eraseFile(String filePath) throws IOException
    {
    	Path path = Paths.get(filePath);
    	return eraseFile(path);
    }
    
    public static boolean eraseFolder(Path path) throws IOException
    {
    	if(Files.exists(path) && Files.isDirectory(path))
    	{
    		Files.delete(path);
    		return true;
    	}
    	else return false;
    }

    public static boolean eraseFolder(String filePath) throws IOException
    {
    	Path path = Paths.get(filePath);
    	return eraseFolder(path);
    }
    public Set<String> areThereFilesOfThisSchema(String filePath, String regEx) throws IOException
    {
    	Path path = Paths.get(filePath);
    	return areThereFilesOfThisSchema(path, regEx);
    }
    
    public Set<String> areThereFilesOfThisSchema(Path path, String regEx) throws IOException
    {
    	Set<String> fileNames = new HashSet<>();
        
    	// Define the folder path and regex pattern
        Path aPath = path.toAbsolutePath();
        String folderPath = aPath.toString();
        
        if(!(Files.exists(aPath) && Files.isDirectory(aPath))) throw new IOException("Folder doesn't exist. Or isn't a Folder!");

        File folder = new File(folderPath);

        File[] listOfFiles = folder.listFiles();
        	
        // Create a FileFilter using the provided regex pattern
        FileFilter fileFilter = (file)->
        {

        	// Use regular expression to match file names
        	Pattern pattern = Pattern.compile(regEx);
                    
        	Matcher matcher = pattern.matcher(file.getName());      
        	if(matcher.matches())fileNames.add(file.getName());

        	return matcher.matches();
        };
        
        // Get a list of files that match the regex pattern
        File[] matchingFiles = folder.listFiles(fileFilter);

    	return fileNames;
    }
}
