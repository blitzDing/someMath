package someMath;

import java.util.HashSet;
import java.util.Set;

public class PolynomSolver 
{

	public static Set<ComplexDoubleFMT> quadraticSolver(Double b, Double c)
	{
		
		Set<ComplexDoubleFMT> set = new HashSet<>();
		double alpha = (b/2);
		double radicand = (alpha*alpha)-c;
	
		//What Happens if radicand==0?? Double Solution?
		if(radicand>=0)
		{
			double realA = -alpha - Math.sqrt(radicand);
			double realB = -alpha + Math.sqrt(radicand);
			
			ComplexDoubleFMT solutionA = new ComplexDoubleFMT(realA, 0.0);
			ComplexDoubleFMT solutionB = new ComplexDoubleFMT(realB, 0.0);
			
			set.add(solutionA);
			set.add(solutionB);
		
			return set;
		}

		double real = alpha;
		double imaginary = Math.sqrt(-radicand);
		
		ComplexDoubleFMT solutionA = new ComplexDoubleFMT(real, imaginary); //conjugated
		ComplexDoubleFMT solutionB = new ComplexDoubleFMT(real, -imaginary);//conjugated
		
		set.add(solutionA);
		set.add(solutionB);
		
		return set;
	}
	
	public static Set<ComplexDoubleFMT> quadraticSolver(Double a, Double b, Double c)
	{
		return quadraticSolver((b/a), (c/a));
	}
	
	public static Set<ComplexDoubleFMT> cubicSolver(Double b, Double c, Double d)
	{
		//TODO:
		return null;
	}
}
