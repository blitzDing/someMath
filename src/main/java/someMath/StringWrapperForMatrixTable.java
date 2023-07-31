package someMath;

public class StringWrapperForMatrixTable implements MultiplyableAndAddable<StringWrapperForMatrixTable>
{
	
	private final String value;
	
	public StringWrapperForMatrixTable(String value)
	{
		this.value = value;
	}

	@Override
	public boolean hasNeutralOne() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public StringWrapperForMatrixTable multiplyWith(StringWrapperForMatrixTable e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringWrapperForMatrixTable getNeutralOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasNeutralZero() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public StringWrapperForMatrixTable addWith(StringWrapperForMatrixTable e) 
	{
		return new StringWrapperForMatrixTable(this.value + e.value);
	}

	@Override
	public StringWrapperForMatrixTable subtractArg(StringWrapperForMatrixTable e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringWrapperForMatrixTable getNeutralZero() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString()
	{
		return value;
	}

}
