import java.io.IOException;


public class ShortestPath extends CommandLineProgram {
	private NGraph graph;

	public ShortestPath(NGraph graph) {
		this.graph = graph;
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
		return 0;
	}
}
