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
		return $(createGraph(new Object[][]{{"1", "2", 0.5}}));
	}
	
	
	private NGraph createGraph(Object[][] edges) {
		NGraph graph = new NGraph(new Graph());
		for (Object[] edgeInfo : edges) {
			if (edgeInfo[0] == null) return null;
			
			String startNodeId = (String) edgeInfo[0];
			String endNodeId = (String) edgeInfo[1];
			double weight = (double) edgeInfo[2];
			
			NNode startNode = createNode(startNodeId);
			NNode endNode = createNode(endNodeId);
			
			NEdge edge = createEdge(startNodeId, endNodeId, weight);
			graph.add(startNode, endNode);
			graph.add(edge);			
		}
		return graph;
	}
	
	private NEdge createEdge(String id1, String id2, double weight) {
		Edge.EdgeType defaultEdgeType = Edge.EdgeType.Bus;
		return createEdge(id1, id2, weight, defaultEdgeType);
	}
	
	private NEdge createEdge(String id1, String id2, double weight, Edge.EdgeType type) {
		return new NEdge(new Edge(id1, id2, weight, type));
	}
	
	private NNode createNode(String id) {
		return new NNode(new Node(id));
	}
}
