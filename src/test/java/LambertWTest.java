import org.junit.jupiter.api.Test;

import static someMath.SmallTools.*;

import static consoleTools.TerminalXDisplay.*;

public class LambertWTest
{

	@Test
	public void testLambertW()
	{
		double lambert = 1;
		double value =lambert*Math.pow(Math.E, lambert);
		assert(Math.abs(lambert-lambertW(value))<prettySmall);
		
		lambert = 2;
		value =lambert*Math.pow(Math.E, lambert);
		assert(Math.abs(lambert-lambertW(value))<prettySmall);
		System.out.println("lamberW(" + lambert + ") = " + value);
	}

	@Test
	public void testSuperRoot()
	{
		
		double root1= 5.5;
		double t = Math.pow(root1, root1);
		System.out.println("superRoot("+t+") = " + superRoot(t));
		assert(Math.abs(root1-superRoot(t))<prettySmall);
		
		root1= 6.4;
		t = Math.pow(root1, root1);
		System.out.println("superRoot("+t+") = " + superRoot(t));
		assert(Math.abs(root1-superRoot(t))<prettySmall);
	}
}