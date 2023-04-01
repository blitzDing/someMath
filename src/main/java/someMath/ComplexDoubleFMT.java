package someMath;

public class ComplexDoubleFMT implements MultiplyableAndAddable<ComplexDoubleFMT>
{

	private final double real;
	private final double imaginary;
	
	public ComplexDoubleFMT(double real, double imaginary)
	{
		
		this.real = real;
		this.imaginary = imaginary;
	}

	@Override
	public ComplexDoubleFMT multiplyWith(ComplexDoubleFMT e) 
	{
		return new 
				ComplexDoubleFMT(this.real*e.real-this.imaginary*e.imaginary, this.real*e.imaginary+this.imaginary*e.real);
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
	
	public boolean equals(Object obj)
	{
		if (obj == this) return true;
		
	    if (!(obj instanceof ComplexDoubleFMT)) return false;
	    
	    ComplexDoubleFMT other = (ComplexDoubleFMT)obj;
	    if(!((other.real== this.real)&&(other.imaginary==this.imaginary)))return false;
	    
	    return true;
	}
}