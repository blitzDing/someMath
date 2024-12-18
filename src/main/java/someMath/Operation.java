package someMath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


import someMath.exceptions.MathException;

public class Operation<E>
{
	
	private final String name;
	private final E neutrum;
	private final int minOperands;
	private final int maxOperands;
	private final Function<List<E>, E> op;
	
	public Operation(String name, E neutrum, int minOperands, int maxOperands, Function<List<E>, E> op) throws MathException
	{
		this.name = name;
		this.neutrum = neutrum;
		if(minOperands<1)throw new MathException("At least one Operand is needed when defining a Operation.");
		if(maxOperands<minOperands) throw new MathException("max. Operands must be equal or bigger then min. Operands.");

		this.minOperands = minOperands;
		this.maxOperands = maxOperands;
		this.op = op;
	}
	
	public E operate(E... operands) throws MathException
	{		
		int size = operands.length;
		if(size>maxOperands||size<minOperands)throw new MathException("Not the right nr of Operands");
		
		
		List<E> operandsAsList = Arrays.asList(operands);
		
		return op.apply(operandsAsList);
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
