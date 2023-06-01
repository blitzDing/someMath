import org.junit.jupiter.api.Test;


import java.util.*;

import someMath.ComplexDoubleFMT;

public class ComplexOperationTest 
{
	
	int listSize = 7;
	List<ComplexDoubleFMT> nrList = new ArrayList<>();

	public ComplexOperationTest()
	{
		fillList();
	}
	
	private void fillList()
	{
		for(int n=0;n<listSize;n++)
		{
			nrList.add(createRndmCNrQuadBounds(-5, 5));
		}
	}
	@Test
	public void testConjugate()
	{
		
		for(int n=0;n<listSize;n++)
		{
			ComplexDoubleFMT a = nrList.get(n);
			assert(a.multiplyWith(a.getConjugate()).getImaginaryPart() == 0.0);
		}
	}
	
	public void testDivision()
	{
		
		ComplexDoubleFMT a = nrList.get(0);
		System.out.println("a: " + a);
		System.out.println("amount:" + a.amount());
		System.out.println("argument: " + a.Arg() +"\n");
		
		ComplexDoubleFMT b = nrList.get(1);
		System.out.println("b: " + b);
		System.out.println("amount:" + b.amount());
		System.out.println("argument: " + b.Arg() +"\n");
		
		ComplexDoubleFMT c = a.multiplyWith(b);
		System.out.println("(a*b)=c: " + c);
		System.out.println("amount:" + c.amount());
		System.out.println("argument: " + c.Arg() +"\n");
		
		ComplexDoubleFMT d = c.divideBy(b);
		System.out.println("(c/b)=d: " + d);
		System.out.println("amount:" + d.amount());
		System.out.println("argument: " + d.Arg() +"\n");
		
		System.out.println("a-d: " + a.subtractArg(d));
		System.out.println("Arg(a/d): " + a.divideBy(d).Arg());

		double prettySmall = Math.pow(10, -15);
		System.out.println(prettySmall);
		
		assert(Math.abs( a.subtractArg(d).amount() )  < prettySmall);
		assert(Math.abs( a.divideBy(d).Arg() )  < prettySmall);
		
		System.out.println(a.Arg());
		
	}
	
	private ComplexDoubleFMT createRndmComplexNr(int minReal, int maxReal, int minImaginary, int maxImaginary)
	{
		
		double real = Math.random()*(maxReal-minReal)+ minReal;
		double imaginary = Math.random()*(maxImaginary-minImaginary)+minImaginary;

		return new ComplexDoubleFMT(real, imaginary);
	}

	private ComplexDoubleFMT createRndmCNrQuadBounds(int min, int max)
	{
		return createRndmComplexNr(min, max, min, max);
	}
}