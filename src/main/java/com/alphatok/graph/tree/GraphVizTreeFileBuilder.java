package com.alphatok.graph.tree;

import com.alphatok.domain.TreeNode;
import com.alphatok.graph.GraphVizFileBuilder;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class GraphVizTreeFileBuilder extends GraphVizFileBuilder {

    private final Set<GraphVizTreeNode> nodes = new LinkedHashSet<>();
    private final Set<GraphVizTreeEdge> edges = new LinkedHashSet<>();

    static ThreadLocal<HashMap<TreeNode, Integer>> NODE_ID_CACHE = new ThreadLocal<>();
    static ThreadLocal<AtomicInteger> NO_INC_CACHE = new ThreadLocal<>();

    public static GraphVizTreeFileBuilder create(TreeNode node){
        if (node == null) {
            throw new RuntimeException("empty node");
        }

        // transform treenode
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(node);

        LinkedHashMap<TreeNode, Integer> nodeMap = new LinkedHashMap<>();
        NODE_ID_CACHE.set(nodeMap);
        NO_INC_CACHE.set(new AtomicInteger(1));

        GraphVizTreeFileBuilder builder = new GraphVizTreeFileBuilder();
        try{
            while (!nodeQueue.isEmpty()) {
                TreeNode p = nodeQueue.poll();
                GraphVizTreeNode parentTreeNode = new GraphVizTreeNode(getNodeId(p));
                parentTreeNode.setAttrLable(String.valueOf(p.val));
                builder.addNode(parentTreeNode);

                TreeNode left = p.left;
                if (left != null) {
                    GraphVizTreeNode leftTreeNode = new GraphVizTreeNode(getNodeId(left));
                    leftTreeNode.setAttrLable(String.valueOf(left.val));
                    builder.addNode(leftTreeNode);
                    builder.addEdge(new GraphVizTreeEdge(getNodeId(p), getNodeId(left)));
                    nodeQueue.add(left);
                }else{
                    // virtual node && edge
                    TreeNode virtualLeft = random();
                    builder.addNode(new GraphVizTreeNodeVirtual(getNodeId(virtualLeft)));
                    builder.addEdge(new GraphVizTreeEdgeVirtual(getNodeId(p), getNodeId(virtualLeft)));
                }

                TreeNode right = p.right;
                if (right != null) {
                    GraphVizTreeNode rightTreeNode = new GraphVizTreeNode(getNodeId(right));
                    rightTreeNode.setAttrLable(String.valueOf(right.val));
                    builder.addNode(rightTreeNode);
                    builder.addEdge(new GraphVizTreeEdge(getNodeId(p), getNodeId(right)));
                    nodeQueue.add(right);
                }else{
                    // virtual node && edge
                    TreeNode virtualRight = random();
                    builder.addNode(new GraphVizTreeNodeVirtual(getNodeId(virtualRight)));
                    builder.addEdge(new GraphVizTreeEdgeVirtual(getNodeId(p), getNodeId(virtualRight)));
                }
            }
        } finally {
            NODE_ID_CACHE.remove();
            NO_INC_CACHE.remove();
        }

        return builder;
    }

    private void addEdge(GraphVizTreeEdge edge) {
        edges.add(edge);
    }

    private void addNode(GraphVizTreeNode node) {
        nodes.add(node);
    }

    public static TreeNode random(){
        return new TreeNode(-1);
    }

    private static String getNodeId(TreeNode p) {
        HashMap<TreeNode, Integer> nodeMap = NODE_ID_CACHE.get();
        Integer no = nodeMap.get(p);
        if (no == null) {
            no = NO_INC_CACHE.get().getAndIncrement();
            nodeMap.put(p, no);
        }

        return String.format("No%s", no);
    }

    @Override
    public StringBuilder build() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(GraphVizFileBuilder.START_LINE);
        // build nodes;
        for (GraphVizTreeNode node : nodes) {
            buffer.append("    ").append(node.toString()).append(";\n");
        }

        // build edges
        for (GraphVizTreeEdge edge : edges) {
            buffer.append("    ").append(edge.toString()).append(";\n");
        }

        buffer.append(GraphVizFileBuilder.END_LINE);
        return buffer;
    }


}