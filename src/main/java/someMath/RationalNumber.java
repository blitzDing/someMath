package someMath;


import java.math.BigInteger;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.util.Pair;

import static someMath.NaturalNumber.*;

public class RationalNumber implements SubtractableAndDivideable<RationalNumber>
{

	public final NaturalNumber numerator;
	public final NaturalNumber denominator;
	public final NaturalNumber integerPart;
	public final boolean sign; //false means negative.
	
	private static int upperEndOfCiphers = 12;

	public static final RationalNumber rZero = new RationalNumber("hey", true, zero, zero, one);
	public static final RationalNumber rOne = new RationalNumber("hi", true, zero, one, one);
	
	public static final Pattern pattern = Pattern.compile("(\\-)?((\\d+\\s)|(\\d+$))?((\\-)?(\\d+)(/)(\\d+))?");
	
	//Remember: NaturalNumber can't be lower than Zero!!!
	public RationalNumber(NaturalNumber integerPart, NaturalNumber numerator, NaturalNumber denominator) throws RNumException, NaturalNumberException
	{
		this(true, integerPart, numerator, denominator);
	}

	public RationalNumber(boolean sign, NaturalNumber integerPart, NaturalNumber numerator, NaturalNumber denominator)throws RNumException, NaturalNumberException
	{
		
		if(denominator.equals(zero))
		{
			System.out.println(denominator);
			throw new RNumException("Denominator can't be zero");
		}
				
		this.sign = sign;
		
		NaturalNumber[] shortend = shortening(numerator, denominator);

		this.integerPart = shortend[0].addWith(integerPart);
		this.numerator   = shortend[1];
		this.denominator = shortend[2];
	}
	
	//Default positive RN.
	public RationalNumber(NaturalNumber numerator, NaturalNumber denominator) throws RNumException, NaturalNumberException
	{
		//The default.
		this(true, numerator, denominator);
	}
	
	public RationalNumber(boolean sign, NaturalNumber numerator, NaturalNumber denominator) throws RNumException, NaturalNumberException
	{
		this(sign, zero, numerator, denominator);
	}
	
