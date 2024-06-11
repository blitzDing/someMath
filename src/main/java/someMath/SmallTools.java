package someMath;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import javafx.util.Pair;

import static someMath.NaturalNumber.*;

public class SmallTools
{

	public final static Set<Character> cipherSet = new HashSet<>(Arrays.asList('0','1','2','3','4','5','6','7', '8','9'));
	
    final public static BigInteger negativeOne = BigInteger.valueOf(-1);
    final public static BigDecimal half = BigDecimal.valueOf(0.5d);
    final public static BigDecimal pi = BigDecimal.valueOf(Math.PI);
    final public static BigDecimal e = BigDecimal.valueOf(Math.E);
 
    final public static double prettySmall = Math.pow(10, -11);

	private static final int standartDeepnessForRoot = 3;
    	
	
	public static int dezimalstellenVonInt(int n)
	{
		
		String s=String.valueOf(n);
		s.trim();
		int l = s.length();
		return l;
	}


	public static <T> List<T> permute(List<T> source, List<Integer> op)
	{


		Object[] destArray = new Object[source.size()];

		if(op.size()!=source.size())throw new IllegalArgumentException("Operation List Invalid");

		for (int n = 0; n < op.size(); n++)
		{
			if (!op.contains(n)) throw new IllegalArgumentException("Operation List Invalid");
			destArray[op.get(n)]= source.get(n);
		}

		@SuppressWarnings("unchecked")
		List<T> dest = (List<T>)(Object)Arrays.asList(destArray);

		return dest;
	}
	
	public static boolean isInteger(String s)
	{
		if(s.isEmpty()) return false;
		String k = s.trim();
		for(int i = 0; i < k.length(); i++)
		{

			if(i == 0 && k.charAt(i) == '-')continue;
			
			if(!(cipherSet.contains(k.charAt(i)))) return false;
		}
		
		return true;
	}
        
	public static NaturalNumber cantorPairNr(NaturalNumber x, NaturalNumber y) throws NaturalNumberException, DivisionByZeroException, CollectionException, RNumException
	{
		
		NaturalNumber two = new NaturalNumber(2);

		NaturalNumber sum = x.add(y);
		NaturalNumber sumPlusOne = sum.add(one);
		NaturalNumber product = sum.multiplyWith(sumPlusOne).divideBy(two);

		NaturalNumber cpNr = y.add(product);
        
		return cpNr;
	}
	
	public static Pair<BigDecimal, BigDecimal> getCantorPair(BigDecimal cpNr)
	{
		return null;
	}
	
	public static Double log(Double basis, Double potenz)
	{

		return Math.log(potenz)/Math.log(basis);
	}
        
	public static int greatestCommonDivisor(int a, int b)
	{
        	return gcd(a,b);
	}
        
	public static int gcd(int a, int b)
	{
		//a should be bigger or equal b, so sorting is needed
		if(a<b)switchValues(a, b);
        	
		if(b==0)return a;
		else return gcd(b,Math.floorMod(a,b));
	}


	public static void switchValues(Integer a, Integer b)//making use of the fact that a and b
	{													 //are Objects!!!
		Integer c = b;
		b=a;
		a = c;
	}
	
	//Smallest Common Multiple.
	public static int scm(int a, int b)
	{
		return (a*b)/gcd(a,b);
	}
	
	public static NaturalNumber gcd(NaturalNumber a, NaturalNumber b) throws NaturalNumberException
	{
		BigInteger biNum = a.getNumberCore();
		BigInteger biDenom = b.getNumberCore();
		
		BigInteger biGCD = biNum.gcd(biDenom);
		
		return new NaturalNumber(biGCD);
	}

	//Smallest Common Multiple.
	public static NaturalNumber scm(NaturalNumber a, NaturalNumber b) throws DivisionByZeroException, NaturalNumberException, CollectionException, RNumException
	{
		return (a.multiplyWith(b)).divideBy(gcd(a,b));
	}
	
	public static int randomInt(int max, int min)
	{
		
		return (int)((Math.random()*(max-min))+min);
	}
	
	public static RationalNumber rndRNr(RationalNumber max, RationalNumber min)
	{
		//TODO:
		return null;
	}

	public static <T extends SubtractableAndDivideable<T>> T getNthPotenz(T basis, int exponent) throws NaturalNumberException, RNumException, CloneNotSupportedException, DivisionByZeroException, CollectionException
	{
		
		int newExponent = Math.abs(exponent);
		
		T potenz = basis.getNeutralOne();
		
		if(exponent== 0)return potenz;
		if(exponent==1)return basis;
		
		for(int n = 0;n<newExponent;n++)
		{
			potenz = basis.multiplyWith(potenz);
		}
		
		if(exponent<0) return basis.getNeutralOne().divideBy(potenz);
		
		return potenz;
	}
	
	public static <T extends SubtractableAndDivideable<T>> T getNthRoot(int deepNess, T basis, int root) throws IllegalArgumentException, NaturalNumberException, RNumException, CloneNotSupportedException, DivisionByZeroException, CollectionException
	{
		if(!basis.hasNeutralOne())throw new IllegalArgumentException("Can't do root without a neutral One.");
		if(root==0)throw new IllegalArgumentException("Can't do Zero's root.");
		int newRoot = root;
		if(root<0)newRoot = -root;
		
		T currentValue =  basis.getNeutralOne();
		if(root==1)return basis;
		
		T startValue = basis.getNeutralOne();
		T ainz = basis.divideBy(startValue);
		T two = ainz.add(ainz);
		T half = ainz.divideBy(two);
		
		for(int n=0;n<deepNess;n++)
		{

			T power = getNthPotenz(currentValue, newRoot-1);
			System.out.println("New Power: " + power);

			T div = basis.divideBy(power);
			System.out.println("New div: " + div);
			
			T sum = currentValue.add(div);
			System.out.println("New Sum: " + sum);
			
			T newValue = half.multiplyWith(sum);
			System.out.println("New Value: " + newValue);
			
			if(newValue.equals(currentValue))return newValue;
			currentValue = newValue;
		}
		
		if(root<0)return basis.getNeutralOne().divideBy(currentValue);
		
		return currentValue;
	}
	
	public static <T extends SubtractableAndDivideable<T>> T getNthRoot(T basis, int root) throws IllegalArgumentException, NaturalNumberException, RNumException, CloneNotSupportedException, DivisionByZeroException, CollectionException
	{
		return getNthRoot(standartDeepnessForRoot, basis, root);
	}	
}