package someMath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MathSetClassClosedUnderUnion
{

	public static <T> Set<Set<T>> createFamilie(Set<Set<T>> origin)
	{
		
		if(origin==null)throw new IllegalArgumentException("Set is null.");

		Set<Set<T>> output = new HashSet<>(origin);
		
		for(Set<T> e1: origin)
		{
			
			if(e1==null)throw new IllegalArgumentException("There is a null in the Set.");
			for(Set<T> e2: origin)
			{

				if(e2==null)throw new IllegalArgumentException("There is a null in the Set.");
				if(e1.equals(e2))continue;
				else
				{
					Set<T> a = new HashSet<>();
					a.addAll(e1);
					a.addAll(e2);
					output.add(a);

					if(famTest(output))return output;
				}
			}
		}

		if(!famTest(output))return createFamilie(output);

		return output;
	}

	public static <T> boolean famTest(Set<Set<T>> toBeTested)
	{
		
		if(toBeTested==null)return false;
		
		for(Set<T> member: toBeTested)
		{
			
			if(member==null)throw new IllegalArgumentException("There is a null in the Set.");
			for(Set<T> anotherMember: toBeTested)
			{

				if(anotherMember==null)throw new IllegalArgumentException("There is a null in the Set.");

				Set<T> union = new HashSet<>();
				union.addAll(member);
				union.addAll(anotherMember);
				
				if(!toBeTested.contains(union))return false;
			}
		}
		
		return true;
	}

	public <T> int[] typeOfSetOfSets(Set<Set<T>> origin)throws CollectionException
	{
		
		int size = origin.size();
		int [] type = new int[size];
		
		List<Set<T>> listOfSets = new ArrayList<>(origin);

		for(int n=1;n<size+1;n++)
		{
			type[n-1]=0;
			Set<Set<Set<T>>> subSets = CollectionManipulation.allSubSetsOfSizeN(origin, n);
			
			for(Set<Set<T>> oneCutOut: subSets)
			{
				Set<Set<Set<T>>> copy = new HashSet<>(subSets);
				copy.remove(oneCutOut);
				
				for(Set<T> set: oneCutOut)
				{
					
				}
			}
		
		}
		/*
		type[0]=0;
		for(int n=0;n<size;n++)
		{
			List<Set<T>> copy = new ArrayList<>(origin);
			Set<T> set = copy.get(n);
			copy.remove(set);
			
			for(T t: set)
			{
				if(!multipleContain(t, copy))
				{
					type[0]++;
					break;
				}
			}
		}
		*/
		return null;
	}
	
	public <T, G extends Collection<H>, H extends Collection<T>> boolean multipleContain(T t, G container)
	{
	
		for(H coll: container)
		{
			if(coll.contains(t))return true;
		}
	
		return false;
	}
}