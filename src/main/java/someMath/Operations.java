package someMath;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import someMath.exceptions.MathException;

public class Operations<E>
{	
	
	public final String add = "add";
	public final String multiply = "multiply";
	public final String minus = "minus";
	public final String divide = "div";
	public final String pow = "pow";
	public final String root = "root";
	public final String log = "log";

	public final Set<String> opNames = new HashSet<>(Arrays.asList(add, multiply, minus, divide, pow, root, log));

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

	public E add(E...eArray) throws MathException
	{
		
		if(!definedOperations.containsKey(add))throw new MathException("Addition not defined!");
		Operation<E> op = definedOperations.get(add);
		return op.operate(eArray);
	}

	public E multiply(E...eArray) throws MathException
	{
		
		if(!definedOperations.containsKey(multiply))throw new MathException("Multiplication not defined!");
		Operation<E> op = definedOperations.get(multiply);
		return op.operate(eArray);
	}

	public E minus(E...eArray) throws MathException
	{
		
		if(!definedOperations.containsKey(minus))throw new MathException("Subtraction not defined!");
		Operation<E> op = definedOperations.get(minus);
		return op.operate(eArray);
	}

	public E dived(E...eArray) throws MathException
	{
		
		if(!definedOperations.containsKey(divide))throw new MathException("Division not defined!");
		Operation<E> op = definedOperations.get(divide);
		return op.operate(eArray);
	}

	public E pow(E...eArray) throws MathException
	{
		
		if(!definedOperations.containsKey(pow))throw new MathException("Exponentiation not defined!");
		Operation<E> op = definedOperations.get(pow);
		return op.operate(eArray);
	}

	public E root(E...eArray) throws MathException
	{
		
		if(!definedOperations.containsKey(root))throw new MathException("Root not defined!");
		Operation<E> op = definedOperations.get(root);
		return op.operate(eArray);
	}
	
	public E log(E...eArray) throws MathException
	{
		
		if(!definedOperations.containsKey(log))throw new MathException("Logarithim not defined!");
		Operation<E> op = definedOperations.get(log);
		return op.operate(eArray);
	}

	public E getNeutrumOfOperation(String name)
	{
		
		Operation<E> op = definedOperations.get(name);
	
		return op.getNeutrum();
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