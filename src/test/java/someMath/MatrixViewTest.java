package someMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MatrixViewTest 
{

	@Test
	void test() throws NaturalNumberException, CollectionException, RNumException, CloneNotSupportedException, DivisionByZeroException
	{
		
		DoubleFMT[][]fValues = new DoubleFMT[2][2];
		fValues[0][0] = new DoubleFMT(1.0);
		fValues[0][1] = new DoubleFMT(0.0);
		fValues[1][0] = new DoubleFMT(0.0);
		fValues[1][1] = new DoubleFMT(1.0);
		/*
		fValues[1][1] = new DoubleFMT(1.0);
		fValues[1][2] = new DoubleFMT(0.0);
		fValues[2][0] = new DoubleFMT(0.0);
		fValues[2][1] = new DoubleFMT(0.0);
		fValues[2][2] = new DoubleFMT(1.0);
		*/
		Matrix<DoubleFMT> matrix = new Matrix<>(fValues);
		System.out.println(matrix);
		DoubleFMT det = (DoubleFMT)MatrixOps.getDeterminant(matrix);
		System.out.println(det.toString());
	}
}
