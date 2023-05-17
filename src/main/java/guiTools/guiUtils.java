package guiTools;

import java.util.Collection;
import java.util.function.Consumer;

import javafx.scene.Node;
import javafx.scene.control.Control;

public class guiUtils 
{

	public static void doCollectionOfNodes(Collection<? extends Node> nodes, Consumer<? super Node> nodeConsumer)
	{
		for(Node node: nodes)nodeConsumer.accept(node);
	}
}
