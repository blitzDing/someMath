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
			nrList.add(createRndmCNrQuadBounds(0, 9));
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
		System.out.println("argument: " + a.argument() +"\n");
		
		ComplexDoubleFMT b = nrList.get(1);
		System.out.println("b: " + b);
		System.out.println("amount:" + b.amount());
		System.out.println("argument: " + b.argument() +"\n");
		
		ComplexDoubleFMT c = a.multiplyWith(b);
		System.out.println("(a*b)=c: " + c);
		System.out.println("amount:" + c.amount());
		System.out.println("argument: " + c.argument() +"\n");
		
		ComplexDoubleFMT d = c.divideBy(b);
		System.out.println("(c/b)=d: " + d);
		System.out.println("amount:" + d.amount());
		System.out.println("argument: " + d.argument() +"\n");
		
		System.out.println("a-d: " + a.subtractArg(d));

		assert(Math.abs( (a.subtractArg(d).amount()) ) < 0.00001);
		assert(Math.abs( (a.divideBy(d).argument()) ) < 0.00001);
		
		System.out.println(a.argument());
		
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
