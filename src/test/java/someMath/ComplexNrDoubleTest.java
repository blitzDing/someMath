package someMath;


import org.junit.jupiter.api.Test;


import java.util.*;



class ComplexNrDoubleTest 
{
		
	List<ComplexNrDouble> nrList = new ArrayList<>();

	double prettySmall = Math.pow(10, -11);

	ComplexNrDouble one = new ComplexNrDouble(1,0);
	ComplexNrDouble i = new ComplexNrDouble(0,1);
	ComplexNrDouble minusI = new ComplexNrDouble(0,-1);
	ComplexNrDouble minusOne = new ComplexNrDouble(-1, 0);
		
	ComplexNrDouble q1 = new ComplexNrDouble(1, 1);		//quadrant 1
	ComplexNrDouble q2 = new ComplexNrDouble(-1, 1);	//quadrant 2
	ComplexNrDouble q3 = new ComplexNrDouble(-1, -1);	//quadrant 3
	ComplexNrDouble q4 = new ComplexNrDouble(1, -1);	//quadrant 4

		
	@Test
	public void testConjugate() throws InterruptedException
	{
			
		System.out.println("Testing Conjugate.");
		System.out.println("******************");
		
		Thread.sleep(1500);
			
		for(int n=0;n<6;n++)
		{
			ComplexNrDouble a = createRndmCNrQuadBounds(-5,5);
			display("a", a);
			display("a*(a')", a.multiplyWith(a.getConjugate()));
				
			assert(Math.abs( a.multiplyWith(a.getConjugate()).getImaginaryPart() ) < prettySmall);
		}
	}

	@Test
	public void testAddition() throws InterruptedException
	{
			
		System.out.println("Testing Addition.");
		System.out.println("*****************");
		
		Thread.sleep(1500);
			
		for(int n=0;n<6;n++)
		{
			ComplexNrDouble z = createRndmCNrQuadBounds(-5,5);
			ComplexNrDouble anti = new ComplexNrDouble(-z.getRealPart(), -z.getImaginaryPart());
				
			display("z", z);
			display("anti-z", anti);
			display("sum", z.addWith(anti));
				
			assert(z.addWith(anti).equals(z.getNeutralZero()));
				
			ComplexNrDouble times2 = z.addWith(z);
			display("z+z", times2);
				
			assert( Math.abs( times2.Arg()-z.Arg() ) < prettySmall);
			assert( Math.abs( times2.amount()-2*z.amount()) < prettySmall);

			ComplexNrDouble times3 = times2.addWith(z);
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
			ComplexNrDouble c = createRndmCNrQuadBounds(-3,3);
			ComplexNrDouble d = c.subtractArg(c);
			display("c", c);
			display("c-c", d);
					
			assert(d.equals(c.getNeutralZero()));
			
			ComplexNrDouble ur = new ComplexNrDouble(0,0);
			ComplexNrDouble minusC = ur.subtractArg(c);
			
			assert(minusC.amount()==c.amount());
		}
	}
	
