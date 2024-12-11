package someMath;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import consoleTools.BashSigns;
import someMath.exceptions.CollectionException;

import static someMath.CollectionManipulation.*;
import static consoleTools.TerminalXDisplay.*;


public class CollectionManipulationTests
{

	static Set<Character> originSet = getSetOfFirstNLatinLettersLowerCase(4);
	static Set<Character> otherSet = getSetOfFirstNLatinLettersLowerCase(5);


	@Test
	public void testPowerSet() throws CollectionException
	{

		printBoldAndBlue("\nTesting power Set.");

		Set<Set<Character>> pSet = powerSet(originSet);
		System.out.println(collectionToString(pSet));
		
		assert(pSet.size()==16);
		
		pSet = powerSet(otherSet);
		System.out.println(collectionToString(pSet));
		System.out.println(BashSigns.boldRBCPX + "hi" + BashSigns.boldRBCSX);
		
		assert(pSet.size()==32);
	}
	
	@Test
	public void combinationsWithRepition() throws CollectionException
	{
		
		printBoldAndBlue("\nTesting Combinations with Repition.");
		int n = originSet.size();
		int k = 2;
		System.out.println(n + " choose " + k + " with Repition.");
		Set<List<Character>> combis = combinationsOfSizeNWithRepition(originSet, k);
		System.out.println(collectionToString(combis));
		
		assert(combis.size()==SmallTools.nChooseK(n+k-1, k));

		n = otherSet.size();
		k = 3;
		System.out.println(n + " choose " + k + " with Repition.");
		combis = combinationsOfSizeNWithRepition(otherSet, k);
		System.out.println(collectionToString(combis));
		
		assert(combis.size()==SmallTools.nChooseK(n+k-1, k));
	}
	
	@Test
	public void permutationsWithRepitionTest() throws CollectionException
	{
		
		printBoldAndBlue("\nTesting Permutations with Repition.");
		List<Character> originList = new ArrayList<>(Arrays.asList('a', 'b', 'a', 'b', 'b'));
		int n = originList.size();
		int k1 = 3;
		int k2 = 2;;
		System.out.println("Permutation of " + n + " k1:" + k1 + " k2:" + k2);
		Set<List<Character>> permutations = permutationsWithRepition(originList);
		System.out.println(collectionToString(permutations));
		
		assert(permutations.size()==SmallTools.faculty(n)/(SmallTools.faculty(k1)*SmallTools.faculty(k2)));

		originList = new ArrayList<>(Arrays.asList('a', 'a', 'a', 'b', 'b', 'c', 'c'));
		n = originList.size();
		int k3 = 2;
		System.out.println("Permutation of " + n + " k1:" + k1 + " k2:" + k2 + " k3:" +k3);
		permutations = permutationsWithRepition(originList);
		System.out.println(collectionToString(permutations));
		
		assert(permutations.size()==SmallTools.faculty(n)/(SmallTools.faculty(k1)*SmallTools.faculty(k2)*SmallTools.faculty(k3)));
	}

	@Test
	public void permutationsWithoutRepitionTest() throws CollectionException
	{
		
		printBoldAndBlue("\nTesting Permutations without Repition.");
		int n = originSet.size();
		System.out.println("Permutation of " + n);
		Set<List<Character>> permutations = permutationsWithoutRepition(originSet);
		System.out.println(collectionToString(permutations));
		
		assert(permutations.size()==SmallTools.faculty(n));

		n= otherSet.size();
		System.out.println("Permutations of " + n);
		permutations = permutationsWithoutRepition(otherSet);
		System.out.println(collectionToString(permutations));
		
		assert(permutations.size()==SmallTools.faculty(n));
	}
	
	@Test
	public void combinationsOfSizeN() throws CollectionException
	{
		
		printBoldAndBlue("\nTesting Combinations without Repition.");
		int n = originSet.size();
		int k = 2;
		System.out.println(n + " Choose " + k);
		Set<Set<Character>> combis = combinationsOfSizeNWithoutRepition(originSet, k);
		System.out.println(collectionToString(combis));
		
		assert(combis.size()==SmallTools.nChooseK(n, k));

		n= otherSet.size();
		k= 3;
		System.out.println(n + " Choose " + k);
		combis = combinationsOfSizeNWithoutRepition(otherSet, k);
		System.out.println(collectionToString(combis));
		
		assert(combis.size()==SmallTools.nChooseK(n, k));
	}

	@Test
	public void variationsWithRepition() throws CollectionException
	{
		
		printBoldAndBlue("\nTesting Variations with Reptition.");
		int n = originSet.size();
		int k= 2;
		System.out.println( k + " out of " + n);
		Set<List<Character>> variations = variationsOfSizeNWithRepition(originSet, k);
		System.out.println(collectionToString(variations));
		
		assert((double)(variations.size())==Math.pow(n, k));

		n = otherSet.size();
		k = 3;
		System.out.println(k + " out of " + n);
		variations = variationsOfSizeNWithRepition(otherSet, k);
		System.out.println(collectionToString(variations));
		
		assert((double)(variations.size())==Math.pow(n, k));
	}

	@Test
	public void testProductOfSetAndList() throws CollectionException
	{
		
		printBoldAndBlue("\nTesting product of Set and List.");

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
	public void testProductSum() throws CollectionException
	{
		printBoldAndBlue("\nTesting product Sum.");

		Set<Integer> set = getSetOfFirstNIntegers(5);
		set.remove(0);
		
		Double p = productSumSet(set,2);
		
		assert(p==((4.0*3)+(4*2)+4+(3*2)+3+2));
		
		p = sumProductSet(set,2);
		
		assert(p== (1.0+2)*(1+3)*(1+4)*(2+3)*(2+4)*(3+4));
		
	}
}