package someMath;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;
import java.util.List;


import javafx.util.Pair;

public class SmallTools
{

    final public static BigDecimal zero = BigDecimal.valueOf(0);
    final public static BigDecimal one = BigDecimal.valueOf(1);
    final public static BigDecimal negativeOne = BigDecimal.valueOf(-1);
    final public static BigDecimal half = BigDecimal.valueOf(1/2);
    final public static BigDecimal pi = BigDecimal.valueOf(Math.PI);
    final public static BigDecimal e = BigDecimal.valueOf(Math.E);

    //n aus k
	public static int combinations(int pick, int pool)
	{
		if(pick>pool)throw new IllegalArgumentException("pick should not be bigger than pool.");
		return factorial(pool)/(factorial(pool-pick)*factorial(pick));
	}

    public static int theBiggerEndOfAFactorial(int n, int k)
    {

        if(k>n)throw new IllegalArgumentException("n must be equal or larger then k");
        return factorial(n)/factorial(k);
    }

	public static <T> Set<Set<T>> powerSet(Set<T> origin)
	{

		if(origin.size()==0)
		{

			Set<T> emptySet = new HashSet<>();
			Set<Set<T>> easySet = new HashSet<>();
			easySet.add(emptySet);

			return easySet;
		}

		Set<T> less = new HashSet<>(origin);
		T t = CollectionManipulation.catchRandomElementOfSet(less);
		less.remove(t);

		return enlarge(t, powerSet(less));
	}

	public static <M> Set<Set<M>> enlarge(M m, Set<Set<M>> pSet)
	{

		Set<Set<M>> pSetcopy = new HashSet<>(pSet);

		for(Set<M> inside: pSet)
		{

			Set<M> copy = new HashSet<>(inside);
			copy.add(m);
			pSetcopy.add(copy);
		}

		return pSetcopy;
	}

	public static Set<List<Object>> cartasianProduct(Set<?> ...inputSetsArr)
	{

		List[] intern = new List[inputSetsArr.length];

		for(int n=0;n<inputSetsArr.length;n++)
		{
			List<Object> l = new ArrayList<>(inputSetsArr[n]);

			intern[n]= new ArrayList<>(l);
		}

		return _cProduct(intern);
	}

	private static Set<List<Object>> _cProduct(List<?> ...lists)
	{

		int indexMaxOfLN[] = new int[lists.length];
		int actualIndexLN[] = new int[lists.length];
		int absNrOfObjectsInProd = 1;

		for(int n=0;n<lists.length;n++)
		{
			indexMaxOfLN[n] = lists[n].size();
			actualIndexLN[n]=0;
			absNrOfObjectsInProd *=indexMaxOfLN[n];
		}

		absNrOfObjectsInProd *= lists.length;

		List<Object> lo = new ArrayList<>();
		Set<List<Object>> outputSet = new HashSet<>();

		for(int n=0;n<absNrOfObjectsInProd;n++)
		{

			int listCounter = n % lists.length;
			Object obj = lists[listCounter].get(actualIndexLN[listCounter]);
			lo.add(obj);

			if (n % lists.length == lists.length - 1)
			{

				outputSet.add(lo);
				lo = new ArrayList<>();

				actualIndexLN[0] += 1;
				actualIndexLN[0]=actualIndexLN[0]%indexMaxOfLN[0];

				int p[] = new int[lists.length];
				p[0]=lists.length;
				for(int k=1;k<p.length;k++)//k starts with one!
				{
					p[k]=indexMaxOfLN[k-1]*p[k-1];
				}

				for(int k=1;k<p.length;k++)
				{
					if((n+1)%p[k]==0)actualIndexLN[k]+=1;
					actualIndexLN[k]=actualIndexLN[k]%indexMaxOfLN[k];
				}}
		}
		return outputSet;
	}

	public static int dezimalstellenVonInt(int n)
	{
		
		String s=String.valueOf(n);
		s.trim();
		int l = s.length();
		return l;
	}

	public static List<List<Integer>> allPermutationsOfNWithDuplicates(int ...k)
	{

		if(k==null) throw new IllegalArgumentException("Parameter can't be null");

		int count = 0;
		int sum = 0;

		for(int i=0;i<k.length;i++)
		{
			if(k[i]<0) throw new IllegalArgumentException("the argument must be greater then Zero.");
			if(k[i]<2) count++;
			sum += k[i];
		}

		//the following condition is the case if all k equal to one!!!
		if(count==k.length)return allPermutationsOfNWithoutDuplicates(sum);

		List<List<Integer>> dest = new ArrayList<>();
		if(k.length==1)//checks if there is only one k
		{
			// if there is only one k then create
			// a list full of Zeros and wrap it
			// up and return the wrap.
			List<Integer> inside = new ArrayList<>();
			for(int i=0;i<sum;i++)inside.add(0);
			dest.add(inside);
			return dest;
	}

		Arrays.sort(k);
		for(int i=0;i<k.length;i++)
		{

			//find the first k-Element larger then 1;
			if(k[i]>1)
			{
				int [] tmp = Arrays.copyOf(k, k.length);
				tmp[i]--;
				return specialFuse(i, allPermutationsOfNWithDuplicates(tmp));
			}
		}

		return null;
	}

	private static List<List<Integer>> specialFuse(int i, List<List<Integer>> inputListList)
	{

		Set<List<Integer>> dest = new HashSet<>();

		for(int n=0;n<inputListList.size();n++)
		{
			List<Integer> inside = inputListList.get(n);

			for(int position=0;position<inside.size();position++)
			{
				inside.add(i,position);
			}

			dest.add(inside);
		}

		List<List<Integer>> result = new ArrayList<>(dest);

		return result;
	}

