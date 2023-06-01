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


public class LoadAndSave 
{

	
	public static Object loadObject(String path) throws IOException, ClassNotFoundException
	{
		
		File f = new File(path);
		FileInputStream fis = new FileInputStream(f);
		@SuppressWarnings("resource")
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Object o = ois.readObject();
		ois.close();
		fis.close();
		
		return o;
	}
	
	public static <O> void saveObject(String path, O o) throws IOException
	{
		
		File f = new File(path);
		FileOutputStream fos = new FileOutputStream(f);
		@SuppressWarnings("resource")
		ObjectOutputStream oss = new ObjectOutputStream(fos);
		
		oss.writeObject(o);
		
		oss.close();
		fos.close();
	}
	
	public static void saveText(String path, String fileName, String Text) throws IOException
	{
		File f = new File(path+'/'+fileName);
		FileWriter fw = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(Text);
		
		bw.close();
		fw.close();
		
	}
	
	public static String loadText(String path, String fileName) throws IOException
	{
		
		File f = new File(path+'/'+fileName);
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