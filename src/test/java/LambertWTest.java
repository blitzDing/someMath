import org.junit.jupiter.api.Test;

import static someMath.SmallTools.*;

import static consoleTools.TerminalXDisplay.*;

class LambertWTest
{

	@Test
	void testLambertW()
	{
		double lambert = 1;
		double value =lambert*Math.pow(Math.E, lambert);
		assert(Math.abs(lambert-lambertW(value,2))<prettySmall);
		
		lambert = 2;
		value =lambert*Math.pow(Math.E, lambert);
		assert(Math.abs(lambert-lambertW(value,2))<prettySmall);
		System.out.println("lamberW(" + lambert + ") = " + value);
	}

	@Test
	void testSuperRoot()
	{
		
		double root1= 5.5;
		double t = Math.pow(root1, root1);
		System.out.println("superRoot("+t+") = " + superRoot(t,2));
		assert(Math.abs(root1-superRoot(t,2))<prettySmall);
		
		root1= 6.4;
		t = Math.pow(root1, root1);
		System.out.println("superRoot("+t+") = " + superRoot(t,2));
		assert(Math.abs(root1-superRoot(t,2))<prettySmall);
	}
}
