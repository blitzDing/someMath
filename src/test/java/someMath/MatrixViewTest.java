package someMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MatrixViewTest 
{

	@Test
	public void test() throws NaturalNumberException, CollectionException, RNumException, CloneNotSupportedException, DivisionByZeroException
	{
		
		DoubleFMT[][]fValues = new DoubleFMT[3][3];
		fValues = new DoubleFMT[3][3];
		fValues[0][0] = new DoubleFMT(1.0);
		fValues[0][1] = new DoubleFMT(0.0);
		fValues[0][2] = new DoubleFMT(7.0);
		fValues[1][0] = new DoubleFMT(0.0);
		fValues[1][1] = new DoubleFMT(1.0);
		fValues[1][2] = new DoubleFMT(0.0);
		fValues[2][0] = new DoubleFMT(0.1);
		fValues[2][1] = new DoubleFMT(0.0);
		fValues[2][2] = new DoubleFMT(1.0);
		
		Matrix<DoubleFMT> matrix = new Matrix<>(fValues);
		System.out.println(matrix);
		DoubleFMT det = MatrixOps.getDeterminant(matrix);
		System.out.println(det);
		
		Matrix<DoubleFMT> tMatrix = MatrixOps.transponedMatrix(matrix);
		System.out.println(tMatrix);
		det = MatrixOps.getDeterminant(tMatrix);
		System.out.println(det);
	}
}