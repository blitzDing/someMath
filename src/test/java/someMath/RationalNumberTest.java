package someMath;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static someMath.RationalNumber.*;


public class RationalNumberTest
{


	static int zero;
	static int one;
	static int zwei;
	static int vier;
	static int drei;
	static int ten;
	static int sex;
	static int five;
	static int hexMex;
	static int max;
	static int fourToo;


	@BeforeAll
	public static void prepare() throws NaturalNumberException, RNumException, DivisionByZeroException, CollectionException, CloneNotSupportedException
	{
		
		zero = 0;
		one = 1;
		zwei = 2;
		vier = 4;
		drei = 3;
		five = 5;
		ten = 10;
		sex = 6;
		hexMex = 16;
		max = Integer.MAX_VALUE;
	}
/*
	@Test
	public void edgeTest() throws RNumException, DivisionByZeroException, CollectionException, CloneNotSupportedException, NaturalNumberException
	{
		
		System.out.println("Edge Test");
		System.out.println("*********");

		RNumException exception = assertThrows(RNumException.class,()->
		{
			RationalNumber maxInt = new RationalNumber(max, drei, zero);
		});
		
		RationalNumber edge = new RationalNumber(max, zwei, drei);
		System.out.println("Edge: " + edge);

		RationalNumber overTheEdge = edge.multiplyWith(edge);
		System.out.println("overTheEdge integerPart: " + overTheEdge.getIntegerPart());
		System.out.println("overTheEdge numerator: " + overTheEdge.getNumerator());
		System.out.println("overTheEdge denominator: " + overTheEdge.getDenominator());
		
		assert(RationalNumber.divisionByZeroMsg.equals(exception.getMessage()));
	}
*/

