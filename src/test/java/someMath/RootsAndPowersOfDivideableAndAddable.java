package someMath;


import org.junit.jupiter.api.Test;

class RootsAndPowersOfDivideableAndAddable {

	@Test
	void test() throws InterfaceNumberException 
	{
		ComplexNrDouble cnd = new ComplexNrDouble(0, 1);
		ComplexNrDouble t = SmallTools.getNthPotenz(cnd, 2);
		
		//Hmm algo doesnt work for neutral one; it just finds the trivial solution.
		//Problem is there is more than one solution in the case of complex Nr..
		ComplexNrDouble v = SmallTools.getNthRoot(cnd.getNeutralOne(), 4);
		System.out.println(t);
		System.out.println(v);
		ComplexNrDouble thrdRoot = SmallTools.getNthRoot(cnd, 3);
		System.out.println(SmallTools.getNthPotenz(thrdRoot, 3));//Good approximation.
		
		assert(t.equals(new ComplexNrDouble(-1, 0)));
	}

}