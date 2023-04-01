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
		int nrOfLayers = layers.size();
		List<Double> sumList = new ArrayList<>();
		sumList.add(layers.get(0));
		
		//Careful it starts with one.
		for(int n=1;n<nrOfLayers;n++)
		{
			sumList.add(layers.get(n) + sumList.get(n-1));
		}

		return sumList;
	}
	
	public static double randomNrBoundBetween(List<Double> layers)
	{
		double sum = layers.stream().reduce(0.0, Double::sum);
		
		return Math.random()*sum;
	}
	
	public static <A> int  betweenWhichElements(double betweener, List<Pair<A, Double>> layers)
	{
		
		int nrOfLayers = layers.size();
		if(betweener<=0)return -1;//Before the first Layer.
		
		List<Double> upStacking = new ArrayList<>();
		for(int n=0;n<nrOfLayers;n++)
		{
			upStacking.add(layers.get(n).getValue());
		}
		
		List<Double> layerSumUpToIndex = sumUpToIndex(upStacking);
		
		if(betweener>layerSumUpToIndex.get(nrOfLayers-1))return nrOfLayers;//Beyond the Last Layer
		
		for(int n=0;n<nrOfLayers;n++)
		{
			if(betweener<=layerSumUpToIndex.get(n))return n;
		}
		
		throw new IllegalArgumentException("Something is Wrong.");
	}
	
	public static <A> List<Double> getRidOfTheGeneric(List<Pair<A, Double>> layers)
	{
		
		int nrOfLayers = layers.size();
		
		List<Double> upStacking = new ArrayList<>();
		for(int n=0;n<nrOfLayers;n++)
		{
			upStacking.add(layers.get(n).getValue());
		}
		
		return upStacking;
	}

	public static <T> T catchRandomElementOfSet(Set<T> set)
	{
		
		List<T> list = new ArrayList<>(set);
		
		return catchRandomElementOfList(list);
	}
	
	public static <T> T catchRandomElementOfList(List<T> list)
	{
		
		int n = list.size();
		int r = (int)(Math.random()*n);
		
		return list.get(r);
	}
	
	public static <T> Set<List<T>> cartesianProduct(List<Set<T>> input)
	{
		
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
 }