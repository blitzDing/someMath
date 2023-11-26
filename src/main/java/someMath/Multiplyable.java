package someMath;

/*
 * Any Class implementing this Interface
 * should make the Generic 'E' identical 
 * to its own type.
*/
public interface Multiplyable<E> 
{
	
	public boolean hasNeutralOne();
	
	public E multiplyWith(E e) throws InterfaceNumberException;
	
	public E getNeutralOne() throws InterfaceNumberException;
}
