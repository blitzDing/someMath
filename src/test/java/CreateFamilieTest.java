

import org.junit.jupiter.api.Test;

import someMath.SmallTools;

import java.util.*;

class CreateFamilieTest {

	@Test
	void test() 
	{
		
		Set<Character> one = new HashSet<>();
		one.add('a');
		
		Set<Character> two = new HashSet<>();
		two.add('b');
		two.add('c');
		
		Set<Character> three = new HashSet<>();
		three.add('d');
		three.add('e');
		
		Set<Set<Character>> origin = new HashSet<>();
		origin.add(one);
		origin.add(two);
		origin.add(three);
		
		Set<Set<Character>> output = SmallTools.createFamilie(origin);

		System.out.println("\nOrigin:");
		for(Set<Character> e: origin)
		{
			System.out.print("{");
			
			for(Character c: e)
			{
				System.out.print(c+",");
			}
			System.out.print("}, ");
			
		}
		
		System.out.println("\nOutput:");
		for(Set<Character> e: output)
		{
			System.out.print("{");
			
			for(Character c: e)
			{
				System.out.print(c+",");
			}
			System.out.print("}, ");
		}
		
		for(Set<Character> s1: output)
		{
			for(Set<Character> s2: output)
			{
				Set<Character> a = new HashSet<>();
				a.addAll(s1);
				a.addAll(s2);
				
				assert(output.contains(a));
			}
		}
	}

}
