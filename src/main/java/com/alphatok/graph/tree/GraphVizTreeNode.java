package com.alphatok.graph.tree;

import java.util.*;

public class GraphVizTreeNode {

    private final String nodeId;
    private final Map<String, String> attrs = new TreeMap<>(String::compareTo);

    public GraphVizTreeNode(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setAttrLable(String label){
        attrs.put("label", label);
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
        if (!(o instanceof GraphVizTreeNode)) return false;
        GraphVizTreeNode that = (GraphVizTreeNode) o;
        return nodeId.equals(that.nodeId);
    }

    @Override
    public int hashCode() {
        return nodeId.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(nodeId).append(" [");
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
