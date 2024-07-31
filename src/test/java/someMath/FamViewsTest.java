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

	@BeforeEach
	void prep()
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
	}

	@Test
	void test() throws CollectionException
	{
		int n = 26;
		Set<Character> main = getSetOfFirstNLatinLettersLowerCase(n);
		Set<Set<Character>> fam = createFamilie(basis);
	
		Set<Character> implodedBasis = implode(basis);

		System.out.println("Fam Size: " + fam.size());
		System.out.println(collectionToString(fam));
		for(Character c: implodedBasis)
		{
			System.out.println(c + " is abundand: " + isAbundand(c, fam));
		
			Set<Set<Character>> sets = findContainingSets(c, fam);
			System.out.println("Nr. of Sets containing " + c + ": " + sets.size());
			//System.out.println(collectionToString(sets));
		}
		
		System.out.println(collectionToString(fam));
	}

}
