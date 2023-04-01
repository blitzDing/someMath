package someMath;

public class RealNrManipulation 
{

	//p = x^x. Solved for x. x = superRoot(p).
	public static double superRoot(double p)
	{
		
		int iterations = 40;
		
		if(p<=0)throw new 
			IllegalArgumentException("Can't solve for super Root if argument smaller or equal to Zero.");
		
		double [] z = new double[iterations+1];
		z[0] = 2.0;//start Value
		
		for(int n=0;n<iterations;n++)
		{
			double q = Math.log(p)/Math.log(z[n]);
			z[n+1] = 0.5*(z[n] + q);
		}
		
		return z[iterations];
	}
}
