package someMath;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;


class MathSetClassClosedUnderUnionTest {

	@Test
	void test()
	{

		Set<Character> bigOne = new HashSet<>();
		bigOne.add('a');
		bigOne.add('b');
		bigOne.add('c');
		bigOne.add('d');
		bigOne.add('e');
		
		Set<Character> smallOne = new HashSet<>();
		smallOne.add('a');
		smallOne.add('b');
		smallOne.add('c');
		
		Set<Character> anotherSmallOne = new HashSet<>();
		anotherSmallOne.add('c');
		anotherSmallOne.add('d');
		anotherSmallOne.add('e');
		
		Set<Character> differentAndSmall = new HashSet<>();
		differentAndSmall.add('f');
		differentAndSmall.add('g');
		differentAndSmall.add('h');
		
		Set<Set<Character>> fam1 = new HashSet<>();
		fam1.add(bigOne);
		fam1.add(smallOne);
		fam1.add(anotherSmallOne);
		
		assert(MathSetClassClosedUnderUnion.famTest(fam1));

		Set<Character> betweenbigAndSmall = new HashSet<>();
		betweenbigAndSmall.add('a');
		betweenbigAndSmall.add('b');
		betweenbigAndSmall.add('c');
		betweenbigAndSmall.add('d');
		
		Set<Character> evenSmaller = new HashSet<>();
		smallOne.add('a');
		smallOne.add('b');
	
		
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
}
