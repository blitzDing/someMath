package someMath;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OpsComplexNrDouble extends Operations<ComplexNrDouble>
{

	private static final Set<Operation<ComplexNrDouble>> set = new HashSet<>
	(Arrays.asList(new AddComplexNrDouble()));
	
	private OpsComplexNrDouble(Set<Operation<ComplexNrDouble>> set)
	{
		super(set);
		// TODO Auto-generated constructor stub
	}
	
	public OpsComplexNrDouble()
	{
		this(set);
	}

}
