package consoleTools;

public class InputArgumentException extends Exception 
{

	private String msg;
	
	public InputArgumentException(String msg)
	{
		this.msg = msg;
		System.out.println(msg);
	}
}
