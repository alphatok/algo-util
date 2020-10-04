package com.alphatok.util;

import com.alphatok.domain.TreeNode;
import com.alphatok.graph.tree.GraphVizTreeFileBuilder;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class GraphUtil {

    private static String TMP_PATH;
    static {
        File directory = new File("");
        try {
            TMP_PATH = directory.getCanonicalPath() + File.separator + "target";
        } catch (IOException e) {
            TMP_PATH = "/tmp";
        }
    }
    // TODO REFACTOR
    private static String DOT_COMMAND = "dot";

    static ThreadLocal<HashMap<TreeNode, Integer>> NODE_ID_CACHE = new ThreadLocal<>();
    static ThreadLocal<AtomicInteger> NO_INC_CACHE = new ThreadLocal<>();


    public static void paintAndOpen(TreeNode node, boolean open) throws IOException, InterruptedException {
        if (node == null) {
            return;
        }

        StringBuilder fileContent = GraphVizTreeFileBuilder.create(node).build();
        File tmpPath = new File(TMP_PATH);
        if (!tmpPath.exists()) {
            tmpPath.mkdirs();
        }
        String dotfileName = System.currentTimeMillis() + ".dot";
        String paintFileName = System.currentTimeMillis() + ".gif";
        RuntimeUtil.execCommand("cd " + tmpPath);
        writeGraphToFile(fileContent.toString(), dotfileName);

        paintViz(dotfileName, paintFileName);
        if (open) {
            openByDefaultImageView(paintFileName);
        }
    }

    public static void paintViz(String dotfile, String paintfile) throws IOException, InterruptedException {
        String command = String.format("%s %s -Tgif -o %s", DOT_COMMAND, dotfile, paintfile);
        RuntimeUtil.execCommand(command);
    }

    public static void writeGraphToFile(String fileContent, String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                boolean newFile = file.createNewFile();
                if (!newFile) {
                    throw new RuntimeException("failed to create file:" + filename);
                }
            }
            try(FileWriter fileWriter = new FileWriter(file.getAbsoluteFile())) {
                try( BufferedWriter bw = new BufferedWriter(fileWriter)) {
                    bw.write(fileContent);
                }
            }
        } catch (java.io.IOException ioe) {
            throw new RuntimeException("failed to write file:" + filename);
        }
    }

    public static void openByDefaultImageView(String fileName) throws IOException {
        File f = new File(fileName);
        Desktop dt = Desktop.getDesktop();
        dt.open(f);
    }
}
