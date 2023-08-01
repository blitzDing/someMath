package someMath;

import java.util.ArrayList;
import java.util.List;

public class StringManipulation 
{

	public static String customMonoRepeatChar(char c, int n)
	{
		String output = "";
		
		for(int i=0;i<n;i++)output =output+c;
		
		return output;
	}
	
	public static int countOccurrenceOfString(String source, String search)
	{
        int count = 0, index = 0;
        List<Integer> indices=new ArrayList<>();
        while ((index = source.indexOf(search, index)) != -1)
        {
            count++;
            indices.add(index);
            index++;
        }
                
        return count;
	}
	
    public static int countOccurrenceOfChar(String source, char search)
	{
		String s = ""+search;
		return countOccurrenceOfString(source, s);
	}
}
