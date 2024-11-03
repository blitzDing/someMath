package someMath;

public class DoubleField implements SubtractableAndDivideable<DoubleField>
{

	private final Double core;
	
	public DoubleField(Double core)
	{
		this.core = core;
	}

	@Override
	public DoubleField subtract(DoubleField e) throws RNumException, NaturalNumberException, CloneNotSupportedException,
			DivisionByZeroException, CollectionException
	{
		return new DoubleField(core - e.core);
	}

	@Override
	public boolean hasNeutralZero()
	{
		return true;
	}

	@Override
	public DoubleField add(DoubleField e) throws NaturalNumberException, RNumException, CloneNotSupportedException,
			CollectionException, DivisionByZeroException
	{
		return new DoubleField(core + e.core);
	}

	@Override
	public DoubleField getNeutralZero() throws NaturalNumberException
	{
		return new DoubleField(0.0);
	}

	@Override
	public DoubleField divideBy(DoubleField t) throws DivisionByZeroException, NaturalNumberException,
			CollectionException, RNumException, CloneNotSupportedException
	{
		return new DoubleField(core/t.core);
	}

	@Override
	public boolean hasNeutralOne()
	{
		return true;
	}

	@Override
	public DoubleField multiplyWith(DoubleField e) throws NaturalNumberException, RNumException,
			CloneNotSupportedException, DivisionByZeroException, CollectionException
	{

		return new DoubleField(core*e.core);
	}

	@Override
	public DoubleField getNeutralOne() throws NaturalNumberException
	{
		return new DoubleField(1.0);
	}

	public Double getCore()
	{
		return core;
	}
}
