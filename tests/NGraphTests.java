import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import junitparams.Parameters;

import org.junit.Test;

import static junitparams.JUnitParamsRunner.$;
import junitparams.JUnitParamsRunner;

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
	
	@Test
	@Parameters
	public void findingNeighboursOfNodes(NGraph graph, List<NNode> expectedNeighbours, NNode node) throws Exception {
		assertEquals(expectedNeighbours, graph.findNeighbours(node));
	}
	private Object[] parametersForFindingNeighboursOfNodes() {
		return $(
				$(NGraph.createGraph(new Object[]{"1", "2", 1.0}), 
									 Arrays.asList(new Object[]{new NNode("2")}),
									 new NNode("1")
				),
				$(NGraph.createGraph(new Object[]{"1", "2", 1.0}, new Object[]{"1", "3", 1.0}),
									 Arrays.asList(new Object[]{new NNode("2"), new NNode("3")}), 
									 new NNode("1")
				)
				);
	}
}
