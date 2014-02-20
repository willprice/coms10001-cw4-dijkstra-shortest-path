import static org.junit.Assert.*;
import junitparams.*;
import junitparams.converters.*;
import junitparams.mappers.*;



import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class CommandLineProgramTests {

	@Test
	@Parameters
	public void isPart(boolean expectedTruthValue, int expectedPart, int part) {
		assertEquals(expectedTruthValue, new CommandLineProgram().isPart(expectedPart, new String[]{"-p" + part}));
	}
	
	private Object[] parametersForIsPart() {
		return $(
				$(true, 1, 1),
				$(false, 1, 2)		
				);
	}

}
