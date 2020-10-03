package com.alphatok.util;

import com.alphatok.domain.ListNode;
import com.alphatok.domain.TreeNode;
import com.alphatok.graph.GraphViz;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class GraphUtil {

    private static String TMP_PATH = "F:\\github\\algo-util\\target";
    private static String DOT_PATH = "C:\\Program Files\\Graphviz 2.44.1\\bin\\dot.exe";

    static ThreadLocal<HashMap<TreeNode, Integer>> NODE_ID_CACHE = new ThreadLocal<>();
    static ThreadLocal<AtomicInteger> NO_INC_CACHE = new ThreadLocal<>();


    public static void paintAndOpen(TreeNode node) throws IOException, InterruptedException {
        if (node == null) {
            return;
        }

        GraphViz gViz = new GraphViz(TMP_PATH, DOT_PATH);
        gViz.start_graph();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        LinkedHashMap<TreeNode, Integer> nodeMap = new LinkedHashMap<>();
        NODE_ID_CACHE.set(nodeMap);
        NO_INC_CACHE.set(new AtomicInteger(1));
        gViz.addln("forcelabels=true;");
        try {
            while (!queue.isEmpty()) {
                TreeNode p = queue.poll();
                gViz.addln(getNodeIdLabel(p));
                gViz.addNode(String.format("%s;", getNodeId(p)));

                TreeNode left = p.left;
                if (left != null) {
                    gViz.addln(getNodeIdLabel(left));
                    gViz.addln(String.format("%s->%s;", getNodeId(p), getNodeId(left)));
                    queue.add(left);
                }else{
                    TreeNode random = random();
                    gViz.addln(getNodeIdLabelBlank(random));
                    gViz.addln(String.format("%s->%s;", getNodeId(p), getNodeId(random)));
                }

                TreeNode right = p.right;
                if (right != null) {
                    gViz.addln(getNodeIdLabel(right));
                    gViz.addln(String.format("%s->%s;", getNodeId(p), getNodeId(right)));
                    queue.add(right);
                }else{
                    TreeNode random = random();
                    gViz.addln(getNodeIdLabelBlank(random));
                    gViz.addln(String.format("%s->%s;", getNodeId(p), getNodeId(random)));
                }
            }
        } finally {
            NODE_ID_CACHE.remove();
            NO_INC_CACHE.remove();
        }

        gViz.end_graph();
        gViz.run();

        String fileName = "F:\\github\\algo-util\\target\\dotGif.gif";
        try {
            openByDefaultImageView(fileName);
        } catch (IOException e) {
            throw new RuntimeException("failed to open file: " + fileName);
        }
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

        return String.format("Node%s", no);
    }

    private static String getNodeIdLabel(TreeNode p) {
        HashMap<TreeNode, Integer> nodeMap = NODE_ID_CACHE.get();
        Integer no = nodeMap.get(p);
        if (no == null) {
            no = NO_INC_CACHE.get().getAndIncrement();
            nodeMap.put(p, no);
        }

        return String.format("Node%s [label=%s] ", no, p.val);
    }

    private static String getNodeIdLabelBlank(TreeNode p) {
        HashMap<TreeNode, Integer> nodeMap = NODE_ID_CACHE.get();
        Integer no = nodeMap.get(p);
        if (no == null) {
            no = NO_INC_CACHE.get().getAndIncrement();
            nodeMap.put(p, no);
        }

        return String.format("Node%s [label=nil] ", no, p.val);
    }

    public static void openByDefaultImageView(String fileName) throws IOException {
        File f = new File(fileName);
        Desktop dt = Desktop.getDesktop();
        dt.open(f);
        System.out.println("Done.");
    }

    public static void openByDefaultImageViewCmdline(String fileName) throws IOException, InterruptedException {
        String [] commands = {
                "cmd.exe","/c","start","\"DummyTitle\"","\"" + fileName + "\""
        };
        Process p = Runtime.getRuntime().exec(commands);
        p.waitFor();
    }
}
