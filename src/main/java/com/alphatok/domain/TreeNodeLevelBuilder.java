package com.alphatok.domain;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNodeLevelBuilder {

    private TreeNode root = null;

    public final static String SEPARATOR = ",";
    public final static String EMPTY_NODE = "#";

    private String[] nodes = null;

    /**
     * https://blog.nowcoder.net/n/954373f213e14eeab0a69ed0e9ef1b6e
     *
     * @param str 1,2,3,#,#,4,#,#,5
     * @return TreeNodeLevelBuilder
     */
    public static TreeNodeLevelBuilder create(String str){
        TreeNodeLevelBuilder builder = new TreeNodeLevelBuilder();
        builder.nodes = str.split(SEPARATOR);;
        return builder;
    }

    public TreeNode build(){
        if (nodes == null || nodes.length == 0) {
            return null;
        }

        int idx = 0;
        root = new TreeNode(Integer.parseInt(nodes[idx]));
        Queue<TreeNode> level = new LinkedList<>();
        level.add(root);
        while(!level.isEmpty()){
            TreeNode front = level.poll();

            idx++;
            if (idx < nodes.length){
                if (!EMPTY_NODE.equals(nodes[idx])) {
                    front.left = new TreeNode(Integer.parseInt(nodes[idx]));
                    level.add(front.left);
                }
            }

            idx++;
            if (idx < nodes.length){
                if (!EMPTY_NODE.equals(nodes[idx])) {
                    front.right = new TreeNode(Integer.parseInt(nodes[idx]));
                    level.add(front.right);
                }
            }
        }

        return root;
    }
}
