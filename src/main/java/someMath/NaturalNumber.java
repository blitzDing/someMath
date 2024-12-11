package someMath;

import java.math.BigInteger;
import java.util.Objects;

import someMath.exceptions.CollectionException;
import someMath.exceptions.DivisionByZeroException;
import someMath.exceptions.NaturalNumberException;
import someMath.exceptions.RNumException;


//Even so it implements addition, subtraction, multiplication and division this is
// NOT a mathematical Group!!!!
public class NaturalNumber extends Number implements Cloneable
{
	
	private final Integer numberCore;

	/*Remember:*/
	public final Integer min = 0;
	public final Integer max = Integer.MAX_VALUE;
	public static final BigInteger biZero = new BigInteger("0");
	public static final BigInteger biOne = new BigInteger("1");
	
	public static final NaturalNumber zero = new NaturalNumber("0");
	public static final NaturalNumber one = new NaturalNumber("1");
	
	public NaturalNumber(Integer numberCore) throws NaturalNumberException
	{
		if(numberCore<0)throw new NaturalNumberException("Can't work with negative Integers.");

		this.numberCore = numberCore;
	}

	
	private NaturalNumber(String s)
	{
		this.numberCore = Integer.parseInt(s);
	}
	
	public boolean hasNeutralZero()
	{
		return true;
	}

	
	public boolean hasNeutralOne() 
	{
		return true;
	}

	public NaturalNumber getNeutralZero() throws NaturalNumberException
	{
		return new NaturalNumber(0);
	}


	public NaturalNumber getNeutralOne() throws NaturalNumberException
	{
		return new NaturalNumber(1);
	}

	
	public NaturalNumber multiplyWith(NaturalNumber e) throws NaturalNumberException
	{
		
		if(this.equals(zero)||e.equals(zero))return zero;
		Integer p = numberCore*e.numberCore;
		
		return new NaturalNumber(p);
	}

	public NaturalNumber add(NaturalNumber e) throws NaturalNumberException 
	{
		Integer s = numberCore + e.numberCore;
		
		return new NaturalNumber(s);
	}
	
	public NaturalNumber subtract(NaturalNumber e) throws NaturalNumberException
	{
		
		Integer s = numberCore - e.numberCore;
		
		return new NaturalNumber(s);//Throws Exception if e is greater Then this.
	}
	
	/**Careful is rounded down like integer division.*/
	public NaturalNumber divideBy(NaturalNumber t)
			throws DivisionByZeroException, NaturalNumberException, CollectionException, RNumException
	{
		
		if(t.equals(zero))throw new DivisionByZeroException();
		if(this.equals(zero))return zero;
		if(this.isSmallerThen(t))return zero;
		if(this.equals(t))return one;
		if(t.equals(one))return new NaturalNumber(numberCore);
		
		NaturalNumber current = zero.clone();
		int counter = 0;
		
		for(;(!(current.isGreaterThen(this)));counter++)
		{
			current = current.add(t);
		}

		return new NaturalNumber(counter-1);
	}
	
	public boolean isGreaterThen(NaturalNumber n)
	{
		return (numberCore-n.numberCore>0);
	}
	
	public boolean isSmallerThen(NaturalNumber n)
	{
		return (numberCore-n.numberCore<0);
	}
	
	public int hashCode()
	{
		return Objects.hash(numberCore);
	}
	
	public boolean equals(Object obj)
	{
		
		if (obj == this) return true;
		
	    if (!(obj instanceof NaturalNumber)) return false;
	    
	    NaturalNumber other = (NaturalNumber)obj;
	    
	    if(!other.numberCore.equals(numberCore))return false;
	    
	    return true;
	}

	public String toString()
	{
		return numberCore + "";
	}
	
	public NaturalNumber clone()
	{
		try
		{
			return new NaturalNumber(numberCore);
		}
		catch (NaturalNumberException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public double doubleValue()
	{
		return numberCore.doubleValue();

	}

	@Override
	public float floatValue()
	{
		return numberCore.floatValue();
	}

	@Override
	public int intValue()
	{
		return numberCore.intValue();
	}

	@Override
	public long longValue() 
	{
		return numberCore.longValue();
	}
	
	public static void activateToBigWarning()
	{
		toBigWarningOn = true;
	}
	
	public static void deActivateToBigWarning()
	{
		toBigWarningOn = false;
	}
}