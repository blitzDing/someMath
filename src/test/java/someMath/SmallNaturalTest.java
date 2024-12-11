package someMath;

import org.junit.jupiter.api.Test;

import someMath.exceptions.CollectionException;
import someMath.exceptions.DivisionByZeroException;
import someMath.exceptions.NaturalNumberException;
import someMath.exceptions.RNumException;

public class SmallNaturalTest
{

	@Test
	public void isGreaterTest() throws NaturalNumberException
	{
		int z1 = SmallTools.randomInt(500, 0);
		int z2 = SmallTools.randomInt(500, 0);

		SmallNatural a = new SmallNatural(z1);
		SmallNatural b = new SmallNatural(z2);
		
		boolean isEqual = !a.isGreaterThen(b)&&!a.isSmallerThen(b);
		boolean isNotEqual = a.isGreaterThen(b)==!a.isSmallerThen(b);
		
		assert(!(isEqual&&isNotEqual));//Is that an Xor?
	}

	@Test
	public void subtractionAdditionMultiplicationTest() throws NaturalNumberException, RNumException, CloneNotSupportedException, DivisionByZeroException, CollectionException
	{

		int z1 = SmallTools.randomInt(31, 21);
		int z2 = SmallTools.randomInt(20, 1);
		int z3 = SmallTools.randomInt(20, 1);

		SmallNatural a = new SmallNatural(z1);
		SmallNatural b = new SmallNatural(z2);
		

		SmallNatural sum = (a.add(b));
		SmallNatural diff = (a.subtract(b));

		SmallNatural a2 = a.multiplyWith(a);
		SmallNatural b2 = b.multiplyWith(b);

		boolean check = (a2.subtract(b2)).equals(sum.multiplyWith(diff));
		
		assert(check);
		
		
		SmallNatural c = new SmallNatural(z3);
		
		SmallNatural ac = a.multiplyWith(c);
		SmallNatural bc = b.multiplyWith(c);
		
		assert(ac.isGreaterThen(bc));
	}

	@Test
	public void divisonTest() throws NaturalNumberException, DivisionByZeroException, CollectionException, RNumException, CloneNotSupportedException
	{
		
		SmallNatural a = new SmallNatural(17);
		SmallNatural b = new SmallNatural(3);
		SmallNatural c = new SmallNatural(12);
		SmallNatural e = new SmallNatural(5);
		SmallNatural f = new SmallNatural(4);

		SmallNatural d1 = a.divideBy(b);//Hole Number division rounding down
		System.out.println("d1 = " + d1 +" = a/b = " +a+"/"+b+".");
		assert(d1.equals(e));
		
		SmallNatural d2 = c.divideBy(b);
		System.out.println("d2 = " + d2 +" = c/b = " +c+"/"+b+".");
		assert(d2.equals(f));
	}
}