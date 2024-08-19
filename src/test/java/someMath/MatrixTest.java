package someMath;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class MatrixTest
{

	@Test
	public void testMatrizen() throws Exception 
	{
	
		DoubleFMT[][]fValues = new DoubleFMT[2][2];
		DoubleFMT a11 = new DoubleFMT(3.0);
		fValues[0][0] = a11;
		fValues[0][1] = new DoubleFMT(1.0);
		fValues[1][0] = new DoubleFMT(2.0);
		fValues[1][1] = new DoubleFMT(4.0);
		
		Matrix<DoubleFMT> m = new Matrix<>(fValues);
		DoubleFMT det = (DoubleFMT) MatrixOps.getDeterminant(m);
		
		System.out.println(m + "\n" + det.toString() + "\n" + a11);
		
		Set<DoubleFMT> eigenvalues = m.getEigenvalues();
		List<DoubleFMT> list = new ArrayList<DoubleFMT>(eigenvalues);
		
		Comparator<DoubleFMT> compa = (a,b)->
		{
			if(a.value>b.value) return  1;
			if(b.value>a.value) return -1;
			
			return 0;
		};
		list.sort(compa);
		
		DoubleFMT x1 = list.get(0);
		DoubleFMT x2 = list.get(1);
		
		System.out.println("Eigenvalues: " + x1.toString() + ", " + x2.toString());
		
		assert(x1.value.equals(2.0));
		assert(x2.value.equals(5.0));

		fValues = new DoubleFMT[3][3];
		fValues[0][0] = new DoubleFMT(1.0);
		fValues[0][1] = new DoubleFMT(0.0);
		fValues[0][2] = new DoubleFMT(0.0);
		fValues[1][0] = new DoubleFMT(0.0);
		fValues[1][1] = new DoubleFMT(1.0);
		fValues[1][2] = new DoubleFMT(0.0);
		fValues[2][0] = new DoubleFMT(0.0);
		fValues[2][1] = new DoubleFMT(0.0);
		fValues[2][2] = new DoubleFMT(1.0);
		
		m = new Matrix<>(fValues);
		det = (DoubleFMT) MatrixOps.getDeterminant(m);
		
		System.out.println(m + "\n" + det.toString());
		
	}
}
