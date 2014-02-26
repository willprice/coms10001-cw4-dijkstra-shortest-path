import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static junitparams.JUnitParamsRunner.$;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ShortestPathTests {

	private double maxError = 0.0001;

	@Test
	@Parameters
	public void shortestPathOnUnweightedGraphs(double expectedLength, NGraph graph, NNode endNode) throws Exception {
		ShortestPath shortestPath = new ShortestPath(graph, new NNode("1"), endNode);
		assertEquals(expectedLength, shortestPath.calculateShortestPath(), maxError);
	}
	@SuppressWarnings("unused")
	private Object[] parametersForShortestPathOnUnweightedGraphs() {
		return $(
				$(1, NGraph.createGraph(new Object[]{"1", "2", 1.0}), new NNode("2")),
				$(2, NGraph.createGraph(new Object[][]{{"1", "2", 1.0}, {"2", "3", 1.0}}), new NNode("3")),
				$(2, NGraph.createGraph(new Object[]{"1", "2", 1.0}, new Object[]{"1", "3", 3.0}, new Object[]{"2", "3", 1.0}),
					 new NNode("3"))
				);
		
		

	}
	
	@Test
	@Parameters
	public void initialDistanceValues(Map<NNode, Double> expectedDistances, NGraph graph, NNode startNode) throws Exception {
		ShortestPath shortestPath = createShortestPath(graph, startNode, null);
		shortestPath.assignInitialDistances();
		assertEquals(expectedDistances, shortestPath.getCurrentBestEstimates());
	}
	@SuppressWarnings("unused")
	private Object[] parametersForInitialDistanceValues() {
		return $(
				$(createHashMap(new Object[]{"1", 0.0}, new Object[]{"2", Double.POSITIVE_INFINITY}), 
				  NGraph.createGraph(new Object[]{"1", "2", 1.0}), new NNode("1")),
				  $(createHashMap(new Object[]{"1", 0.0}, new Object[]{"2", Double.POSITIVE_INFINITY}, 
						  		  new Object[]{"3", Double.POSITIVE_INFINITY}),
				  NGraph.createGraph(new Object[]{"1", "2", 1.0}, new Object[]{"2", "3", 1.0}), new NNode("1"))
				);
	}
	
	@Test
	@Parameters
	public void tentativeDistancesToNodes(Map<NNode, Double> expectedDistances, NGraph graph, NNode currentNode) throws Exception {
		ShortestPath shortestPath = createShortestPath(graph, null, null);
		shortestPath.calculateTentativeDistancesToNodes(currentNode);
		assertEquals(expectedDistances, shortestPath.getTentativeDistancesToNodes());
	}
	@SuppressWarnings("unused")
	private Object[] parametersForTentativeDistancesToNodes() {
		return $(
				$(createHashMap(new Object[]{"1", 0.0}, new Object[]{"2", 1.0}),
				  NGraph.createGraph(new Object[]{"1", "2", 1.0}),
				  new NNode("1")),
				$(createHashMap(new Object[]{"1", 0.0}, new Object[]{"2", 2.0}),
				  NGraph.createGraph(new Object[]{"1", "2", 2.0}),
				  new NNode("1")),
				$(createHashMap(new Object[]{"1", 0.0}, new Object[]{"2", 1.0}, new Object[]{"3", 1.0}),
				  NGraph.createGraph(new Object[]{"1", "2", 1.0}, new Object[]{"1", "3", 1.0}),
				  new NNode("1")),
				$(createHashMap(new Object[]{"1", 0.0}, new Object[]{"2", 1.0}, new Object[]{"3", Double.POSITIVE_INFINITY}),
				  NGraph.createGraph(new Object[]{"1", "2", 1.0}, new Object[]{"2", "3", 1.0}),
				  new NNode("1")),
				$(createHashMap(new Object[]{"1", 0.0}, new Object[]{"2", 1.0}, 
						        new Object[]{"3", 1.0}, new Object[]{"4", Double.POSITIVE_INFINITY}),
				  NGraph.createGraph(new Object[]{"1", "2", 1.0}, new Object[]{"1", "3", 1.0}, new Object[]{"3", "4", 1.0}),
				  new NNode("1"))
				);
	}
	
	@Test
	public void updateCurrentBestEstimatesInFirstIteration() throws Exception {
		NNode startNode = new NNode("1");
		NNode currentNode = startNode;
		NGraph graph = NGraph.createGraph(new Object[]{"1", "2", 1.0}, new Object[]{"1", "3", 1.0}, new Object[]{"2", "3", 1.0});
		Map<NNode, Double> expectedCurrentBestEstimate = createHashMap(new Object[]{"1", 0.0}, new Object[]{"2", 1.0},
																	   new Object[]{"3", 1.0});
		ShortestPath shortestPath = createShortestPath(graph, startNode, null);
		shortestPath.updateCurrentBestEstimates(currentNode);
		assertEquals(expectedCurrentBestEstimate, shortestPath.getCurrentBestEstimates());
	}

	@Test
	public void updateCurrentBestEstimatesInSecondIteration() throws Exception {
		NNode startNode = new NNode("1");
		NNode currentNode = startNode;
		NGraph graph = NGraph.createGraph(new Object[]{"1", "2", 1.0}, new Object[]{"1", "3", 3.0}, new Object[]{"2", "3", 1.0});
		Map<NNode, Double> expectedCurrentBestEstimate = createHashMap(new Object[]{"1", 0.0}, new Object[]{"2", 1.0},
																	   new Object[]{"3", 2.0});
		ShortestPath shortestPath = createShortestPath(graph, startNode, null);
		
		shortestPath.updateCurrentBestEstimates(currentNode);
		shortestPath.unvisitedNodes.remove(currentNode);
		currentNode = new NNode("2");
		shortestPath.updateCurrentBestEstimates(currentNode);
		
		assertEquals(expectedCurrentBestEstimate, shortestPath.getCurrentBestEstimates());
	}
		
	private ShortestPath createShortestPath(NGraph graph, NNode startNode, NNode endNode) {
		ShortestPath shortestPath = new ShortestPath(graph, startNode, endNode);
		return shortestPath;
	}
	
	private Map<NNode, Double> createHashMap(Object[]...hashMapEntries) {
		Map<NNode, Double> map = new HashMap<>();
		for (Object[] entry : hashMapEntries) {
			String nodeId = (String)entry[0];
			NNode node = new NNode(nodeId);
			Double expectedBestDistanceEstimate = (Double) entry[1];
			map.put(node, expectedBestDistanceEstimate);
		}
		return map;
	}
}
