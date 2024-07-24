package someMath;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static consoleTools.BashSigns.*;
import static consoleTools.TerminalXDisplay.*;

import static someMath.MathSetClassClosedUnderUnion.*;


class MathSetClassClosedUnderUnionTest 
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
	void prepare()
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
	void implosionTest() throws CollectionException
	{
		printlnBoldAndBlue("Implosion test");

		Set<Set<Character>> someSets = new HashSet<>();
		someSets.add(bigOne);
		someSets.add(smallOne);
		someSets.add(betweenbigAndSmall);
		someSets.add(bigOne);
		someSets.add(anotherDifferentAndSmall);
		
		
		System.out.println("Origin: \n" + collectionToString(someSets));
		Set<Character> intersection = MathSetClassClosedUnderUnion.intersectHoleSetOfSets(someSets);
		System.out.println("\nIntersection:\n" + collectionToString(intersection));
	}

	@Test
	void findClusterTest() throws CollectionException
	{
		printlnBoldAndBlue("Find Cluster test");

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
	void traversClusterTest() throws CollectionException
	{
		printlnBoldAndBlue("Traverse Cluster test");

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
	void findContainingSetsTest() throws CollectionException
	{
		printlnBoldAndBlue("find Containg Sets test");

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
	void famTestTest() throws CollectionException
	{

		printlnBoldAndBlue("fam Test test");	
		
		Set<Set<Character>> fam1 = new HashSet<>();
		fam1.add(bigOne);
		fam1.add(smallOne);
		fam1.add(anotherSmallOne);
		
		System.out.println(collectionToString(fam1));
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
		someSets.add(differentAndSmall);
		printlnBoldAndBlue(collectionToString(bigOne));
		
		int [] type = MathSetClassClosedUnderUnion.typeOfSetOfSets(someSets);
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


		System.out.println("\n\n");

		for(int n=0;n<type.length;n++)
		{
		 System.out.println(n + "-->" + type[n]);
		}
	}

	@Test
	void createFamTest()
	{
		printlnBoldAndBlue("Not testing create Fam.");
		assert(true);
	}
	
	void printlnBoldAndBlue(String s)
	{
		System.out.println(boldBBCPX+s+boldBBCSX);
	}
}
