package someMath;


import org.junit.jupiter.api.Test;




class ComplexNrDoubleTest 
{

	double prettySmall = Math.pow(10, -11);
	int millisWait = 150;

	ComplexNrDouble one = new ComplexNrDouble(1,0);
	ComplexNrDouble i = new ComplexNrDouble(0,1);
	ComplexNrDouble minusI = new ComplexNrDouble(0,-1);
	ComplexNrDouble minusOne = new ComplexNrDouble(-1, 0);
		
		
	@Test
	public void testConjugate() throws InterruptedException
	{
			
		System.out.println("Testing Conjugate.");
		System.out.println("******************");
		
		Thread.sleep(millisWait);
			
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
		
		Thread.sleep(millisWait);
			
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
		
		Thread.sleep(millisWait);
		
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

			
			assert((product.Arg()-firstQuadNr.Arg()-Math.PI/2) < prettySmall);
			assert(Math.abs( product.Arg()-i.Arg()-firstQuadNr.Arg() ) < prettySmall);
		}
		
		for(int n=0;n<10;n++)
		{

			ComplexNrDouble a = createRndmCNr1stQuadrant(10);
			ComplexNrDouble b = createRndmCNr2ndQuadrant(10);


			ComplexNrDouble p1 = a.multiplyWith(b);
				
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
				
			ComplexNrDouble c = createRndmCNr3rdQuadrant(10);
			ComplexNrDouble d = createRndmCNr4thQuadrant(10);

			ComplexNrDouble p2 = c.multiplyWith(d);
				
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
									
				try 
				{
					Thread.sleep(500);
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
				System.out.println("                  wp2: " + wp2);

				sum = sum+360;

					
				try
				{
					Thread.sleep(500);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
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
			ComplexNrDouble a = createRndmCNrQuadBounds(-10,10);
			display("a", a);
			
			ComplexNrDouble b = createRndmCNrQuadBounds(-10, 10);
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

	
	@Test
	public void testLogarithm() throws InterruptedException
	{
		
		System.out.println("Testing Logarithm.");
		System.out.println("******************");
		for(int n=0;n<10;n++)
		{
			ComplexNrDouble l = createRndmCNr1stQuadrant(10);
			display("l", l);
		
			ComplexNrDouble loga = l.Log();	
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
		
		ComplexNrDouble basis = new ComplexNrDouble(0, 1);
		ComplexNrDouble exponent = new ComplexNrDouble(2, 0);
		ComplexNrDouble square = basis.toThePowerOf(exponent);

		display("basis", basis);
		display("exponent", exponent);
		display("square", square);
		
		assert(Math.abs( square.getRealPart()+1 )< prettySmall);
		assert( ( square.Arg()==Double.NaN )||( Math.abs(square.Arg())-Math.PI < prettySmall));

		ComplexNrDouble exponent2 = new ComplexNrDouble(3, 0);
		ComplexNrDouble cubic = basis.toThePowerOf(exponent2);

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
			
			ComplexNrDouble z = createRndmCNrQuadBounds(-5,5);
			display("z", z);
			
			
			ComplexNrDouble j = z.polarRepresentation();
			display("j(z Polar): ", j);
			
			ComplexNrDouble k = j.fromPolarToGaussEbene();
			display("k=j'=z",k);
			
			ComplexNrDouble d=  z.subtractArg(k);
			display("z-k",d);
			
			assert(distance(k,z)<0.00000001);
		}
	}

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
		if(output.getRealPart()==0)return createRndmComplexNr(-max,0,0,max);
		
		assert(output.getRealPart()<0);
		assert(output.getImaginaryPart()>=0);
			
		return output;
	}

	private ComplexNrDouble createRndmCNr3rdQuadrant(int max)
	{
		if(max<=0)throw new IllegalArgumentException("max must be greater then Zero!");
			
		ComplexNrDouble output = createRndmCNrQuadBounds(0, -max);
		
		double real = output.getRealPart();
		double imaginary = output.getImaginaryPart();
		if(real==0||imaginary==0)return createRndmCNr3rdQuadrant(max);
		
		assert(output.getRealPart()<0);
		assert(output.getImaginaryPart()<0);
			
		return output;
	}

	private ComplexNrDouble createRndmCNr4thQuadrant(int max)
	{
		if(max<=0)throw new IllegalArgumentException("max must be greater then Zero!");

		ComplexNrDouble output = createRndmComplexNr(0, max, 0, -max);
		
		if(output.getImaginaryPart()==0)return createRndmCNr4thQuadrant(max);
		
		assert(output.getRealPart()>=0);
		assert(output.getImaginaryPart()<0);
			
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
		System.out.println("amount("+name+") = " + z.amount());
		System.out.println("Quadrant " + getQuadrant(z) + "\n");
	}
	
	public int getQuadrant(ComplexNrDouble z)
	{
		
		double x = z.getRealPart();
		double y = z.getImaginaryPart();
		
		if((x>=0)&&(y>=0)) return 1;
		if((x<0)&&(y>=0))  return 2;
		if((x<0)&&(y<0))   return 3;
		if((x>=0)&&(y<0))  return 4;
		
		return 0;
	}
}