package someMath;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static someMath.RationalNumber.*;
import static someMath.NaturalNumber.*;
public class ComplexRationalNrTest 
{

	int millisWait = 0;

	static RationalNumber rMinusOne;

	ComplexRationalNr cOne = new ComplexRationalNr(rOne, rZero);
	ComplexRationalNr i = new ComplexRationalNr(rZero, rOne);
	ComplexRationalNr minusI = new ComplexRationalNr(rZero, rMinusOne);
	ComplexRationalNr minusOne = new ComplexRationalNr(rMinusOne, rZero);
	
	@BeforeAll
	public static void init() throws RNumException, NaturalNumberException, CloneNotSupportedException, DivisionByZeroException, CollectionException
	{
		rMinusOne = rZero.subtract(rOne);
	}
		
	@Test
	public void testConjugate() throws InterruptedException, NaturalNumberException, RNumException, CloneNotSupportedException, IllegalArgumentException, DivisionByZeroException, CollectionException
	{
		
		System.out.println("Testing Conjugate of ComplexNr");
		System.out.println("******************************");
		

		RationalNumber arg1 = new RationalNumber(2, 3);
		RationalNumber arg2 = new RationalNumber(11, 7);
			
		ComplexRationalNr a = new ComplexRationalNr(arg1, arg2);
		
		System.out.println("a: " + a);
		System.out.println("Conjugate of a: " + a.getConjugate());

		RationalNumber amount = a.getAmount();
		System.out.println("amount of a = " + amount);

		RationalNumber img = a.multiplyWith(a.getConjugate()).getImaginaryPart();
		assert(img.equals(rZero));
		
		System.out.println("a*conjugate(a) = " + a.multiplyWith(a.getConjugate()));
		//ComplexRationalNr theRealA = a.addWith(a.getConjugate());
		//ComplexRationalNr realA = new ComplexRationalNr(a.getRealPart(), rZero);
		//ComplexRationalNr cTwo = new ComplexRationalNr(new RationalNumber(zwei, one), rZero);
		//ComplexRationalNr theRealARealPart = realA.multiplyWith(cTwo);
		//RationalNumber img2 = theRealA.getImaginaryPart();
		//assert(img2.equals(rZero));
		//System.out.println("a + conjugate(a) = " + theRealA);
		//assert(theRealARealPart.getRealPart().equals(theRealA.getRealPart()));
		
		ComplexRationalNr b = new ComplexRationalNr(arg2, arg1);
		
		//amount = b.getAmount();
		
		System.out.println("b: " + b);
		System.out.println("Conjugate of b: " + b.getConjugate());

		img = b.multiplyWith(b.getConjugate()).getImaginaryPart();
		assert(img.equals(rZero));
		
		System.out.println("b*conjugate(b) = " + b.multiplyWith(b.getConjugate()));
		RationalNumber amountSquaredA = a.multiplyWith(a.getConjugate()).getRealPart();
		RationalNumber amountSquaredB = b.multiplyWith(b.getConjugate()).getRealPart();
		System.out.println("Amount squared a: " + amountSquaredA);
		System.out.println("Amount squared a: " + amountSquaredB);
	}

