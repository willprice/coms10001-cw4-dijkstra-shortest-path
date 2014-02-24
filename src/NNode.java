
public class NNode {
	private Node node;

	public NNode(Node node) {
		this.node = node;
	}

	public String id() {
		return node.name();
	}

}
