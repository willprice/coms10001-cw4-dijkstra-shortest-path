import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShortestPath extends CommandLineProgram {
	private NGraph graph;
	private Map<NNode, Double> currentBestEstimates;
	private NNode startNode;
	private NNode endNode;
	private List<NNode> unvisitedNodes;

	public ShortestPath(NGraph graph) {
		this.graph = graph;
		currentBestEstimates = new HashMap<>();
		unvisitedNodes = new ArrayList<>(graph.nodes());
	}

	public ShortestPath(NGraph graph, NNode startNode, NNode endNode) {
		this(graph);
		this.startNode = startNode;
		this.endNode = endNode;
		unvisitedNodes.remove(startNode);
	}

	public static void main(String[] args) {
		String filename = args[1];
		
		Reader reader = new Reader();
		try {
			reader.read(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ShortestPath program = new ShortestPath(new NGraph(reader.graph()));
		if (program.isPart(1, args)) {
			program.println("Part 1");
		}
	}

	public double calculateShortestPath() {
		return 1;
	}

	public void assignInitialDistances() {
		for (NNode node : graph.nodes())
			currentBestEstimates.put(node, Double.POSITIVE_INFINITY);
		currentBestEstimates.put(startNode, 0.0);
	}

	public Map<NNode, Double> getCurrentBestEstimates() {
		return currentBestEstimates;
	}

	public Map<NNode, Double> calculateTentativeDistancesToNodes(NNode currentNode) {
		return new HashMap<>();
	}
}
