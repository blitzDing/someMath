package someMath;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.util.Pair;

public class DirectedWeightedGraph <Vertex>//Vertex is a Generic not a Class!!!
{

	private final Map<Vertex, Set<Pair<Double, Vertex>>> connectionMap;
	
	public DirectedWeightedGraph()
	{		
		connectionMap = new HashMap<>();
	}
	
	/*
	 * a is connected to b. But not the other Way around.
	 * b is not connected to a. So every Connection has 
	 * a direction.
	*/
	public void connect(Vertex a, Vertex b, double weight)
	{
		
		Set<Pair<Double, Vertex>> destiniesOfA = getDestiniesOf(a);

		destiniesOfA.add(new Pair<Double, Vertex>(weight, b));//If it's already there it won't add. This is a Set.
		connectionMap.put(a, destiniesOfA);
	}
	
	public void doubleConnect(Vertex a, Vertex b, double weightA, double weightB)
	{
		connect(a,b, weightA);
		connect(b,a, weightB);
	}
	
	public Set<Pair<Double, Vertex>> getDestiniesOf(Vertex a)
	{
		
		Set<Pair<Double, Vertex>> destiniesOfA;

		if(connectionMap.containsKey(a))destiniesOfA = connectionMap.get(a);
		else destiniesOfA = new HashSet<>();

		return destiniesOfA;
	}
	
	public double getWeightOfConnection(Vertex a, Vertex b)
	{
		
		Set<Pair<Double, Vertex>> destiniesOfA = getDestiniesOf(a);

		if(destiniesOfA.isEmpty())throw new IllegalArgumentException("The Vertex in the first Argument is not 'Source' of any Connection.");
		else
		{
			for(Pair<Double, Vertex> destination: destiniesOfA)
			{
				if(destination.getValue().equals(b))return destination.getKey();
			}
		}
		
		throw new IllegalArgumentException("Vertex " + a + " is not pointing to " + b.toString());
	}
	
	public Set<Vertex> whoPointsToThisVertex(Vertex a)
	{

		Set<Vertex> incoming = new HashSet<>();

		for(Vertex b: connectionMap.keySet())
		{
			Set<Pair<Double, Vertex>> set = connectionMap.get(b);

			for(Pair<Double, Vertex> con: set)
			{
				if(con.getValue().equals(a))incoming.add(b);
			}
		}

		return incoming;
	}
}