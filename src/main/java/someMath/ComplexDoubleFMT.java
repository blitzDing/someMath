package someMath;

import java.util.Objects;

import javafx.util.Pair;

public class ComplexDoubleFMT implements MultiplyableAndAddable<ComplexDoubleFMT>
{

	private final double real;
	private final double imaginary;
	
	public ComplexDoubleFMT(double real, double imaginary)
	{
		
		this.real = real;
		this.imaginary = imaginary;
	}
	
	///Written with capital Letter because of Math Standards.
	//
	public double Arg()
	{
		return polarRepresentation().getImaginaryPart();
	}
	
	public double amount()
	{
		return Math.sqrt(real*real + imaginary*imaginary);
	}
	
	public ComplexDoubleFMT getConjugate()
	{
		return new ComplexDoubleFMT(real, -imaginary);
	}

	@Override
	public ComplexDoubleFMT multiplyWith(ComplexDoubleFMT e) 
	{
		return new 
				ComplexDoubleFMT(this.real*e.real-this.imaginary*e.imaginary, this.real*e.imaginary+this.imaginary*e.real);
	}

	public ComplexDoubleFMT divideBy(ComplexDoubleFMT e)
	{

		ComplexDoubleFMT fresh = this.multiplyWith(e.getConjugate());

		double divisor = e.multiplyWith(e.getConjugate()).real;

		
		return new ComplexDoubleFMT(fresh.real/divisor, fresh.imaginary/divisor);
	}
	
	@Override
	public ComplexDoubleFMT addWith(ComplexDoubleFMT e) 
	{
		return new ComplexDoubleFMT(this.real + e.real, this.imaginary + e.imaginary);
	}
	
	public double getRealPart() {return real;}
	
	public double getImaginaryPart() {return imaginary;}
	
	public String toString()
	{
		
		if(imaginary<0) return real + " -i" + Math.abs(imaginary);
		
		return real + " + i" + imaginary;
	}

	@Override
	public ComplexDoubleFMT getNeutralOne() 
	{
		return new ComplexDoubleFMT(1,0);
	}

	@Override
	public ComplexDoubleFMT getNeutralZero() 
	{
		return new ComplexDoubleFMT(0,0);
	}

	@Override
	public boolean hasNeutralOne() 
	{
		return true;
	}

	@Override
	public boolean hasNeutralZero()
	{
		return true;
	}

	@Override
	public ComplexDoubleFMT subtractArg(ComplexDoubleFMT e) 
	{
		return new ComplexDoubleFMT(this.real - e.real, this.imaginary - e.imaginary);
	}
	
	public ComplexDoubleFMT polarRepresentation()
	{
		
		double alpha = Math.asin(this.imaginary/this.amount());
		double r = amount();
		
		return new ComplexDoubleFMT(r, alpha);
	}
	
	public int hashCode()
	{
		return Objects.hash(real, imaginary);
	}
	
	public boolean equals(Object obj)
	{
		if (obj == this) return true;
		
	    if (!(obj instanceof ComplexDoubleFMT)) return false;
	    
	    ComplexDoubleFMT other = (ComplexDoubleFMT)obj;
	    if(!((other.real== this.real)&&(other.imaginary==this.imaginary)))return false;
	    
	    return true;
	}
}