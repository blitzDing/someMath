package someMath;

/*
 * Any Class implementing this Interface
 * should make the Generic 'E' identical 
 * to its own type.
*/
public abstract class MultiplyableAndAddable<M extends Multiplyable<M>, A extends M> extends Addable<A>
{
	public MultiplyableAndAddable(A zero)
	{
		super(zero);
		// TODO Auto-generated constructor stub
	}
}
