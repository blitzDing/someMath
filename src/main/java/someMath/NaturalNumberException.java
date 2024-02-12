package someMath;

public class NaturalNumberException extends Exception
{

	private static final long serialVersionUID = -4952066859888561137L;
	String msg;
	
	public NaturalNumberException(String msg)
	{
		System.out.println(msg);
	}
}