package someMath;


import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static someMath.SmallNatural.*;

public class RationalNumber implements Cloneable, SubtractableAndDivideable<RationalNumber>
{

	public static final String divisionByZeroMsg = "Denominator can't be zero";
	public static final String displacedMinusChar = "Only one minus sign and please upfront.";
	public static final String maleFormedStringArgument = "maleformed String Argument";
	
	private int numerator;
	private int denominator;
	private int integerPart;
	
	private final boolean sign; //false means negative.

	public static final RationalNumber rZero = new RationalNumber("hey", 0, 0, 1);
	public static final RationalNumber rOne = new RationalNumber("hi", 1, 0, 1);
	
	public static final Pattern pattern = Pattern.compile("(\\-)?((\\d+\\s)|(\\d+$))?((\\-)?(\\d+)(/)(\\d+))?");
	

	public RationalNumber(int integerPart, int numerator, int denominator) throws RNumException, DivisionByZeroException, CollectionException, CloneNotSupportedException
	{
		
		if(denominator==0)throw new RNumException(divisionByZeroMsg);

		if(numerator<0&&denominator<0)
		{
			numerator = Math.abs(numerator);
			denominator = Math.abs(denominator);
		}

		int newNum = integerPart*denominator+numerator;

		boolean newNumNegative = (newNum<0);
		boolean denomNegative = (denominator<0);

		boolean newNumPositive = (newNum>0);
		boolean denomPositive = (denominator>0);

		boolean sameSame = (newNumNegative&&denomNegative)||(newNumPositive&&denomPositive);
		
		int newNumAbs = Math.abs(newNum);
		int denomAbs = Math.abs(denominator);
		
		int [] shortend = shortening(newNumAbs, denomAbs);
		
		if(sameSame)
		{
			
			this.integerPart = shortend[0];
			this.numerator = shortend[1];
			this.denominator = shortend[2];
			
			this.sign = !newNumNegative;//Includes Zero as Positive.
			
			return;
		}
		
		this.integerPart = -shortend[0];
		this.numerator = -shortend[1];
		this.denominator = shortend[2]; //denominator in a fleshed out instance never
										//smaller or equal Zero!
		
		this.sign = !newNumNegative;//Includes Zero as Positive.
	}
	
	//Default positive RN.
	public RationalNumber(int numerator, int denominator) throws RNumException, NaturalNumberException, DivisionByZeroException, CollectionException, CloneNotSupportedException
	{
		//The default.
		this(0, numerator, denominator);
	}

	public RationalNumber(String s) throws RNumException, DivisionByZeroException, CollectionException, CloneNotSupportedException
	{
		this(parseString(s)[0], parseString(s)[1], parseString(s)[2]);
	}

	//The only reason for this constructor are static 
	//constants like zero and one. Because the other
	//Constructors throw Exceptions. I made private 
	//because there are is no shortening. And no 
	//Calculating sign. So if ever there gone be 
	//more static constants be careful.
	private RationalNumber(String hey, int integerPart, int numerator, int denominator)
	{
		this.sign = (integerPart>=0);
		
		this.integerPart = integerPart;
		this.numerator = numerator;
		this.denominator = denominator;
	}

	//Careful please no negatives!!!
	private int[] shortening(int numerator, int denominator)
	{
		
		int[] theThreeParts = new int[3];
		theThreeParts[0] = 0;
		theThreeParts[1] = 0;
		theThreeParts[2] = 0;
		
		if(numerator<denominator)
		{
			theThreeParts[0] = 0;
			theThreeParts[1] = numerator;
			theThreeParts[2] = denominator;
			
			return theThreeParts;
		}
		
		int[] down = shortening(numerator-denominator, denominator);

		theThreeParts[0] = theThreeParts[0]+(down[0]+1);
		theThreeParts[1] =  down[1];
		theThreeParts[2] =  down[2];
			
		return theThreeParts;
		
	}
	
	private static int[] parseString(String s) throws RNumException
	{
		
		int theParts[] = new int[3];
		
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
			

			String fractionPart = matcher.group(5);
			String intPart = matcher.group(3);
			
			if(intPart!=null)integerPart = Integer.parseInt(intPart.trim());
			if(intPart!=null&&fractionPart==null)
			{

				theParts[1] = 0;
				theParts[2] = 1;

				if(firstMinus!=null)theParts[0] = -Integer.parseInt(intPart);
				else theParts[0] = -Integer.parseInt(intPart);
								
				return theParts;			
			}
			
			String numeratorString = matcher.group(7);
			numerator = Integer.parseInt(numeratorString);
			
			String denominatorString = matcher.group(9);
			denominator = Integer.parseInt(denominatorString);
			
			if(intPart==null&&fractionPart!=null)
			{
				
				theParts[0] = 0;

				theParts[2] = denominator;

				if(secondMinus!=null)theParts[1] = -numerator;
				else theParts[1] = numerator;

				return theParts;
			}
			
			if(intPart!=null&&fractionPart!=null)
			{
				
				theParts[2] = denominator;

				if(firstMinus!=null)theParts[0] = -Integer.parseInt(intPart);
				else theParts[0] = -Integer.parseInt(intPart);

				if(secondMinus!=null)theParts[1] = -numerator;
				else theParts[1] = numerator;
			}
		}
		
