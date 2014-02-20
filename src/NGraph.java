import java.util.List;


public class NGraph {
	protected List<NNode> nodes;
	protected List<NEdge> edges;
	
	public NGraph(List<Edge> edges, List<Node> nodes) {
		this.edges().addAll(edges);
		this.nodes().addAll(nodes);
	}

	public NGraph() {
		super();
	}

	public void add(NNode... nodes) {
		for (NNode node : nodes) {
			add(node);
		}
	}

	public void changeAllWeights(double weight) {
		for (Edge edge : edges()) {
			edge.weight = weight;
		}
	}
	
}
