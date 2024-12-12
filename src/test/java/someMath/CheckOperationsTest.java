package someMath;

import org.junit.jupiter.api.Test;

public class CheckOperationsTest
{
	@Test
	public void test()
	{
		OpsComplexNrDouble ops =new OpsComplexNrDouble();
		Operation<ComplexNrDouble> addUp = ops.getOperation(AddComplexNrDouble.add);
		
		ComplexNrDouble a = addUp.getNeutrum();
		ComplexNrDouble b = new ComplexNrDouble(1,1);
		ComplexNrDouble c = addUp.execute(a, b);
		
		assert(c.equals(b));
	}

}
