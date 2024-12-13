package someMath.exceptions;

public class MathException extends Exception
{

	private final String msg;
	
	public MathException(String msg)
	{
		this.msg = msg;
	}
	
	public String getMessage()
	{
		return msg;
	}
}
