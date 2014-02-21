import java.util.List;


public class NGraph {
	protected List<NNode> nodes;
	protected List<NEdge> edges;
	
	public NGraph(Graph graph) {
		for (Node node : graph.nodes()) {
			nodes.add(new NNode(node));
		}
		
		for (Edge edge : graph.edges()) {
			edges.add(new NEdge(edge));
		}
	}
	
	public void add(NNode... nodes) {
		for (NNode node : nodes) {
			add(node);
		}
	}

	public void changeAllWeights(double weight) {
		for (NEdge edge : edges()) {
			edge.setWeight(weight);
		}
	}

	public List<NEdge> edges() {
		return edges;
	}

	public void add(NEdge edge) {
		edges.add(edge);
	}
	
}
