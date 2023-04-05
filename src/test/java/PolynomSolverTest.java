import java.util.Set;

import org.junit.jupiter.api.Test;

import someMath.CollectionManipulation;
import someMath.ComplexDoubleFMT;
import someMath.PolynomSolver;

public class PolynomSolverTest 
{

	@Test
	public void testSolver()
	{
		
		//equals x²+2 = 0.
		Set<ComplexDoubleFMT> set = PolynomSolver.quadraticSolver(1.0, 0.0, 2.0);
		assert(set.size()==2);
		ComplexDoubleFMT z = CollectionManipulation.catchRandomElementOfSet(set);
		
		assert(Math.abs(z.getImaginaryPart())==Math.sqrt(2));
		
		System.out.println(set);
	}
}
