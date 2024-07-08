

import java.util.List;

import org.junit.jupiter.api.Test;

import someMath.EratosthenesSieb;

public class EratosthenesSiebTest 
{

	@Test
	public void testPrimes()
	{
		new EratosthenesSieb(1000);
		List<Integer> primeList = EratosthenesSieb.getPrimeList();
		assert(primeList.get(0)==2);
		
		
		System.out.println("size: " + primeList.size()+" ### Last Prime: " + primeList.get(999));
	}
}
