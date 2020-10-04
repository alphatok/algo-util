package com.alphatok.graph.tree;

import com.alphatok.graph.GraphVizFileBuilder;

public class GraphVizTreeNodeVirtual extends GraphVizTreeNode {
    public GraphVizTreeNodeVirtual(String nodeId) {
        super(nodeId);
        addAttrs(GraphVizFileBuilder.WHITE_ALL);
        addAttr("virtual", "true");
    }
}
