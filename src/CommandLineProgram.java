public class CommandLineProgram {
	private CommandLineProgram program;

	public CommandLineProgram() {
	}
	
	public boolean isPart(int partNumber, String[] args) {
		if (args[0].equals("-p" + partNumber))
			return true;			
		return false;
	}
	
	public void println(String message) {
		System.out.println(message);
	}
	
	public void print(String message) {
		System.out.print(message);
	}
}
