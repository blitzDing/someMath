package someMath;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.util.Pair;

public class DirectedWeightedGraph <V>//Vertex is a Generic not a Class!!!
{

	private final Map<V, Set<Pair<Double, V>>> connectionMap;
	
	public DirectedWeightedGraph()
	{		
		connectionMap = new HashMap<>();
	}
	
	/*
	 * v1 is connected to v2. But not the other Way around.
	 * v2 is not connected to v1. So every Connection has 
	 * a direction.
	*/
	public void connect(V v1, V v2, double weight)
	{
		
		Set<Pair<Double, V>> destiniesOfV1 = getDestiniesOf(v1);

		destiniesOfV1.add(new Pair<Double, V>(weight, v2));//If it's already there it won't add. This is a Set.
		connectionMap.put(v1, destiniesOfV1);
	}
	
	public void doubleConnect(V v1, V v2, double weightV1, double weightV2)
	{
		connect(v1, v2, weightV1);
		connect(v2, v1, weightV2);
	}
	
	public Set<Pair<Double, V>> getDestiniesOf(V v)
	{
		
		Set<Pair<Double, V>> destiniesOfV;

		if(connectionMap.containsKey(v))destiniesOfV = connectionMap.get(v);
		else destiniesOfV = new HashSet<>();

		return destiniesOfV;
	}
	
	public double getWeightOfConnection(V v1, V v2)
	{
		
		Set<Pair<Double, V>> destiniesOfV1 = getDestiniesOf(v1);

		if(destiniesOfV1.isEmpty())throw new IllegalArgumentException("The Vertex in the first Argument is not 'Source' of any Connection.");
		else
		{
			for(Pair<Double, V> destination: destiniesOfV1)
			{
				if(destination.getValue().equals(v2))return destination.getKey();
			}
		}
		
		throw new IllegalArgumentException("Vertex " + v1 + " is not pointing to " + v2);
	}
	
	public Set<V> whoPointsToThisVertex(V v1)
	{

		Set<V> incoming = new HashSet<>();

		for(V v2: connectionMap.keySet())
		{
			Set<Pair<Double, V>> set = connectionMap.get(v2);

			for(Pair<Double, V> con: set)
			{
				if(con.getValue().equals(v1))incoming.add(v2);
			}
		}

		return incoming;
	}
}