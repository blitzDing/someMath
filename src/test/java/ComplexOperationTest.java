import org.junit.jupiter.api.Test;


import java.util.*;

import someMath.CollectionManipulation;
import someMath.ComplexNrDouble;

public class ComplexOperationTest 
{
	
	List<ComplexNrDouble> nrList = new ArrayList<>();

	
	ComplexNrDouble one = new ComplexNrDouble(1,0);
	ComplexNrDouble i = new ComplexNrDouble(0,1);
	ComplexNrDouble minusI = new ComplexNrDouble(0,-1);
	ComplexNrDouble minusOne = new ComplexNrDouble(-1, 0);
	
	ComplexNrDouble q1 = new ComplexNrDouble(1, 1);		//quadrant 1
	ComplexNrDouble q2 = new ComplexNrDouble(-1, 1);	//quadrant 2
	ComplexNrDouble q3 = new ComplexNrDouble(-1, -1);	//quadrant 3
	ComplexNrDouble q4 = new ComplexNrDouble(1, -1);	//quadrant 4

	public ComplexOperationTest()
	{	
		fillList();
	}
	
	private void fillList()
	{
		
		nrList.add(q1);
		nrList.add(q2);
		nrList.add(q3);
		nrList.add(q4);		
	}
	
	public void testAddition()
	{
		
		System.out.println("Testing Addition.");
		
		for(int n=0;n<10;n++)
		{
			ComplexNrDouble z = createRndmCNrQuadBounds(-5,5);
			ComplexNrDouble anti = new ComplexNrDouble(-z.getRealPart(), -z.getImaginaryPart());
			
			display("z", z);
			display("anti-z", anti);
			display("sum", z.addWith(anti));
			
			assert(z.addWith(anti).equals(z.getNeutralZero()));
		}
	}
	
	public void testSubtraction()
	{
		
		System.out.println("Testing Subtraction.");
		
		ComplexNrDouble c = CollectionManipulation.catchRandomElementOfList(nrList);
		
		display("c", c);
		display("c-c", c.subtractArg(c));
		
		assert(c.subtractArg(c).equals(c.getNeutralZero()));
	}
	
	public void testMultiplication()
	{
		
		System.out.println("Testing Multiplication.");
		
		display("i", i);
		display("-i", minusI);
		display("product", i.multiplyWith(minusI));
		
		for(int n=0;n<10;n++)
		{
			ComplexNrDouble a = createRndmCNrQuadBounds(0.1, 5);//Erster Quadrant.
			ComplexNrDouble b = createRndmCNrQuadBounds(0.1, 5);//Erster Quadrant.
			
			
			ComplexNrDouble c = a.multiplyWith(b);
			
			double ra = a.amount();
			double rb = b.amount();
			double rc = c.amount();
			
			double wa = Math.toDegrees(a.Arg());
			double wb = Math.toDegrees(b.Arg());
			double wc = Math.toDegrees(c.Arg());
			
			display("a", a);
			display("b", b);
			display("a*b", c);
			
			System.out.println("rc - (ra*rb) = " + (rc -ra*rb));
			System.out.println("wc - (wa+wb) = " + (wc - (wa+wb)) + "\n");

			assert(Math.abs( rc-ra*rb )     < 0.000001);
			assert(Math.abs( wc - (wa+wb) ) < 0.000001);
		}
	}

	public void testDivision()
	{
		System.out.println("Testing Division.");
		
		ComplexNrDouble a = nrList.get(0);
		display("a", a);
		
		ComplexNrDouble b = nrList.get(1);
		display("b", b);

		ComplexNrDouble c = a.multiplyWith(b);
		display("a*b", c);
		
		ComplexNrDouble d = c.divideBy(b);
		display("d=c/b", d);
		
		ComplexNrDouble e = a.subtractArg(d);
		display("e=a-d", e);

		ComplexNrDouble f = a.divideBy(d);
		display("f=a/d", f);

		double prettySmall = Math.pow(10, -15);
		System.out.println(prettySmall);
		
		assert(Math.abs( a.subtractArg(d).amount() )  < prettySmall);
		assert(Math.abs( a.divideBy(d).Arg() )  < prettySmall);
		
		System.out.println(a.Arg()+"\n");
		
	}
	
	public void testConjugate()
	{
		
		System.out.println("Testing Conjugate.");
		
		for(int n=0;n<nrList.size();n++)
		{
			ComplexNrDouble a = createRndmCNrQuadBounds(-5,5);
			display("a", a);
			
			display("a*(a')", a.multiplyWith(a.getConjugate()));
			
			assert(Math.abs( a.multiplyWith(a.getConjugate()).getImaginaryPart() ) < 0.00000001);
		}
	}

