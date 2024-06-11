package consoleTools;

import java.awt.Point;
import java.util.Collection;
import java.util.List;

public class TerminalXDisplay 
{

	public static String pointToString(String name, Point p)
	{
		return name + "("+p.x+", "+p.y+")";
	}
	
	public static <T> String collectionToString(Collection<T> collection)
	{
		
		int s = collection.size();
		int counter = 0;
		
		String output = "{";
		
		if(!(collection instanceof List))
		{

			for(T t: collection)
			{
				
				if(t instanceof Collection)
				{
					
					Collection<?> t2 = (Collection<?>)t;
					output = output + collectionToString(t2);
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
			
			for(int n=0;n<s;n++)
			{
				
				T t = list.get(n);
				if(t instanceof Collection)
				{
					
					Collection<?> t2 = (Collection<?>)t;
					output = output + collectionToString(t2);
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
		output = output + "}\n";
		
		return output;
	}

}
