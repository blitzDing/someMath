package someMath;

import java.util.HashSet;
import java.util.Set;

public class PolynomSolver 
{

	/*
	public static Set<ComplexNrDouble> quadraticSolver(RationalNumber koeffizent, RationalNumber koeffizent2) throws Exception
	{
		
		Set<ComplexNrDouble> set = new HashSet<>();
		
		RationalNumber negativeOne = new RationalNumber(false, 1,1);
		RationalNumber zero = RationalNumber.zero;
		RationalNumber half = new RationalNumber(1, 2);
		RationalNumber alpha = koeffizent.multiplyWith(half);
		
		RationalNumber radicand = (alpha.multiplyWith(alpha)).subtract(koeffizent2);
	
		//What Happens if radicand==0?? Double Solution?
		if(radicand.isGreaterThen(zero))
		{
			RationalNumber realA = (alpha.multiplyWith(negativeOne)).subtract(SmallTools.getNthRoot(radicand, 2));
			RationalNumber realB = (alpha.multiplyWith(negativeOne)).addWith(SmallTools.getNthPotenz(radicand, 2));
			
			double x1 = realA.doubleApproximation();
			double x2 = realB.doubleApproximation();
			
			ComplexNrDouble solutionA = new ComplexNrDouble(x1, 0.0);
			ComplexNrDouble solutionB = new ComplexNrDouble(x2, 0.0);
			
			set.add(solutionA);
			set.add(solutionB);
		
			return set;
		}

		double real = alpha.doubleApproximation();
		RationalNumber negativeR = radicand.multiplyWith(negativeOne);
		
		RationalNumber iTimes;
		double imaginary;
		
		if(negativeR.isGreaterThen(RationalNumber.zero))
		{
			iTimes= SmallTools.getNthRoot(negativeR, 2);
			imaginary= iTimes.doubleApproximation();
		}
		else
		{
			imaginary = 0;
		}
		
		ComplexNrDouble solutionA = new ComplexNrDouble(real, imaginary); //conjugated
		ComplexNrDouble solutionB = new ComplexNrDouble(real, -imaginary);//conjugated
		
		set.add(solutionA);
		set.add(solutionB);
		
		return set;
	}
	
	public static Set<ComplexNrDouble> quadraticSolver(RationalNumber k1, RationalNumber k2, RationalNumber k3) throws Exception
	{
		return quadraticSolver(k2.divideBy(k1), k3.divideBy(k1));
	}
	
	public static Set<ComplexNrDouble> cubicSolver(Double b, Double c, Double d)
	{
		//TODO:
		return null;
	}
	*/
	
}
