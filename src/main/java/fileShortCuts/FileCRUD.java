package fileShortCuts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.nio.file.Path;



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
}