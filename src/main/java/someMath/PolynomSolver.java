package someMath;

import java.util.HashSet;
import java.util.Set;

public class PolynomSolver 
{

	public static Set<ComplexNrDouble> quadraticSolver(Double b, Double c)
	{
		
		Set<ComplexNrDouble> set = new HashSet<>();
		double alpha = (b/2);
		double radicand = (alpha*alpha)-c;
	
		//What Happens if radicand==0?? Double Solution?
		if(radicand>=0)
		{
			double realA = -alpha - Math.sqrt(radicand);
			double realB = -alpha + Math.sqrt(radicand);
			
			ComplexNrDouble solutionA = new ComplexNrDouble(realA, 0.0);
			ComplexNrDouble solutionB = new ComplexNrDouble(realB, 0.0);
			
			set.add(solutionA);
			set.add(solutionB);
		
			return set;
		}

		double real = alpha;
		double imaginary = Math.sqrt(-radicand);
		
		ComplexNrDouble solutionA = new ComplexNrDouble(real, imaginary); //conjugated
		ComplexNrDouble solutionB = new ComplexNrDouble(real, -imaginary);//conjugated
		
		set.add(solutionA);
		set.add(solutionB);
		
		return set;
	}
	
	public static Set<ComplexNrDouble> quadraticSolver(Double a, Double b, Double c)
	{
		return quadraticSolver((b/a), (c/a));
	}
	
	public static Set<ComplexNrDouble> cubicSolver(Double b, Double c, Double d)
	{
		//TODO:
		return null;
	}
}
