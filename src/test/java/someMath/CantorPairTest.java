package someMath;


import org.junit.jupiter.api.Test;

import someMath.exceptions.CollectionException;
import someMath.exceptions.DivisionByZeroException;
import someMath.exceptions.NaturalNumberException;
import someMath.exceptions.RNumException;


public class CantorPairTest 
{
	
	@Test
	public void someThing() throws NaturalNumberException, DivisionByZeroException, CollectionException, RNumException
	{


		for(int n=0;n<4;n++)
		{
			
			
			int z1 = SmallTools.randomInt(30, 1);
			
			NaturalNumber nn1 = new NaturalNumber(z1);
			NaturalNumber p0 = SmallTools.cantorPairNr(NaturalNumber.zero, nn1);
			display("Pair", NaturalNumber.zero, nn1);
			NaturalNumber nn2 = nn1.add(NaturalNumber.one);
			NaturalNumber p1 = SmallTools.cantorPairNr(nn2, NaturalNumber.zero);
			display("Pair Successor", nn2, NaturalNumber.zero);
			
			assert(nn1.add(NaturalNumber.one).equals(new NaturalNumber(z1+1)));
			assert(p1.equals(p0.add(NaturalNumber.one)));
			
			NaturalNumber p2 = SmallTools.cantorPairNr(nn1, nn2);
			display("other Pair", nn1, nn2);
			NaturalNumber smallNN1 = nn1.subtract(NaturalNumber.one);
			NaturalNumber bigNN2 = nn2.add(NaturalNumber.one);
			NaturalNumber p3 = SmallTools.cantorPairNr(smallNN1, bigNN2);
			display("Other Pair Successor", smallNN1, bigNN2);
			
			assert(p2.add(NaturalNumber.one).equals(p3));
		}
	}
	
	public void display(String name, NaturalNumber a, NaturalNumber b) throws NaturalNumberException, DivisionByZeroException, CollectionException, RNumException
	{
		
		NaturalNumber nn = SmallTools.cantorPairNr(a, b);
		System.out.println(name + "(" + a + ", " + b + ") = " + nn);
	}
}