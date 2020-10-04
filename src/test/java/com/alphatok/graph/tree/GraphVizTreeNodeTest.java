package com.alphatok.graph.tree;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class GraphVizTreeNodeTest {

    @Test
    public void testToString() {
        GraphVizTreeNode nodes = new GraphVizTreeNode("Node8");

        nodes.addAttr("label", "nil");
        Map<String, String> map = new HashMap<>();
        map.put("color", "white");
        map.put("fontcolor", "white");
        nodes.addAttrs(map);

        String expected = "Node8 [color=white,fontcolor=white,label=nil]";
        assertEquals(expected, nodes.toString());
    }

    @Test
    public void testToStringNoAttr() {
        GraphVizTreeNode nodes = new GraphVizTreeNode("Node8");
        String expected = "Node8 []";
        assertEquals(expected, nodes.toString());
    }

    @Test
    public void testHash() {
        Map<GraphVizTreeNode, String> map = new HashMap<>();
        GraphVizTreeNode node1 = new GraphVizTreeNode("Node1");
        map.put(node1, "Node1");
        GraphVizTreeNode node2 = new GraphVizTreeNode("Node2");
        map.put(node2, "Node2");
        GraphVizTreeNode node1_dup = new GraphVizTreeNode("Node1");
        map.put(node1_dup, "Node1_dup");
        GraphVizTreeNode node3 = new GraphVizTreeNode("Node3");
        map.put(node3, "Node3");
        assertEquals(3, map.size());
        assertEquals("Node1_dup", map.get(node1));
        assertEquals("Node1_dup", map.get(node1_dup));
        assertEquals("Node2", map.get(node2));
        assertEquals("Node3", map.get(node3));
    }
}