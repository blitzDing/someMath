package myToolBoxMaven;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import someMath.RationalNumber;


public class RationalNumberTests 
{

	@Test
	public void test() 
	{
		/*
        int negativeZero = -0;
        int positiveZero = +0;
        System.out.println( negativeZero == positiveZero );
        System.out.println( negativeZero > positiveZero );
        System.out.println( negativeZero < positiveZero );
        System.out.println( Math.min( negativeZero, positiveZero ) );
        System.out.println( Math.max( negativeZero, positiveZero ) );
        //System.out.println( 1/ positiveZero );
        //System.out.println( 1/ negativeZero );
        */
		
		RationalNumber a = new RationalNumber(4, 2);
		assertEquals("2", a.toString());
		assertEquals(2,   a.integerPart);
		assertEquals(0,   a.numerator);
		assertEquals(1,   a.denominator);
		
		RationalNumber b = new RationalNumber(16, 4);
		assertEquals("4", b.toString());
		assertEquals(4,   b.integerPart);
		assertEquals(0,   b.numerator);
		assertEquals(1,   b.denominator);
		
		RationalNumber c = new RationalNumber(false, 3, 16, 10);
		assertEquals("-4 6/10", c.toString());
		assertEquals(4,        c.integerPart);
		assertEquals(6,        c.numerator);
		assertEquals(10,       c.denominator);
		assertEquals("46/10", c.expandedVersionToString());//must be not negative!;
		
		RationalNumber d = new RationalNumber("5 2/3");
		assertEquals("5 2/3", d.toString());
		assertEquals(5,       d.integerPart);
		assertEquals(2,       d.numerator);
		assertEquals(3,       d.denominator);
		assertEquals("17/3", d.expandedVersionToString());//must be not negative!;

		RationalNumber e = new RationalNumber("5/7");
		assertEquals("5/7", e.toString());
		assertEquals(0,     e.integerPart);
		assertEquals(5,     e.numerator);
		assertEquals(7,     e.denominator);

		RationalNumber f = new RationalNumber("-5 2/3");
		assertEquals("-5 2/3", f.toString());
		assertEquals(5,     f.integerPart);
		assertEquals(2,     f.numerator);
		assertEquals(3,     f.denominator);
		assertEquals(f.sign, false);
		assertEquals("17/3", f.expandedVersionToString());
		
		RationalNumber g = new RationalNumber("-50/7");
		assertEquals("-7 1/7", g.toString());
		assertEquals(7,     g.integerPart);
		assertEquals(1,     g.numerator);
		assertEquals(7,     g.denominator);

		RationalNumber h = new RationalNumber("25/7");
		assertEquals("3 4/7", h.toString());
		assertEquals(3,     h.integerPart);
		assertEquals(4,     h.numerator);
		assertEquals(7,     h.denominator);
		assertEquals("25/7", h.expandedVersionToString());
		
		//RationalNumber i = new RationalNumber("7-20/10");//throws white space illegal argument exception.
		
		RationalNumber cTimesA = c.multiplyWith(a);
		assert(cTimesA.doubleApproximation() == -9.2d);
		
		assert(a.multiplyWith(a).doubleApproximation() == 4.0d);
		
		assert(c.multiplyWith(c).doubleApproximation() == 21.16d);
		
		assert(!a.isGreaterThen(b));                //Two positive a = 2.0d. b = a² = 4.0d;
		assert(c.isGreaterThen(cTimesA));           //Two negatives. c = -7 1/7. cTimesA = - 9.2d(-9 1/5).
		assert(!c.isGreaterThen(c));				//Two positives c and c.
		assert(c.multiplyWith(c).isGreaterThen(a)); //a = 2.0d. c² = 21.16d.
		assert(a.isGreaterThen(c));                 //one negative one positive. a = 2.0. c = -4.6.
		
		assert(b.equals(a.multiplyWith(a)));
		assert(c.multiplyWith(c).isGreaterThen(b)); //c² = 21.16d. b² = 4.0d.
		
		assertEquals(a.multiplyWith(a).divideBy(a), a);
		assertEquals(a.divideBy(a).addWith(RationalNumber.one), new RationalNumber(true, 2, 0, 1));

		RationalNumber samesame = a.addWith(RationalNumber.zero);
		RationalNumber alsoSame = samesame.multiplyWith(RationalNumber.one);
		assertEquals(samesame, a);
		assertEquals(samesame, alsoSame);
		
		RationalNumber nix = c.multiplyWith(RationalNumber.zero);
		RationalNumber auchNix = b.multiplyWith(RationalNumber.zero);
		assertEquals(nix, RationalNumber.zero);
		assertEquals(nix, auchNix);
	}
}