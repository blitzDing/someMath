package someMath;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

	public E execute(String name, E e1, E e2)
	{
		Operation<E> op = definedOperations.get(name);
		return op.execute(e1, e2);
	}

	public E getNeutrumOfOperation(String name)
	{
		return null;
	}
	
	public Operation<E> getOperation(String name)
	{
		return definedOperations.get(name);
	}
}