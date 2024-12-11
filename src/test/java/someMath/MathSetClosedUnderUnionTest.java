package someMath;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import someMath.exceptions.CollectionException;

import static consoleTools.TerminalXDisplay.*;

import static someMath.MathSetClosedUnderUnion.*;


public class MathSetClosedUnderUnionTest 
{

	static Set<Character> bigOne;
	
	static Set<Character> anotherSmallOne;

	static Set<Character> smallOne;

	static Set<Character> differentAndSmall;
	
	static Set<Character> alsoDifferent;
	
	static Set<Character> clusterBSet;
	
	static Set<Character> anotherDifferentAndSmall;

	static Set<Character> betweenbigAndSmall;

	static Set<Character> evenSmaller;
	
	static Set<Character> xotic;

	@BeforeEach
	public void prepare()
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
		
		alsoDifferent = new HashSet<>();
		alsoDifferent.add('g');
		alsoDifferent.add('h');
		alsoDifferent.add('i');
		
		clusterBSet = new HashSet<>();
		clusterBSet.add('i');
		clusterBSet.add('j');
		clusterBSet.add('k');
		
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
		
		xotic = new HashSet<>();
		xotic.add('x');
		xotic.add('y');
		xotic.add('z');
	}
	
	@Test
	public void implosionTest() throws CollectionException
	{
		printBoldAndBlue("Implosion test");

		Set<Set<Character>> someSets = new HashSet<>();
		someSets.add(bigOne);
		someSets.add(smallOne);
		someSets.add(betweenbigAndSmall);
		someSets.add(bigOne);
		someSets.add(anotherDifferentAndSmall);
		
		
		System.out.println("Origin: \n" + collectionToString(someSets));
		Set<Character> intersection = intersectHoleSetOfSets(someSets);
		System.out.println("\nIntersection:\n" + collectionToString(intersection));
	}

	@Test
	public void findClusterTest() throws CollectionException
	{
		printBoldAndBlue("Find Cluster test");

		Set<Set<Character>> someSets = new HashSet<>();
		
		Set<Set<Character>> clusterA = new HashSet<>();
		Set<Set<Character>> clusterB = new HashSet<>();
		Set<Set<Character>> clusterC = new HashSet<>();
		
		clusterA.add(bigOne);
		clusterA.add(smallOne);
		clusterA.add(anotherSmallOne);//cluster A
		someSets.addAll(clusterA);
		
		clusterB.add(differentAndSmall);
		clusterB.add(alsoDifferent);
		clusterB.add(clusterBSet);//cluster B
		someSets.addAll(clusterB);
		
		clusterC.add(xotic);//cluster C
		someSets.addAll(clusterC);
		
		Set<Set<Set<Character>>> clusters = findClusters(someSets);
		
		System.out.println(collectionToString(clusters));
		
		assert(clusters.size()==3);
		assert(clusters.contains(clusterA));
		assert(clusters.contains(clusterB));
		assert(clusters.contains(clusterC));
	}

	@Test
	public void traversClusterTest() throws CollectionException
	{
		printBoldAndBlue("Traverse Cluster test");

		Set<Set<Character>> someSets = new HashSet<>();
		
		Set<Set<Character>> clusterA = new HashSet<>();
		Set<Set<Character>> clusterB = new HashSet<>();
		Set<Set<Character>> clusterC = new HashSet<>();
		
		clusterA.add(bigOne);
		clusterA.add(smallOne);
		clusterA.add(anotherSmallOne);//cluster A
		someSets.addAll(clusterA);
		
		clusterB.add(differentAndSmall);
		clusterB.add(alsoDifferent);
		clusterB.add(clusterBSet);//cluster B
		someSets.addAll(clusterB);
		
		clusterC.add(xotic);//cluster C
		someSets.addAll(clusterC);
		
		Set<Set<Character>> cluster = traverseCluster(bigOne, someSets);
		
		System.out.println(collectionToString(cluster));
		
		assert(cluster.size()==3);
		assert(cluster.equals(clusterA));
	}

	@Test
	public void findContainingSetsTest() throws CollectionException
	{
		printBoldAndBlue("find Containg Sets test");

		Set<Set<Character>> someSets = new HashSet<>();
		Set<Set<Character>> clusterA = new HashSet<>();
		Set<Set<Character>> clusterB = new HashSet<>();
		Set<Set<Character>> clusterC = new HashSet<>();
		
		clusterA.add(bigOne);
		clusterA.add(smallOne);
		clusterA.add(anotherSmallOne);//cluster A
		someSets.addAll(clusterA);
		
		clusterB.add(differentAndSmall);
		clusterB.add(alsoDifferent);
		clusterB.add(clusterBSet);//cluster B
		someSets.addAll(clusterB);
		
		clusterC.add(xotic);//cluster C
		someSets.addAll(clusterC);
		
		Character chara = (Character)'c';
		
		Set<Set<Character>> containers = findContainingSets(chara, someSets);
		
		assert(containers.size()==3);
		assert(containers.containsAll(clusterA));
		
		System.out.println(collectionToString(containers));
	}

	@Test
	public void abundanceTest() throws CollectionException
	{
		
		printBoldAndBlue("Abundance Test test");
		
		int n = 7;
		Set<Character> main = CollectionManipulation.getSetOfFirstNLatinLettersLowerCase(n);
		Set<Set<Character>> powerSet = CollectionManipulation.powerSet(main);
	
		
		for(Character c2: main)
		{
			assert(isAbundand(c2, powerSet));
		}
	}
	@Test
	public void famTestTest() throws CollectionException
	{

		printBoldAndBlue("fam Test test");	
		
		Set<Set<Character>> fam1 = new HashSet<>();
		fam1.add(bigOne);
		fam1.add(smallOne);
		fam1.add(anotherSmallOne);
		
		System.out.println(collectionToString(fam1));
		assert(famTest(fam1));	
		
		Set<Set<Character>> fam2 = new HashSet<>();
		fam1.add(bigOne);
		fam1.add(betweenbigAndSmall);
		fam1.add(smallOne);
		fam1.add(evenSmaller);

		assert(famTest(fam2));

		Set<Set<Character>> fam3 = new HashSet<>();
		fam3.add(smallOne);
		fam3.add(anotherSmallOne);
		
		assertFalse(famTest(fam3));

		Set<Set<Character>> fam4 = new HashSet<>();
		fam4.add(bigOne);
		fam4.add(differentAndSmall);
		
		assertFalse(famTest(fam4));
	}
	
	/*
	@Test
	public void typeTest() throws CollectionException
	{
		printBoldAndBlue("Type Set of Sets test");
		Set<Set<Character>> someSets = new HashSet<>();
		someSets.add(smallOne);
		someSets.add(anotherSmallOne);
		someSets.add(bigOne);
		someSets.add(differentAndSmall);
		printBoldAndBlue(collectionToString(bigOne));
		
		int [] type = typeOfSetOfSets(someSets);
		
		assert(type.length==2);
		assert(type[0]==2);
		assert(type[1]==1);
		System.out.println("Size: " + type.length);
		System.out.println("****" + type[0] + ", " + type[1]);

		type = MathSetClassClosedUnderUnion.typeOfSetOfSets(someSets);
		assert(type.length==3);
		assert(type[0]==0);
		assert(type[1]==2);
		assert(type[2]==1);


		System.out.println("\n\n");

		for(int n=0;n<type.length;n++)
		{
		 System.out.println(n + "-->" + type[n]);
		}
	}
    */
	
	@Test
	public void createFamTest()
	{
		printBoldAndBlue("Not testing create Fam.");
		assert(true);
	}
}
