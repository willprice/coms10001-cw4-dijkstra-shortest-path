
public class ShortestPath extends CommandLineProgram {
	public static void main(String[] args) {
		ShortestPath program = new ShortestPath();
		if (program.isPart(1, args)) {
			program.println("Part 1");
		}
	}
}
