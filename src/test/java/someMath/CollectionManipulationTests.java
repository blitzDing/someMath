package someMath;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import consoleTools.BashSigns;
import consoleTools.TerminalXDisplay;

import static someMath.CollectionManipulation.*;

class CollectionManipulationTests
{

	static Set<Character> originSet = getSetOfFirstNLatinLettersLowerCase(4);
	static Set<Character> otherSet = getSetOfFirstNLatinLettersLowerCase(5);


	@Test
	void testPowerSet() throws CollectionException
	{

		System.out.println("\nTesting power Set.");

		Set<Set<Character>> pSet = powerSet(originSet);
		System.out.println(TerminalXDisplay.collectionToString(pSet));
		
		assert(pSet.size()==16);
		
		pSet = powerSet(otherSet);
		System.out.println(TerminalXDisplay.collectionToString(pSet));
		System.out.println(BashSigns.boldRBCPX + "hi" + BashSigns.boldRBCSX);
		
		assert(pSet.size()==32);
	}
	
	@Test
	void testSubSetsOfSizeN() throws CollectionException
	{
		
		System.out.println("\nTesting sub Sets.");
		System.out.println("2 of 4");
		Set<Set<Character>> subSets = allSubSetsOfSizeN(originSet, 2);
		System.out.println(TerminalXDisplay.collectionToString(subSets));
		
		assert(subSets.size()==6);

		System.out.println("3 of 5");
		subSets = allSubSetsOfSizeN(otherSet, 3);
		System.out.println(TerminalXDisplay.collectionToString(subSets));
		
		assert(subSets.size()==10);
		/*
		Set<Set<Set<Character>>> subSetsOfSize3 = allSubSetsOfSizeN(powerSet(originSet), 3);
		System.out.println(TerminalXDisplay.collectionToString(subSetsOfSize3));
		*/
	}

	@Test
	void testProductOfSetAndList() throws CollectionException
	{
		
		System.out.println("\nTesting product of Set and List.");

		Set<Integer> set = getSetOfFirstNIntegers(5);
		set.remove(0);
		
		Double p = multiplySetElements(set);
		
		assert(p==(4.0*3*2));
		
		p = addSetElements(set);
		
		assert(p== 1.0+2+3+4);
		
		List<Integer> list = new ArrayList<>(set);
		
		Collections.sort(list);
		
		p = addListElements(list, 3);
		
		assert(p==6.0);
		
		p = multiplyListElements(list, 2);
		
		assert(p==2.0);
	}
	
	@Test
	void testProductSum() throws CollectionException
	{
		System.out.println("\nTesting product Sum.");

		Set<Integer> set = getSetOfFirstNIntegers(5);
		set.remove(0);
		
		Double p = productSumSet(set,2);
		
		assert(p==((4.0*3)+(4*2)+4+(3*2)+3+2));
		
		p = sumProductSet(set,2);
		
		assert(p== (1.0+2)*(1+3)*(1+4)*(2+3)*(2+4)*(3+4));
		
	}
}