		throw new RNumException("Can' interpret String as Rational Number.");
	}
	
	public double doubleApproximation() throws NaturalNumberException, RNumException, DivisionByZeroException, CollectionException
	{

		int factor = -1;
		if(sign)factor = 1;
		
		Double integerPartAsDouble = (double)integerPart;
		Double numeratorAsDouble = (double)numerator;
		Double denominatorAsDouble = (double)denominator;
		
		return factor*(integerPartAsDouble + (numeratorAsDouble/denominatorAsDouble));
	}
	
	public int expand(int integer, int numerator, int denominator) throws NaturalNumberException
	{
		return integer*denominator+numerator;
	}
	
	public String expandedVersionToString() throws NaturalNumberException
	{
		int numerator = expand(this.integerPart, this.numerator, this.denominator);
		return numerator + "/" + this.denominator;
	}
	
	@Override
	public boolean hasNeutralOne()
	{
		return true;
	}

	@Override
	public RationalNumber multiplyWith(RationalNumber e) throws NaturalNumberException, RNumException, DivisionByZeroException, CollectionException, CloneNotSupportedException
	{
		
		if(this.equals(rZero)||e.equals(rZero))return rZero;
		
		int expandedNumeratorThis = expand(this.integerPart, this.numerator, this.denominator);
		int expandedNumeratorE    = expand(e.integerPart, e.numerator, e.denominator);
		int productOfNumerators = expandedNumeratorThis*expandedNumeratorE;
		int productOfDenominators = denominator*e.denominator;

		
		RationalNumber output = new RationalNumber(productOfNumerators, productOfDenominators);
	
		
		return output;
	}

	public RationalNumber divideBy(RationalNumber r) throws DivisionByZeroException, NaturalNumberException, RNumException, CollectionException, CloneNotSupportedException
	{
		
		if(r.equals(rZero))throw new DivisionByZeroException();
		
		if(this.equals(rZero))return rZero;
		
		int intR = r.integerPart;
		int numR = r.numerator;
		int denomR = r.denominator;
		
		int expandedNumerator = expand(intR, numR, denomR);

		RationalNumber reciprocal = new RationalNumber(r.denominator, expandedNumerator);
		RationalNumber output = this.multiplyWith(reciprocal);
		
		return output;
	}


	@Override
	public RationalNumber getNeutralOne() 
	{
		return rOne;
	}

	@Override
	public boolean hasNeutralZero() 
	{
		return true;
	}

	@Override
	public RationalNumber addWith(RationalNumber r) throws CloneNotSupportedException, NaturalNumberException, RNumException, DivisionByZeroException, CollectionException
	{
		
		if(this.equals(rZero))return r.clone();
		if(r.equals(rZero))return this.clone();

		int expandNum = expand(integerPart, numerator, denominator);
		int expandNumR = expand(r.integerPart, r.numerator, r.denominator);
		
		int newNum = expandNum*r.denominator + expandNumR*denominator;
		int newDenominator = r.denominator*denominator;

		return new RationalNumber(newNum, newDenominator);
	}
	
	public RationalNumber getAmount() throws RNumException, NaturalNumberException, DivisionByZeroException, CollectionException, CloneNotSupportedException
	{
		return new RationalNumber(Math.abs(integerPart), Math.abs(numerator), Math.abs(denominator));
	}

	@Override
	public RationalNumber getNeutralZero() 
	{
		return rZero;
	}

	@Override
	public RationalNumber subtract(RationalNumber e) throws RNumException, NaturalNumberException, CloneNotSupportedException, DivisionByZeroException, CollectionException 
	{
		
		RationalNumber minus = new RationalNumber(-e.integerPart, -e.numerator, -e.denominator);
		
		return this.addWith(minus);
	}
	
	public int hashCode()
	{
		return Objects.hash(integerPart, numerator, denominator);
	}

	public boolean isGreaterThen(RationalNumber other) throws NaturalNumberException, RNumException, DivisionByZeroException, CollectionException
	{
		
		boolean isEqual = (this.equals(other));
		if(isEqual)return false;
		
		if(this.equals(rZero)&&other.equals(rZero)) return false;
		if(sign&&!other.sign) return true;
		if(!sign&&other.sign) return false;

		if(sign==other.sign)//Both signs positve
		{
			if(integerPart>other.integerPart)return true;
			if(integerPart<other.integerPart) return false;

			//if it continues computing at this point it is clear the integerParts
			//are equal.

			if(denominator==other.denominator)return numerator>other.numerator;

			
			int numeratorThis = numerator*other.denominator;
			int numeratorOther = other.numerator*denominator;
			
			return numeratorThis>numeratorOther;
		}
		

		return false;
	}

	public boolean equals(Object obj)
	{
		
		if (obj == this) return true;
		
	    if (!(obj instanceof RationalNumber)) return false;
	    RationalNumber other = (RationalNumber)obj;
	    
	    
	    if(this.toString().trim().equals("-0")&&other.toString().trim().equals("0"))return true;
	    if(other.toString().trim().equals("-0")&&this.toString().trim().endsWith("0"))return true;
	    
	    if(!this.toString().equals(other.toString()))return false;
	    
	    return true;
	}

	public String toString()
	{
		
		if(numerator==0)return integerPart + "";
		if(integerPart==0) return "(" + numerator + "/" + denominator + ")";
		
		return "(" + integerPart + " + " + numerator + "/" + denominator + ")";
	}
	
	public RationalNumber clone() throws CloneNotSupportedException
	{
		try
		{
			return new RationalNumber(integerPart, numerator, denominator);
		}
		catch(DivisionByZeroException | RNumException | CollectionException e)
		{
			System.out.println("This shouldnt happen!");
			e.printStackTrace();
			return null;
		} 
	}
	
	public int getIntegerPart() throws NaturalNumberException
	{
		return integerPart;
	}

	public int getNumerator() throws NaturalNumberException
	{
		return numerator;
	}

	public int getDenominator() throws NaturalNumberException
	{
		return denominator;
	}
	
	public boolean getSign()
	{
		return sign;
	}
}