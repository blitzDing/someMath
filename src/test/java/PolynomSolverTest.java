import java.util.Set;

import org.junit.jupiter.api.Test;

import someMath.CollectionException;
import someMath.CollectionManipulation;
import someMath.ComplexNrDouble;
import someMath.NaturalNumberException;
import someMath.NaturalNumber;
import someMath.PolynomSolver;
import someMath.RationalNumber;
import static someMath.RationalNumber.*;
import static someMath.NaturalNumber.*;

public class PolynomSolverTest 
{
	
	private static final double prettySmall = Math.pow(10, -11);

	@Test
	public void testSolver() throws Exception
	{
		
		NaturalNumber zwei = new NaturalNumber(2);
		RationalNumber two = new RationalNumber(zwei, one);
		
		/*TODO:
		//equals x²+2 = 0.
		Set<ComplexNrDouble> set = PolynomSolver.quadraticSolver(one, zero, two);
		assert(set.size()==2);
		ComplexNrDouble z = CollectionManipulation.catchRandomElementOfSet(set);
		
		assert(Math.abs(z.getImaginaryPart())-Math.sqrt(2)<=prettySmall);
		
		System.out.println(set);
		*/
	}
}
