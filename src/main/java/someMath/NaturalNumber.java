package someMath;

import java.math.BigInteger;
import java.util.Objects;

//Even so it implements addition, subtraction, multiplication and division this is
// NOT a mathematical Group!!!!
public class NaturalNumber implements SubtractableAndDivideable<NaturalNumber>, Cloneable
{

	private final BigInteger numberCore;
	
	public static final BigInteger biZero = new BigInteger("0");
	public static final BigInteger biOne = new BigInteger("1");
	
	public static final NaturalNumber zero = new NaturalNumber("0");
	public static final NaturalNumber one = new NaturalNumber("1");
	
	public NaturalNumber(BigInteger numberCore) throws NaturalNumberException
	{
		if(numberCore.compareTo(biZero)<0)throw new NaturalNumberException("Can't work with negative Integers.");

		this.numberCore = numberCore;
	}

	public NaturalNumber(int numberCore) throws NaturalNumberException
	{
		if(numberCore<0)throw new NaturalNumberException("Can't work with negative Integers.");
				
		this.numberCore = new BigInteger(""+numberCore);
	}
	
	private NaturalNumber(String s)
	{
		this.numberCore = new BigInteger(s);
	}

	@Override
	public boolean hasNeutralZero()
	{
		return true;
	}

	@Override
	public boolean hasNeutralOne() 
	{
		return true;
	}

	@Override
	public NaturalNumber getNeutralZero() throws NaturalNumberException
	{
		return new NaturalNumber(0);
	}

	@Override
	public NaturalNumber getNeutralOne() throws NaturalNumberException
	{
		return new NaturalNumber(1);
	}

	@Override
	public NaturalNumber multiplyWith(NaturalNumber e) throws NaturalNumberException
	{
		
		if(this.equals(zero)||e.equals(zero))return zero;
		BigInteger p = numberCore.multiply(e.getNumberCore());
		
		return new NaturalNumber(p);
	}

	@Override
	public NaturalNumber add(NaturalNumber e) throws NaturalNumberException 
	{
		BigInteger s = numberCore.add(e.getNumberCore());
		
		return new NaturalNumber(s);
	}
	
	public NaturalNumber subtract(NaturalNumber e) throws NaturalNumberException
	{
		
		BigInteger s = numberCore.subtract(e.getNumberCore());
		
		return new NaturalNumber(s);//Throws Exception if e is greater Then this.
	}
	
	/**Careful is rounded down like integer division.*/
	@Override
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
		if(this.getNumberCore().compareTo(n.numberCore)>0)return true;
		
		return false;
	}
	
	public boolean isSmallerThen(NaturalNumber n)
	{
		if(this.getNumberCore().compareTo(n.numberCore)<0)return true;
		
		return false;
	}

	public BigInteger getNumberCore()
	{
		return numberCore;
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
	    
	    if(!other.getNumberCore().equals(numberCore))return false;
	    
	    return true;
	}
	
	public double doubleApproximation()
	{
		return this.numberCore.doubleValue();
	}
	
	public int integerApproximation()
	{
		return this.numberCore.intValue();
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
			
			System.out.println("Shouldn't happen!");
			e.printStackTrace();
			return null;
		}
	}
}