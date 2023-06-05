package someMath;

import java.util.Objects;


public class ComplexNrDouble implements MultiplyableAndAddable<ComplexNrDouble>
{

	private final double real;
	private final double imaginary;
	
	public ComplexNrDouble(double real, double imaginary)
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
	
	public ComplexNrDouble getConjugate()
	{
		return new ComplexNrDouble(real, -imaginary);
	}

	@Override
	public ComplexNrDouble multiplyWith(ComplexNrDouble e) 
	{
		
		double newReal = this.real*e.real - this.imaginary*e.imaginary;
		double newImaginary = this.real*e.imaginary + this.imaginary*e.real;
		
		return new ComplexNrDouble(newReal, newImaginary);
	}

	public ComplexNrDouble divideBy(ComplexNrDouble e)
	{

		ComplexNrDouble fresh = this.multiplyWith(e.getConjugate());

		double divisor = e.multiplyWith(e.getConjugate()).real;

		
		return new ComplexNrDouble(fresh.real/divisor, fresh.imaginary/divisor);
	}
	
	@Override
	public ComplexNrDouble addWith(ComplexNrDouble e) 
	{
	
		return new ComplexNrDouble(this.real + e.real, this.imaginary + e.imaginary);
	}
	
	public double getRealPart() {return real;}
	
	public double getImaginaryPart() {return imaginary;}
	
	@Override
	public ComplexNrDouble subtractArg(ComplexNrDouble e) 
	{
		
		return new ComplexNrDouble(this.real - e.real, this.imaginary - e.imaginary);
	}
	
	public ComplexNrDouble polarRepresentation()
	{
		
		double sinus = (this.imaginary/this.amount());
		
		double alpha;
		if(Math.abs(this.amount())>0)alpha= Math.asin(this.imaginary/this.amount());
		else alpha = 0;
		
		double r = this.amount();
		
		if(this.imaginary>0&&this.real<0)
			alpha = Math.toRadians(180-Math.toDegrees(alpha));//2nd Quadrant
		if(this.imaginary<0&&this.real<0)
			alpha = Math.toRadians(-180-Math.toDegrees(alpha));//3rd Quadrant
		
		
		return new ComplexNrDouble(r, alpha);
	}
	
	public ComplexNrDouble logarithm()
	{
		return new ComplexNrDouble(Math.log(amount()), Arg());
	}
	
	public ComplexNrDouble fromPolarToGaussEbene()
	{
		
		double angle = imaginary;
		
		double degrees = Math.toDegrees(angle);
		
		double x = real*Math.cos(angle);
		double y = real*Math.sin(angle);
		
		return new ComplexNrDouble(x,y);
	}
	
	public ComplexNrDouble toThePowerOf(ComplexNrDouble exponent)
	{

		ComplexNrDouble loga = this.logarithm();
		ComplexNrDouble newExpo = loga.multiplyWith(exponent);
		
		System.out.println("New Expo: " + newExpo);
		
		double newReal = newExpo.real * Math.cos(newExpo.imaginary);
		double newAlpha = newExpo.real * Math.sin(newExpo.imaginary);
		
		return new ComplexNrDouble(newReal, newAlpha);
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
	public ComplexNrDouble getNeutralOne() 
	{
		return new ComplexNrDouble(1,0);
	}

	@Override
	public ComplexNrDouble getNeutralZero() 
	{
		return new ComplexNrDouble(0,0);
	}

	public int hashCode()
	{
		return Objects.hash(real, imaginary);
	}
	
	public boolean equals(Object obj)
	{
		if (obj == this) return true;
		
	    if (!(obj instanceof ComplexNrDouble)) return false;
	    
	    ComplexNrDouble other = (ComplexNrDouble)obj;
	    if(!((other.real== this.real)&&(other.imaginary==this.imaginary)))return false;
	    
	    return true;
	}
	
	public String toString()
	{
		
		if(imaginary<0) return real + " -i" + Math.abs(imaginary);
		
		return real + " + i" + imaginary;
	}

}