	/*
	@Test
	public void testAddition() throws InterruptedException
	{
			
		System.out.println("Testing Addition.");
		System.out.println("*****************");
		
		Thread.sleep(millisWait);
			
		for(int n=0;n<6;n++)
		{
			ComplexRationalNr z = createRndmCNrQuadBounds(-5,5);
			ComplexRationalNr anti = new ComplexRationalNr(-z.getRealPart(), -z.getImaginaryPart());
				
			display("z", z);
			display("anti-z", anti);
			display("sum", z.addWith(anti));
				
			assert(z.addWith(anti).equals(z.getNeutralZero()));
				
			ComplexRationalNr times2 = z.addWith(z);
			display("z+z", times2);
				
			assert( Math.abs( times2.Arg()-z.Arg() ) < prettySmall);
			assert( Math.abs( times2.amount()-2*z.amount()) < prettySmall);

			ComplexRationalNr times3 = times2.addWith(z);
			display("z+z+z", times3);
				
			assert( Math.abs( times3.Arg()-z.Arg() ) < prettySmall);
			assert( Math.abs( times3.amount()-3*z.amount() ) < prettySmall);
		}
	}
	
	@Test
	public void testSubtraction()
	{
			
		System.out.println("Testing Subtraction.");
		System.out.println("********************");
			
		for(int n=0;n<6;n++)
		{
			ComplexRationalNr c = createRndmCNrQuadBounds(-3,3);
			ComplexRationalNr d = c.subtract(c);
			display("c", c);
			display("c-c", d);
					
			assert(d.equals(c.getNeutralZero()));
			
			ComplexRationalNr ur = new ComplexRationalNr(rZero, rZero);
			ComplexRationalNr minusC = ur.subtract(c);
			
			assert(minusC.amount()==c.amount());
		}
	}
	
	@Test
	public void testMultiplication() throws InterruptedException
	{
			
		System.out.println("Testing Multiplication.");
		System.out.println("***********************");
		
		Thread.sleep(millisWait);
		
		display("i", i);
		display("-i", minusI);
		display("product", i.multiplyWith(minusI));
			
		assert(i.multiplyWith(minusI).equals(one));
		
		for(int n=0;n<4;n++)
		{
			ComplexRationalNr firstQuadNr = createRndmCNr1stQuadrant(5);
			ComplexRationalNr product = firstQuadNr.multiplyWith(i);
			display("a", firstQuadNr);
			display("a*i", product);

			
			assert((product.Arg()-firstQuadNr.Arg()-Math.PI/2) < prettySmall);
			assert(Math.abs( product.Arg()-i.Arg()-firstQuadNr.Arg() ) < prettySmall);
		}
		
		for(int n=0;n<10;n++)
		{

			ComplexRationalNr a = createRndmCNr1stQuadrant(10);
			ComplexRationalNr b = createRndmCNr2ndQuadrant(10);


			ComplexRationalNr p1 = a.multiplyWith(b);
				
			Double ra = a.amount();
			Double rb = b.amount();
			Double rp1 = p1.amount();
				
			Double wa = Math.toDegrees(a.Arg());
			Double wb = Math.toDegrees(b.Arg());
			Double wp1 = Math.toDegrees(p1.Arg());
				
			display("a", a);
			display("b", b);
			display("a*b", p1);
				
			double sum = wa+wb;
				
			if(sum >  180)
			{
					
				System.out.println("HEY!!!!! Old sum: " + sum);
				System.out.println("         New sum: " + (sum-360));
				System.out.println("             wp1: " + wp1);
					
				sum =  -360+sum;
			}
				
			if(sum < -180)
			{
					
					
				System.out.println("ANTI-HEY!!!!! Old sum: " + sum);
				System.out.println("              New sum: " + (sum+360));
				System.out.println("                  wp1: " + wp1);

				sum = sum+360;
			}

			System.out.println("rp1 - (ra*rb) = " + (rp1 - (ra*rb)));
			System.out.println("wp1 - (wa+wb) = " + (wp1 - (sum)) + "\n");

			assert(Math.abs( rp1 - ra*rb ) < prettySmall);
			if(wp1*sum>0)assert(Math.abs( wp1 - sum )   < prettySmall);
			else assert(Math.abs( wp1 + sum) < prettySmall);
				
			ComplexRationalNr c = createRndmCNr3rdQuadrant(10);
			ComplexRationalNr d = createRndmCNr4thQuadrant(10);

			ComplexRationalNr p2 = c.multiplyWith(d);
				
			Double rc = c.amount();
			Double rd = d.amount();
			Double rp2 = p2.amount();
				
			Double wc = Math.toDegrees(c.Arg());
			Double wd = Math.toDegrees(d.Arg());
			Double wp2 = Math.toDegrees(p2.Arg());
				
			display("c", c);
			display("d", d);
			display("c*d", p2);
				
			sum = wc+wd;
				
			if(sum >  180)
			{
					
				System.out.println("HEY!!!!! Old sum: " + sum);
				System.out.println("         New sum: " + (sum-360));
				System.out.println("             wp2: " + wp2);
					
				sum =  -360+sum;
			}
				
			if(sum < -180)
			{
					
					
				System.out.println("ANTI-HEY!!!!! Old sum: " + sum);
				System.out.println("              New sum: " + (sum+360));
				System.out.println("                  wp2: " + wp2);

				sum = sum+360;

			}

			System.out.println("rp2 - (rc*rd) = " + (rp2 - (rc*rd)));
			System.out.println("wp2 - (wc+wd) = " + (wp2 - (sum)) + "\n");

			assert(Math.abs( rp2 - rc*rd ) < prettySmall);
			if(wp2*sum>0)assert(Math.abs( wp2 - sum )   < prettySmall);
			else assert(Math.abs( wp2 + sum) < prettySmall);
		}
	}

	@Test
	public void testDivision() throws InterruptedException
	{
		
		System.out.println("Testing Division.");
		System.out.println("*****************");

		Thread.sleep(millisWait);

		for(int n=0;n<5;n++)
		{
			ComplexRationalNr a = createRndmCNrQuadBounds(-10,10);
			display("a", a);
			
			ComplexRationalNr b = createRndmCNrQuadBounds(-10, 10);
			display("b", b);

			ComplexRationalNr c = a.multiplyWith(b);
			display("a*b", c);
			
			ComplexRationalNr d = c.divideBy(b);
			display("d=c/b", d);
			
			ComplexRationalNr e = a.subtract(d);
			display("e=a-d", e);

			ComplexRationalNr f = a.divideBy(d);
			display("f=a/d", f);

			assert(Math.abs( a.subtract(d).amount() )  < prettySmall);
			assert(Math.abs( a.divideBy(d).Arg() )  < prettySmall);
			
			System.out.println(a.Arg()+"\n");
		}
			
	}

	@Test
	public void testArgAndAmount() throws InterruptedException
	{
		
		System.out.println("Testing Arg&&Amount");
		System.out.println("*******************");
		
		Thread.sleep(millisWait);
		
		for(int l=0;l<5;l++)
		{
				
			int n = l+1;
				
			ComplexRationalNr q = new ComplexRationalNr(n, n);
				
			display("q", q);
				
			assert(Math.abs( Math.toDegrees(q.Arg()) - 45 )) < prettySmall;
				
			ComplexRationalNr p = new ComplexRationalNr(-n, n);
				
			display("p", p);
			assert Math.abs( Math.toDegrees(p.Arg()) - 135 ) < prettySmall;

			ComplexRationalNr s = new ComplexRationalNr(-n, -n);
				
			display("s", s);
			assert Math.abs( Math.toDegrees(s.Arg()) + 135 ) < prettySmall;
				
			ComplexRationalNr t = new ComplexRationalNr(n, -n);
				
			display("t", t);
			assert Math.abs( Math.toDegrees(t.Arg()) + 45 ) < prettySmall;
				
				
			ComplexRationalNr a = createRndmCNrQuadBounds(10,15); //First quadrant
				
			ComplexRationalNr square = a.multiplyWith(a);
				
			double ra = a.amount();
			double wa = a.Arg();
				
			double rs = square.amount();
			double ws = square.Arg();
				
			display("a", a);
			display("square", square);
				
			System.out.println("rs - ra*ra = " + (rs-ra*ra));
			System.out.println("ws - 2*wa = " + (ws-2*wa));
				
			assert(Math.abs( rs - ra*ra )  < prettySmall);
			assert(Math.abs( ws - 2*wa )   < prettySmall);
		}
	}

	
	@Test
	public void testLogarithm() throws InterruptedException
	{
		
		System.out.println("Testing Logarithm.");
		System.out.println("******************");
		for(int n=0;n<10;n++)
		{
			ComplexRationalNr l = createRndmCNr1stQuadrant(10);
			display("l", l);
		
			ComplexRationalNr loga = l.Log();	
			display("log(l)", loga);
		
			assert((loga.getRealPart()-Math.log(l.amount()))<prettySmall);
			assert((loga.getImaginaryPart()-l.Arg())<prettySmall);
		}
		
		Thread.sleep(millisWait);
		
	}
	
	@Test
	public void testPowers() throws InterruptedException
	{
		//TODO: Debug!!
		System.out.println("Testing powers.");
		System.out.println("***************");
		
		Thread.sleep(millisWait);
		
		ComplexRationalNr basis = new ComplexRationalNr(0, 1);
		ComplexRationalNr exponent = new ComplexRationalNr(2, 0);
		ComplexRationalNr square = basis.toThePowerOf(exponent);

		display("basis", basis);
		display("exponent", exponent);
		display("square", square);
		
		assert(Math.abs( square.getRealPart()+1 )< prettySmall);
		assert( ( square.Arg()==Double.NaN )||( Math.abs(square.Arg())-Math.PI < prettySmall));

		ComplexRationalNr exponent2 = new ComplexRationalNr(3, 0);
		ComplexRationalNr cubic = basis.toThePowerOf(exponent2);

		display("exponent2", exponent2);
		display("cubic", cubic);

		assert(Math.abs( cubic.getImaginaryPart()+1) < prettySmall);
		assert(Math.abs( cubic.Arg()+(Math.PI/2) )< prettySmall);
	}

	@Test
	public void testPolarAndBackTransform()
	{

		for(int n=0;n<0;n++)
		{
			
			ComplexRationalNr z = createRndmCNrQuadBounds(-5,5);
			display("z", z);
			
			
			ComplexRationalNr j = z.polarRepresentation();
			display("j(z Polar): ", j);
			
			ComplexRationalNr k = j.fromPolarToGaussEbene();
			display("k=j'=z",k);
			
			ComplexRationalNr d=  z.subtract(k);
			display("z-k",d);
			
			assert(distance(k,z)<0.00000001);
		}
	}

	private ComplexRationalNr createRndmComplexNr(int minReal, int maxReal, int minImaginary, int maxImaginary)
	{
			
		double real = Math.random()*(maxReal-minReal)+ minReal;
		double imaginary = Math.random()*(maxImaginary-minImaginary)+minImaginary;

		return new ComplexRationalNr(real, imaginary);
	}

	private ComplexRationalNr createRndmCNrQuadBounds(double min, double max)
	{
		return createRndmComplexNr(min, max, min, max);
	}
		
	private ComplexRationalNr createRndmCNr1stQuadrant(int max)
	{
		if(max<=0)throw new IllegalArgumentException("max must be greater then Zero!");
			
		ComplexRationalNr output = createRndmCNrQuadBounds(0, max);
		assert(output.getRealPart()>=0);
		assert(output.getImaginaryPart()>=0);
		
		return output;
	}
		
	private ComplexRationalNr createRndmCNr2ndQuadrant(int max)
	{

		if(max<=0)throw new IllegalArgumentException("max must be greater then Zero!");

		ComplexRationalNr output = createRndmComplexNr(-max, 0, 0, max);
		if(output.getRealPart()==0)return createRndmComplexNr(-max,0,0,max);
		
		assert(output.getRealPart()<0);
		assert(output.getImaginaryPart()>=0);
			
		return output;
	}

	private ComplexRationalNr createRndmCNr3rdQuadrant(int max)
	{
		if(max<=0)throw new IllegalArgumentException("max must be greater then Zero!");
			
		ComplexRationalNr output = createRndmCNrQuadBounds(0, -max);
		
		double real = output.getRealPart();
		double imaginary = output.getImaginaryPart();
		if(real==0||imaginary==0)return createRndmCNr3rdQuadrant(max);
		
		assert(output.getRealPart()<0);
		assert(output.getImaginaryPart()<0);
			
		return output;
	}

	private ComplexRationalNr createRndmCNr4thQuadrant(int max)
	{
		if(max<=0)throw new IllegalArgumentException("max must be greater then Zero!");

		ComplexRationalNr output = createRndmComplexNr(0, max, 0, -max);
		
		if(output.getImaginaryPart()==0)return createRndmCNr4thQuadrant(max);
		
		assert(output.getRealPart()>=0);
		assert(output.getImaginaryPart()<0);
			
		return output;
	}

	public RationalNumber distance(ComplexRationalNr a, ComplexRationalNr b)
	{
			
		RationalNumber deltaX = a.getRealPart().subtract(b.getRealPart();
		RationalNumber deltaY = a.getImaginaryPart().subtract(b.getImaginaryPart());
		
		RationalNumber deltaX2 = deltaX.multiplyWith(deltaX);
		RationalNumber deltaY2 = deltaY.multiplyWith(deltaY);
		
		RationalNumber sum = deltaX2.addWith(deltaY2);
		
		RationalNumber sqrt = SmallTools.getNthRoot(sum, 2);
		
		return sqrt;			
	}
	*/
	
	public void display(String name, ComplexRationalNr z) throws NaturalNumberException, RNumException, CloneNotSupportedException, IllegalArgumentException, DivisionByZeroException, CollectionException
	{
			
		System.out.println(name + " = " + z);
		//TODO: System.out.println("Arg(" + name + ") = " + Math.toDegrees(z.Arg()));
		System.out.println("amount("+name+") = " + z.getAmount());
		System.out.println("Quadrant " + getQuadrant(z) + "\n");
	}
	
	public int getQuadrant(ComplexRationalNr z) throws NaturalNumberException, RNumException, DivisionByZeroException, CollectionException
	{
		
		double x = z.getRealPart().doubleApproximation();
		double y = z.getImaginaryPart().doubleApproximation();
		
		if((x>=0)&&(y>=0)) return 1;
		if((x>=0)&&(y<0))  return 2;
		if((x<0)&&(y<0))   return 3;
		if((x<0)&&(y>=0))  return 4;
		
		return 0;
	}
}
