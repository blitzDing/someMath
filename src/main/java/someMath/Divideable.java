package someMath;

public interface Divideable <T> extends Multiplyable<T>
{

	public T divideBy(T t) throws InterfaceNumberException, CollectionException;//Careful if u have no inverse for every E.
}
