package someMath;

public class NaturalNumberException extends Exception
{

	private static final long serialVersionUID = -4952066859888561137L;
	private final String msg;
	
	public NaturalNumberException(String msg)
	{
		
		this.msg = msg;
		
		System.out.println(msg);
	}
	
	public String getMessage()
	{
		return msg;
	}
}