package someMath;

public class DivisionByZeroException extends Exception
{

	private static final long serialVersionUID = -8186148276418067021L;
	
	private final String msg = "Division by Zero.";
	
	public DivisionByZeroException()
	{
		
	}
	
	public String getMessage()
	{
		return msg;
	}
}