	public RationalNumber(String s) throws RNumException, NumberFormatException, NaturalNumberException
	{
		
		String trimed = s.trim();
		Matcher matcher = pattern.matcher(trimed);
		
		NaturalNumber integerPart = zero;
		NaturalNumber numerator;
		NaturalNumber denominator;
		boolean sign;
		
		if(matcher.find())
		{
			String firstMinus = matcher.group(1);
			String secondMinus = matcher.group(6);
			
			if(firstMinus!=null&&secondMinus!=null) throw new RNumException("Only one minus sign and please upfront.");
			
			if(firstMinus!=null||secondMinus!=null) sign = false;
			else sign = true;

			String fractionPart = matcher.group(5);
			String intPart = matcher.group(3);
			
			if(fractionPart==null&&intPart==null)throw new RNumException("Don't now What that is.");
			if(intPart==null&&fractionPart==null)throw new RNumException("Integer part and Fraction part are not there. Can't parse this.");
			if(intPart!=null)integerPart = new NaturalNumber(Integer.parseInt(intPart.trim()));
			if(intPart!=null&&fractionPart==null)
			{
				
				numerator = zero;
				denominator = one;
				
				this.sign = sign;
				
				NaturalNumber[] shortend = shortening(numerator, denominator);

				this.integerPart = shortend[0].addWith(integerPart);
				this.numerator   = shortend[1];
				this.denominator = shortend[2];

				return;			
			}
			
			String numeratorString = matcher.group(7);
			numerator = new NaturalNumber(Integer.parseInt(numeratorString));
			
			String denominatorString = matcher.group(9);
			denominator = new NaturalNumber(Integer.parseInt(denominatorString));
						
			this.sign = sign;
			
			if(intPart==null&&fractionPart!=null)
			{

				NaturalNumber[] shortend = shortening(numerator, denominator);

				this.integerPart = shortend[0];
				this.numerator   = shortend[1];
				this.denominator = shortend[2];
				
				return;
			}
			
			NaturalNumber[] shortend = shortening(numerator, denominator);

			this.integerPart = shortend[0].addWith(integerPart);
			this.numerator   = shortend[1];
			this.denominator = shortend[2];
			
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
	private RationalNumber(String hey, boolean sign, NaturalNumber integerPart, NaturalNumber numerator, NaturalNumber denominator)
	{
		this.sign = sign;
		this.integerPart = integerPart;
		this.numerator = numerator;
		this.denominator = denominator;
	}

	private NaturalNumber[] shortening(NaturalNumber numerator, NaturalNumber denominator) throws NaturalNumberException
	{
		
		NaturalNumber[] theThreeParts = new NaturalNumber[3];
		theThreeParts[0] = zero;
		theThreeParts[1] = zero;
		theThreeParts[2] = zero;
		
		if(numerator.isSmallerThen(denominator))
		{
			theThreeParts[0]= new NaturalNumber(0);
			theThreeParts[1]=numerator;
			theThreeParts[2]=denominator;
		}
		
		if(numerator.equals(denominator))
		{
			theThreeParts[0]=new NaturalNumber(1);
			theThreeParts[1]=new NaturalNumber(0);
			theThreeParts[2]=new NaturalNumber(1);
		}
		
		if(numerator.isGreaterThen(denominator))
		{
			NaturalNumber[] down = shortening(numerator.subtract(denominator), denominator);
			
			
			theThreeParts[0] = theThreeParts[0].addWith(down[0].addWith(one));
			theThreeParts[1] =  down[1];
			theThreeParts[2] =  down[2];
		}
		
		return theThreeParts;
	}
	
	public double doubleApproximation() throws NaturalNumberException, RNumException
	{

		int factor = -1;
		if(sign)factor = 1;
		
		RationalNumber cut = cutCiphers();
		
		Double integerPartAsDouble = cut.integerPart.doubleApproximation();
		Double numeratorAsDouble = cut.numerator.doubleApproximation();
		Double denominatorAsDouble = cut.denominator.doubleApproximation();
		
		return factor*(integerPartAsDouble + (numeratorAsDouble/denominatorAsDouble));
	}

	private RationalNumber cutCiphers() throws NaturalNumberException, RNumException
	{
		
		/*Remember: numerator and denominator get Constantly shortend
		 * and the Numerator can't be bigger then the Denominator!!!
		 */
		
		int numeratorLength = numerator.toString().length();
		int denominatorLength = denominator.toString().length();
		
		int sizeDiff = denominatorLength - numeratorLength;
		
		if(sizeDiff>=upperEndOfCiphers)
		{
			return new RationalNumber(sign, integerPart, zero, one);
		}
		
		boolean numeratorBig = numeratorLength>upperEndOfCiphers;
		boolean denominatorBig = denominatorLength>upperEndOfCiphers;
		
		boolean nAndDEqual = (numeratorLength == denominatorLength);
		
		if(numeratorBig&&denominatorBig&&nAndDEqual)
		{
			
			BigInteger oldNumerator = numerator.getNumberCore();
			BigInteger newNumerator = new BigInteger(oldNumerator.toString().substring(0, upperEndOfCiphers));
			
			BigInteger oldDenominator = denominator.getNumberCore();
			BigInteger newDenominator = new BigInteger(oldDenominator.toString().substring(0, upperEndOfCiphers));
			
			NaturalNumber numo = new NaturalNumber(newNumerator);
			NaturalNumber denio = new NaturalNumber(newDenominator);
			
			RationalNumber newRN = new RationalNumber(sign, integerPart, numo, denio);
			
			return newRN;
		}
		
		if(denominatorBig&&!(numeratorBig))
		{
			
			//TODO:
			BigInteger biOldNumerator = numerator.getNumberCore();
			BigInteger biNewNumerator = new BigInteger(biOldNumerator.toString().substring(0, (numeratorLength-1)));
			
			BigInteger biOldDenominator = denominator.getNumberCore();
			BigInteger biNewDenominator = new BigInteger(biOldDenominator.toString().substring(0, (numeratorLength-1)));
			
			NaturalNumber nnn = new NaturalNumber(biNewNumerator);
			NaturalNumber nnd = new NaturalNumber(biNewDenominator);
			
			return new RationalNumber(sign, integerPart, nnn, nnd);
		}
		
		return this;
	}
	
	public Pair<NaturalNumber, NaturalNumber> expand() throws NaturalNumberException
	{
		
		NaturalNumber newNumerator = this.integerPart.multiplyWith(denominator).addWith(numerator);
		
		return new Pair<>(newNumerator, denominator);
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
	public RationalNumber multiplyWith(RationalNumber e) throws NaturalNumberException, RNumException
	{
		
		if(this.equals(rZero)||e.equals(rZero))return rZero;
		
		NaturalNumber expandedNumeratorThis = expand().getKey();
		NaturalNumber expandedNumeratorE    = e.expand().getKey();
		
		boolean bothNegative = (!e.sign&&!this.sign);
		boolean bothPositive = e.sign&&this.sign;
		
		boolean sign = (bothNegative||bothPositive);
		
		NaturalNumber productOfNumerators = expandedNumeratorThis.multiplyWith(expandedNumeratorE);
		NaturalNumber productOfDenominators = denominator.multiplyWith(e.denominator);
		
		RationalNumber output = new RationalNumber(sign, productOfNumerators, productOfDenominators);
	
		return output.cutCiphers();
	}

	public RationalNumber divideBy(RationalNumber r) throws DivisionByZeroException, NaturalNumberException, RNumException
	{
		
		if(r.equals(rZero))throw new DivisionByZeroException();
		
		if(this.equals(rZero))return rZero;
		
		NaturalNumber expandedNumerator = (r.integerPart.multiplyWith(r.denominator)).addWith(r.numerator);
		boolean signOfE = r.sign;
		
		RationalNumber reciprocal = new RationalNumber(signOfE, r.denominator, expandedNumerator);
		RationalNumber output = this.multiplyWith(reciprocal);
		
		return output.cutCiphers();
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
	public RationalNumber addWith(RationalNumber r) throws NaturalNumberException, RNumException, CloneNotSupportedException 
	{
		
		if(this.equals(rZero))return r;
		if(r.equals(rZero))return this;

		if(this.sign==r.sign)
		{
			NaturalNumber newIntegerPart = this.integerPart.addWith(r.integerPart);
			NaturalNumber newNotShortendNumerator = (this.numerator.multiplyWith(r.denominator)).addWith(r.numerator.multiplyWith(this.denominator));
			NaturalNumber newNotShortendDenominator = this.denominator.multiplyWith(r.denominator);
		
			return new RationalNumber(sign, newIntegerPart, newNotShortendNumerator, newNotShortendDenominator);
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
		
		Pair<NaturalNumber, NaturalNumber> ex1 = rNDominating.expand();
		Pair<NaturalNumber, NaturalNumber> ex2 = underDog.expand();
		
		NaturalNumber newEx1Numerator = ex1.getKey().multiplyWith(ex2.getValue());
		NaturalNumber newDenominator = ex1.getValue().multiplyWith(ex2.getValue());
				
		NaturalNumber newEx2Numerator = ex2.getKey().multiplyWith(ex1.getValue());
				
		NaturalNumber newNumerator= newEx1Numerator.subtract(newEx2Numerator);
		
		return new RationalNumber(rNDominating.sign, newNumerator, newDenominator).cutCiphers();
	}

	public RationalNumber getAmount() throws RNumException, NaturalNumberException
	{
		
		if(sign)return this;
		else return new RationalNumber(true, integerPart, numerator, denominator).cutCiphers();
	}

	@Override
	public RationalNumber getNeutralZero() 
	{
		return rZero;
	}

	@Override
	public RationalNumber subtract(RationalNumber e) throws RNumException, NaturalNumberException, CloneNotSupportedException 
	{
		
		RationalNumber minus = new RationalNumber(!e.sign, e.integerPart, e.numerator, e.denominator);
		
		return this.addWith(minus);
	}

	public int hashCode()
	{
		return Objects.hash(integerPart, numerator, denominator);
	}

	public boolean isGreaterThen(RationalNumber other) throws NaturalNumberException, RNumException
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

			NaturalNumber numeratorThis = numerator.multiplyWith(other.denominator);
			NaturalNumber numeratorOther = other.numerator.multiplyWith(denominator);
			
			if(numeratorThis.isGreaterThen(numeratorOther)) return true;
		}
		
		if(!sign&&!other.sign)
		{

			RationalNumber positiveA = new RationalNumber(true, integerPart, numerator, denominator);
			RationalNumber positiveB = new RationalNumber(true, other.integerPart, other.numerator, other.denominator);

			return !positiveA.isGreaterThen(positiveB);
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
		
		if(numerator.equals(zero)&&sign)return integerPart + "";
		if(numerator.equals(zero)&&!sign)return "-"+integerPart;
		if(integerPart.equals(zero)&&sign) return "(" + numerator + "/" + denominator + ")";
		if(integerPart.equals(zero)&&!sign)return "-(" + numerator + "/" + denominator + ")";
		
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
		catch(RNumException | NaturalNumberException e)
		{
			System.out.println("This shouldnt happen!");
			e.printStackTrace();
			return null;
		}
	}
}