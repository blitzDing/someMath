import java.util.Set;

import org.junit.jupiter.api.Test;

import someMath.CollectionException;
import someMath.CollectionManipulation;
import someMath.ComplexNrDouble;
import someMath.PolynomSolver;

public class PolynomSolverTest 
{

	@Test
	public void testSolver() throws CollectionException
	{
		
		//equals x²+2 = 0.
		Set<ComplexNrDouble> set = PolynomSolver.quadraticSolver(1.0, 0.0, 2.0);
		assert(set.size()==2);
		ComplexNrDouble z = CollectionManipulation.catchRandomElementOfSet(set);
		
		assert(Math.abs(z.getImaginaryPart())==Math.sqrt(2));
		
		System.out.println(set);
	}
}
