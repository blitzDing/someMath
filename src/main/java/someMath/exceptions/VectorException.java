package someMath.exceptions;

public class VectorException extends Exception
{
	private final String msg;
	
	
	public VectorException(String msg)
	{
		this.msg = msg;
	}
	
	public String getMessage()
	{
		return msg;
	}

}
