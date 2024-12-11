package someMath;

public abstract class SubtractableAndDivideable<D extends Divideable<D>, E extends D> extends Subtractable<E>
{
	public SubtractableAndDivideable(E zero)
	{
		super(zero);
		// TODO Auto-generated constructor stub
	}
}

