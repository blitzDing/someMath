package someMath;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class MatrixOps 
{

	public static <A extends SubtractableAndDivideable<A>> void walkThrouMatrix(Matrix<A> matrix, BiConsumer<Integer, Integer> bic)
	{
		
		for(int n=0;n<matrix.getRows();n++)
		{
			for(int m=0;m<matrix.getColumns();m++)
			{
				bic.accept(n,m);
			}
		}
	}
	
	public static <A extends SubtractableAndDivideable<A>> Matrix<A> subMatrixOfElement(Matrix<A> matrix, int row, int column)
	{
		
		List<A> list = new ArrayList<>();
		BiConsumer<Integer, Integer> bic = (n,m)->{if(n!=row&&m!=column)list.add(matrix.getValue(n, m));};
		walkThrouMatrix(matrix, bic);
		
		return new Matrix<A>(matrix.getRows()-1,matrix.getColumns()-1,list);
	}
	
	private static <A extends SubtractableAndDivideable<A>> A developeDet(Matrix<A> matrix, int i, boolean doDevelopeByRow) throws NaturalNumberException, CollectionException, RNumException, CloneNotSupportedException, DivisionByZeroException
	{

		List<A> list = rowAsList(matrix, i);;
		
		A a = CollectionManipulation.catchRandomElementOfList(list);
		A value = (A) a.getNeutralZero();
		A factor;

		if(doDevelopeByRow)//develops By Row i.
		{			
			for(int n=0;n<matrix.getColumns();n++)
			{
				
				Matrix<A> subM = subMatrixOfElement(matrix, i, n);

				if((n+i)%2==1)factor= 
						a.getNeutralZero().subtract(a.getNeutralOne());
				else factor = a.getNeutralOne();
				
				value = value
						.addWith(factor.multiplyWith(list.get(n)
								.multiplyWith(getDeterminant(subM))));
			}
			
		}
		else//Develops By Column i.
		{
			list = columnAsList(matrix, i);

			for(int m=0;m<matrix.getRows();m++)
			{

				Matrix<A> subM = subMatrixOfElement(matrix, m,i);

				if((m+i)%2==1)factor= a.getNeutralZero().subtract(a.getNeutralOne());
				else factor = a.getNeutralOne();
				
				value = value.addWith(factor.multiplyWith(list.get(m).multiplyWith(getDeterminant(subM))));
			}
		}
		return value;
	}

	public static <A extends SubtractableAndDivideable<A>> List<A> columnAsList(Matrix<A> matrix, int i)
	{
		

		List<A> list = new ArrayList<>();
		
		BiConsumer<Integer, Integer> bic = (n,m) -> {if(m==i)list.add(matrix.getValue(n, m));};
			
		walkThrouMatrix(matrix, bic);
		
		return list;
	}

	public static <A extends SubtractableAndDivideable<A>> List<A> rowAsList(Matrix<A> matrix, int i)
	{
		
		List<A> list = new ArrayList<>();
		
		BiConsumer<Integer, Integer> bic = (n,m) -> {if(n==i)list.add(matrix.getValue(n, m));};
			
		walkThrouMatrix(matrix, bic);
		
		return list;
	}

	public static <E extends SubtractableAndDivideable<E>> Matrix<E> swapedRows(Matrix<E> matrix, int a, int b)
	{
		
		int rows = matrix.getRows();
		int columns = matrix.getColumns();
		
		@SuppressWarnings("unchecked")
		E[][] newValArr = (E[][]) new MultiplyableAndAddable[rows][columns];
		BiConsumer<Integer, Integer> bic = (n,m)->
		{
			newValArr[n][m] = matrix.getValue(n, m);
			if(n==a)newValArr[n][m] = matrix.getValue(b, m);
			if(n==b)newValArr[n][m] = matrix.getValue(a, m);
		};
		
		walkThrouMatrix(matrix, bic);
		
		return new Matrix<E>(newValArr);
	}
	
	public static <E extends SubtractableAndDivideable<E>> Matrix<E> swapedColumns(Matrix<E> matrix, int a, int b)
	{
		
		int rows = matrix.getRows();
		int columns = matrix.getColumns();
		
		@SuppressWarnings("unchecked")
		E[][] newValArr = (E[][]) new MultiplyableAndAddable[rows][columns];
		BiConsumer<Integer, Integer> bic = (n,m)->
		{
			newValArr[n][m] = matrix.getValue(n, m);
			if(m==a)newValArr[n][m] = matrix.getValue(n, b);
			if(m==b)newValArr[n][m] = matrix.getValue(n, a);
		};
		
		walkThrouMatrix(matrix, bic);
		
		return new Matrix<E>(newValArr);
	}


	public static <E extends SubtractableAndDivideable<E>> Matrix<E> transponedMatrix(Matrix<E> matrix)
	{	
		//Switched indices in 2 lines here.
	
		int columns = matrix.getColumns();
		int rows = matrix.getRows();
		
		@SuppressWarnings("unchecked")
		E[][] newValArr = (E[][]) new MultiplyableAndAddable[columns][rows];
		
		//It is important that the indices are Switched that way.
		//And not the other way around because the walk thru is 
		//in the old Matrix.(This) It might be that the index 
		//(it's switched-> m,n) doesn't Exist in the Old Matrix 
		//(if it is not quadratic) then (n,m) will not(!) exist 
		//in the output Matrix either.
		BiConsumer<Integer, Integer> bic = (n,m)-> newValArr[m][n] = matrix.getValue(n, m);
		walkThrouMatrix(matrix, bic);
	
		return new Matrix<E>(newValArr);		
	}

	public static <X extends SubtractableAndDivideable<X>> X getDeterminant(Matrix<X> matrix) throws NaturalNumberException, CollectionException, RNumException, CloneNotSupportedException, DivisionByZeroException
	{
		
		int rows = matrix.getRows();
		//int columns = matrix.getColumns();
		
		if(!matrix.isQuadratic())throw new IllegalArgumentException("Can't calculate the Determinant. Matrix is not Quadratic.");
		if(rows==2)//2*2 case.
		{
			
			X a = matrix.getValue(0, 0);
			X b = matrix.getValue(0, 1);
			X c = matrix.getValue(1, 0);
			X d = matrix.getValue(1, 1);
			
			X d1 = a.multiplyWith(d);
			X d2 = b.multiplyWith(c);
			
			return d1.subtract(d2);
		}

		if(rows>2)
		{
			int r = (int)(Math.random()*2);
			boolean doDevelopeByRow = (r==1);
		
			//boolean doDevelopeByCol = !doDevelopeByRow;//No need
			//(Nr. of) rows = (Nr. of) columns because this Matrix is quadratic;
		
			int i = (int)(Math.random()*rows);
				
			return developeDet(matrix, i, doDevelopeByRow);
		}
		
		throw new IllegalArgumentException("At least Two Rows are needed.");
	}

}