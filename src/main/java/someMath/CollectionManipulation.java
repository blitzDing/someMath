package someMath;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.util.Pair;


public class CollectionManipulation 
{

	public static List<Double> sumUpToIndex(List<Double> layers)
	{

		List<Double> sumList = new ArrayList<>();
		

		if(layers==null)return sumList;
		
		sumList.add(layers.get(0));
		int nrOfLayers = layers.size();
		
		//Careful it starts with one.
		for(int n=1;n<nrOfLayers;n++)
		{
			
			Double d = layers.get(n);
			Double d2 = sumList.get(n-1);
			
			if(d==null||d2==null)throw new IllegalArgumentException("At least one Argument is null.");
			sumList.add(d + sumList.get(n-1));
		}

		return sumList;
	}
	
	public static double randomNrBoundBetween(List<Double> layers)
	{
		if(layers==null||layers.isEmpty())return 0.0d;

		double sum = layers.stream().reduce(0.0, Double::sum);
		
		return Math.random()*sum;
	}
	
	public static <A> int  betweenWhichElements(double betweener, List<Pair<A, Double>> layers)
	{
		
		if(layers==null||layers.isEmpty())return 0;
		
		int nrOfLayers = layers.size();
		if(betweener<=0)return -1;//Before the first Layer.
		
		List<Double> upStacking = new ArrayList<>();
		for(int n=0;n<nrOfLayers;n++)
		{
			
			Double d = layers.get(n).getValue();
			if(d==null)throw new IllegalArgumentException("At least one value is null");
			upStacking.add(d);
		}
		
		List<Double> layerSumUpToIndex = sumUpToIndex(upStacking);
		
		if(betweener>layerSumUpToIndex.get(nrOfLayers-1))return nrOfLayers;//Beyond the Last Layer
		
		for(int n=0;n<nrOfLayers;n++)
		{
			if(betweener<=layerSumUpToIndex.get(n))return n;
		}
		
		throw new IllegalArgumentException("Something is Wrong.");
	}
	
	public static <A> List<Double> getRidOfTheGeneric(List<Pair<A, Double>> layers) throws CollectionException
	{
		
		if(layers==null)throw new CollectionException("List is Null.");
		
		int nrOfLayers = layers.size();
		
		List<Double> upStacking = new ArrayList<>();
		for(int n=0;n<nrOfLayers;n++)
		{
			upStacking.add(layers.get(n).getValue());
		}
		
		return upStacking;
	}

	public static <T> T catchRandomElementOfSet(Set<T> set) throws CollectionException
	{
		
		if(set==null||set.isEmpty())throw new CollectionException("Set is empty or Null.");
		
		List<T> list = new ArrayList<>(set);
		
		return catchRandomElementOfList(list);
	}
	
	public static <T> T catchRandomElementOfList(List<T> list) throws CollectionException
	{
		
		if(list==null||list.isEmpty())throw new CollectionException("List is empty or Null.");
		
		int n = list.size();
		int r = (int)(Math.random()*n);
		
		return list.get(r);
	}
	
	public static <T> Set<List<T>> cartesianProduct(List<Set<T>> input) throws CollectionException
	{
		
		if(input==null)throw new CollectionException("List is Null.");

		Set<List<T>> output = new HashSet<>();
		int s = input.size();
		
		if(s==0)return output;
				
		Set<T> insider = input.get(0);
		List<Set<T>> smaller = new ArrayList<>(input);
		smaller.remove(insider);
		
		if(s==1)
		{
			for(T t: insider)
			{
				List<T> list = new ArrayList<>();
				list.add(t);
				output.add(list);
			}
			
			return output;
		}
		
		for(T t: insider)
		{
			
			for(List<T> list: cartesianProduct(smaller))
			{
				
				list.add(t);
				output.add(list);
			}
		}
		
		return output;
	}

	public static <T> Set<Set<T>> powerSet(Set<T> input) throws CollectionException
	{

		if(input==null)throw new IllegalArgumentException("Set can't be Null.");
		if(input.contains(null))throw new IllegalArgumentException("Set contains Null.");
	
		Set<Set<T>> output = new HashSet<>();
		if(input.size()==0)
		{
			output.add(new HashSet<T>());//Add an (the) emptySet;
			return output;
		}
	
		Set<T> copy = new HashSet<>(input);
		T cutOut = CollectionManipulation.catchRandomElementOfSet(copy);
		copy.remove(cutOut);
		for(Set<T> set: powerSet(copy))
		{
			output.add(set);
			Set<T> withCutOut = new HashSet<>(set);
			withCutOut.add(cutOut);
			output.add(withCutOut);
		}
	
		return output;
	}
	
	public static <T> Set<Set<T>> allSubSetsOfSizeN(Set<T> originSet, int n) throws CollectionException
	{

		Set<T> emptySet = new HashSet<>();
		Set<Set<T>> output = new HashSet<>();
		if(n<0) throw new CollectionException("n is to small. It is below Zero.");
		if(n>originSet.size())throw new CollectionException("n is bigger then the Set size.");
		if(originSet==null)throw new CollectionException("Set can't be null.");
		
		if(n==0||originSet.isEmpty()) 
		{
			output.add(emptySet);
			return output;
		}

		if(n==originSet.size())
		{
			output.add(originSet);
			return output;
		}

		for(T t: originSet)
		{		
			Set<T> copy = new HashSet<>(originSet);
			copy.remove(t);

			for(Set<T> set: allSubSetsOfSizeN(copy, n))
			{
				output.add(set);
			}
		}

		return output;
	}

	public static Set<Integer> getSetOfFirstNIntegers(int n)
	{

		Set<Integer> output = new HashSet<>();

		for(int i=0;i<n;i++)output.add(i);
		
		return output;
	}

	public static Set<Character> getSetOfFirstNLatinLetters(int n)
	{

		Set<Character> output = new HashSet<>();

		
		for(int i=0;i<n;i++)
		{
			int num = 'a';
			int newNum = num+i;
			char newChar = (char)newNum;
			output.add(newChar);
		}
		
		return output;
	}
}