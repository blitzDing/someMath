

import java.util.List;

import org.junit.jupiter.api.Test;

import someMath.EratosthenesSieb;

public class EratosthenesSiebTest 
{

	@Test
	public void testPrimes()
	{
		EratosthenesSieb es = new EratosthenesSieb(20);
		List<Integer> primeList = es.getPrimeList();
		assert(primeList.get(0)==2);
		
		
		System.out.println(primeList);
	}
}
