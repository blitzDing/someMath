package someMath;

import java.util.Objects;

//FMT=ForMathTools
public class DoubleFMT implements SubtractableAndDivideable<DoubleFMT>
{
	
	public final Double value;
	
	public DoubleFMT(Double value)
	{
		this.value = value;
	}

	@Override
	public boolean hasNeutralOne() 
	{
		return true;
	}

	@Override
	public DoubleFMT getNeutralOne()
	{
		return new DoubleFMT(1.0);
	}

	@Override
	public boolean hasNeutralZero() 
	{
		return true;
	}

	@Override
	public DoubleFMT getNeutralZero() 
	{
		return new DoubleFMT(0.0);
	}

	@Override
	public DoubleFMT multiplyWith(DoubleFMT e)
	{
		return new DoubleFMT(e.value*this.value);
	}

	@Override
	public DoubleFMT add(DoubleFMT e)
	{
		return new DoubleFMT(e.value+this.value);
	}


	@Override
	public DoubleFMT subtract(DoubleFMT e) 
	{
		
		return new DoubleFMT(this.value - e.value);
	}
	
	public String toString()
	{
		return value.toString();
	}

	@Override
	public DoubleFMT divideBy(DoubleFMT t) 
	{
		return new DoubleFMT(value/t.value);
	}
	
	public int hashCode()
	{
		return Objects.hash(value);
	}
	
	public boolean equals(Object other)
	{
		if(other==null)return false;
		
		if(other==this)return true;
		
		if(getClass()!=other.getClass())return false;
		
		DoubleFMT otherDFMT = (DoubleFMT)other;
		
		return value.equals(otherDFMT.value);
	}
}