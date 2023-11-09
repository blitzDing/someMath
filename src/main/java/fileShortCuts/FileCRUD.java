package fileShortCuts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/** The Goal is to make File Operations more readable. And if possible more concise */
public class FileCRUD 
{

	public static Object loadObject(String pathStr) throws IOException, ClassNotFoundException
	{
		
		File f = new File(pathStr);
		FileInputStream fis = new FileInputStream(f);
		
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Object o = ois.readObject();
		ois.close();
		fis.close();
		
		return o;
	}
	
	public static <O> void saveObject(String pathStr, O o) throws IOException
	{
		
		File f = new File(pathStr);
		FileOutputStream fos = new FileOutputStream(f);
	
		ObjectOutputStream oss = new ObjectOutputStream(fos);
		
		oss.writeObject(o);
		
		oss.close();
		fos.close();
	}
	
	public static void saveText(String pathStr , String textPayload) throws IOException
	{
		File f = new File(pathStr);
		FileWriter fw = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(textPayload);

		bw.close();
		fw.close();
	}

	public static void saveText(Path path, String textPayload) throws IOException 
	{
		saveText(path.toAbsolutePath().toString(), textPayload);
	}

	public static String loadText(String pathStr) throws IOException
	{
		
		File f = new File(pathStr);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
        
		String aktuelleZeile;
        String output = "";
        int counter = 0;
        
        while((aktuelleZeile=br.readLine()) != null)
        {
        	if(counter>0)output += '\n'+aktuelleZeile;
        	else output += aktuelleZeile;
        	counter++;
        }
        
        fr.close();
        br.close();
		return output;
	}
	
	public static boolean isThereThisFile(Path path)
	{
		if(Files.exists(path) && (!Files.isDirectory(path)))return true;
    	else return false;
	}
	
	public static boolean isThereThisFile(String pathStr)
    {
          Path path = Paths.get(pathStr);
          return isThereThisFile(path);
    }

	public static boolean isThereThisFolder(Path path)
    {
    	if(Files.exists(path) && Files.isDirectory(path))return true;
    	else return false;
    }
    
	public static boolean isThereThisFolder(String dir)
    {
		Path path = Paths.get(dir);
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

    public static boolean eraseFile(String pathStr) throws IOException
    {
    	Path path = Paths.get(pathStr);
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

    public static boolean eraseFolder(String dir) throws IOException
    {
    	Path path = Paths.get(dir);
    	return eraseFolder(path);
    }
    
    public static Set<String> areThereFilesOfThisSchema(String dir, String regEx) throws IOException
    {
    	Path path = Paths.get(dir);
    	return areThereFilesOfThisSchema(path, regEx);
    }
    
    public static Set<String> areThereFilesOfThisSchema(Path path, String regEx) throws IOException
    {
    	Set<String> fileNames = new HashSet<>();
        
        if(!(Files.exists(path) && Files.isDirectory(path))) throw new IOException("Folder doesn't exist. Or isn't a Folder!");

        // Create a FileFilter using the provided regex pattern
        FileFilter fileFilter = (file)->
        {
        	
        	String fileName = file.getName();
        	//Matcher matcher = pattern.matcher(file.getName());      
        	if(fileName.toString().matches(regEx))fileNames.add(fileName);

        	return true;
        };
        
        // Get a list of files that match the regex pattern
        path.toFile().listFiles(fileFilter);

    	return fileNames;
    }
}