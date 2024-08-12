import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static someMath.SmallTools.*;
import static consoleTools.TerminalXDisplay.*;
class LambertWTest {

	@Test
	void testLambertW()
	{
		double t = lambertW(Math.E);
		assert(Math.abs(t-1)<prettySmall);
		
		double x = 2*Math.pow(Math.E, 2);
		printBoldAndBlue(""+lambertW(x));
		assert(Math.abs(2.0-lambertW(x))<prettySmall);
	}

}
