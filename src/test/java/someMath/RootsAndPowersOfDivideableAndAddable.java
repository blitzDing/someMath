package someMath;


import org.junit.jupiter.api.Test;

public class RootsAndPowersOfDivideableAndAddable
{

	@Test
	public void test() throws Exception
	{
				
		ComplexNrDouble cnd = new ComplexNrDouble(0, 1);
		ComplexNrDouble t = SmallTools.getNthPotenz(cnd, 2);
		
		//Hmm algo doesnt work for neutral one; it just finds the trivial solution.
		//Problem is there is more than one solution in the case of complex Nr..
		ComplexNrDouble v = SmallTools.getNthRoot(7, cnd.getNeutralOne(), 4);
		System.out.println(t);
		System.out.println(v);
		ComplexNrDouble thrdRoot = SmallTools.getNthRoot(7, cnd, 3);
		System.out.println(SmallTools.getNthPotenz(thrdRoot, 3));//Good approximation.
		
		assert(t.equals(new ComplexNrDouble(-1, 0)));
	}

}