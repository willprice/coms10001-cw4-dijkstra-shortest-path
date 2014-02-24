import static org.junit.Assert.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static junitparams.JUnitParamsRunner.$;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ShortestPathTests {

	private double maxError = 0.0001;

	@Test
	@Parameters(method="unweightedGraphs")
	public void shortestPathOnUnweightedGraphs(double expectedLength, NGraph graph) throws Exception {
		ShortestPath shortestPath = new ShortestPath(graph);
		assertEquals(expectedLength, shortestPath.calculateShortestPath(), maxError);
	}
	@SuppressWarnings("unused")
	private Object[] unweightedGraphs() {
		return $(
				$(1, NGraph.createGraph(new Object[][]{{"1", "2", 1.0}})),
				$(2, NGraph.createGraph(new Object[][]{{"1", "2", 1.0}, {"2", "3", 1.0}}))
				);
	}

}
