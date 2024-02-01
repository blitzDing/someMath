package someMath;

public interface Divideable <T> extends Multiplyable<T>
{

	//Careful if u have no inverse for every T.
	public T divideBy(T t) throws DivisionByZeroException;
}
