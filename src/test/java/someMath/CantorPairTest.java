package someMath;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.Test;
import static someMath.NaturalNumber.*;

public class CantorPairTest 
{
	
	@Test
	public void someThing() throws NaturalNumberException, DivisionByZeroException, CollectionException, RNumException
	{

		NaturalNumber bd = SmallTools.cantorPairNr(new NaturalNumber(0), new NaturalNumber(0));
		NaturalNumber bd2 = SmallTools.cantorPairNr(new NaturalNumber(1), new NaturalNumber(0));
		NaturalNumber bd3= SmallTools.cantorPairNr(new NaturalNumber(1), new NaturalNumber(1));
		NaturalNumber bd4 = SmallTools.cantorPairNr(new NaturalNumber(1), new NaturalNumber(2));
		NaturalNumber bd5 = SmallTools.cantorPairNr(new NaturalNumber(2), new NaturalNumber(0));
		NaturalNumber bd6 = SmallTools.cantorPairNr(new NaturalNumber(2), new NaturalNumber(1));
		NaturalNumber bd7 = SmallTools.cantorPairNr(new NaturalNumber(2), new NaturalNumber(2));
		
		System.out.println("(0, 0) = " + bd);
		System.out.println("(1, 0) = " + bd2);
		System.out.println("(1, 1) = " + bd3);
		System.out.println("(1, 2) = " + bd4);
		System.out.println("(2, 0) = " + bd5);
		System.out.println("(2, 1) = " + bd6);
		System.out.println("(2, 2) = " + bd7);
	}
}