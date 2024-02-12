package someMath;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.List;

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

	private static final int standartDeepness = 6;
    
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
	
	public static Set<Set<Character>> createFamilie(Set<Set<Character>> origin)
	{
		Set<Set<Character>> output = new HashSet<>(origin);
		
		for(Set<Character> e1: origin)
		{
			
			for(Set<Character> e2: origin)
			{
				if(e1.equals(e2))continue;
				else
				{
					Set<Character> a = new HashSet<>();
					a.addAll(e1);
					a.addAll(e2);
					output.add(a);
				}
			}
		}
		
		if(!origin.equals(output)) return createFamilie(output);

		return output;
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

		NaturalNumber sum = x.addWith(y);
		NaturalNumber sumPlusOne = sum.addWith(one);
		NaturalNumber product = sum.multiplyWith(sumPlusOne).divideBy(two);

		NaturalNumber cpNr = y.addWith(product);
        
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
		if(a<b)
		{
			int holdIt=b;
			b=a;
			a=holdIt;
		}
        	
		if(b==0)return a;
		else return gcd(b,Math.floorMod(a,b));
	}

	public static int scm(int a, int b)
	{
		return (a*b)/gcd(a,b);
	}
	
	public static int randomInt(int max, int min)
	{
		
		return (int)((Math.random()*(max-min))+min);
	}
	
	public static RationalNumber rndRNr(RationalNumber max, RationalNumber min)
	{
		
		return null;
	}

	public static <T extends SubtractableAndDivideable<T>> T getNthPotenz(T basis, int exponent) throws NaturalNumberException, RNumException, CloneNotSupportedException, DivisionByZeroException, CollectionException
	{
		
		int newExponent = exponent;
		if(exponent<0)newExponent = -exponent;
		
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
		
		T startValue = basis.getNeutralOne().addWith(basis.getNeutralOne());
		T ainz = basis.divideBy(startValue);
		T two = ainz.addWith(ainz);
		T half = ainz.divideBy(two);

		for(int n=0;n<deepNess;n++)
		{
			T newValue = half.multiplyWith(currentValue.addWith
					(basis.divideBy(getNthPotenz(currentValue, newRoot-1))));
			
			if(newValue.equals(currentValue))return newValue;
			
			currentValue = newValue;
		}
		
		if(root<0)return basis.getNeutralOne().divideBy(currentValue);
		
		return currentValue;
	}
	
	public static <T extends SubtractableAndDivideable<T>> T getNthRoot(T basis, int root) throws IllegalArgumentException, NaturalNumberException, RNumException, CloneNotSupportedException, DivisionByZeroException, CollectionException
	{
		return getNthRoot(standartDeepness, basis, root);
	}	
}