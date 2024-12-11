package someMath.exceptions;

public class CollectionException extends Exception
{

	private static final long serialVersionUID = -4203776761962670684L;

	final String msg;

	public CollectionException(String msg)
	{
		this.msg = msg;
	}
	
	public String getMessage()
	{
		return msg;
	}
}