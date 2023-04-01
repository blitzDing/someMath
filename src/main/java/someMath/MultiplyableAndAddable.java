package someMath;

/*
 * Any Class implementing this Interface
 * should make the Generic 'E' identical 
 * to its own type.
*/
public interface MultiplyableAndAddable<E> extends Multiplyable<E>, Addable<E>
{

}
