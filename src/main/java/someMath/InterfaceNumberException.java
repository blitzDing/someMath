package someMath;

public class InterfaceNumberException extends Exception
{

	private static final long serialVersionUID = -4952066859888561137L;
	String msg;
	
	public InterfaceNumberException(String msg)
	{
		System.out.println(msg);
	}
}