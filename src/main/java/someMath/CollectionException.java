package someMath;

public class CollectionException extends Exception 
{

	private final String msg;

	public CollectionException(String msg)
	{
		this.msg = msg;
	}
	
	public String getMessage()
	{
		return msg;
	}
}