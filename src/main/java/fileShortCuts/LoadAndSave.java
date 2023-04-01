package fileShortCuts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
		
		return o;
	}
	
	public static <O> void saveObject(String path, O o) throws IOException
	{
		
		File f = new File(path);
		FileOutputStream fos = new FileOutputStream(f);
		@SuppressWarnings("resource")
		ObjectOutputStream oss = new ObjectOutputStream(fos);
		
		oss.writeObject(o);
	}
}