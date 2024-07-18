package someMath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import consoleTools.BashSigns;
import consoleTools.TerminalXDisplay;


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
		if(toBeTested.isEmpty())return true;
		
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

	public static <T> int[] typeOfSetOfSets(Set<Set<T>> origin)throws CollectionException
	{
		
		int size = origin.size();
		int [] type = new int[size];
		
		System.out.println("Origin: \n" + TerminalXDisplay.collectionToString(origin));

		for(int n=1;n<size+1;n++)
		{
			type[n-1]=0;
		}
		
		for(int n=1;n<size+1;n++)
		{
			Set<Set<Set<T>>> subSets = CollectionManipulation.allSubSetsOfSizeN(origin, n);
			printlnBoldAndGreen("All SubSets of Size " + n);
			System.out.println(TerminalXDisplay.collectionToString(subSets)+"\n\n");
			ArrayList<Set<Set<T>>> listOfSubSets = new ArrayList<>(subSets);
			
			for(Set<Set<T>> cutOut: listOfSubSets)
			{

				printlnBoldAndGreen("New CutOut: " + TerminalXDisplay.collectionToString(cutOut));
			
				ArrayList<Set<Set<T>>> l2 = new ArrayList<>();
				l2.addAll(listOfSubSets);

				l2.remove(cutOut);
				assert(l2.size()+1==listOfSubSets.size());

				Set<T> intersection = intersectHoleSetOfSets(cutOut);
				System.out.println("CutOut Intersection: " + TerminalXDisplay.collectionToString(intersection));
			
				for(T t: intersection)
				{
				
					System.out.println("Testing: " + t);

					boolean isExclusive = true;
					for(Set<Set<T>> subSet: l2)
					{

						System.out.println("Test subSet: " + TerminalXDisplay.collectionToString(subSet));
						System.out.println("Intersection of the above: " + TerminalXDisplay.collectionToString(intersectHoleSetOfSets(subSet)));

						if(multipleContain(t, subSet))
						{
							isExclusive = false;
							System.out.println(t+" is not exclusive.");
							break;
						}
						else
						{
							System.out.println(t + " seems exclusive so far;");
						}
					
					}
					if(isExclusive)
					{
						type[n-1]++;
						break;
					}
				}
			}
		}
		
		return type;
	}
	
	public static <C> Set<Set<Set<C>>> findClusters(Set<Set<C>> origin) throws CollectionException
	{

		Set<Set<Set<C>>> output = new HashSet<>();
		if(origin.isEmpty())return output;


		Set<C> set = CollectionManipulation.catchRandomElementOfSet(origin);
		Set<Set<C>> container = new HashSet<>();
		container.add(set);
		output.add(container);
		
		//This loop "Groups". It is important that C has it's equal and
		//hashCode Method overwritten and well defined.
		for(C c: set)
		{

			Set<Set<C>> g2 = findContainingSets(c, origin);
			g2.remove(set);
			if(g2.isEmpty())break;

			Set<Set<Set<C>>> g3 = findClusters(g2);
			output.addAll(g3);
		}

		return output;
	}
	
	public static <C> Set<Set<C>> findContainingSets(C c, Set<Set<C>> origin)
	{

		Set<Set<C>> output = new HashSet<>();

		if(!multipleContain(c, origin))return output;
		
		for(Set<C> set: origin)
		{
			if(set.contains(c))output.add(set);
		}

		return output;
	}
	
	public static <T, G extends Collection<H>, H extends Collection<T>> boolean multipleContain(T t, G container)
	{
	
		for(H coll: container)
		{
			if(!coll.contains(t))return false;
		}
	
		return true;
	}
	
	public static <T> Set<T> intersectHoleSetOfSets(Set<Set<T>> origin) throws CollectionException
	{
		
		Set<T> output = new HashSet<>();
		
		for(Set<T> set: origin)
		{
			for(T t: set)
			{
				if(multipleContain(t, origin))output.add(t);
			}
		}

		return output;
	}
	
	public static void printlnBoldAndGreen(String s)
	{
		System.out.println(BashSigns.boldGBCPX+s+BashSigns.boldGBCSX);
	}

	public static <T>  Map<String, Set<T>> makeMapOfSets(Collection<Set<T>> origin) throws CollectionException
	{
		int size = origin.size();
		Set<Character> names = CollectionManipulation.getSetOfFirstNLatinLettersUppercase(size);
		Map<String, Set<T>> output = new HashMap<>();

		for(Set<T> set: origin)
		{
			Character cName = CollectionManipulation.catchRandomElementOfSet(names);
			String name = cName.toString();
			output.put(name, set);
			names.remove(cName);
		}
		
		
		return output;
	}
}