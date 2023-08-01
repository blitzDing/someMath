package consoleTools;

import java.util.List;

import someMath.StringManipulation;

public class TerminalTableDisplay 
{
	private final List<String> headers;
	private final List<List<String>> values;
	private int longestString = 0;//row length minus 1.
	private int columns = 0;
	//private int rows = 0;
	
	public TerminalTableDisplay(List<String> headers, List<List<String>> values)
	{
		this.headers = headers;
		this.values = values;
		this.longestString = lengthOfLongestString();
		this.columns = headers.size();
		//this.rows = values.size();
		
	}
	
	private int lengthOfLongestString()
	{
		
		int longest = 0;
		
		for(String header: headers)
		{
			if(header.length()>longest)longest= header.length();
		}
		
		for(List<String> row: values)
		{
			for(String value: row)
			{
				if(value.length()>longest)longest=value.length();
			}
		}
		
		return longest;
	}
	
	public String toString()
	{
		
		//Ceiling:
		int doubleBorderLength = columns*2+1;
		int payLoadOfCellLength = longestString+1;
		int rowLength = ((payLoadOfCellLength*columns)+doubleBorderLength);
		String FloorOrCeiling = "||"+StringManipulation.customMonoRepeatChar('-', rowLength)+"||\n";
		String output = FloorOrCeiling+"||";
		
		//Headers:
		for(String header: headers)
		{
			int headerLength = header.length();
			String whiteSpace = StringManipulation.customMonoRepeatChar(' ',payLoadOfCellLength-headerLength+1);
			output = output+whiteSpace+header+"||";
		}
		output = output+"\n"+FloorOrCeiling;
		
		//Cells row by row
		for(List<String> row: values)
		{
			output = output+"||";
			for(String cell: row)
			{
				int cellLength = cell.length();
				String whiteSpace = StringManipulation.customMonoRepeatChar(' ', payLoadOfCellLength-cellLength+1);
				output = output+whiteSpace+cell+"||";
			}
			output = output+"\n";
		}
		
		//Floor
		output = output+FloorOrCeiling;

		return output;
	}
}