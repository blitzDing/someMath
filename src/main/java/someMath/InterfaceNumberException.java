package someMath;

public class InterfaceNumberException extends Exception
{

	String msg;
	
	public InterfaceNumberException(String msg)
	{
		System.out.println(msg);
	}
}