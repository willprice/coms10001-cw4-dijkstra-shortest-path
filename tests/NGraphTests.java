import static org.junit.Assert.*;

import java.util.List;

import junitparams.Parameters;

import org.junit.Test;

import static junitparams.JUnitParamsRunner.$;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class NGraphTests {

	private double maxError = 0.0001;

	@Test
	@Parameters(method="exampleGraphs")
	public void testAllWeightsAreChangedToOne(NGraph graph) {
		if (graph == null) fail("Graph is null");
		graph.changeAllWeights(1);
		for (NEdge edge : graph.edges())
			assertEquals(1, edge.weight(), maxError);
	}
	@SuppressWarnings("unused")
	private Object[] exampleGraphs() {
		return $(NGraph.createGraph(new Object[][]{{"1", "2", 0.5}}));
	}
}
