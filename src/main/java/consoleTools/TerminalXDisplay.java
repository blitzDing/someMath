package consoleTools;

import java.awt.Point;
import java.util.Collection;
import java.util.List;

import someMath.CollectionException;
import static someMath.CollectionManipulation.*;
import static someMath.StringManipulation.*;
import static consoleTools.BashSigns.*;

public class TerminalXDisplay 
{

	public static String pointToString(String name, Point p)
	{
		return name + "("+p.x+", "+p.y+")";
	}
	
	private static <T> String collectionToString(Collection<T> collection, int e) throws CollectionException
	{
		
		int s = collection.size();
		int counter = 0;
		
		String output = customMonoRepeatChar(' ', e) + "{";
		
		if(!(collection instanceof List))
		{

			T tt = catchRandomElementOfCollection(collection);
			if(tt instanceof Collection)output = output + "\n";
			
			for(T t: collection)
			{

				if(t instanceof Collection)
				{
					
					Collection<?> t2 = (Collection<?>)t;
					output = output + collectionToString(t2, e+1)+",";
					continue;
				}
				if(counter==s-1)
				{
					if(t!=null)output = output + t.toString();
					else output = output + "null";
				}
				else
				{
					if(t!=null)output = output + t.toString() + ", ";
					else output = output + "null" + ", ";
				}
				counter++;
			}	
		}
		else
		{
			
			List<T> list = (List<T>)(collection);
			T t = list.get(0);
			
			if(t instanceof Collection)output = output + "\n";

			
			for(int n=0;n<s;n++)
			{
				
				t = list.get(n);

				if(t instanceof Collection)
				{
					Collection<?> t2 = (Collection<?>)t;
					output = output + collectionToString(t2, e+2)+",";
					continue;
				}

				if(n==s-1)
				{
					if(t!=null)output = output + t.toString();
					else output = output + "null";
					
				}
				else 
				{
					if(t!=null)output = output + t.toString() + ", ";
					else output = output + "null" + ", ";
				}
			}
		}
		output = output + "}";//;
		
		return output;
	}

	public static <T> String collectionToString(Collection<T> collection) throws CollectionException
	{
		return collectionToString(collection, 0);
	}

	public static void printBoldAndGreen(String s)
	{
		System.out.println(boldGBCPX+s+boldGBCSX);
	}

	public static void printBoldAndBlue(String s)
	{
		System.out.println(boldBBCPX+s+boldBBCSX);
	}
	
	public static void printBoldAndYellow(String s)
	{
		System.out.println(boldYBCPX+s+boldYBCSX);
	}

	public static void printBoldAndRed(String s)
	{
		System.out.println(boldRBCPX+s+boldRBCSX);
	}
	
}
