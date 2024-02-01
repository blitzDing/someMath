package someMath;

import java.awt.Point;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RationalNumber implements SubtractableAndDivideable<RationalNumber>
{

	public final int numerator;
	public final int denominator;
	public final int integerPart;
	public final boolean sign; //false means negative.
	public final Point pointVersion;
	
	public static final RationalNumber zero = new RationalNumber(0,1);
	public static final RationalNumber one = new RationalNumber(1,1);
	
	public static final Pattern pattern = Pattern.compile("(\\-)?((\\d+\\s)|(\\d+$))?((\\-)?(\\d+)(/)(\\d+))?");

	public RationalNumber(boolean sign, int integerPart, int numerator, int denominator)
	{

		if(integerPart<0)
			throw new IllegalArgumentException("Integer can't be lower than Zero. "
					+ "The Rational Number can be made negative Thru the Boolean part in the Constructor.");

		if((denominator==0)||(denominator<0))
		{
			System.out.println(denominator);
			throw new IllegalArgumentException("Denominator can't be eqaul or lower than zero. "
					+ "The Rational Number can be made negative Thru the Boolean part in the Constructor.");
		}
		
		if(numerator<0)
			throw new IllegalArgumentException("Numerator can't be lower than Zero. "
					+ "The Rational Number can be made negative Thru the Boolean part in the Constructor.");
		
		this.sign = sign;
		
		int[] shortend = shortening(numerator, denominator);

		this.integerPart = shortend[0]+integerPart;
		this.numerator   = shortend[1];
		this.denominator = shortend[2];
		
		this.pointVersion = expandedVersion();
	}
	
	//Default positive RN.
	public RationalNumber(int numerator, int denominator)
	{
		//The default.
		this(true, numerator, denominator);
	}
	
	public RationalNumber(boolean sign, int numerator, int denominator)
	{
		if(numerator<0)
			throw new IllegalArgumentException("Denominator can't be lower than Zero. "
					+ "Please address thru a Constructor with the Boolean as Front Argument.");

		if(denominator<=0)
			throw new IllegalArgumentException("Denominator can't be lower or equal than Zero. "
					+ "Please address thru a Constructor with the Boolean as Front Argument.");

		if(numerator==0)
		{
			this.integerPart = 0;
			this.numerator   = 0;
			this.denominator = 1;
		}
		else
		{
			
			int [] shortend = shortening(numerator, denominator);

			this.integerPart = shortend[0];
			this.numerator   = shortend[1];
			this.denominator = shortend[2];
		}
		
		this.sign = sign;
		this.pointVersion = expandedVersion();
	}
	
	public RationalNumber(String s)
	{
		
		String trimed = s.trim();
		Matcher matcher = pattern.matcher(trimed);
		
		int integerPart = 0;
		int numerator;
		int denominator;
		boolean sign;
		
		if(matcher.find())
		{
			String firstMinus = matcher.group(1);
			String secondMinus = matcher.group(6);
			
			if(firstMinus!=null&&secondMinus!=null) throw new IllegalArgumentException("Only one minus sign and please upfront.");
			
			if(firstMinus!=null||secondMinus!=null) sign = false;
			else sign = true;

			String fractionPart = matcher.group(5);
			String intPart = matcher.group(3);
			
			if(fractionPart==null&&intPart==null)throw new IllegalArgumentException("Don't now What that is.");
			if(intPart==null&&fractionPart==null)throw new IllegalArgumentException("Integer part and Fraction part are not there. Can't parse this.");
			if(intPart!=null)integerPart = Integer.parseInt(intPart.trim());
			if(intPart!=null&&fractionPart==null)
			{
				
				numerator = 0;
				denominator = 1;
				
				this.sign = sign;
				
				int[] shortend = shortening(numerator, denominator);

				this.integerPart = shortend[0]+integerPart;
				this.numerator   = shortend[1];
				this.denominator = shortend[2];
				
				this.pointVersion = expandedVersion();

				return;			
			}
			
			String numeratorString = matcher.group(7);
			numerator = Integer.parseInt(numeratorString);
			
			String denominatorString = matcher.group(9);
			denominator = Integer.parseInt(denominatorString);
						
			this.sign = sign;
			
			if(intPart==null&&fractionPart!=null)
			{

				int[] shortend = shortening(numerator, denominator);

				this.integerPart = shortend[0];
				this.numerator   = shortend[1];
				this.denominator = shortend[2];
				
				this.pointVersion = expandedVersion();
				
				return;
			}
			
			int[] shortend = shortening(numerator, denominator);

			this.integerPart = shortend[0]+integerPart;
			this.numerator   = shortend[1];
			this.denominator = shortend[2];
			
			this.pointVersion = expandedVersion();

			return;			
		}
		
		throw new IllegalArgumentException("Can' interpret String as Rational Number.");
	}
	
	private int[] shortening(int numerator, int denominator)
	{
		
		int[] theThreeParts = new int[3];
		
		if(numerator<denominator)
		{
			theThreeParts[0]=0;
			theThreeParts[1]=numerator;
			theThreeParts[2]=denominator;
		}
		
		if(numerator==denominator)
		{
			theThreeParts[0]=1;
			theThreeParts[1]=0;
			theThreeParts[2]=1;
		}
		
		if(numerator>denominator)
		{
			int[] down = shortening(numerator-denominator, denominator);
			
			theThreeParts[0] += down[0]+1;
			theThreeParts[1] =  down[1];
			theThreeParts[2] =  down[2];
		}
		
		return theThreeParts;
	}
	
	public double doubleApproximation()
	{

		int factor = -1;
		if(sign)factor = 1;
		Double integerPartAsDouble = (double) integerPart;
		Double numeratorAsDouble = (double) numerator;
		Double denominatorAsDouble = (double) denominator;
		
		return factor*(integerPartAsDouble + (numeratorAsDouble/denominatorAsDouble));
	}

	public Point expandedVersion()
	{
		
		int newNumerator = numerator+denominator*integerPart;
		
		return new Point(newNumerator, denominator);
	}

	public String expandedVersionToString()
	{
		return pointVersion.x + "/" + pointVersion.y;
	}
	
	@Override
	public boolean hasNeutralOne()
	{
		return true;
	}

	@Override
	public RationalNumber multiplyWith(RationalNumber e)
	{
		
		int expandedNumeratorThis = (integerPart*denominator)+numerator;
		int expandedNumeratorE    = (e.integerPart*e.denominator)+e.numerator;
		
		boolean bothNegative = (!e.sign&&!this.sign);
		boolean bothPositive = e.sign&&this.sign;
		
		boolean sign = (bothNegative||bothPositive);
		
		int productOfNumerators = expandedNumeratorThis*expandedNumeratorE;
		
		return new RationalNumber(sign, productOfNumerators, denominator*e.denominator);
	}

	public RationalNumber divideBy(RationalNumber e) throws DivisionByZeroException
	{
		
		if(e.equals(zero))throw new DivisionByZeroException();
		
		if(this.equals(zero))return zero;
		
		int expandedNumerator = (e.integerPart*e.denominator)+e.numerator;
		boolean signOfE = e.sign;
		
		RationalNumber reciprocal = new RationalNumber(signOfE, e.denominator, expandedNumerator);
		RationalNumber output = this.multiplyWith(reciprocal);
		
		return output;
	}
	
	@Override
	public RationalNumber getNeutralOne() 
	{
		return one;
	}

	@Override
	public boolean hasNeutralZero() 
	{
		return true;
	}

	@Override
	public RationalNumber addWith(RationalNumber e) 
	{
	
		if(this.sign==e.sign)
		{
			int newIntegerPart = this.integerPart + e.integerPart;
			int newNotShortendNumerator = (this.numerator*e.denominator) + (e.numerator*this.denominator);
			int newNotShortendDenominator = this.denominator*e.denominator;
		
			return new RationalNumber(sign, newIntegerPart, newNotShortendNumerator, newNotShortendDenominator);
		}
		
		RationalNumber belowZero = null;
		RationalNumber aboveZero = null;
		
		if(!e.sign)
		{
			belowZero = e;
			aboveZero = this;
		}
		
		if(!this.sign)
		{
			belowZero = this;
			aboveZero = e;
		}
		
		int newNumeratorAbove = belowZero.denominator*((aboveZero.integerPart*aboveZero.denominator)+aboveZero.numerator);
		int newNumeratorBelow = aboveZero.denominator*((belowZero.integerPart*belowZero.denominator)+belowZero.numerator); 
		
		int newDenominator = aboveZero.denominator*belowZero.denominator;
		int newNumerator = newNumeratorAbove-newNumeratorBelow;
		
		boolean sign = (newNumeratorAbove<newNumeratorBelow);
		
		return new RationalNumber(sign, newNumerator, newDenominator);
	}


	@Override
	public RationalNumber getNeutralZero() 
	{
		return zero;
	}

	@Override
	public RationalNumber subtract(RationalNumber e) 
	{
		
		return new RationalNumber(!e.sign, e.integerPart, e.numerator, e.denominator);
	}

	public int hashCode()
	{
		return Objects.hash(integerPart, numerator, denominator);
	}

	public boolean isGreaterThen(RationalNumber other)
	{
		
		if(this.equals(zero)&&other.equals(zero)) return false;
		if(sign&&!other.sign) return true;
		if(!sign&&other.sign) return false;

		if(sign&&other.sign)
		{
			if(integerPart>other.integerPart) return true;
			if(integerPart<other.integerPart) return false;

			//if it continues computing at this point it is clear the integerParts
			//are equal.

			if((denominator==other.denominator)&&(numerator>other.numerator)) return true;

			//if it continues computing at this point it is clear the denominators 
			//are different and the integer Parts are equal.
		
			int f1 = numerator*other.denominator;
			int f2 = other.numerator*denominator;
		
			if(f1>f2) return true;
		}
		
		if(!sign&&!other.sign)
		{
			if(integerPart<other.integerPart) return true;
			if(integerPart>other.integerPart) return false;

			//if it continues computing at this point it is clear the integerParts
			//are equal.

			if((denominator==other.denominator)&&(numerator<other.numerator)) return true;

			//if it continues computing at this point it is clear the denominators 
			//are different and the integer Parts are equal.
		
			int f1 = numerator*other.denominator;
			int f2 = other.numerator*denominator;
		
			if(f1<f2) return true;
		}

		return false;
	}

	public boolean equals(Object obj)
	{
		if (obj == this) return true;
		
	    if (!(obj instanceof RationalNumber)) return false;
	    RationalNumber other = (RationalNumber)obj;
	    
	    if(!(other.integerPart == this.integerPart)) return false;
	    if(!(other.numerator   == this.numerator))   return false;
	    if(!(other.denominator == this.denominator)) return false;
	
	    return true;
	}

	public String toString()
	{
		char c = '-';
		String output = "";
		if(!sign)output = c+output;
		
		if(numerator == 0)	return output + integerPart;
		if(integerPart == 0)  return output + numerator + "/" + denominator;

		return output + integerPart + " " + numerator + "/" + denominator;
	}
}