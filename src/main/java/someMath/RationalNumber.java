package someMath;


import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.util.Pair;

import static someMath.SmallNatural.*;

public class RationalNumber implements Cloneable, SubtractableAndDivideable<RationalNumber>
{

	public static final String divisionByZeroMsg = "Denominator can't be zero";
	public static final String displacedMinusChar = "Only one minus sign and please upfront.";
	public static final String maleFormedStringArgument = "maleformed String Argument";
	private SmallNatural numerator;
	private SmallNatural denominator;
	private SmallNatural integerPart;
	public final boolean sign; //false means negative.
	
	private static int upperEndOfCiphers = 4;

	public static final RationalNumber rZero = new RationalNumber("hey", true, snZero, snZero, snOne);
	public static final RationalNumber rOne = new RationalNumber("hi", true, snZero, snOne, snOne);
	
	public static final Pattern pattern = Pattern.compile("(\\-)?((\\d+\\s)|(\\d+$))?((\\-)?(\\d+)(/)(\\d+))?");
	
	//Remember: SmallNatural can't be lower than Zero!!!
	public RationalNumber(SmallNatural integerPart, SmallNatural numerator, SmallNatural denominator) throws RNumException, NaturalNumberException, DivisionByZeroException, CollectionException, CloneNotSupportedException
	{
		this(true, integerPart, numerator, denominator);
	}

	public RationalNumber(boolean sign, SmallNatural integerPart, SmallNatural numerator, SmallNatural denominator)throws RNumException, NaturalNumberException, DivisionByZeroException, CollectionException, CloneNotSupportedException
	{
		
		if(denominator.equals(snZero))
		{
			System.out.println(denominator);
			throw new RNumException(divisionByZeroMsg);
		}
				
		this.sign = sign;
		
		int oldNum = numerator.getNumberCore();
		int oldDenom = denominator.getNumberCore();
		
		int[] shortend = shortening(oldNum, oldDenom);

		int newInteger = shortend[0] + integerPart.getNumberCore();
		int newNumerator   = shortend[1];
		int newDenominator = shortend[2];
		
		this.integerPart = new SmallNatural(newInteger);
		this.numerator = new SmallNatural(newNumerator);
		this.denominator = new SmallNatural(newDenominator);
	}
	
	//Default positive RN.
	public RationalNumber(SmallNatural numerator, SmallNatural denominator) throws RNumException, NaturalNumberException, DivisionByZeroException, CollectionException, CloneNotSupportedException
	{
		//The default.
		this(true, numerator, denominator);
	}
	
	public RationalNumber(boolean sign, SmallNatural numerator, SmallNatural denominator) throws RNumException, NaturalNumberException, DivisionByZeroException, CollectionException, CloneNotSupportedException
	{
		this(sign, snZero, numerator, denominator);
	}
	
	public RationalNumber(String s) throws RNumException, NumberFormatException, NaturalNumberException, DivisionByZeroException, CollectionException, CloneNotSupportedException
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
			
			if(firstMinus!=null&&secondMinus!=null) throw new RNumException(displacedMinusChar);
			
			if(firstMinus!=null||secondMinus!=null) sign = false;
			else sign = true;

			String fractionPart = matcher.group(5);
			String intPart = matcher.group(3);
			
			if(fractionPart==null&&intPart==null)throw new RNumException(maleFormedStringArgument);
			if(intPart!=null)integerPart = Integer.parseInt(intPart.trim());
			if(intPart!=null&&fractionPart==null)
			{
				
				numerator = 0;
				denominator = 1;
				
				this.sign = sign;

				
				int newIntegerPart = integerPart;

				this.integerPart = new SmallNatural(newIntegerPart);
				this.numerator   = new SmallNatural(numerator);
				this.denominator = new SmallNatural(denominator);

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

				this.integerPart = new SmallNatural(shortend[0]);
				this.numerator   = new SmallNatural(shortend[1]);
				this.denominator = new SmallNatural(shortend[2]);
				
				return;
			}
			
			int[] shortend = shortening(numerator, denominator);

			int newIntegerPart = shortend[0] + integerPart;
			int newNumerator   = shortend[1];
			int newDenominator = shortend[2];
			
			this.integerPart = new SmallNatural(newIntegerPart);
			this.numerator = new SmallNatural(newNumerator);
			this.denominator = new SmallNatural(newDenominator);
			
