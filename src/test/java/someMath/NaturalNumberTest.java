package someMath;

import org.junit.jupiter.api.Test;

import someMath.exceptions.MathException;

public class NaturalNumberTest
{

	@Test
	public void test() throws MathException, NaturalNumberException
	{
		NaturalNumber a = NaturalNumber.zero;
		NaturalNumber b = NaturalNumber.one;
		
		NaturalNumberOps nno = new NaturalNumberOps();
		
		NaturalNumber c = nno.add(a, b);
		
		assert(c.equals(b));
		
		NaturalNumber d = nno.add(b, c);
		
		assert(d.isGreaterThen(b));
	}
}
