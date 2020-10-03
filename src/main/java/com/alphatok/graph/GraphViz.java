package com.alphatok.graph;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * source: https://blog.csdn.net/qq_18208435/article/details/70464654
 */
public class GraphViz {
    private String runPath = "";
    private String dotPath = "";
    private String runOrder = "";
    private String dotCodeFile = "dotcode.txt";
    private String resultGif = "dotGif";
    private StringBuilder graph = new StringBuilder();

    Runtime runtime = Runtime.getRuntime();

    public void run() throws InterruptedException, IOException {
        File file = new File(runPath);
        file.mkdirs();
        writeGraphToFile(graph.toString(), runPath);
        creatOrder();
        Process exec = runtime.exec(runOrder);
        exec.waitFor(10, TimeUnit.SECONDS);
    }

    public void creatOrder() {
        runOrder += dotPath + " ";
        runOrder += runPath;
        runOrder += "\\" + dotCodeFile + " ";
        runOrder += "-T gif ";
        runOrder += "-o ";
        runOrder += runPath;
        runOrder += "\\" + resultGif + ".gif";
    }

    public void writeGraphToFile(String dotcode, String filename) {
        try {
            File file = new File(filename + "\\" + dotCodeFile);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(dotcode.getBytes());
            fos.close();
        } catch (java.io.IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public GraphViz(String runPath, String dotPath) {
        this.runPath = runPath;
        this.dotPath = dotPath;
    }

    public void add(String line) {
        graph.append("\t" + line);
    }

    public void addln(String line) {
        graph.append("\t" + line + "\n");
    }

    public void addNode(String line) {
        graph.append("\t" + line + "\n");
    }

    public void addln() {
        graph.append('\n');
    }

    public void start_graph() {
        graph.append("digraph G {\n");
//        graph.append("node [style=filled color=\"#C0FF3E\"]\n");
//        graph.append("edge [color=\"sienna\" fontcolor=\"green\"]\n");
    }

    public void end_graph() {
        graph.append("}");
    }
}