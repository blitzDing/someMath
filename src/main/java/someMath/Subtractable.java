package someMath;

public interface Subtractable <E> extends Addable<E>
{

	public E subtract(E e) throws RNumException, NaturalNumberException, CloneNotSupportedException;
}
