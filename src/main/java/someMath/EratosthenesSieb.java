package someMath;

import java.util.ArrayList;
import java.util.List;

public class EratosthenesSieb 
{

	private static List<Integer> primeList = new ArrayList<>();
	private int primeNr=1;
	
	public EratosthenesSieb(int maxPrimeNr)
	{
		
		int integerStart = 2;
		primeList.add(integerStart);
		
		for(int k = integerStart;primeNr<maxPrimeNr;k++)
		{
			
			boolean isPrime = true;
			for(int m: primeList)if(k%m==0)isPrime = false;

			if(isPrime)
			{
				primeList.add(k);
				primeNr++;
			}
		}
	}
	
	public static List<Integer> getPrimeList()
	{
		return primeList;
	}
}