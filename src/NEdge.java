
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
}
