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
		assert(Math.abs(lambert-lambertW(value))<prettySmall);
		
		lambert = 2;
		value =lambert*Math.pow(Math.E, lambert);
		assert(Math.abs(lambert-lambertW(value))<prettySmall);
		printBoldAndBlue(value+" to "+lambert);
	}

	@Test
	void testSuperRoot()
	{
		
		double root1= 5.5;
		double t = Math.pow(root1, root1);
		printBoldAndGreen(superRoot(t)+"");
		//assert(Math.abs(root1-superRoot(t))<prettySmall);
		
		root1= 6.4;
		t = Math.pow(root1, root1);
		printBoldAndBlue(t+" to "+superRoot(t));
		//assert(Math.abs(root1-superRoot(t))<prettySmall);
	}
}
