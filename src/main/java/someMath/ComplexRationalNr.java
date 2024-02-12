package someMath;

import static someMath.RationalNumber.*;


import java.util.Objects;

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
	public ComplexRationalNr subtract(ComplexRationalNr e) throws RNumException, NaturalNumberException, CloneNotSupportedException
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
	public ComplexRationalNr addWith(ComplexRationalNr e) throws NaturalNumberException, RNumException, CloneNotSupportedException
	{
		
		RationalNumber newReal = real.addWith(e.real);
		RationalNumber newImaginary = imaginary.addWith(e.imaginary);

		return new ComplexRationalNr(newReal, newImaginary);
	}

	@Override
	public ComplexRationalNr getNeutralZero() throws NaturalNumberException 
	{
		return zero;
	}

	@Override
	public ComplexRationalNr divideBy(ComplexRationalNr t) throws DivisionByZeroException, NaturalNumberException, RNumException, CloneNotSupportedException
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
	public ComplexRationalNr multiplyWith(ComplexRationalNr e) throws NaturalNumberException, RNumException, CloneNotSupportedException
	{
		
		RationalNumber realTimesReal = this.real.multiplyWith(e.real);
		RationalNumber imgTimesImg = this.imaginary.multiplyWith(e.imaginary);
		
		RationalNumber newReal = (realTimesReal).subtract(imgTimesImg);
		
		RationalNumber realTimesImg = this.real.multiplyWith(e.imaginary);
		RationalNumber imgTimesReal = this.imaginary.multiplyWith(e.real);
		
		RationalNumber newImaginary = realTimesImg.addWith(imgTimesReal);
		
		return new ComplexRationalNr(newReal, newImaginary);
	}

	@Override
	public ComplexRationalNr getNeutralOne() throws NaturalNumberException
	{
		return one;
	}

	public ComplexRationalNr getConjugate() throws RNumException, NaturalNumberException, CloneNotSupportedException
	{
		
		RationalNumber minusOne = rZero.subtract(rOne);
		
		return new ComplexRationalNr(real, imaginary.multiplyWith(minusOne));
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
		
		ComplexRationalNr pureReal = SmallTools.getNthRoot(multiplyWith(getConjugate()), 2);
		
		return pureReal.getRealPart();
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
		
		String output = "(" + this.real + " + " +this.imaginary + "i)";
		return output;
	}
}