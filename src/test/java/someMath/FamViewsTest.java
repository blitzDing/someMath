package someMath;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static someMath.CollectionManipulation.*;
import static someMath.MathSetClassClosedUnderUnion.*;
import static consoleTools.TerminalXDisplay.*;
class FamViewsTest
{

	static Set<Character> A;
	static Set<Character> B;
	static Set<Character> C;
	static Set<Character> D;
	static Set<Character> E;
	static Set<Character> F;
	static Set<Character> G;
	static Set<Character> H;
	static Set<Character> I;

	static Set<Set<Character>> basis;

	
	void prep()
	{
		
	}

	@Test
	void test() throws CollectionException
	{
		
		A = new HashSet<>(Arrays.asList('a', 'b', 'x'));
		B = new HashSet<>(Arrays.asList('c', 'd', 'x'));
		C = new HashSet<>(Arrays.asList('e', 'f', 'y'));
		D = new HashSet<>(Arrays.asList('g', 'h', 'y'));
		E = new HashSet<>(Arrays.asList('i', 'j', 'k'));
		F = new HashSet<>(Arrays.asList('l', 'm', 'n'));
		G = new HashSet<>(Arrays.asList('o', 'p', 'q'));
		H = new HashSet<>(Arrays.asList('j', 'm', 'p'));

		basis = new HashSet<>(Arrays.asList(A, B, C, D, E, F, G, H));

		printBoldAndBlue("Basis = A bis H");
		Set<Set<Character>> fam = createFamilie(basis);
	
		Set<Character> implodedBasis = implode(basis);
		displayContentOfFamByAbundance(fam);
	}

	@Test
	void test2() throws CollectionException
	{

		printBoldAndBlue("Basis = {A, B, C, D, E, F, G, H}");
		A = new HashSet<>(Arrays.asList('a', 'b', 'c', 'y'));
		B = new HashSet<>(Arrays.asList('d', 'e', 'f', 'y'));
		C = new HashSet<>(Arrays.asList('g', 'h', 'i', 'y'));
		D = new HashSet<>(Arrays.asList('j', 'k', 'l', 'y'));
		E = new HashSet<>(Arrays.asList('m', 'n', 'o', 'y'));
		F = new HashSet<>(Arrays.asList('p', 'q', 'r', 'y'));
		G = new HashSet<>(Arrays.asList('s', 't', 'u', 'y'));
		H = new HashSet<>(Arrays.asList('v', 'w', 'x', 'y'));

		basis = new HashSet<>(Arrays.asList(A, B, C, D, E, F, G, H));

		Set<Set<Character>> fam = createFamilie(basis);
		displayContentOfFamByAbundance(fam);
	}

	@Test
	void test3() throws CollectionException
	{

		printBoldAndBlue("Basis = {A, B, C, D, E, F}");
		A = new HashSet<>(Arrays.asList('a', 'b', 'c', 'y'));
		B = new HashSet<>(Arrays.asList('d', 'e', 'f', 'y'));
		C = new HashSet<>(Arrays.asList('g', 'h', 'i', 'y'));
		D = new HashSet<>(Arrays.asList('j', 'k', 'l', 'x'));
		E = new HashSet<>(Arrays.asList('m', 'n', 'o', 'x'));
		F = new HashSet<>(Arrays.asList('p', 'q', 'r', 'x'));

		basis = new HashSet<>(Arrays.asList(A, B, C, D, E, F));

		Set<Set<Character>> fam = createFamilie(basis);
		displayContentOfFamByAbundance(fam);

	}

	@Test
	void test4() throws CollectionException
	{

		printBoldAndBlue("Basis = {A, B, C, D, E, F}");
		A = new HashSet<>(Arrays.asList('a', 'b', 'c', 'y'));
		B = new HashSet<>(Arrays.asList('d', 'e', 'f', 'y'));
		C = new HashSet<>(Arrays.asList('g', 'h', 'i', 'z'));
		D = new HashSet<>(Arrays.asList('j', 'k', 'l', 'z'));
		E = new HashSet<>(Arrays.asList('m', 'n', 'o', 'x'));
		F = new HashSet<>(Arrays.asList('p', 'q', 'r', 'x'));

		basis = new HashSet<>(Arrays.asList(A, B, C, D, E, F));

		Set<Set<Character>> fam = createFamilie(basis);
		displayContentOfFamByAbundance(fam);
	}
	
	void displayContentOfFamByAbundance(Set<Set<Character>> fam) throws CollectionException
	{
		Set<Character> implodedFam = implode(fam);

		System.out.println("Fam Size: " + fam.size());
		printBoldAndYellow(collectionToString(fam));
		int cnt = 0;
		for(Character c: implodedFam)
		{
			if(!isAbundand(c, fam))
			{
				
				printBoldAndRed(c + " is not abundand: ");
		
				Set<Set<Character>> sets = findContainingSets(c, fam);
				System.out.println("Nr. of Sets containing " + c + ": " + sets.size());
			}
			else cnt++;
		}
		if(cnt==implodedFam.size())printBoldAndYellow("Everything is abundand");
	}
}