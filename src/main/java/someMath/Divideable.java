package someMath;

import someMath.exceptions.CollectionException;
import someMath.exceptions.DivisionByZeroException;
import someMath.exceptions.NaturalNumberException;
import someMath.exceptions.RNumException;

public abstract class Divideable <T> extends Multiplyable<T>
{

	public Divideable(T one) 
	{
		super(one);
		// TODO Auto-generated constructor stub
	}

	//Careful if u have no inverse for every T.
	public abstract T divideBy(T t) throws DivisionByZeroException, NaturalNumberException, CollectionException, RNumException, CloneNotSupportedException;
}
