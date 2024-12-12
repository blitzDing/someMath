package someMath;

public class AddComplexNrDouble extends Operation<ComplexNrDouble>
{

	public static final String add = "ComplexAdd";
	
	public AddComplexNrDouble()
	{
		super(add, new ComplexNrDouble(0,0));
	}
	
	public ComplexNrDouble execute(ComplexNrDouble...aCouple)
	{
		double x = getNeutrum().getRealPart();
		double y = getNeutrum().getRealPart();
		
		ComplexNrDouble start = new ComplexNrDouble(x, y);
		
		for(ComplexNrDouble c: aCouple)
		{
			double a = (start.getRealPart()+c.getRealPart());
			double b = (start.getImaginaryPart()+c.getImaginaryPart());
			
			start = new ComplexNrDouble(a, b);
		}
		
		return start;
	}
	
	public ComplexNrDouble getNeutrum()
	{
		return super.getNeutrum();
	}
}