	public void testArgAndAmount()
	{
		
		for(int l=0;l<nrList.size();l++)
		{
			
			int n = l+1;
			
			ComplexNrDouble q = new ComplexNrDouble(n, n);
			
			System.out.println("q" + n + " = " + q);
			System.out.println("Arg(q" + n + ") = " + Math.toDegrees(q.Arg()));
			System.out.println("Amount(q" + n + ") = " + q.amount() + "\n");
			
			assert (Math.abs( Math.toDegrees(q.Arg()) - 45 )) < 0.0000001;
			
			ComplexNrDouble p = new ComplexNrDouble(-n, n);
			
			System.out.println("p" + n + " = " + p);
			System.out.println("Arg(p" + n + ") = " + Math.toDegrees(p.Arg()));
			System.out.println("Amount(p" + n + ") = " + p.amount() + "\n");
			
			assert Math.abs( Math.toDegrees(p.Arg()) - 135 ) < 0.0000001;

			ComplexNrDouble s = new ComplexNrDouble(-n, -n);
			
			System.out.println("s" + n + " = " + s);
			System.out.println("Arg(s" + n + ") = " + Math.toDegrees(s.Arg()));
			System.out.println("Amount(s" + n + ") = " + s.amount() + "\n");
			
			assert Math.abs( Math.toDegrees(s.Arg()) + 135 ) < 0.0000001;
			
			ComplexNrDouble t = new ComplexNrDouble(n, -n);
			
			System.out.println("t" + n + " = " + t);
			System.out.println("Arg(t" + n + ") = " + Math.toDegrees(t.Arg()));
			System.out.println("Amount(t" + n + ") = " + t.amount() + "\n");
			
			assert Math.abs( Math.toDegrees(t.Arg()) + 45 ) < 0.0000001;
			
			
			ComplexNrDouble a = createRndmCNrQuadBounds(10,15); //First quadrant
			
			ComplexNrDouble square = a.multiplyWith(a);
			
			double ra = a.amount();
			double wa = a.Arg();
			
			double rs = square.amount();
			double ws = square.Arg();
			
			System.out.println("a = " + a);
			System.out.println("Arg(a) = " + Math.toDegrees(wa));
			System.out.println("Amount(a) = " + ra);

			System.out.println("square = " + square);
			System.out.println("Arg(square) = " + Math.toDegrees(ws));
			System.out.println("Amount(square) = " + rs);

			System.out.println("rs - ra*ra = " + (rs-ra*ra));
			System.out.println("ws - 2*wa = " + (ws-2*wa));
			
			assert(Math.abs( rs - ra*ra )  < 0.0000001);
			assert(Math.abs( ws - 2*wa )   < 0.0000001);
		}
	}

	/*
	public void testPowers()
	{
		ComplexNrDouble basis = new ComplexNrDouble(0, 1);
		ComplexNrDouble exponent = new ComplexNrDouble(2, 0);
		ComplexNrDouble square = basis.toThePowerOf(exponent);
		
		System.out.println("Basis a: " + basis);
		System.out.println("Square: " + square);
		System.out.println("Square Angle: " + square.Arg() + "\n");
		
		assert(Math.abs( square.Arg()-(Math.PI/2) )< 0.00000001);

		ComplexNrDouble exponent2 = new ComplexNrDouble(3, 0);
		ComplexNrDouble cubic = basis.toThePowerOf(exponent);
		
		System.out.println("Basis a: " + basis);
		System.out.println("Cubic: " + cubic);
		System.out.println("Cubic Angle: " + cubic + "\n");
		
		assert(Math.abs( cubic.Arg()+(Math.PI/2) )< 0.00000001);
	}
	
	/*
	public void testPolarAndBackTransform()
	{
		
		for(ComplexNrDouble z: nrList)
		{
			System.out.println("z: " + z);
			double arg = z.Arg();
			System.out.println("Arg(z): " + arg);
		
			ComplexNrDouble j = z.polarRepresentation();
			System.out.println("j(z Polar): "+ j);
		
			ComplexNrDouble k = j.fromPolarToGaussEbene();
			System.out.println("k: " + k + "\ndistance(k,z): " + distance(k,z));
		
			System.out.println("z-k: " + z.subtractArg(k) + "\n");
			
			assert(distance(k,z)<0.00000001);
		}
	}
	*/

	private ComplexNrDouble createRndmComplexNr(double minReal, double maxReal, double minImaginary, double maxImaginary)
	{
		
		double real = Math.random()*(maxReal-minReal)+ minReal;
		double imaginary = Math.random()*(maxImaginary-minImaginary)+minImaginary;

		return new ComplexNrDouble(real, imaginary);
	}

	private ComplexNrDouble createRndmCNrQuadBounds(double min, double max)
	{
		return createRndmComplexNr(min, max, min, max);
	}
	
	public double distance(ComplexNrDouble a, ComplexNrDouble b)
	{
		
		double deltaX = a.getRealPart()-b.getRealPart();
		double deltaY = a.getImaginaryPart()-b.getImaginaryPart();
		
		return Math.sqrt(deltaX*deltaX+deltaY*deltaY);
				
	}
	
	public void display(String name, ComplexNrDouble z)
	{
		
		System.out.println(name + " = " + z);
		System.out.println("Arg(" + name + ") = " + z.Arg());
		System.out.println("amount("+name+") = " + z.amount() + "\n");
	}
}