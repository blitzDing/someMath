package someMath;


import org.junit.jupiter.api.Test;

class NaturalNumberTest
{

	@Test
	void isGreaterTest() throws NaturalNumberException
	{
		int z1 = SmallTools.randomInt(1000, 0);
		int z2 = SmallTools.randomInt(1000, 0);

		NaturalNumber a = new NaturalNumber(z1);
		NaturalNumber b = new NaturalNumber(z2);
		
		boolean isEqual = !a.isGreaterThen(b)&&!a.isSmallerThen(b);
		boolean isNotEqual = a.isGreaterThen(b)==!a.isSmallerThen(b);
		
		assert(!(isEqual&&isNotEqual));//Is that an Xor?
	}

	@Test
	void subtractionAdditionMultiplicationTest() throws NaturalNumberException
	{

		int z1 = SmallTools.randomInt(2000, 1001);
		int z2 = SmallTools.randomInt(1000, 0);

		NaturalNumber a = new NaturalNumber(z1);
		NaturalNumber b = new NaturalNumber(z2);

		NaturalNumber sum = (a.addWith(b));
		NaturalNumber diff = (a.subtract(b));
		
		boolean check = (a.multiplyWith(a)).subtract(b.multiplyWith(b)).equals(sum.multiplyWith(diff));
		
		assert(check);
		
		System.out.println(check);
	}
	
	@Test
	void divisonTest() throws NaturalNumberException, DivisionByZeroException, CollectionException, RNumException
	{
		
		NaturalNumber a = new NaturalNumber(17);
		NaturalNumber b = new NaturalNumber(3);
		NaturalNumber c = new NaturalNumber(12);
		NaturalNumber e = new NaturalNumber(5);
		NaturalNumber f = new NaturalNumber(4);

		NaturalNumber d1 = a.divideBy(b);
		System.out.println("D1: " + d1);
		assert(d1.equals(e));
		
		NaturalNumber d2 = c.divideBy(b);
		System.out.println("D2: " + d2);
		assert(d2.equals(f));
	}

}