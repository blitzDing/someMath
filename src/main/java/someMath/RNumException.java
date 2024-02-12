package someMath;

public class RNumException extends Exception
{

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
