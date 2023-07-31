import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import someMath.Matrix;
import someMath.StringWrapperForMatrixTable;


class StringMatrixTest 
{

	@Test
	void test() 
	{
		StringWrapperForMatrixTable n = new StringWrapperForMatrixTable("Name");
		StringWrapperForMatrixTable state = new StringWrapperForMatrixTable("Status");
		StringWrapperForMatrixTable time = new StringWrapperForMatrixTable("Time");
		
		StringWrapperForMatrixTable[] names = new StringWrapperForMatrixTable[3];
		StringWrapperForMatrixTable[] states = new StringWrapperForMatrixTable[3];
		StringWrapperForMatrixTable[] times = new StringWrapperForMatrixTable[3];

		names[0] = new StringWrapperForMatrixTable("Kathrin");
		names[1] = new StringWrapperForMatrixTable("Marina");
		names[2] = new StringWrapperForMatrixTable("Liane");

		states[0] = new StringWrapperForMatrixTable("SexEx");
		states[1] = new StringWrapperForMatrixTable("LoveSexEx");
		states[2] = new StringWrapperForMatrixTable("cuteButRude");

		times[0] = new StringWrapperForMatrixTable("Zero");
		times[1] = new StringWrapperForMatrixTable("OneHalf");
		times[2] = new StringWrapperForMatrixTable("seductive");
		
		int rows = 4;
		int columns = 3;
		StringWrapperForMatrixTable[][] valArr = new StringWrapperForMatrixTable[rows][columns];
		
		valArr[0][0] = n;
		valArr[0][1] = state;
		valArr[0][2] = time;
		
		valArr[1][0] = names[0];
		valArr[2][0] = names[1];
		valArr[3][0] = names[2];
		
		valArr[1][1] = states[0];
		valArr[2][1] = states[1];
		valArr[3][1] = states[2];

		valArr[1][2] = times[0];
		valArr[2][2] = times[1];
		valArr[3][2] = times[2];

		Matrix<StringWrapperForMatrixTable> show = new Matrix(valArr);
		
		System.out.println(show);
		
		assert(true);
	}

}
