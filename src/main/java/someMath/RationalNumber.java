package someMath;


import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import someMath.exceptions.CollectionException;
import someMath.exceptions.DivisionByZeroException;
import someMath.exceptions.NaturalNumberException;
import someMath.exceptions.RNumException;


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
	
	public static final String vorzeichen = "([+-])";
	public static final String oVorzeichen = vorzeichen + "?";
	public static final String ciphers = "(\\d+)";
	public static final String space = "(\\s+)";
	public static final String oSpace = space + "?";
	public static final String bruchStrich = "(/)";

	public static final String intPart =   "(" + oVorzeichen + ciphers + ")";
	public static final String oIntPart = intPart + "?";

	public static final String fractional = 
			"(" + oVorzeichen + ciphers + bruchStrich + oVorzeichen + ciphers + ")";
	public static final String sFractional = 
			"(" + vorzeichen + ciphers + bruchStrich + oVorzeichen + ciphers + ")";
			
	public static final String oFractional = fractional + "?";
	
	public static final Pattern patternCompound = Pattern.compile(intPart + sFractional);
	public static final Pattern patternIntOnly = Pattern.compile(intPart);
	public static final Pattern patternFracOnly = Pattern.compile(fractional);
	

	public RationalNumber(int integerPart, int numerator, int denominator) throws RNumException, DivisionByZeroException, CollectionException, CloneNotSupportedException
	{
		
		if(denominator==0)throw new RNumException(divisionByZeroMsg);

		int newNum = integerPart*denominator+numerator;

		int [] shortend = shortening(newNum, denominator);
				
		this.integerPart = shortend[0];
		this.numerator = shortend[1];
		this.denominator = shortend[2]; //denominator in a fleshed out instance never
										//smaller or equal Zero!
		
		this.sign = !(integerPart<0||(integerPart<=0&&numerator<0));//Includes Zero as Positive.
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

	private int[] shortening(int numerator, int denominator)
	{
		
		int[] theThreeParts = new int[3];
		theThreeParts[0] = 0;
		theThreeParts[1] = 0;
		theThreeParts[2] = 0;
		
		int numAbs = Math.abs(numerator);
		int denomAbs = Math.abs(denominator);
		
		if(numAbs<denomAbs)
		{
			theThreeParts[0] = 0;
			theThreeParts[1] = numerator/SmallTools.gcd(numerator, denominator);
			theThreeParts[2] = denominator/SmallTools.gcd(numerator, denominator);
			
			return theThreeParts;
		}
		
		int[] down = shortening(numAbs-denomAbs, denomAbs);

		theThreeParts[0] = theThreeParts[0]+(down[0]+1);
		theThreeParts[1] =  down[1];
		theThreeParts[2] =  down[2];
		
		boolean sameSame = (numerator<0&&denominator<0)||(numerator>0&&denominator>0);
		if(sameSame)return theThreeParts;
		else
		{
			
			theThreeParts[0] = -theThreeParts[0];
			theThreeParts[1] = -theThreeParts[1];
			
			return theThreeParts;
		}
	}
	
	/*
	private static void displayGroups(String s, Matcher matcher)
	{
		
		System.out.println("Groups in String s: " + s);
		int i = matcher.groupCount();
		for(int m=0;m<i+1;m++)System.out.println("GroupNr: "+ m + " Group: " + matcher.group(m));
	}
	*/

	private static int[] parseString(String s) throws RNumException
	{
		
		int theParts[] = new int[3];
		
		String trimed = s.trim();
		
		Matcher compoundMatcher = patternCompound.matcher(trimed);
		Matcher intMatcher = patternIntOnly.matcher(trimed);
		Matcher fracMatcher = patternFracOnly.matcher(trimed);
				
		if(compoundMatcher.matches())
		{
			
			//displayGroups(s, compoundMatcher);
			System.out.println("Int+Frac");

			//String firstSign = compoundMatcher.group(2);
			String secondSign = compoundMatcher.group(5);
			String thirdSign = compoundMatcher.group(8);

			if(secondSign==null)throw new RNumException("Missing plus or minus Char.");
				
			theParts[0] = Integer.parseInt(compoundMatcher.group(1));
			
			int numAbs = Integer.parseInt(compoundMatcher.group(6));
			int num = numAbs;
			if(secondSign.equals("-"))num = -numAbs;
						
			int denomAbs = Integer.parseInt(compoundMatcher.group(9));
			int denom = denomAbs;
			if(thirdSign!=null&&thirdSign.equals("-"))denom = -denomAbs;
				

			theParts[1] = num;
			theParts[2] = denom;

			return theParts;
		}			
	
		if(intMatcher.matches())
		{
			
			//displayGroups(s, intMatcher);
			System.out.println("Int only");

			int n = Integer.parseInt(s);
			theParts[0] = n;
			theParts[1] = 0;
			theParts[2] = 1;
				
			return theParts;
		}

		if(fracMatcher.matches())
		{
			
			String firstSign = fracMatcher.group(2);
			String secondSign = fracMatcher.group(5);

			//displayGroups(s, fracMatcher);
			System.out.println("Frac only");

			int numAbs = Integer.parseInt(fracMatcher.group(3));
			int num = numAbs;
			if((firstSign!=null)&&firstSign.equals("-"))
			{

				System.out.println("Switch 1st Sign");
				num = -numAbs;
			}
					
			int denomAbs = Integer.parseInt(fracMatcher.group(6));
			int denom = denomAbs;
			if((secondSign!=null)&&secondSign.equals("-"))
			{
				System.out.println("Switch 2st Sign");
				denom = -denomAbs;
			}

			theParts[0] = 0;
			theParts[1] = num;
			theParts[2] = denom;
		
			return theParts;
		}
		
		throw new RNumException("Can't parse the String.");
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
	public RationalNumber add(RationalNumber r) throws CloneNotSupportedException, NaturalNumberException, RNumException, DivisionByZeroException, CollectionException
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
		
		return this.add(minus);
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

	public int hashCode()
	{
		return Objects.hash(toString());
	}
	
	public boolean equals(Object obj)
	{
		if(obj==null)return false;
		
		if (obj == this) return true;
		
	    if (!(obj instanceof RationalNumber)) return false;
	    RationalNumber other = (RationalNumber)obj;
	    
	    
	    if(this.toString().trim().equals("-0")&&other.toString().trim().equals("0"))return true;
	    if(other.toString().trim().equals("-0")&&this.toString().trim().equals("0"))return true;
	    
	    return (this.toString().equals(other.toString()));
	}

	public String toString()
	{
		
		if(numerator==0)return integerPart + "";
		if(integerPart==0) return "(" + numerator + "/" + denominator + ")";
		
		if(numerator<0) return "(" + integerPart  + numerator + "/" + denominator + ")";
		else return "(" + integerPart + " + " + numerator + "/" + denominator + ")";
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