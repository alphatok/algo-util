package com.alphatok.graph.tree;

import java.util.*;

public class GraphVizTreeEdge {
    String nodeIdA;
    String nodeIdB;
    Map<String, String> attrs = new TreeMap<>(String::compareTo);

    public GraphVizTreeEdge(String nodeIdA, String nodeIdB) {
        this.nodeIdA = nodeIdA;
        this.nodeIdB = nodeIdB;
    }

    public String getNodeIdA() {
        return nodeIdA;
    }

    public String getNodeIdB() {
        return nodeIdB;
    }

    public void addAttr(String key, String val) {
        attrs.put(key, val);
    }

    public void addAttrs( Map<String, String> attrs){
        this.attrs.putAll(attrs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GraphVizTreeEdge)) return false;
        GraphVizTreeEdge that = (GraphVizTreeEdge) o;
        return nodeIdA.equals(that.nodeIdA) &&
                nodeIdB.equals(that.nodeIdB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeIdA, nodeIdB);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(nodeIdA).append("->").append(nodeIdB).append(" [");
        // Node9->Node10 [color=white];
        Iterator<Map.Entry<String, String>> iterator = attrs.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            buffer.append(entry.getKey()).append("=").append(entry.getValue());
            if (iterator.hasNext()) {
                buffer.append(",");
            }
        }
        buffer.append("]");
        return buffer.toString();
    }
}
