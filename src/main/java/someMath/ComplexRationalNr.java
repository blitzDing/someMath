package someMath;

import static someMath.RationalNumber.*;


import java.util.Objects;

import someMath.exceptions.CollectionException;
import someMath.exceptions.DivisionByZeroException;
import someMath.exceptions.NaturalNumberException;
import someMath.exceptions.RNumException;

public class ComplexRationalNr implements SubtractableAndDivideable<ComplexRationalNr>
{

	public static final ComplexRationalNr zero = new ComplexRationalNr(rZero, rZero);
	
	public static final ComplexRationalNr one = new ComplexRationalNr(rOne, rOne);


	private final RationalNumber real;
	private final RationalNumber imaginary;


	public ComplexRationalNr(RationalNumber real, RationalNumber imaginary)
	{
		
		this.real = real;
		this.imaginary = imaginary;
	}
	
	@Override
	public ComplexRationalNr subtract(ComplexRationalNr e) throws RNumException, NaturalNumberException, CloneNotSupportedException, DivisionByZeroException, CollectionException
	{
		
		RationalNumber newReal = real.subtract(e.real);
		RationalNumber newImaginary = imaginary.subtract(e.imaginary);
		
		return new ComplexRationalNr(newReal, newImaginary);
	}

	@Override
	public boolean hasNeutralZero() 
	{
		return true;
	}

	@Override
	public ComplexRationalNr add(ComplexRationalNr e) throws NaturalNumberException, RNumException, CloneNotSupportedException, DivisionByZeroException, CollectionException
	{
		
		RationalNumber newReal = real.add(e.real);
		RationalNumber newImaginary = imaginary.add(e.imaginary);
		
		return new ComplexRationalNr(newReal, newImaginary);
	}

	@Override
	public ComplexRationalNr getNeutralZero() throws NaturalNumberException 
	{
		return zero;
	}

	@Override
	public ComplexRationalNr divideBy(ComplexRationalNr t) throws DivisionByZeroException, NaturalNumberException, RNumException, CloneNotSupportedException, CollectionException
	{
		if(t.equals(zero))throw new DivisionByZeroException();
		if(this.equals(zero))return zero;
		
		ComplexRationalNr fresh = this.multiplyWith(t.getConjugate());
		RationalNumber divisor = t.multiplyWith(t.getConjugate()).real;

		RationalNumber newReal = fresh.real.divideBy(divisor);
		RationalNumber newImaginary = fresh.imaginary.divideBy(divisor);
		
		return new ComplexRationalNr(newReal, newImaginary);
	}

	@Override
	public boolean hasNeutralOne()
	{
		return true;
	}

	@Override
	public ComplexRationalNr multiplyWith(ComplexRationalNr e) throws NaturalNumberException, RNumException, CloneNotSupportedException, DivisionByZeroException, CollectionException
	{
		
		RationalNumber realTimesReal = this.real.multiplyWith(e.real);
		RationalNumber imgTimesImg = this.imaginary.multiplyWith(e.imaginary);
		
		RationalNumber newReal = (realTimesReal).subtract(imgTimesImg);
		
		RationalNumber realTimesImg = this.real.multiplyWith(e.imaginary);
		RationalNumber imgTimesReal = this.imaginary.multiplyWith(e.real);
		
		RationalNumber newImaginary = realTimesImg.add(imgTimesReal);
		
		return new ComplexRationalNr(newReal, newImaginary);
	}

	@Override
	public ComplexRationalNr getNeutralOne() throws NaturalNumberException
	{
		return one;
	}

	public ComplexRationalNr getConjugate() throws RNumException, NaturalNumberException, CloneNotSupportedException, DivisionByZeroException, CollectionException
	{
		
		RationalNumber minusOne = rZero.subtract(rOne);
		RationalNumber newImaginary = imaginary.multiplyWith(minusOne);

		return new ComplexRationalNr(real, newImaginary);
	}
	
	public RationalNumber getRealPart()
	{
		return real;
	}
	
	public RationalNumber getImaginaryPart()
	{
		return imaginary;
	}
	
	public RationalNumber getAmount() throws NaturalNumberException, RNumException, CloneNotSupportedException, IllegalArgumentException, DivisionByZeroException, CollectionException
	{

		if(imaginary.equals(rZero))return real;
		if(real.equals(rZero))return imaginary;
		
		RationalNumber a2 = real.multiplyWith(real);
		RationalNumber b2 = imaginary.multiplyWith(imaginary);
		RationalNumber sum = a2.add(b2);
		
		System.out.println("Trying to find root of " + sum);
		RationalNumber pureReal = SmallTools.getNthRoot(sum, 2);
		
		return pureReal;
	}
		
	public int hashCode()
	{
		return Objects.hash(real, imaginary);
	}
	
	public boolean equals(Object obj)
	{
		
		if (obj == this) return true;
		
	    if (!(obj instanceof ComplexNrDouble)) return false;
	    
	    ComplexRationalNr other = (ComplexRationalNr)obj;
	    if(!((other.real.equals(this.real))&&(other.imaginary.equals(this.imaginary))))return false;

		return true;
	}
	
	public String toString()
	{
		
		try
		{
			boolean notSmallerThanZero = imaginary.isGreaterThen(rZero)||imaginary.equals(rZero);
			if(notSmallerThanZero)return "(" + this.real + "+" +this.imaginary + "i)";
			else 
			{
				
				RationalNumber negativImg = new RationalNumber(-1,0,1).multiplyWith(this.imaginary);
				
				return "(" + this.real + "-" + negativImg + "i)";
			}
		}
		catch (NaturalNumberException | RNumException | DivisionByZeroException | CollectionException | CloneNotSupportedException e) 
		{
			System.out.println("WTF how could this happen!");
			e.printStackTrace();
		}
		
		return "Something went wrong";
	}
}