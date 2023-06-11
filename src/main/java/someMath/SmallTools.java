package someMath;


import java.math.BigDecimal;

import java.util.*;
import java.util.List;



public class SmallTools
{

	public final static Set<Character> cipherSet = new HashSet<>(Arrays.asList('0','1','2','3','4','5','6','7', '8','9'));
	
    final public static BigDecimal zero = BigDecimal.valueOf(0);
    final public static BigDecimal one = BigDecimal.valueOf(1);
    final public static BigDecimal negativeOne = BigDecimal.valueOf(-1);
    final public static BigDecimal half = BigDecimal.valueOf(1/2);
    final public static BigDecimal pi = BigDecimal.valueOf(Math.PI);
    final public static BigDecimal e = BigDecimal.valueOf(Math.E);
 
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
        
	public static BigDecimal cantorPairNr(BigDecimal x, BigDecimal y)
	{

        BigDecimal s = x.add(y);
        BigDecimal cp = y.add(half.multiply(s.multiply(s.add(one))));
        
		return cp;
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
}