package consoleTools;

import java.util.Collection;
import java.util.*;

import someMath.StringManipulation;

public class TerminalTableDisplay 
{
	private final List<String> headers;
	private final List<List<String>> cellValues;
	private int columns = 0;
	private int rows = 0;
	private final int cellWidth = 20;
	private final Cell [][]cell;
	private final char delimiter;
	private final int dL = 1; //delimiterLength = 1;
	
	public TerminalTableDisplay(List<String> headers, List<List<String>> cellValues, char delimiter)
	{
		this.delimiter = delimiter;
		List<List<String>> newCellValues = new ArrayList<>();
		newCellValues.add(headers);
		newCellValues.addAll(cellValues);
		this.headers = headers;
		this.cellValues = newCellValues;
		this.rows = this.cellValues.size();
		this.columns = headers.size();
		System.out.println(Math.floorDiv(102, 5));
		System.out.println("columns: "+columns+" rows: "+rows);
		
		cell = new Cell[rows][columns];

		for(int n=0;n<rows;n++)
		{
			List<String> row = this.cellValues.get(n);
			
			for(int m=0;m<columns;m++)
			{
				
				String s = row.get(m);
				if(s.contains("\n"))
				{
					System.out.println("Warning Cell contains \'\\n\'. Will be replaced with Whitespace.");
					s= s.replace('\n', ' ');
				}

				cell[n][m] = new Cell(breakupContent(s));
				System.out.println("row: " + n +" column: " + m);
				System.out.println(cell[n][m]);
			}
		}
	}
	
	public String[] breakupContent(String s)
	{
		
		int l = s.length();
		if(l<=cellWidth)
		{
			
			int r = cellWidth-l;
			String[] singleLine = new String[1];
			singleLine[0] = delimiter + String.valueOf(s)+StringManipulation.customMonoRepeatChar(' ', r);
			
			return singleLine;
		}
		
		int linesNr = Math.floorDiv(l, (cellWidth));
		if(l%(cellWidth)!=0)linesNr=linesNr+1;

		String[] lines = new String[linesNr];
		
		String copy = String.valueOf(s);
		
		for(int n=0;n<linesNr;n++)
		{
			String a = "";
			
			if(copy.length()>=cellWidth)a = copy.substring(0, cellWidth);
			else 
			{
				int r = cellWidth-copy.length();
				a = String.valueOf(copy)+StringManipulation.customMonoRepeatChar(' ', r);
			}

			System.out.println("Line: " + a + ". Length: " + a.length() + ".");

			if(copy.length()>=cellWidth)copy = copy.substring(cellWidth, copy.length());

			lines[n]=delimiter + a;
		}
		
		return lines;
	}
	
	public int mostLinesInThatRow(int row)
	{
		
		int maxRow = 1;
		
		for(int m=0;m<columns;m++)
		{
			int x = cell[row][m].getNrOfLines();
			if(x>maxRow)maxRow = x;
		}
		
		return maxRow;
	}
	
	public String toString()
	{
		String output = "";
		
		for(int n=0;n<rows;n++)
		{
			List<String> row = this.cellValues.get(n);
			int maxRow = mostLinesInThatRow(n);
			System.out.println("Max lines in row " + n + " is:" + maxRow);
			for(int l=0;l<maxRow;l++)
			{
				
				for(int m=0;m<columns;m++) 
				{
					output = output + cell[n][m].getLineByNr(l);
				}
				
				output = output + delimiter + "\n";
			}
			
			//floors (plural).
			output = output + StringManipulation.customMonoRepeatChar('-', (columns*cellWidth)+(dL*columns) + dL)+"\n";
		}
		
		//Roof
		output = StringManipulation.customMonoRepeatChar('-', (columns*cellWidth)+(dL*columns) + dL)+"\n"+output;
		
		return output;
	}
	
	private class Cell
	{
		
		private final int nrOfLines;
		private final String [] lines;
		
		public Cell(String [] lines)
		{
			this.lines = lines;
			this.nrOfLines = lines.length;
		}
		
		public String getLineByNr(int n)
		{
			String output = delimiter + StringManipulation.customMonoRepeatChar(' ', cellWidth);
			
			if(n<nrOfLines)output = lines[n];
			
			return output;
		}
		
		public int getNrOfLines()
		{
			return nrOfLines;
		}
		
		public String toString()
		{
			String output = "";
			
			for(int n=0;n<nrOfLines;n++)
			{
				output = output+lines[n]+"\n";
			}
			
			
			return output;
		}
	}
}