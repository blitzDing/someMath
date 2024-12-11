package someMath;

import java.util.ArrayList;
import java.util.List;

import someMath.exceptions.CollectionException;
import someMath.exceptions.DivisionByZeroException;
import someMath.exceptions.NaturalNumberException;
import someMath.exceptions.RNumException;
import someMath.exceptions.VectorException;

public class LinearDependency
{

	public static <T extends SubtractableAndDivideable<T>> 
	boolean	columnVectors(Matrix<T>... columnVectors) throws VectorException
	{

		if(columnVectors.length==0)throw new VectorException("No vectors overhanded.");

		int nrOfVectors = columnVectors.length;
		int columns = columnVectors[0].getColumns();
		
		if(nrOfVectors>columns)return true;
		
		for(Matrix<T> vector: columnVectors)
		{
			if(columns!=vector.getColumns())throw new VectorException("Vectors are not uniform.");
		}
		
		return false;
	}
	
	public static <T extends SubtractableAndDivideable<T>>
	boolean vectorPairLinearDependent(Matrix<T> v1, Matrix<T> v2) throws VectorException, NaturalNumberException, DivisionByZeroException, CollectionException, RNumException, CloneNotSupportedException
	{
		
		throwsExceptionIfNotValideColumnVector(v1);
		throwsExceptionIfNotValideColumnVector(v2);
		int columnsV1 = v1.getColumns();
		int columnsV2 = v2.getColumns();
		if(columnsV1!=columnsV2)throw new VectorException("These two vectors don't populate the same Vectorspace.");

		if(isNullVector(v1)||isNullVector(v2))return true;

		for(int n=0;n<columnsV1;n++)
		{
			T t1 = v1.getValue(0, n);
			T t2 = v2.getValue(0, n);
			T t0 = t1.getNeutralZero();
			
			T factor = null;
			
			boolean thereIsAZeroInV1 = false;
			boolean thereIsAZeroInV2 = false;

			
			if(factor.equals(null)||n==0)
			{
				if(t1.equals(t0))
				{
					thereIsAZeroInV1= true;
					continue;
				}
				if(thereIsAZeroInV1)return false;
				
				if(t2.equals(t0))
				{
					thereIsAZeroInV2 = true;
					continue;
				}
				if(thereIsAZeroInV2)return false;

				factor = t1.divideBy(t2);
				
			}
		}
		return false;
	}
	
	public static <T extends SubtractableAndDivideable<T>> boolean isNullVector(Matrix<T> v) throws NaturalNumberException, VectorException
	{
		
		throwsExceptionIfNotValideColumnVector(v);
		
		int rows = v.getRows();
		
		T t1= v.getValue(0, 0);
		T zero = t1.zero;

		for(int n =0;n<rows;n++)
		{
			T t = v.getValue(n, 0);
			if(!t.equals(zero))return false;
		}

		return true;
	}
	
	public static <T extends SubtractableAndDivideable<T>> List<Integer> positionsOfZeros(Matrix<T> v) throws NaturalNumberException
	{

		
		List<Integer> positions = new ArrayList<>();
		int rows = v.getRows();
		T t1 = v.getValue(0, 0).getNeutralZero();
		return positions;
	}

	public static <T extends SubtractableAndDivideable<T>>void 
	throwsExceptionIfNotValideColumnVector(Matrix<T> v) throws VectorException
	{
		int rows = v.getRows();
		if(rows!=1)throw new VectorException("This is a not column Vector.");		

	}	
}
