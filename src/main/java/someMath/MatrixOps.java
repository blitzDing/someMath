package someMath;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

import someMath.exceptions.MathException;

public class MatrixOps <E extends Operations<E>> extends Operations<Matrix<E>>
{	
	
	public final Function<List<Matrix<E>>, Matrix<E>> addFunc = (matrixList)->
	{
		
		Matrix<E> a = matrixList.get(0);
		Matrix<E> b = matrixList.get(0);

		int rows = a.getRows();
		int columns = a.getColumns();
		
		List<E> newValArr = new ArrayList<>();
		
		BiConsumer<Integer, Integer> bic = (n,m)->
		{
			try
			{
				newValArr.add(a.getValue(n, m).add(b.getValue(n, m)));
			}
			catch (MathException e)
			{
				e.printStackTrace();
			}
		};
		
		walkThrouMatrix(a, bic);
	
		return new Matrix<E>(rows, columns, newValArr);
	};

	Function<List<Matrix<E>>, Matrix<E>> multiplyFunc =(matrixist) ->
	{

		Matrix<E> t = matrixist.get(0);
		Matrix<E> a = matrixist.get(0);
		
		if(!(t.getColumns()==a.getRows())) throw new IllegalArgumentException("Can't Multiply these Matrixes.");
		int newRows = t.getRows();
		int newColumns = a.getColumns();
		E someValue = t.getValue(0, 0);
		
		List<E> newValues = new ArrayList<>();
		
		for(int n=0;n<newRows;n++) 
		{
			for(int m=0;m<newColumns;m++)
			{
				E currentValue = someValue.getNeutrumOfOperation(someValue.multiply);
			
				for(int i=0;i<t.getColumns();i++)
				{
					try
					{
						currentValue = currentValue.add(t.getValue(n, i).multiply(a.getValue(i, m)));
					}
					catch (MathException e)
					{
						e.printStackTrace();
					}
				}
				newValues.add(currentValue);
			}
		}
		
		return new Matrix<E>(t.getRows(),a.getColumns(),newValues);
	};

	/*
	 * 
	 * 	@Override
	public Matrix<E> divideBy(Matrix<E> t) throws NaturalNumberException, CollectionException, RNumException, CloneNotSupportedException, DivisionByZeroException
	{
		
		E e = (E) valArr[0][0];//half dummy half neutralZero.
		if(t==null||MatrixOps.getDeterminant(t)==e.getNeutralZero());
		// TODO Auto-generated method stub
		return null;
	}
	
		public Set<E> getEigenvalues() throws RNumException, NaturalNumberException, Exception
	{
		
		if(!this.isQuadratic||this.rows!=2) throw new IllegalArgumentException("Can only calculate Eigenvalues for 2 by 2 Matrizies.");
		
		E ainz = valArr[0][0].getNeutralOne();
		
		E a = valArr[0][0];
		E d = valArr[1][1];
		
		E two = ainz.add(ainz);
		E s = (a.add(d)).divideBy(two);
		E r = s.multiplyWith(s).subtract(MatrixOps.getDeterminant(this));
		
		E x1 = (s.add(SmallTools.getNthRoot(7, r, 2)));
		E x2 = (s.subtract(SmallTools.getNthRoot(7, r, 2)));
		Set<E> set = new HashSet<>();
		set.add(x1);
		set.add(x2);
		
		return set;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Matrix<E> subtract(Matrix<E> a)
	{
		E[][] newValArr;
		newValArr = (E[][]) new MultiplyableAndAddable[rows][columns];
		
		BiConsumer<Integer, Integer> bic = (n,m)-> 
		{
			try {
				newValArr[n][m] = valArr[n][m].subtract(a.valArr[n][m]);
			} catch (RNumException | NaturalNumberException | CloneNotSupportedException | DivisionByZeroException | CollectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		MatrixOps.walkThrouMatrix(this, bic);
	
		return new Matrix<E>(newValArr);
	}

	public Matrix<E> getNeutrumOfOperation(String name) 
	{
		if(!isQuadratic) throw new MathException("This Kind of Matrix has no Neutral Multiplicator.");
		//TODO: Use enum down here!!! 
		if(!name.equals("add")&&!name.equals("multipy")) throw new MathException("Don't know Operation");
		List<E> listOfValues = new ArrayList<>();
		E someValue = MatrixOps.rowAsList(this, 0).get(0);//I just need a value. Doesn't matter it's State as long it isn't Null.
		
		BiConsumer<Integer, Integer> bic = (n,m)->
		{
			try
			{
				if(n==m)listOfValues.add(someValue.getNeutralOne());
				else listOfValues.add(someValue.getNeutralZero());
			}
			catch (NaturalNumberException e)
			{
				e.printStackTrace();
			}
		};
		MatrixOps.walkThrouMatrix(this, bic); 
		
		return new Matrix<E>(rows, columns, listOfValues);
	}

	@Override
	public boolean hasNeutralZero() 
	{
		//No matter the kind of Matrix.
		return true;
	}


	@Override

	public Matrix<E> getNeutralZero() 
	{
		List<E> listOfValues = new ArrayList<>();
		E someValue = MatrixOps.rowAsList(this, 0).get(0);//I just need a value. Doesn't matter its State as long it isn't Null.
		
		BiConsumer<Integer, Integer> bic = (n,m)->
		{
			try
			{
			listOfValues.add(someValue.getNeutralZero());
			}
			catch(NaturalNumberException inexc)
			{
				inexc.printStackTrace();
			}
		};
		MatrixOps.walkThrouMatrix(this, bic);

		return new Matrix<E>(rows, columns, listOfValues);
	}
	 */

	Operation<Matrix<E>> add;
	Operation<Matrix<E>> multiply;
	Operations<Matrix<E>> opsMat;
	
	public MatrixOps(Set<Operation<Matrix<E>>> set) throws MathException
	{
		super(set);
		add = new Operation(super.add, null,2, Integer.MAX_VALUE, addFunc);
		multiply = new Operation(super.multiply, null, 2, 2, multiplyFunc);
		setOperation(add);
		setOperation(multiply);
	}
	
}
