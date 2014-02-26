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
	public List<NNode> unvisitedNodes;
	private Map<NNode, Double> tentativeDistancesToNodes;

	public ShortestPath(NGraph graph) {
		this.graph = graph;
		currentBestEstimates = new HashMap<>();
		unvisitedNodes = new ArrayList<>(graph.nodes());
	}

	public ShortestPath(NGraph graph, NNode startNode, NNode endNode) {
		this(graph);
		this.startNode = startNode;
		this.endNode = endNode;
		assignInitialDistances();
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
			NNode startNode = new NNode(args[2]);
			NNode endNode = new NNode(args[3]);

			program = new ShortestPath(new NGraph(reader.graph()), startNode, endNode);
			program.graph.changeAllWeights(1.0);
			program.println("Number of moves: " + (int)program.calculateShortestPath());
		} else if (program.isPart(2, args)) {
			NNode startNode = new NNode(args[2]);
			NNode endNode = new NNode(args[3]);

			program = new ShortestPath(new NGraph(reader.graph()), startNode, endNode);
			program.println(String.format("Distance Travelled: %.2fkm", program.calculateShortestPath()));
		}
	}

	public double calculateShortestPath() {
		while (!(unvisitedNodes.isEmpty())) {
			NNode minimalBestEstimateNode = getUnvisitedNodeWithMinimalCurrentBestEstimate();
			unvisitedNodes.remove(minimalBestEstimateNode);
			updateCurrentBestEstimates(minimalBestEstimateNode);
		}
		return getCurrentBestEstimates().get(endNode);
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
		tentativeDistancesToNodes = new HashMap<>();
		for (NEdge edge : graph.edges()) {
			NNode oppositeNode = edge.connectedTo(currentNode);
			if (oppositeNode != null)
				tentativeDistancesToNodes.put(oppositeNode, edge.weight());
			else {
				updateCurrentBestDistanceForNodeNotConnectedToCurrentNode(new NNode(edge.id1()));
				updateCurrentBestDistanceForNodeNotConnectedToCurrentNode(new NNode(edge.id2()));
			}
		}
		tentativeDistancesToNodes.put(currentNode, 0.0);
		return tentativeDistancesToNodes;
	}
	
	public Map<NNode, Double> getTentativeDistancesToNodes() {
		return tentativeDistancesToNodes;
	}

	private void updateCurrentBestDistanceForNodeNotConnectedToCurrentNode(NNode node) {
		Double currentBestDistance = tentativeDistancesToNodes.get(node);
		if (currentBestDistance == null || currentBestDistance == Double.POSITIVE_INFINITY)
			tentativeDistancesToNodes.put(node, Double.POSITIVE_INFINITY);
	}

	public void updateCurrentBestEstimates(NNode currentNode) {
		Map<NNode, Double> tentativeDistances = calculateTentativeDistancesToNodes(currentNode);
		Double bestEstimateForCurrentNode = currentBestEstimates.get(currentNode);
		for (NNode node : unvisitedNodes) {
			Double updatedBestEstimate = Math.min(currentBestEstimates.get(node), bestEstimateForCurrentNode + tentativeDistances.get(node));
			currentBestEstimates.put(node, updatedBestEstimate);
		}
	}
	
	private NNode getUnvisitedNodeWithMinimalCurrentBestEstimate() {
		NNode currentNodeWithMinimalBestEstimate = unvisitedNodes.get(0);
		for (NNode node : unvisitedNodes) {
				if (currentBestEstimates.get(node) < currentBestEstimates.get(currentNodeWithMinimalBestEstimate)) {
					currentNodeWithMinimalBestEstimate = node;
				}
		}
		return currentNodeWithMinimalBestEstimate;
	}
		
}
