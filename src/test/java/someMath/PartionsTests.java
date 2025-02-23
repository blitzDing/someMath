package someMath;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import consoleTools.TerminalXDisplay;
import someMath.exceptions.CollectionException;

public class PartionsTests
{

	@Test
	public void summandsBiggerTest() throws CollectionException
	{
		Set<List<Integer>> set =Partitions.summandsBiggerSet(1, 2, 4);
	
		System.out.println(set);
		
		set = Partitions.summandsOfAnySizeFixedSummandNr(2, 3);
		
		System.out.println(set);
	}
}
