package someMath;

public class Operation<E>
{
	
	private final String name;
	private final E neutrum;
	
	public Operation(String name, E neutrum)
	{
		this.name = name;
		this.neutrum = neutrum;
	}
	
	//Must be overwritten to be useful.
	public E execute(E...e1)
	{
		return null;
	}
	
	
	public Boolean hasNeutralElement()
	{
		return !(neutrum==null);
	}
	
	public E getNeutrum()
	{
		return neutrum;
	}

	public String getName()
	{
		return name; 
	}
}
