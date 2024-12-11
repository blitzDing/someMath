package someMath;

import someMath.exceptions.CollectionException;
import someMath.exceptions.DivisionByZeroException;
import someMath.exceptions.NaturalNumberException;
import someMath.exceptions.RNumException;

public abstract class Subtractable<E> extends Addable<E>
{

	public Subtractable(E zero)
	{
		super(zero);
		// TODO Auto-generated constructor stub
	}

	public abstract E subtract(E e) throws RNumException, NaturalNumberException, CloneNotSupportedException, DivisionByZeroException, CollectionException;
}
