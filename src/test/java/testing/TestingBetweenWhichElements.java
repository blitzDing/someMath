package testing;


public class TestingBetweenWhichElements 
{

	/*
	public static void main(String[] args) 
	{
		List<Pair<Character, Double>> list = new ArrayList<>( Arrays.asList
			(
				new Pair<Character, Double>('@', 1.0),
				new Pair<Character, Double>('#', 1.5),
				new Pair<Character, Double>('Q', 2.3), 
				new Pair<Character, Double>('€', 0.5),
				new Pair<Character, Double>('€', 0.5),
				new Pair<Character, Double>('Q', 2.3),
				new Pair<Character, Double>('#', 1.5),
				new Pair<Character, Double>('@', 1.0)
			)
		);
		int size = list.size();
		
		
		List<Double> l2 = CollectionManipulation.getRidOfTheGeneric(list);
		double z = CollectionManipulation.randomNrBoundBetween(l2);
		int k = CollectionManipulation.betweenWhichElements(z, list);
		List<Double> sumUpToIndex = CollectionManipulation.sumUpToIndex(l2);
		
		for(int n=0;n<size;n++)
		{
			String note = "";
			if(n==k)note = " <- " + z;
			String lowerBound = "0.0";
			if(n>0)lowerBound = sumUpToIndex.get(n-1)+"";
			String upperBound = sumUpToIndex.get(n)+"";
			System.out.println(list.get(n).getKey()+":: " + lowerBound + "-" + upperBound + note);
		}
	}
	*/
}