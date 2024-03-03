package someMath;



import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static someMath.SmallNatural.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static someMath.RationalNumber.*;



public class RationalNumberTest
{

	private static double prettySmall = Math.pow(10, -11);

	static SmallNatural zwei;
	static SmallNatural vier;
	static SmallNatural drei;
	static SmallNatural ten;
	static SmallNatural sex;
	static SmallNatural five;
	static SmallNatural hexMex;
	static SmallNatural maxSN;
	static RationalNumber fourToo;


	@BeforeAll
	public static void prepare() throws NaturalNumberException, RNumException, DivisionByZeroException, CollectionException, CloneNotSupportedException
	{
		
		zwei = new SmallNatural(2);
		vier = new SmallNatural(4);
		drei = new SmallNatural(3);
		five = new SmallNatural(5);
		ten = new SmallNatural(10);
		sex = drei.multiplyWith(zwei);
		hexMex = new SmallNatural(16);
		maxSN = new SmallNatural(SmallNatural.max);
		fourToo = new RationalNumber(hexMex, vier);
	}

	@Test
	public void edgeTest() throws RNumException, DivisionByZeroException, CollectionException, CloneNotSupportedException, NaturalNumberException
	{
		
		System.out.println("Edge Test");
		System.out.println("*********");

		NaturalNumberException exception = assertThrows(NaturalNumberException.class,()->
		{
			RationalNumber maxInt = new RationalNumber(true, maxSN, drei, zwei);
		});
		
		RationalNumber edge = new RationalNumber(true, maxSN, zwei, drei);
		System.out.println("Edge: " + edge);

		RationalNumber overTheEdge = edge.multiplyWith(edge);
		assert(overTheEdge.getIntegerPart().getNumberCore()==SmallNatural.max);
		assert(overTheEdge.getNumerator().getNumberCore()==0);
		assert(overTheEdge.getDenominator().getNumberCore()==1);
		
		assert(SmallNatural.valueOutOfBounds.equals(exception.getMessage()));
	}

	@Test
	public void testRN() throws RNumException, NaturalNumberException, DivisionByZeroException, CloneNotSupportedException, CollectionException
	{
		
		System.out.println("\nGeneral Testing");
		System.out.println("***************");
		
		RationalNumber a = new RationalNumber(vier, zwei);
		
		assert("2".equals(a.toString()));
		assert(a.getIntegerPart().equals(zwei));
		assert(a.getNumerator().equals(snZero));
		assert(a.getDenominator().equals(snOne));

		assert("4".equals(fourToo.toString()));
		assert(fourToo.getIntegerPart().equals(vier));
		assert(fourToo.getNumerator().equals(snZero));
		assert(fourToo.getDenominator().equals(snOne));
				
		RationalNumber c = new RationalNumber(false, drei, hexMex, ten);
		System.out.println(c);
		assert("-(4 + 3/5)".equals(c.toString()));
		assert(c.getIntegerPart().equals(vier));
		assert(c.getNumerator().equals(drei));
		assert(c.getDenominator().equals(five));
		assert("23/5".equals(c.expandedVersionToString()));//must be not negative!;
		
		RationalNumber d = new RationalNumber("5 2/3");
		assert("(5 + 2/3)".equals(d.toString()));
		assert(d.getIntegerPart().equals(five));
		assert(d.getNumerator().equals(zwei));
		assert(d.getDenominator().equals(drei));
		assert("17/3".equals(d.expandedVersionToString()));//must be not negative!;

		SmallNatural seven = new SmallNatural(7);
		RationalNumber e = new RationalNumber("5/7");
		assert("(5/7)".equals(e.toString()));
		assert(e.getIntegerPart().equals(snZero));
		assert(e.getNumerator().equals(five));
		assert(e.getDenominator().equals(seven));

		RationalNumber f = new RationalNumber("-5 2/3");
		assert("-(5 + 2/3)".equals(f.toString()));
		assert(f.getIntegerPart().equals(five));
		assert(f.getNumerator().equals(zwei));
		assert(f.getDenominator().equals(drei));
		assert(false == f.sign);
		assert("17/3".equals(f.expandedVersionToString()));
		
		RationalNumber g = new RationalNumber("-50/7");
		assert("-(7 + 1/7)".equals(g.toString()));
		assert(g.getIntegerPart().equals(seven));
		assert(g.getNumerator().equals(snOne));
		assert(g.getDenominator().equals(seven));

		RationalNumber h = new RationalNumber("25/7");
		assert("(3 + 4/7)".equals(h.toString()));
		assert(h.getIntegerPart().equals(drei));
		assert(h.getNumerator().equals(vier));
		assert(h.getDenominator().equals(seven));
		assert("25/7".equals(h.expandedVersionToString()));
		
		//RationalNumber i = new RationalNumber("7-20/10");//throws white space illegal argument exception.
		
		RationalNumber cTimesA = c.multiplyWith(a);
		System.out.println("("+ c + ")*(" + a + ") = " +cTimesA);
		assert(cTimesA.doubleApproximation()+ 9.2d < prettySmall);
		
		assert(a.multiplyWith(a).doubleApproximation() == 4.0d);
		
		assert(c.multiplyWith(c).doubleApproximation() == 21.16d);
		
		System.out.println("a: " + a + " ** b: " + fourToo);
		assert(!a.isGreaterThen(fourToo));                //Two positive a = 2.0d. b = a² = 4.0d;
		assert(c.isGreaterThen(cTimesA));           //Two negatives. c = -7 1/7. cTimesA = - 9.2d(-9 1/5).
		assert(!c.isGreaterThen(c));				//Two positives c and c.
		assert(c.multiplyWith(c).isGreaterThen(a)); //a = 2.0d. c² = 21.16d.
		assert(a.isGreaterThen(c));                 //one negative one positive. a = 2.0. c = -4.6.

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
		assert(a.divideBy(a).addWith(rOne).equals(new RationalNumber(true, zwei, snZero, snOne)));

		RationalNumber samesame = a.addWith(rZero);
		RationalNumber alsoSame = samesame.multiplyWith(rOne);
		assert(samesame.equals(a));
		assert(samesame.equals(alsoSame));
		
		
		RationalNumber nix = c.multiplyWith(rZero);
		RationalNumber auchNix = fourToo.multiplyWith(rZero);
		assert(nix.equals(rZero));
		assert(nix.equals(auchNix));
	}
	
	@Test
	void additionTest() throws RNumException, NaturalNumberException, DivisionByZeroException, CollectionException, CloneNotSupportedException
	{

		System.out.println("\nAddition Test Rational Numbers");
		System.out.println("******************************");
		RationalNumber twoThirdst = new RationalNumber(vier, sex);
		RationalNumber fourThirdst = new RationalNumber(vier, sex).addWith(new RationalNumber(vier, sex));
		
		System.out.println(twoThirdst + " + " + twoThirdst + " = " + fourThirdst);
	}
	
	@Test
	void integerExponentTest() throws RNumException, NaturalNumberException, DivisionByZeroException, CollectionException, CloneNotSupportedException
	{

		System.out.println("\nTesting taking two a integer Power (not neccesarly natural number)");
		System.out.println("******************************************************************");
		RationalNumber r1 = new RationalNumber(zwei, drei);
		RationalNumber cube = SmallTools.getNthPotenz(r1, 3);
		
		System.out.println(r1 + "³ = " + cube);
		//assert(cube.equals(r1.multiplyWith(r1.multiplyWith(r1))));
	}
}