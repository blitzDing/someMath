package someMath;

import java.util.List;
import java.util.function.Function;

import someMath.exceptions.MathException;


public class ComplexNrOperations
{
	public static Function<List<ComplexNrDouble>, ComplexNrDouble> addComplex = (list)->
	{
	
		return null;
	};
	
	public static Operation<ComplexNrDouble> addition;

	public ComplexNrOperations()
	{
		try
		{
			addition = 	new Operation("C-Addition", new ComplexNrDouble(0,0),2, Integer.MAX_VALUE, addComplex);
		}
		catch (MathException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}