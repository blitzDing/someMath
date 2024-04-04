package someMath;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class MathSetClassClosedUnderUnionTest 
{

	static Set<Character> bigOne;
	
	static Set<Character> smallOne;
	
	static Set<Character> anotherSmallOne;

	static Set<Character> differentAndSmall;

	static Set<Character> betweenbigAndSmall;

	static Set<Character> evenSmaller;

	@BeforeAll
	static void prepare()
	{
		bigOne = new HashSet<>();
		bigOne.add('a');
		bigOne.add('b');
		bigOne.add('c');
		bigOne.add('d');
		bigOne.add('e');
		
		smallOne = new HashSet<>();
		smallOne.add('a');
		smallOne.add('b');
		smallOne.add('c');
		
		anotherSmallOne = new HashSet<>();
		anotherSmallOne.add('c');
		anotherSmallOne.add('d');
		anotherSmallOne.add('e');

		differentAndSmall = new HashSet<>();
		differentAndSmall.add('f');
		differentAndSmall.add('g');
		differentAndSmall.add('h');

		betweenbigAndSmall = new HashSet<>();
		betweenbigAndSmall.add('a');
		betweenbigAndSmall.add('b');
		betweenbigAndSmall.add('c');
		betweenbigAndSmall.add('d');

		evenSmaller = new HashSet<>();
		evenSmaller.add('a');
		evenSmaller.add('b');
	}
	
	@Test
	void famTestTest()
	{

		
		
		Set<Set<Character>> fam1 = new HashSet<>();
		fam1.add(bigOne);
		fam1.add(smallOne);
		fam1.add(anotherSmallOne);
		
		assert(MathSetClassClosedUnderUnion.famTest(fam1));	
		
		Set<Set<Character>> fam2 = new HashSet<>();
		fam1.add(bigOne);
		fam1.add(betweenbigAndSmall);
		fam1.add(smallOne);
		fam1.add(evenSmaller);

		assert(MathSetClassClosedUnderUnion.famTest(fam2));

		Set<Set<Character>> fam3 = new HashSet<>();
		fam3.add(smallOne);
		fam3.add(anotherSmallOne);
		
		assertFalse(MathSetClassClosedUnderUnion.famTest(fam3));

		Set<Set<Character>> fam4 = new HashSet<>();
		fam4.add(bigOne);
		fam4.add(differentAndSmall);
		
		assertFalse(MathSetClassClosedUnderUnion.famTest(fam4));
	}
	
	@Test
	void createFamTest()
	{
		assert(true);
	}
}
