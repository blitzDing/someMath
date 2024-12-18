package someMath;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import someMath.exceptions.MathException;

public class Operations<E>
{	
	
	public final Map<String, Operation<E>> definedOperations = new HashMap<>();

	public Operations(Set<Operation<E>> set)
	{
		
		for(Operation<E> op: set)
		{
			definedOperations.put(op.getName(), op);
		}
	}

	public E execute(String name, E e1, E e2) throws MathException
	{
		Operation<E> op = definedOperations.get(name);
		return op.operate(e1, e2);
	}

	public E getNeutrumOfOperation(String name)
	{
		return null;
	}
	
	public Operation<E> getOperation(String name)
	{
		return definedOperations.get(name);
	}
	
	public void setOperation(Operation<E> op)
	{
		definedOperations.put(op.getName(), op);
	}
}