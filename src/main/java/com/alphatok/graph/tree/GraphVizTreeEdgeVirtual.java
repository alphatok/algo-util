package com.alphatok.graph.tree;

import com.alphatok.graph.GraphVizFileBuilder;

public class GraphVizTreeEdgeVirtual extends GraphVizTreeEdge{

    public GraphVizTreeEdgeVirtual(String nodeIdA, String nodeIdB) {
        super(nodeIdA, nodeIdB);
        addAttrs(GraphVizFileBuilder.WHITE_ALL);
    }
}
