


import org.junit.jupiter.api.Test;

import someMath.QNumberException;
import someMath.RationalNumber;


public class RationalNumberTest
{

	private static double prettySmall = Math.pow(10, -11);

	@Test
	public void testRN() throws Exception
	{
					
		RationalNumber a = new RationalNumber(4, 2);
		assert("2".equals(a.toString()));
		assert(2 ==   a.integerPart);
		assert(0 ==   a.numerator);
		assert(1 ==   a.denominator);
		
		RationalNumber b = new RationalNumber(16, 4);
		assert("4".equals(b.toString()));
		assert(4 ==   b.integerPart);
		assert(0 ==   b.numerator);
		assert(1 ==   b.denominator);
		
		RationalNumber c = new RationalNumber(false, 3, 16, 10);
		assert("-4 6/10".equals(c.toString()));
		assert(4 == c.integerPart);
		assert(6 == c.numerator);
		assert(10 == c.denominator);
		assert("46/10".equals(c.expandedVersionToString()));//must be not negative!;
		
		RationalNumber d = new RationalNumber("5 2/3");
		assert("5 2/3".equals(d.toString()));
		assert(5 == d.integerPart);
		assert(2 == d.numerator);
		assert(3 == d.denominator);
		assert("17/3".equals(d.expandedVersionToString()));//must be not negative!;

		RationalNumber e = new RationalNumber("5/7");
		assert("5/7".equals(e.toString()));
		assert(0 ==  e.integerPart);
		assert(5 ==  e.numerator);
		assert(7 ==  e.denominator);

		RationalNumber f = new RationalNumber("-5 2/3");
		assert("-5 2/3".equals(f.toString()));
		assert(5 == f.integerPart);
		assert(2 == f.numerator);
		assert(3 == f.denominator);
		assert(false == f.sign);
		assert("17/3".equals(f.expandedVersionToString()));
		
		RationalNumber g = new RationalNumber("-50/7");
		assert("-7 1/7".equals(g.toString()));
		assert(7 == g.integerPart);
		assert(1 == g.numerator);
		assert(7 == g.denominator);

		RationalNumber h = new RationalNumber("25/7");
		assert("3 4/7".equals(h.toString()));
		assert(3 == h.integerPart);
		assert(4 == h.numerator);
		assert(7 == h.denominator);
		assert("25/7".equals(h.expandedVersionToString()));
		
		//RationalNumber i = new RationalNumber("7-20/10");//throws white space illegal argument exception.
		
		RationalNumber cTimesA = c.multiplyWith(a);
		System.out.println("("+ c + ")*(" + a + ") = " +cTimesA);
		assert(cTimesA.doubleApproximation()+ 9.2d < prettySmall);
		
		assert(a.multiplyWith(a).doubleApproximation() == 4.0d);
		
		assert(c.multiplyWith(c).doubleApproximation() == 21.16d);
		
		assert(!a.isGreaterThen(b));                //Two positive a = 2.0d. b = a² = 4.0d;
		assert(c.isGreaterThen(cTimesA));           //Two negatives. c = -7 1/7. cTimesA = - 9.2d(-9 1/5).
		assert(!c.isGreaterThen(c));				//Two positives c and c.
		assert(c.multiplyWith(c).isGreaterThen(a)); //a = 2.0d. c² = 21.16d.
		assert(a.isGreaterThen(c));                 //one negative one positive. a = 2.0. c = -4.6.
		
		assert(b.equals(a.multiplyWith(a)));
		assert(c.multiplyWith(c).isGreaterThen(b)); //c² = 21.16d. b² = 4.0d.
		

		RationalNumber quadrat = (a.multiplyWith(a));
		RationalNumber self = quadrat.divideBy(a);
		//self = self.divideBy(a);
		
		System.out.println(a + " = " + self + "?");
		assert(a.equals(self));
		assert(a.divideBy(a).addWith(RationalNumber.one).equals(new RationalNumber(true, 2, 0, 1)));

		RationalNumber samesame = a.addWith(RationalNumber.zero);
		RationalNumber alsoSame = samesame.multiplyWith(RationalNumber.one);
		assert(samesame.equals(a));
		assert(samesame.equals(alsoSame));
		
		
		RationalNumber nix = c.multiplyWith(RationalNumber.zero);
		RationalNumber auchNix = b.multiplyWith(RationalNumber.zero);
		assert(nix.equals(RationalNumber.zero));
		assert(nix.equals(auchNix));
	}
}