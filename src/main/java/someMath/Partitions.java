package someMath;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Partitions
{

	public static Set<List<Integer>> summandsBiggerSet(int minSizeOfSummand, int nrOfSummands, int sum)
	{

		Set<List<Integer>> output = new HashSet<>();
		
		if(minSizeOfSummand*nrOfSummands>sum)return output;

		if(sum==1)
		{
			List<Integer> list = new ArrayList<>();
			list.add(1);
			output.add(list);
			return output;

		}

		if(nrOfSummands==1)
		{
			List<Integer> list = new ArrayList<>();
			list.add(sum);
			output.add(list);
			return output;
		}
		
		for(int i=minSizeOfSummand;i<nrOfSummands*minSizeOfSummand;i++)
		{
			List<Integer> list = new ArrayList<>();
			list.add(i);
			Set<List<Integer>> set =summandsBiggerSet(i, nrOfSummands-1, sum-i);
			if(set.isEmpty())continue;
			
			for(List<Integer> listLeft: set)list.addAll(listLeft);
			
			output.add(list);
		}

		return output;
	}
	
	public static Set<List<Integer>>summandsOfAnySizeFixedSummandNr(int nrOfSummands, int sum)
	{
		
		
		Set<List<Integer>> output = new HashSet<>();
		if(nrOfSummands>sum)return output;
		
		for(int j=1;j<sum;j++)
		{
			output.addAll(summandsBiggerSet(j, nrOfSummands, sum));
		}
		
		return output;
	}
	
	public static Set<List<Integer>> partionsOfNAsLists(int sum)
	{
		Set<List<Integer>> output = new HashSet<>();
		
		if(sum<1)return output;
		
		for(int e=1;e<sum;e++)
		{
			output.addAll(summandsOfAnySizeFixedSummandNr(e, sum));
		}
		return output;

	}
}
