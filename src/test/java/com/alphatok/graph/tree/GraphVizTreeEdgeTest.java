package com.alphatok.graph.tree;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class GraphVizTreeEdgeTest {

    @Test
    public void testToString() {
        GraphVizTreeEdge nodes = new GraphVizTreeEdge("Node9", "Node10");

        nodes.addAttr("label", "nil");
        Map<String, String> map = new HashMap<>();
        map.put("color", "white");
        map.put("fontcolor", "white");
        nodes.addAttrs(map);

        String expected = "Node9->Node10 [color=white,fontcolor=white,label=nil]";
        assertEquals(expected, nodes.toString());
    }

    @Test
    public void testToStringNoAttr() {
        GraphVizTreeEdge nodes = new GraphVizTreeEdge("Node9", "Node10");

        String expected = "Node9->Node10 []";
        assertEquals(expected, nodes.toString());
    }

    @Test
    public void testHash() {
        Map<GraphVizTreeEdge, String> map = new HashMap<>();
        GraphVizTreeEdge node12 = new GraphVizTreeEdge("Node1", "Node2");
        map.put(node12, "Node1->Node2");

        GraphVizTreeEdge node23 = new GraphVizTreeEdge("Node2", "Node3");
        map.put(node23, "Node2->Node3");
        GraphVizTreeEdge node12_dup = new GraphVizTreeEdge("Node1", "Node2");
        map.put(node12_dup, "Node1->Node2_dp");

        assertEquals(2, map.size());
        assertEquals("Node1->Node2_dp", map.get(node12));
        assertEquals("Node1->Node2_dp", map.get(node12_dup));
        assertEquals("Node2->Node3", map.get(node23));
    }
}