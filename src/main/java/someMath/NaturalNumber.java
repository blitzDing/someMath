package someMath;

public class NaturalNumber implements Addable<NaturalNumber>, Multiplyable<NaturalNumber> 
{

	final int numberCore;
	
	public NaturalNumber(int numberCore) throws InterfaceNumberException
	{
		if(numberCore<0)throw new InterfaceNumberException("Can't work with negative Integers.");
				
		this.numberCore = numberCore;
	}

	@Override
	public boolean hasNeutralZero()
	{
		return true;
	}

	@Override
	public boolean hasNeutralOne() 
	{
		return true;
	}

	@Override
	public NaturalNumber getNeutralZero() throws InterfaceNumberException
	{
		return new NaturalNumber(0);
	}

	@Override
	public NaturalNumber getNeutralOne() throws InterfaceNumberException
	{
		return new NaturalNumber(1);
	}

	@Override
	public NaturalNumber multiplyWith(NaturalNumber e) throws InterfaceNumberException
	{
		int p = numberCore*e.getNumberCore();
		
		return new NaturalNumber(p);
	}

	@Override
	public NaturalNumber addWith(NaturalNumber e) throws InterfaceNumberException 
	{
		int s = numberCore + e.getNumberCore();
		
		return new NaturalNumber(s);
	}
	
	public int getNumberCore()
	{
		return numberCore;
	}
	
	public String toString()
	{
		return numberCore + "";
	}
}