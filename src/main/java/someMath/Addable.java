package someMath;

/*
 * Any Class implementing this Interface
 * should make the Generic 'E' identical 
 * to its own type. Also important for 
 * Matrixes is a equal method overwrite.
*/
public interface Addable<E>
{
	
	public boolean hasNeutralZero();
	
	public E addWith(E e) throws NaturalNumberException, RNumException, CloneNotSupportedException
, CollectionException, DivisionByZeroException;
	
	public E getNeutralZero() throws NaturalNumberException;
}
