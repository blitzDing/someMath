package someMath;

import someMath.exceptions.CollectionException;
import someMath.exceptions.DivisionByZeroException;
import someMath.exceptions.NaturalNumberException;
import someMath.exceptions.RNumException;

/*
 * Any Class implementing this Interface
 * should make the Generic 'E' identical 
 * to its own type. Also important for 
 * Matrixes is a equal method overwrite.
*/
public abstract class Addable<E>
{
	
	public final E zero;
	
	
	public Addable(E zero)
	{
		this.zero = zero;
	}
	
	public abstract boolean hasNeutralZero();
	
	public abstract E add(E e) throws NaturalNumberException, RNumException, CloneNotSupportedException, 
	CollectionException, DivisionByZeroException;
	}