			return;
		}
		
		throw new IllegalArgumentException("Can' interpret String as Rational Number.");
	}
	
	//Good that NaturalNumber can't be lower than Zero!!!
	//The only reason for this constructor are static 
	//constants like zero and one. Because the other
	//Constructors throw Exceptions. I made private 
	//because there are is no shortening. So if ever
	//there gone be more static constants be careful.
	private RationalNumber(String hey, boolean sign, SmallNatural integerPart, SmallNatural numerator, SmallNatural denominator)
	{
		this.sign = sign;
		this.integerPart = integerPart;
		this.numerator = numerator;
		this.denominator = denominator;
	}

	private int[] shortening(int numerator, int denominator) throws NaturalNumberException, DivisionByZeroException, CollectionException, RNumException, CloneNotSupportedException
	{
		

		int[] theThreeParts = new int[3];
		theThreeParts[0] = 0;
		theThreeParts[1] = 0;
		theThreeParts[2] = 0;
		
		if(numerator<denominator)
		{
			theThreeParts[0]= 0;
						
			int grtstcmnDiv = SmallTools.gcd(numerator, denominator);
			int newNumerator = numerator/(grtstcmnDiv);
			theThreeParts[1]= newNumerator;
			int newDenominator = denominator/(grtstcmnDiv);
			theThreeParts[2]= newDenominator;
			
			return theThreeParts;
		}
		
		if(numerator==denominator)
		{
			theThreeParts[0]=1;
			theThreeParts[1]=0;
			theThreeParts[2]=1;

			return theThreeParts;
		}
		
		if(numerator>denominator)
		{
			int[] down = shortening(numerator-denominator, denominator);
			
			
			theThreeParts[0] = theThreeParts[0]+(down[0]+1);
			theThreeParts[1] =  down[1];
			theThreeParts[2] =  down[2];
			
			return theThreeParts;
		}
		
		throw new RNumException("Shhooout");
	}
	
	public double doubleApproximation() throws NaturalNumberException, RNumException, DivisionByZeroException, CollectionException
	{

		int factor = -1;
		if(sign)factor = 1;
		
		Double integerPartAsDouble = (double)integerPart.getNumberCore();
		Double numeratorAsDouble = (double)numerator.getNumberCore();
		Double denominatorAsDouble = (double)denominator.getNumberCore();
		
		return factor*(integerPartAsDouble + (numeratorAsDouble/denominatorAsDouble));
	}
	
	public Pair<Integer, Integer> expand() throws NaturalNumberException
	{
		
		int integer = integerPart.getNumberCore();
		int num = numerator.getNumberCore();
		int denom = denominator.getNumberCore();
		
		int newNumerator = integer*denom+num;
		
		return new Pair<>(newNumerator, denominator.getNumberCore());
	}
	
	public String expandedVersionToString() throws NaturalNumberException
	{
		return expand().getKey() + "/" + expand().getValue();
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
		
		int expandedNumeratorThis = expand().getKey();
		int expandedNumeratorE    = e.expand().getKey();
		
		boolean bothNegative = (!e.sign&&!this.sign);
		boolean bothPositive = e.sign&&this.sign;
		
		boolean sign = (bothNegative||bothPositive);
		
		int productOfNumerators = expandedNumeratorThis*expandedNumeratorE;
		int denomThis = denominator.getNumberCore();
		int denomE = e.denominator.getNumberCore();
		int productOfDenominators = denomThis*denomE;
		
		int g = SmallTools.gcd(productOfNumerators, productOfDenominators);
		int shortNumerator = productOfNumerators/g;
		int shortDenominator = productOfDenominators/g;
		
		SmallNatural newNumerator = null;
		SmallNatural newDenominator = null;
		
		double div = ((double)shortDenominator/(double)shortNumerator);
		
		//if ratio is to big meaning denominator is much bigger
		//then numerator. this clause takes care of it.
		if(div>(double)SmallNatural.max)
		{
			newNumerator = snOne;
			newDenominator = new SmallNatural(SmallNatural.max);

			return new RationalNumber(sign, newNumerator, newDenominator);
		}
		

		double div2 = ((double)shortNumerator)/((double)shortDenominator);
		
		if(div2>(double)SmallNatural.max)
		{
			int newInteger = SmallNatural.max;
			
			SmallNatural maxInt = new SmallNatural(newInteger);
			newNumerator = snZero;
			newDenominator = snOne;
			
			return new RationalNumber(sign, maxInt, newNumerator, newDenominator);
		}

		int theThreeParts [] = shortening(shortNumerator, shortDenominator);

		int newIntegerInt = theThreeParts[0];
		boolean integerPartBig = (newIntegerInt>SmallNatural.max);
		if(integerPartBig)throw new RNumException("IntegerPart out of Bounds.");
		
		int newNumeratorInt = theThreeParts[1];
		int newDenominatorInt = theThreeParts[2];
		
		boolean numeratorBig = (newNumeratorInt>SmallNatural.max);
		boolean denominatorBig = (newDenominatorInt>SmallNatural.max);

		if(numeratorBig||denominatorBig)
		{
			
			int ciphersOfNum = SmallTools.dezimalstellenVonInt(newNumeratorInt);
			int ciphersOfDenom = SmallTools.dezimalstellenVonInt(newDenominatorInt);
			
			int maxCiphers = SmallTools.dezimalstellenVonInt(SmallNatural.max);
		
			int diff1 = maxCiphers-ciphersOfNum;
			int diff2 = maxCiphers-ciphersOfDenom;
			
			double factor1 = Math.pow(10, diff1);
			double factor2 = Math.pow(10, diff2);
			
			double actualFactor = factor1;
			if(diff2<diff1)actualFactor = factor2;
			
			//TODO:Ta-da incomplete!

		}

		newNumerator = new SmallNatural(shortNumerator);
		newDenominator = new SmallNatural(shortDenominator);

		RationalNumber output = new RationalNumber(sign, newNumerator, newDenominator);
	
		
		return output;
	}

	public RationalNumber divideBy(RationalNumber r) throws DivisionByZeroException, NaturalNumberException, RNumException, CollectionException, CloneNotSupportedException
	{
		
		if(r.equals(rZero))throw new DivisionByZeroException();
		
		if(this.equals(rZero))return rZero;
		
		int intR = r.integerPart.getNumberCore();
		int numR = r.numerator.getNumberCore();
		int denomR = r.denominator.getNumberCore();
		
		int expandedNumerator = intR*denomR+numR;

		SmallNatural newNumerator = new SmallNatural(expandedNumerator);
		
		RationalNumber reciprocal = new RationalNumber(r.sign, r.denominator, newNumerator);
		RationalNumber output = 
				this.multiplyWith(reciprocal);
		
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
	public RationalNumber addWith(RationalNumber r) throws NaturalNumberException, RNumException, CloneNotSupportedException, DivisionByZeroException, CollectionException 
	{
		
		if(this.equals(rZero))return r;
		if(r.equals(rZero))return this;

		if(this.sign==r.sign)
		{
			
			int intThis = integerPart.getNumberCore();
			int intR = r.integerPart.getNumberCore();
			int numThis = numerator.getNumberCore();
			int numR = r.numerator.getNumberCore();
			int denomThis = denominator.getNumberCore();
			int denomR = r.denominator.getNumberCore();
			
			int newIntegerPart = intThis+intR;
			int newNotShortendNumerator = numThis*denomR+numR*denomThis;
			int newNotShortendDenominator = denomThis*denomR;

			int g = SmallTools.gcd(newNotShortendNumerator, newNotShortendDenominator);
			int shortendNum = newNotShortendNumerator/g;
			int shortendDenom = newNotShortendDenominator/g;
			
			SmallNatural newInteger = new SmallNatural(newIntegerPart);
			SmallNatural newNumerator = new SmallNatural(shortendNum);
			SmallNatural newDenominator = new SmallNatural(shortendDenom);
			
			System.out.println("new Not Shortend Numerator = " + newNotShortendNumerator);
			
			System.out.println("new not shortend denominator = " + newNotShortendDenominator);
			
			return new RationalNumber(sign, newInteger, newNumerator, newDenominator);
		}
		
		//Now it is clear that signs are different
		
		boolean rDominates = r.getAmount().isGreaterThen(this);
		
		RationalNumber rNDominating;
		RationalNumber underDog;
		
		if(rDominates)
		{
			rNDominating = (RationalNumber) r.clone();
			underDog = (RationalNumber) this.clone();
		}
		else
		{
			rNDominating = (RationalNumber) this.clone();
			underDog = (RationalNumber) r.clone();
		}
		
		Pair<Integer, Integer> ex1 = rNDominating.expand();
		Pair<Integer, Integer> ex2 = underDog.expand();
		
		/*TODO:
		NaturalNumber newEx1Numerator = ex1.getKey().multiplyWith(ex2.getValue());
		NaturalNumber newDenominator = ex1.getValue().multiplyWith(ex2.getValue());
				
		NaturalNumber newEx2Numerator = ex2.getKey().multiplyWith(ex1.getValue());
				
		NaturalNumber newNumerator= newEx1Numerator.subtract(newEx2Numerator);
		
		RationalNumber output = new RationalNumber(rNDominating.sign, newNumerator, newDenominator);
		//Should be output TODO:*/
		
		return null;
	}

	public RationalNumber getAmount() throws RNumException, NaturalNumberException, DivisionByZeroException, CollectionException, CloneNotSupportedException
	{
		return new RationalNumber(true, integerPart, numerator, denominator);
	}

	@Override
	public RationalNumber getNeutralZero() 
	{
		return rZero;
	}

	@Override
	public RationalNumber subtract(RationalNumber e) throws RNumException, NaturalNumberException, CloneNotSupportedException, DivisionByZeroException, CollectionException 
	{
		
		RationalNumber minus = new RationalNumber(!e.sign, e.integerPart, e.numerator, e.denominator);
		
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

		if(sign&&other.sign)//Both signs positve
		{
			if(integerPart.isGreaterThen(other.integerPart))return true;
			if(integerPart.isSmallerThen(other.integerPart)) return false;

			//if it continues computing at this point it is clear the integerParts
			//are equal.

			if(denominator.equals(other.denominator))return numerator.isGreaterThen(other.numerator);

			int numThis = numerator.getNumberCore();
			int numOther = other.numerator.getNumberCore();
			int denomThis = denominator.getNumberCore();
			int denomOther = other.denominator.getNumberCore();
			
			int numeratorThis = numThis*denomOther;
			int numeratorOther = numOther*denomThis;
			
			return numeratorThis>numeratorOther;
		}
		
		if(!sign&&!other.sign)
		{

			if(integerPart.isGreaterThen(other.integerPart))return false;
			if(integerPart.isSmallerThen(other.integerPart)) return true;

			//if it continues computing at this point it is clear the integerParts
			//are equal.

			if(denominator.equals(other.denominator))
			{
				return !numerator.isGreaterThen(other.numerator);
			}

			int numThis = numerator.getNumberCore();
			int numOther = other.numerator.getNumberCore();
			int denomThis = denominator.getNumberCore();
			int denomOther = other.denominator.getNumberCore();
			
			int numeratorThis = numThis*denomOther;
			int numeratorOther = numOther*denomThis;
			
			return (numeratorThis<numeratorOther);
		}

		return false;
	}
	
	public int getUpperEndOfCiphers()
	{
		return upperEndOfCiphers;
	}
	
	public void setUpperEndOfCiphers(int newUpperEnd)
	{
		upperEndOfCiphers = newUpperEnd;
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
		
		if(numerator.equals(snZero)&&sign)return integerPart + "";
		if(numerator.equals(snZero)&&!sign)return "-"+integerPart;
		if(integerPart.equals(snZero)&&sign) return "(" + numerator + "/" + denominator + ")";
		if(integerPart.equals(snZero)&&!sign)return "-(" + numerator + "/" + denominator + ")";
		
		if(sign)return "(" + integerPart + " + " + numerator + "/" + denominator + ")";
		//if(!sign)
		else return "-(" + integerPart + " + " + numerator + "/" + denominator + ")";
	}
	
	public RationalNumber clone()
	{
		try
		{
			return new RationalNumber(sign, integerPart, numerator, denominator);
		}
		catch(NaturalNumberException | DivisionByZeroException | CollectionException | CloneNotSupportedException | RNumException e)
		{
			System.out.println("This shouldnt happen!");
			e.printStackTrace();
			return null;
		} 
	}
	
	public SmallNatural getIntegerPart() throws NaturalNumberException
	{
		return integerPart.clone();
	}

	public SmallNatural getNumerator() throws NaturalNumberException
	{
		return numerator.clone();
	}

	public SmallNatural getDenominator() throws NaturalNumberException
	{
		return denominator.clone();
	}
}