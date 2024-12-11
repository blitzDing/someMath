package someMath;

import java.io.Serializable;
import java.util.Objects;

import someMath.exceptions.CollectionException;
import someMath.exceptions.DivisionByZeroException;
import someMath.exceptions.NaturalNumberException;
import someMath.exceptions.RNumException;

public class SmallNatural implements SubtractableAndDivideable<SmallNatural>, Cloneable, Serializable
{

	private static final long serialVersionUID = 8942842370259459331L;
	
	public static final String valueOutOfBounds = "Small Natural Constructor Argument out of Bounds.";
	public static final int max = 999;
	private final int value;
	private String name;
	
	public static final SmallNatural snZero = new SmallNatural("Zero", 0);
	public static final SmallNatural snOne = new SmallNatural("One", 1);
	
	public SmallNatural(int value) throws NaturalNumberException
	{
		if(value<0||value>max)throw new NaturalNumberException(valueOutOfBounds);
	
		this.value = value;
	}
	
	private SmallNatural(String name, int value)
	{
		
		
		this.value = value;
		this.name = name;
	}
	
	@Override
	public SmallNatural subtract(SmallNatural e) throws RNumException, NaturalNumberException,
			CloneNotSupportedException, DivisionByZeroException, CollectionException
	{
		
		int diff = value-e.getNumberCore();
		
		return new SmallNatural(diff);
	}

	@Override
	public boolean hasNeutralZero()
	{
		return true;
	}

	@Override
	public SmallNatural add(SmallNatural e) throws NaturalNumberException, RNumException,
			CloneNotSupportedException, CollectionException, DivisionByZeroException
	{

		int sum = value + e.getNumberCore();
		
		return new SmallNatural(sum);
	}

	@Override
	public SmallNatural getNeutralZero() throws NaturalNumberException
	{
		return snZero;
	}

	@Override
	public SmallNatural divideBy(SmallNatural t) throws DivisionByZeroException, NaturalNumberException,
			CollectionException, RNumException, CloneNotSupportedException
	{

		if(t.equals(snZero))throw new DivisionByZeroException();
		if(this.equals(snZero))return snZero;
		if(this.isSmallerThen(t))return snZero;
		if(this.equals(t))return snOne;
		if(t.equals(snOne))return new SmallNatural(value);
		
		
		SmallNatural current = snZero.clone();
		int counter = 0;
		
		for(;!(current.isGreaterThen(this));counter++)
		{
			current = current.add(t);
		}
		
		return new SmallNatural(counter-1);
	}

	@Override
	public boolean hasNeutralOne()
	{
		return true;
	}

	@Override
	public SmallNatural multiplyWith(SmallNatural e) throws NaturalNumberException, RNumException,
			CloneNotSupportedException, DivisionByZeroException, CollectionException
	{
		
		int product = value*e.getNumberCore();
		
		return new SmallNatural(product);
	}
	
	public boolean isSmallerThen(SmallNatural sn)
	{
		if(this.equals(sn))return false;
		
		return value<sn.getNumberCore();
	}
	
	public boolean isGreaterThen(SmallNatural sn)
	{
		
		if(this.equals(sn))return false;
		if(this.isSmallerThen(sn))return false;
		
		return true;
	}

	@Override
	public SmallNatural getNeutralOne() throws NaturalNumberException 
	{
		return snOne;
	}

	public int getNumberCore()
	{
		return value;
	}
	
	public SmallNatural clone()
	{
		try
		{
			return new SmallNatural(value);
		}
		catch (NaturalNumberException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public String getName()
	{
		return name;
	}

	public int hashCode()
	{
		return Objects.hash(value);
	}
	
	public boolean equals(Object obj)
	{
		if (obj == this) return true;
		
	    if (!(obj instanceof SmallNatural)) return false;
	    
	    SmallNatural other = (SmallNatural)obj;
	    
	    if(!(other.getNumberCore()==value))return false;
	    
	    return true;
	}
	
	public String toString()
	{
		return ""+value;
	}
}
