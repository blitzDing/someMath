package someMath;

import someMath.exceptions.MathException;

public abstract class Operation<E>
{
	
	private final String name;
	private final E neutrum;
	private final int minOperands;
	private final int maxOperands;
	
	public Operation(String name, E neutrum, int minOperands, int maxOperands) throws MathException
	{
		this.name = name;
		this.neutrum = neutrum;
		if(minOperands<1)throw new MathException("At least one Operand is needed when defining a Operation.");
		if(maxOperands<minOperands) throw new MathException("max. Operands must be equal or bigger then min. Operands.");

		this.minOperands = minOperands;
		this.maxOperands = maxOperands;
	}
	
	//Must be overwritten to be useful.
	protected abstract E execute(E...e1);

	//This is the workhorse and the one the user should execute.
	public final E act(E...e1) throws MathException
	{
		if(!checkOperands(e1))throw new MathException("Nr of Operands not valide.");
		else return execute(e1);
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
	
	private boolean checkOperands(E[] eArray)
	{
		int size = eArray.length;
		return (size<=maxOperands&&size>=minOperands);
	}
}
