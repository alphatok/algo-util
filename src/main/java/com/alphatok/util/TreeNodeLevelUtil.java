package com.alphatok.util;

import com.alphatok.domain.TreeNode;
import com.alphatok.domain.TreeNodeLevelBuilder;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNodeLevelUtil {

    public static void println(TreeNode root){
        if (root == null) {
            System.out.println("null");
            return;
        }

        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        StringBuilder buffer = new StringBuilder();
        while (!nodes.isEmpty()) {
            TreeNode tn = nodes.poll();
            buffer.append(TreeNodeLevelBuilder.SEPARATOR).append(tn.val);

            TreeNode left = tn.left;
            if (left != null) {
                buffer.append(TreeNodeLevelBuilder.SEPARATOR).append(left.val);
                if (left.left != null) {
                    nodes.add(left.left);
                }
                if (left.right != null) {
                    nodes.add(left.right);
                }
            }else{
                buffer.append(TreeNodeLevelBuilder.SEPARATOR).append(TreeNodeLevelBuilder.EMPTY_NODE);
            }

            TreeNode right = tn.right;
            if (right != null) {
                buffer.append(TreeNodeLevelBuilder.SEPARATOR).append(right.val);
                if (right.left != null) {
                    nodes.add(right.left);
                }
                if (right.right != null) {
                    nodes.add(right.right);
                }
            }else{
                buffer.append(TreeNodeLevelBuilder.SEPARATOR).append(TreeNodeLevelBuilder.EMPTY_NODE);
            }
        }

        // remove trailing #
        String serialized = buffer.toString().substring(1);

        // remove prevailing ,
        char[] array = serialized.toCharArray();
        int stopIndex = array.length - 1;
        for (int i = array.length - 1; i >= 0; i--) {
            String str = String.valueOf(array[i]);
            boolean validNodeVal = !TreeNodeLevelBuilder.EMPTY_NODE.equals(str) &&
                    !TreeNodeLevelBuilder.SEPARATOR.equals(str);
            if (validNodeVal){
                stopIndex = i;
                break;
            }
        }

        System.out.println(new String(array, 0, stopIndex + 1));
    }
}
