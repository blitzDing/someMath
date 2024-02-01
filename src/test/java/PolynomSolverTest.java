import java.util.Set;

import org.junit.jupiter.api.Test;

import someMath.CollectionException;
import someMath.CollectionManipulation;
import someMath.ComplexNrDouble;
import someMath.InterfaceNumberException;
import someMath.PolynomSolver;
import someMath.RationalNumber;

public class PolynomSolverTest 
{
	
	private static final double prettySmall = Math.pow(10, -11);

	@Test
	public void testSolver() throws Exception
	{
		
		RationalNumber zero = RationalNumber.zero;
		RationalNumber one = RationalNumber.one;
		RationalNumber two = new RationalNumber(2, 1);
		
		//equals x²+2 = 0.
		Set<ComplexNrDouble> set = PolynomSolver.quadraticSolver(one, zero, two);
		assert(set.size()==2);
		ComplexNrDouble z = CollectionManipulation.catchRandomElementOfSet(set);
		
		assert(Math.abs(z.getImaginaryPart())-Math.sqrt(2)<=prettySmall);
		
		System.out.println(set);
	}
}
