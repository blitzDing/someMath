package someMath;

public class DivisionByZeroException extends Exception
{

	private final String msg = "Division by Zero.";
	
	public DivisionByZeroException()
	{
		
	}
	
	public String getMessage()
	{
		return msg;
	}
}
