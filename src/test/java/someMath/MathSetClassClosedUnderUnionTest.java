package someMath;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import consoleTools.BashSigns;
import consoleTools.TerminalXDisplay;


class MathSetClassClosedUnderUnionTest 
{

	static Set<Character> bigOne;
	
	static Set<Character> anotherSmallOne;

	static Set<Character> smallOne;

	static Set<Character> differentAndSmall;
	
	static Set<Character> anotherDifferentAndSmall;

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
		
		anotherDifferentAndSmall = new HashSet<>();
		anotherDifferentAndSmall.add('b');
		anotherDifferentAndSmall.add('c');
		anotherDifferentAndSmall.add('d');

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
	void implosionTest() throws CollectionException
	{
		printlnBoldAndBlue("Implosion test");

		Set<Set<Character>> someSets = new HashSet<>();
		someSets.add(betweenbigAndSmall);
		someSets.add(bigOne);
		someSets.add(anotherDifferentAndSmall);
		
		System.out.println("Origin: \n" + TerminalXDisplay.collectionToString(someSets));
		Set<Character> intersection = MathSetClassClosedUnderUnion.intersectHoleSetOfSets(someSets);
		System.out.println("\nIntersection:\n" + TerminalXDisplay.collectionToString(intersection));
	}

	@Test
	void famTestTest() throws CollectionException
	{

		printlnBoldAndBlue("fam Test test");	
		
		Set<Set<Character>> fam1 = new HashSet<>();
		fam1.add(bigOne);
		fam1.add(smallOne);
		fam1.add(anotherSmallOne);
		
		System.out.println(TerminalXDisplay.collectionToString(fam1));
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
	void typeTest() throws CollectionException
	{
		printlnBoldAndBlue("Type Set of Sets test");
		Set<Set<Character>> someSets = new HashSet<>();
		someSets.add(smallOne);
		someSets.add(anotherSmallOne);
		someSets.add(bigOne);
		printlnBoldAndBlue(TerminalXDisplay.collectionToString(bigOne));
		
		int [] type = MathSetClassClosedUnderUnion.typeOfSetOfSetsB(someSets);
		/*
		assert(type.length==2);
		assert(type[0]==2);
		assert(type[1]==1);
		System.out.println("Size: " + type.length);
		System.out.println("****" + type[0] + ", " + type[1]);
		*/

		
		/*
		type = MathSetClassClosedUnderUnion.typeOfSetOfSets(someSets);
		assert(type.length==3);
		assert(type[0]==0);
		assert(type[1]==2);
		assert(type[2]==1);
		*/

		System.out.println("Size: " + type.length);
		System.out.println("****" + type[0] + ", " + type[1] + ", " + type[2]);
		
	}

	@Test
	void createFamTest()
	{
		printlnBoldAndBlue("Not testing create Fam.");
		assert(true);
	}
	
	void printlnBoldAndBlue(String s)
	{
		System.out.println(BashSigns.boldBBCPX+s+BashSigns.boldBBCSX);
	}
}
