package someMath;

import java.util.ArrayList;
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
	
	public static <T> boolean criticalTest(Set<Set<T>> toBeTested)
	{

		if(!famTest(toBeTested))return false;//famTest includes null Tests.

		for(Set<T> cutOut: toBeTested)
		{

			Set<Set<T>> smaller = new HashSet<>();
			smaller.addAll(toBeTested);
			smaller.remove(cutOut);

			if(famTest(smaller))return false;
		}

		return true;
	}
}