	@Test
	public void testRN() throws RNumException, NaturalNumberException, DivisionByZeroException, CloneNotSupportedException, CollectionException
	{
		
		System.out.println("\nGeneral Testing");
		System.out.println("***************");
		
		RationalNumber a = new RationalNumber(vier, zwei);
		
		System.out.println("a = " + a);
		assert("2".equals(a.toString()));
		assert(a.getIntegerPart()==zwei);
		assert(a.getNumerator()==zero);
		System.out.println("Denom(a) = " + a.getDenominator());
		assert(a.getDenominator()==one);

		RationalNumber fourToo = new RationalNumber(vier*vier, vier);
		
		assert("4".equals(""+fourToo));
		assert(fourToo.getIntegerPart()==4);
		assert(fourToo.getNumerator()==0);
		assert(fourToo.getDenominator()==1);
				
		RationalNumber c = new RationalNumber(drei, hexMex, ten);
		System.out.println("c = " + c);
		assert("(4 + 3/5)".equals(c.toString()));
		assert(c.getIntegerPart()==vier);
		assert(c.getNumerator()==drei);
		assert(c.getDenominator()==five);
		assert("23/5".equals(c.expandedVersionToString()));//must be not negative!;

		RationalNumber d = new RationalNumber("-5-2/-3");
		System.out.println("d = " + d);		
		assert("(-4-1/3)".equals(d.toString()));
		assert(d.getIntegerPart()==-vier);
		assert(d.getNumerator()==-one);
		assert(d.getDenominator()==drei);
		assert("-13/3".equals(d.expandedVersionToString()));//must be not negative!;

		int seven = 7;
		RationalNumber e = new RationalNumber("5/7");
		System.out.println("e: " + e);
		assert("(5/7)".equals(e.toString()));
		assert(e.getIntegerPart()==0);
		assert(e.getNumerator()==five);
		assert(e.getDenominator()==seven);

		String toParse = "-5-2/3";
		System.out.println("Parsing: "+ toParse);
		RationalNumber f = new RationalNumber(toParse);
		System.out.println("f: " + f);
		assert("(-5-2/3)".equals(f.toString()));
		assert(f.getIntegerPart()==-five);
		assert(f.getNumerator()==-zwei);
		assert(f.getDenominator()==drei);
		assert(false == f.getSign());
		assert("-17/3".equals(f.expandedVersionToString()));
		
		RationalNumber g = new RationalNumber("-50/7");
		System.out.println("g: " + g);
		assert("(-7-1/7)".equals(g.toString()));
		assert(g.getIntegerPart()==-seven);
		assert(g.getNumerator()==-one);
		assert(g.getDenominator()==seven);
		RationalNumber g2 = new RationalNumber("50/-7");
		assert(g.equals(g2));

		RationalNumber h = new RationalNumber("25/7");
		assert("(3 + 4/7)".equals(h.toString()));
		assert(h.getIntegerPart()==drei);
		assert(h.getNumerator()==vier);
		assert(h.getDenominator()==seven);
		assert("25/7".equals(h.expandedVersionToString()));
		
		//RationalNumber i = new RationalNumber("7-20/10");//throws white space illegal argument exception.
		
		RationalNumber cTimesA = c.multiplyWith(a);
		System.out.println("("+ c + ")*(" + a + ") = " +cTimesA);
		
		assert(a.multiplyWith(a).doubleApproximation() == 4.0d);
		
		assert(c.multiplyWith(c).doubleApproximation() == 21.16d);
		
		System.out.println("a: " + a + " ** b: " + fourToo);
		assert(!a.isGreaterThen(fourToo));                //Two positive a = 2.0d. b = a² = 4.0d;
		assert(cTimesA.isGreaterThen(c));           //Two negatives. c = -7 1/7. cTimesA = - 9.2d(-9 1/5).
		assert(!c.isGreaterThen(c));				//Two positives c and c.
		assert(c.multiplyWith(c).isGreaterThen(a)); //a = 2.0d. c² = 21.16d.
		assert(c.isGreaterThen(a));                 //one negative one positive. a = 2.0. c = -4.6.

		RationalNumber quadrat = (a.multiplyWith(a));
		
		System.out.println("Quadrat: " + quadrat);
		System.out.println("a: " + a);
		System.out.println("b: " + fourToo);
		assert(fourToo.equals(quadrat));
		
		assert(fourToo.equals(a.multiplyWith(a)));
		assert(c.multiplyWith(c).isGreaterThen(fourToo)); //c² = 21.16d. b² = 4.0d.
		

		RationalNumber self = quadrat.divideBy(a);
		//self = self.divideBy(a);
		
		System.out.println(a + " = " + self + "?");
		assert(a.equals(self));
		assert(a.divideBy(a).add(rOne).equals(new RationalNumber(zwei, zero, one)));

		RationalNumber samesame = a.add(rZero);
		RationalNumber alsoSame = samesame.multiplyWith(rOne);
		assert(samesame.equals(a));
		assert(samesame.equals(alsoSame));
		
		
		RationalNumber nix = c.multiplyWith(rZero);
		RationalNumber auchNix = fourToo.multiplyWith(rZero);
		assert(nix.equals(rZero));
		assert(nix.equals(auchNix));
	}
	
	@Test
	public void additionTest() throws RNumException, NaturalNumberException, DivisionByZeroException, CollectionException, CloneNotSupportedException
	{

		System.out.println("\nAddition Test Rational Numbers");
		System.out.println("******************************");
		RationalNumber twoThirdst = new RationalNumber(vier, sex);
		RationalNumber fourThirdst = new RationalNumber(vier, sex).add(new RationalNumber(vier, sex));
		
		System.out.println(twoThirdst + " + " + twoThirdst + " = " + fourThirdst);
	}
	
	@Test
	public void integerExponentTest() throws RNumException, NaturalNumberException, DivisionByZeroException, CollectionException, CloneNotSupportedException
	{

		System.out.println("\nTesting taking two a integer Power (not neccesarly natural number)");
		System.out.println("******************************************************************");
		RationalNumber r1 = new RationalNumber(zwei, drei);
		RationalNumber cube = SmallTools.getNthPotenz(r1, 3);
		
		System.out.println(r1 + "³ = " + cube);
		//assert(cube.equals(r1.multiplyWith(r1.multiplyWith(r1))));
	}
}