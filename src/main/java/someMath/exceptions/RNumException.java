package someMath.exceptions;

public class RNumException extends Exception
{

	private static final long serialVersionUID = -8424012469944882789L;

	private final String msg;
	
	public RNumException(String msg)
	{
		this.msg = msg;
	}
	
	public String getMessage()
	{
		return msg;
	}
}