	@Test
	public void testMultiplication() throws InterruptedException
	{
			
		System.out.println("Testing Multiplication.");
		System.out.println("***********************");
		
		Thread.sleep(1500);
		
		display("i", i);
		display("-i", minusI);
		display("product", i.multiplyWith(minusI));
			
		assert(i.multiplyWith(minusI).equals(one));
		
		for(int n=0;n<4;n++)
		{
			ComplexNrDouble firstQuadNr = createRndmCNr1stQuadrant(5);
			ComplexNrDouble product = firstQuadNr.multiplyWith(i);
			display("a", firstQuadNr);
			display("a*i", product);
		
			assert(Math.abs( product.Arg()-i.Arg()-firstQuadNr.Arg() ) < prettySmall);
		}
		
		for(int n=0;n<50;n++)
		{

			ComplexNrDouble a = createRndmCNr1stQuadrant(10);
			ComplexNrDouble b = createRndmCNr2ndQuadrant(10);

			ComplexNrDouble p1 = a.multiplyWith(b);			
			ComplexNrDouble p2 = b.multiplyWith(b);
				
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
									
				try 
				{
					Thread.sleep(1000);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
				
			if(sum < -180)
			{
					
					
				System.out.println("ANTI-HEY!!!!! Old sum: " + sum);
				System.out.println("              New sum: " + (sum+360));
				System.out.println("                  wp1: " + wp1);

				sum = sum+360;

					
				try
				{
					Thread.sleep(1000);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
				
			System.out.println("rp1 - (ra*rb) = " + (rp1 - (ra*rb)));
			System.out.println("wp1 - (wa+wb) = " + (wp1 - (sum)) + "\n");

			assert(Math.abs( rp1 - ra*rb ) < prettySmall);
			if(wp1*sum>0)assert(Math.abs( wp1 - sum )   < prettySmall);
			else assert(Math.abs( wp1 + sum) < prettySmall);
				
		}
	}

	/*
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
			
		assert(Math.abs( a.subtractArg(d).amount() )  < prettySmall);
		assert(Math.abs( a.divideBy(d).Arg() )  < prettySmall);
			
		System.out.println(a.Arg()+"\n");
			
	}

	public void testArgAndAmount()
	{
			
		for(int l=0;l<nrList.size();l++)
		{
				
			int n = l+1;
				
			ComplexNrDouble q = new ComplexNrDouble(n, n);
				
			display("q", q);
				
			assert(Math.abs( Math.toDegrees(q.Arg()) - 45 )) < prettySmall;
				
			ComplexNrDouble p = new ComplexNrDouble(-n, n);
				
			display("p", p);
			assert Math.abs( Math.toDegrees(p.Arg()) - 135 ) < prettySmall;

			ComplexNrDouble s = new ComplexNrDouble(-n, -n);
				
			display("s", s);
			assert Math.abs( Math.toDegrees(s.Arg()) + 135 ) < prettySmall;
				
			ComplexNrDouble t = new ComplexNrDouble(n, -n);
				
			display("t", t);
			assert Math.abs( Math.toDegrees(t.Arg()) + 45 ) < prettySmall;
				
				
			ComplexNrDouble a = createRndmCNrQuadBounds(10,15); //First quadrant
				
			ComplexNrDouble square = a.multiplyWith(a);
				
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

	public void testPowers()
	{

		ComplexNrDouble basis = new ComplexNrDouble(0, 1);
		ComplexNrDouble exponent = new ComplexNrDouble(2, 0);
		ComplexNrDouble square = basis.toThePowerOf(exponent);

		display("basis", basis);
		display("exponent", exponent);
		display("square", square);

		assert(Math.abs( square.Arg()-(Math.PI) )< prettySmall);

		ComplexNrDouble exponent2 = new ComplexNrDouble(3, 0);
		ComplexNrDouble cubic = basis.toThePowerOf(exponent);

		display("exponent2", exponent2);
		display("cubic", cubic);

		assert(Math.abs( cubic.Arg()+(Math.PI/2) )< prettySmall);
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
		
	private ComplexNrDouble createRndmCNr1stQuadrant(int max)
	{
		if(max<=0)throw new IllegalArgumentException("max must be greater then Zero!");
			
		ComplexNrDouble output = createRndmCNrQuadBounds(0, max);
		assert(output.getRealPart()>=0);
		assert(output.getImaginaryPart()>=0);
		
		return output;
	}
		
	private ComplexNrDouble createRndmCNr2ndQuadrant(int max)
	{

		if(max<=0)throw new IllegalArgumentException("max must be greater then Zero!");

		ComplexNrDouble output = createRndmComplexNr(-max, 0, 0, max);
		assert(output.getRealPart()<=0);
		assert(output.getImaginaryPart()>=0);
			
		return output;
	}

	private ComplexNrDouble createRndmCNr3rdQuadrant(int max)
	{
		if(max<=0)throw new IllegalArgumentException("max must be greater then Zero!");
			
		ComplexNrDouble output = createRndmCNrQuadBounds(0, -max);
		assert(output.getRealPart()<=0);
		assert(output.getImaginaryPart()<=0);
			
		return output;
	}

	private ComplexNrDouble createRndmCNr4thQuadrant(int max)
	{
		if(max<=0)throw new IllegalArgumentException("max must be greater then Zero!");

		ComplexNrDouble output = createRndmComplexNr(0, max, 0, -max);
		assert(output.getRealPart()>=0);
		assert(output.getImaginaryPart()<=0);
			
		return output;
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
		System.out.println("Arg(" + name + ") = " + Math.toDegrees(z.Arg()));
		System.out.println("amount("+name+") = " + z.amount() + "\n");
	}
}