	public static List<List<Integer>> allPermutationsOfNWithoutDuplicates(int n)
	{

		if(n<1) throw new IllegalArgumentException("For now keep N bigger than Zero!!!");

		if(n==1)
		{

			List<Integer> shortOne = Arrays.asList(0);
			List<List<Integer>> extremeEasyList = Arrays.asList(shortOne);
			return extremeEasyList;
		}

		//ToDo: How Does this Work? especially the fused-Method???
		return fused(n, allPermutationsOfNWithoutDuplicates(n-1));
	}

	private static List<List<Integer>> fused(int n, List<List<Integer>> l)
	{

		List<List<Integer>> dest = new ArrayList<>();

		for(List<Integer> inside: l)
		{

			// Just adds adds all number from Zero to n-1
			// at position (n-1) to l(inside). Then
			// stores l in dest. How does this Work?
			// It is tailored to work with
			// allPermutationsOfNWithoutDuplicates-Method.
			for(int i=0;i<n;i++)
			{

				List<Integer> expandedList = new ArrayList<Integer>(inside);
				expandedList.add(i,n-1);
				dest.add(expandedList);
			}
		}

		return dest;
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
	public static boolean isInteger(String s) {
		return isInteger(s,10);
	}

	public static boolean isInteger(String s, int radix)
	{
		if(s.isEmpty()) return false;
		for(int i = 0; i < s.length(); i++)
		{
			if(i == 0 && s.charAt(i) == '-')
			{
				if(s.length() == 1) return false;
				else continue;
			}
			if(Character.digit(s.charAt(i),radix) < 0) return false;
		}
		return true;
	}
        
	public static BigDecimal cantorPairNr(BigDecimal x, BigDecimal y)
	{

        BigDecimal s = x.add(y);
        BigDecimal cp = y.add(half.multiply(s.multiply(s.add(one))));

        //
		return cp;
	}

    public static Pair<BigDecimal, BigDecimal> getCantorPair(int n)
    {

        Pair<BigDecimal, BigDecimal> cantorPair;
        BigDecimal nr = BigDecimal.valueOf(n);

        BigDecimal q = getTriangleRoot(nr).round(new MathContext(1, RoundingMode.FLOOR));

        BigDecimal y = nr.subtract(getTriangleNr(q));
        BigDecimal x = q.subtract(y);


        cantorPair = new Pair<BigDecimal, BigDecimal>(x, y);

        return cantorPair;
    }



    public static BigDecimal getTriangleNr(BigDecimal r)
    {
        return half.multiply(r.multiply(r.add(one)));
    }

    public static BigDecimal getTriangleRoot(BigDecimal r)
    {

        BigDecimal eight = BigDecimal.valueOf(8);
        BigDecimal basis = eight.multiply(r).add(one);
        BigDecimal complex = basis.sqrt(MathContext.DECIMAL128);

        return negativeOne.multiply(half).multiply(complex.subtract(one));
    }
        
	public static BigDecimal cantorTupel(BigDecimal ...args)
	{
            
		BigDecimal destiny = args[0];
            
		for(int a=1;a<args.length;a++)
		{
        	destiny = cantorPairNr(args[a], destiny);
		}
            
		return destiny;
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

	public static int factorial(int n)
	{

		if(n<0) throw new IllegalArgumentException("For now keep n bigger then Zero!");
		if(n==0)return 1;
		return factorial(n-1)*n;
	}

	public static Set<List<?>> kartesisichesProdukt(Set<?> ...inputSetsArray)
	{

		Set<List<?>> dest = new HashSet<>();

		if(inputSetsArray.length==1)
		{

			for(Object obj: inputSetsArray[0])
			{
				List<Object> inside = new ArrayList<>();
				inside.add(obj);
				dest.add(inside);
			}

			return dest;
		}

		Set<?> out = inputSetsArray[0];
		Set<?>[] rest = new Set<?>[inputSetsArray.length-1];
		System.arraycopy(inputSetsArray,1,rest,0,inputSetsArray.length-1);

		return expandKProduct(out, kartesisichesProdukt(rest));
	}

	public static Set<List<?>> expandKProduct(Set<?> out, Set<List<?>> source)
	{

		Set<List<?>> dest = new HashSet<>();
		for(Object obj: out)
		{

			for(List<?> l: source)
			{

				List<Object> inside = new ArrayList<>(l);
				inside.add(0, obj);
				dest.add(inside);
			}
		}

		return dest;
	}

	public static int distanceOfPermutationsInSwaps(List<Integer> a, List<Integer> b)
	{
		//ToDo: this Method is not doing what its Name says! Correct it!
        //Remember: A permutation derive Method might be more use full.
		if(a.size()!=b.size()) throw new IllegalArgumentException("Both List have to be the same Size!");

		int d = 0;
		for(int n=0;n<a.size();n++)
		{
			if(!a.contains(n)||!b.contains(n)) throw new IllegalArgumentException("Values of Lists not Valid.");
			if(a.get(n)!=b.get(n))d++;
		}

		/*
		if(d==0)return d;
		return d-1;
		*/

		return d;
	}

    public static double xToThePowerOfX(double z)
    {
        return xToThePowerOfXHelper(z, 3);
    }

    private static double xToThePowerOfXHelper(double z, double a1)
    {

        double sourceNr = Math.log(z);
        double currentNr = Math.log(a1);
        double a2  = 0.5*((sourceNr/currentNr)+a1);

        double diff = Math.abs(Math.pow(a2, a2)-z);

        if(diff<0.000000001)return a2;
        else a2 = xToThePowerOfXHelper(z, a2);

        return a2;
    }
}