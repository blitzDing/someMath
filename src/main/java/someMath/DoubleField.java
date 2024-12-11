package someMath;

import java.util.Objects;

import someMath.exceptions.CollectionException;
import someMath.exceptions.DivisionByZeroException;
import someMath.exceptions.NaturalNumberException;
import someMath.exceptions.RNumException;

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
	
	public int hashCode()
	{
		return Objects.hash(core);
	}
	
	public boolean equals(Object other)
	{
		if(other==null)return false;
		
		if(this==other)return true;
		
		if(this.getClass()!=other.getClass())return false;
		
		DoubleField otherAsDF = (DoubleField)other;
	
		return core.equals(otherAsDF.core);
	}		
}