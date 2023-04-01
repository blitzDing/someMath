package myToolBoxMaven;

import org.junit.Test;

import someMath.DoubleFMT;
import someMath.Matrix;

public class MatrixTests 
{

	@Test
	public void test() 
	{
	
		int[]properties = new int[6];
		properties[0]=5;
		properties[1]=1;
		properties[2]=5;
		properties[3]=1;
		properties[4]=16;
		properties[5]=-16;
		
		Matrix<DoubleFMT> A = createRandomDoubleFMTMatrix(properties);
		
		properties = new int[3];
		properties[0]=5;
		properties[1]=16;
		properties[2]=-16;
		Matrix<DoubleFMT> Q = createRandomDoubleFMTQuadraticMatrix(properties);
		
/*		List<DoubleFMT> list = new ArrayList<>();
		list.add(new DoubleFMT(0.0));
		list.add(new DoubleFMT(2.0));
		list.add(new DoubleFMT(0.0));
		list.add(new DoubleFMT(1.0));
		list.add(new DoubleFMT(0.0));
		list.add(new DoubleFMT(0.0));
		list.add(new DoubleFMT(0.0));
		list.add(new DoubleFMT(0.0));
		list.add(new DoubleFMT(3.0));
		
		Matrix<DoubleFMT> matrix = new Matrix<>(3,3,list);
		System.out.println(MatrixOps.getDeterminant(matrix)+"\n\n"+matrix+"\n\n"+matrix.getRow(1)+"\n\n"+matrix.getColumn(0));
		System.out.println(matrix.getNeutralOne());
		System.out.println(matrix.getNeutralZero());
		Matrix<DoubleFMT> product = matrix.multiplyWith(matrix).multiplyWith(matrix);
		System.out.println(product + "" + MatrixOps.getDeterminant(product));
		System.out.println(product.subtractArg(matrix).subtractArg(product));
		System.out.println(matrix.addWith(matrix));
	
		List<ComplexDoubleFMT> list2 = new ArrayList<>();
		list2.add(new ComplexDoubleFMT(0.0, 0.0));
		list2.add(new ComplexDoubleFMT(2.0, 0.0));
		list2.add(new ComplexDoubleFMT(0.0, 0.0));
		list2.add(new ComplexDoubleFMT(1.0, 0.0));
		list2.add(new ComplexDoubleFMT(0.0, 0.0));
		list2.add(new ComplexDoubleFMT(0.0, 0.0));
		list2.add(new ComplexDoubleFMT(0.0, 0.0));
		list2.add(new ComplexDoubleFMT(0.0, 0.0));
		list2.add(new ComplexDoubleFMT(3.0, 0.0));
		
		Matrix<ComplexDoubleFMT> matrix2 = new Matrix<>(3,3,list2);
		assertEquals(matrix, matrix2);

		
		Matrix<DoubleFMT> matrix3 = new Matrix<>(3,3,list);
		assertEquals(matrix, matrix3);
				
		System.out.println("Matrix:\n"+matrix+"transponed and bottom swap:\n"+MatrixOps.swapedRows(MatrixOps.transponedMatrix(matrix), 2,1));
		System.out.println("Matrix:\n"+matrix.getColumn(0));
		Matrix<DoubleFMT> Natrix = MatrixOps.transponedMatrix(matrix.getColumn(0));
		System.out.println("transponed:\n"+Natrix);
		System.out.println("Column swap:\n"+MatrixOps.swapedColumns(matrix, 0, 1));		

		List<DoubleFMT> list3 = new ArrayList<>();
		list3.add(new DoubleFMT(1.0));
		list3.add(new DoubleFMT(1.0));
		list3.add(new DoubleFMT(1.0));
		list3.add(new DoubleFMT(1.0));
		list3.add(new DoubleFMT(1.0));
		list3.add(new DoubleFMT(1.0));		
		Matrix<DoubleFMT> notQuadratic = new Matrix<>(2,3,list3);

		System.out.println(notQuadratic);
		
		List<DoubleFMT> list4 = new ArrayList<>();
		list4.add(new DoubleFMT(1.0));
		list4.add(new DoubleFMT(1.0));
		list4.add(new DoubleFMT(1.0));
		list4.add(new DoubleFMT(1.0));
		list4.add(new DoubleFMT(1.0));
		list4.add(new DoubleFMT(1.0));		
		Matrix<DoubleFMT> notQuadratic2 = new Matrix<>(3,2,list4);
		
		System.out.println(notQuadratic2);
		
		Matrix<DoubleFMT> quadraticMonoton = notQuadratic.multiplyWith(notQuadratic2);
		System.out.println(quadraticMonoton);
*/
	}

	public Matrix<DoubleFMT> createRandomDoubleFMTMatrix(int[] properties)
	{
	
		assert(properties.length==6);
		
		final int maxRows = properties[0];
		final int minRows = properties[1];
		
		final int maxColumns = properties[2];
		final int minColumns = properties[3];
		
		final int rows = ((int)(Math.random()*(maxRows-minRows))+minRows);
		final int columns = ((int)(Math.random()*(maxColumns-minColumns))+minColumns);
		
		System.out.println("minRows: "+minRows);
		System.out.println("maxRows: "+maxRows);
		System.out.println("rows: "+ rows);

		System.out.println("minColumns: "+minColumns);
		System.out.println("maxColumns: "+maxColumns);
		System.out.println("columns: "+ columns);

		assert(rows<=maxRows);
		assert(rows>=minRows);
		
		assert(columns<=maxColumns);
		assert(columns>=minColumns);
				
				
		//DoubleFMT [][] valArr = null;
		//Matrix<DoubleFMT> nullTest =new Matrix<>(valArr);
		
		
		final double maxValue = properties[4];
		final double minValue = properties[5];
		
		DoubleFMT [][] valArr2 = new DoubleFMT[rows][columns];

		for(int n=0;n<rows;n++)
		{
			for(int m=0;m<columns;m++)
			{
				
						//Integers is not a Must for values of the type DoubleFMT.
				double random = ((int)(Math.random()*(maxValue-minValue))+minValue);
				valArr2[n][m] = new DoubleFMT(random);
			}
		}
		
		Matrix<DoubleFMT> randoMatrix = new Matrix<>(valArr2);
		
		assert((rows==columns)==(randoMatrix.isQuadratic()));

		System.out.println(randoMatrix);
		
		return randoMatrix;
	}
	
	public Matrix<DoubleFMT> createRandomDoubleFMTQuadraticMatrix(int [] properties)
	{

		assert(properties.length==3);
		
		final int[] newProperties = new int[6];
		
		newProperties[0] = properties[0];
		newProperties[1] = properties[0];
		newProperties[2] = properties[0];
		newProperties[3] = properties[0];
		newProperties[4] = properties[1];
		newProperties[5] = properties[2];		
		
		Matrix<DoubleFMT> randoMatrix = createRandomDoubleFMTMatrix(newProperties);
		
		assert(randoMatrix.isQuadratic());
		
		return randoMatrix;
	}
}
