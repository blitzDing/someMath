package consoleTools;

public class InputArgumentException extends Exception
{

	private static final long serialVersionUID = 7981318273716170338L;

	final String msg;
	
	public InputArgumentException(String msg)
	{
		this.msg = msg;
	}
	
	public String getMessage()
	{
		return msg;
	}
}
