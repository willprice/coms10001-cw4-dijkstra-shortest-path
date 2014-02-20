
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class CommandLineProgramTests {

	@Test
	@Parameters
	public void isPart(boolean expectedTruthValue, int expectedPart, int part) {
		assertEquals(expectedTruthValue, new CommandLineProgram().isPart(expectedPart, new String[]{"-p" + part}));
	}
	
	@SuppressWarnings("unused")
	private Object[] parametersForIsPart() {
		return $(
				$(true, 1, 1),
				$(false, 1, 2),
				$(true, 2, 2)
				);
	}

}
