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
		
		int minSize = 1;
		int nrOfSummands = 3;
		int sum = 10;
		Set<List<Integer>> set =Partitions.summandsBiggerSet(minSize, nrOfSummands, sum);
		
		System.out.println(set);
	}
}
