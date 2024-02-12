package someMath;



import org.junit.jupiter.api.Test;

import static someMath.NaturalNumber.*;
import static someMath.RationalNumber.*;



public class RationalNumberTest
{

	private static double prettySmall = Math.pow(10, -11);

	@Test
	public void testRN() throws RNumException, NaturalNumberException, DivisionByZeroException, CloneNotSupportedException
	{
		
		NaturalNumber zwei = new NaturalNumber(2);
		NaturalNumber vier = new NaturalNumber(4);
		
		RationalNumber a = new RationalNumber(vier, zwei);
		
		assert("2".equals(a.toString()));
		assert(a.integerPart.equals(zwei));
		assert(a.numerator.equals(zero));
		assert(a.denominator.equals(one));
		
		NaturalNumber hexMex = new NaturalNumber(16);
		RationalNumber b = new RationalNumber(hexMex, vier);
		assert("4".equals(b.toString()));
		assert(b.integerPart.equals(vier));
		assert(b.numerator.equals(zero));
		assert(b.denominator.equals(one));
		
		NaturalNumber drei = new NaturalNumber(3);
		NaturalNumber ten = new NaturalNumber(10);
		NaturalNumber sex = drei.multiplyWith(zwei);
		
		RationalNumber c = new RationalNumber(false, drei, hexMex, ten);
		System.out.println(c);
		assert("-(4 + 6/10)".equals(c.toString()));
		assert(c.integerPart.equals(vier));
		assert(c.numerator.equals(sex));
		assert(c.denominator.equals(ten));
		assert("46/10".equals(c.expandedVersionToString()));//must be not negative!;
		
		NaturalNumber five = new NaturalNumber(5);
		RationalNumber d = new RationalNumber("5 2/3");
		assert("(5 + 2/3)".equals(d.toString()));
		assert(d.integerPart.equals(five));
		assert(d.numerator.equals(zwei));
		assert(d.denominator.equals(drei));
		assert("17/3".equals(d.expandedVersionToString()));//must be not negative!;

		NaturalNumber seven = new NaturalNumber(7);
		RationalNumber e = new RationalNumber("5/7");
		assert("(5/7)".equals(e.toString()));
		assert(e.integerPart.equals(zero));
		assert(e.numerator.equals(five));
		assert(e.denominator.equals(seven));

		RationalNumber f = new RationalNumber("-5 2/3");
		assert("-(5 + 2/3)".equals(f.toString()));
		assert(f.integerPart.equals(five));
		assert(f.numerator.equals(zwei));
		assert(f.denominator.equals(drei));
		assert(false == f.sign);
		assert("17/3".equals(f.expandedVersionToString()));
		
		RationalNumber g = new RationalNumber("-50/7");
		assert("-(7 + 1/7)".equals(g.toString()));
		assert(g.integerPart.equals(seven));
		assert(g.numerator.equals(one));
		assert(g.denominator.equals(seven));

		RationalNumber h = new RationalNumber("25/7");
		assert("(3 + 4/7)".equals(h.toString()));
		assert(h.integerPart.equals(drei));
		assert(h.numerator.equals(vier));
		assert(h.denominator.equals(seven));
		assert("25/7".equals(h.expandedVersionToString()));
		
		//RationalNumber i = new RationalNumber("7-20/10");//throws white space illegal argument exception.
		
		RationalNumber cTimesA = c.multiplyWith(a);
		System.out.println("("+ c + ")*(" + a + ") = " +cTimesA);
		assert(cTimesA.doubleApproximation()+ 9.2d < prettySmall);
		
		assert(a.multiplyWith(a).doubleApproximation() == 4.0d);
		
		assert(c.multiplyWith(c).doubleApproximation() == 21.16d);
		
		System.out.println("a: " + a + " ** b: " + b);
		assert(!a.isGreaterThen(b));                //Two positive a = 2.0d. b = a² = 4.0d;
		assert(c.isGreaterThen(cTimesA));           //Two negatives. c = -7 1/7. cTimesA = - 9.2d(-9 1/5).
		assert(!c.isGreaterThen(c));				//Two positives c and c.
		assert(c.multiplyWith(c).isGreaterThen(a)); //a = 2.0d. c² = 21.16d.
		assert(a.isGreaterThen(c));                 //one negative one positive. a = 2.0. c = -4.6.

		RationalNumber quadrat = (a.multiplyWith(a));
		
		System.out.println("Quadrat: " + quadrat);
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		assert(b.equals(quadrat));
		
		assert(b.equals(a.multiplyWith(a)));
		assert(c.multiplyWith(c).isGreaterThen(b)); //c² = 21.16d. b² = 4.0d.
		

		RationalNumber self = quadrat.divideBy(a);
		//self = self.divideBy(a);
		
		System.out.println(a + " = " + self + "?");
		assert(a.equals(self));
		assert(a.divideBy(a).addWith(rOne).equals(new RationalNumber(true, zwei, zero, one)));

		RationalNumber samesame = a.addWith(rZero);
		RationalNumber alsoSame = samesame.multiplyWith(rOne);
		assert(samesame.equals(a));
		assert(samesame.equals(alsoSame));
		
		
		RationalNumber nix = c.multiplyWith(rZero);
		RationalNumber auchNix = b.multiplyWith(rZero);
		assert(nix.equals(rZero));
		assert(nix.equals(auchNix));
	}
}