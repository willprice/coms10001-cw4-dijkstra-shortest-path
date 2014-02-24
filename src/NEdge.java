
public class NEdge {

	private Edge edge;
	
	public NEdge(Edge edge) {
		this.edge = edge;
	}

	public void setWeight(double weight) {
		edge.weight = weight;
	}

	public double weight() {
		return edge.weight();
	}
	
	public NNode connectedTo(NNode node) {
		NNode connectedNode = new NNode(edge.connectedTo(node.id()));
		return connectedNode;
	}
	
	public boolean connectsNode(NNode node) {
		return edge.connectsNode(node.id());
	}
}
