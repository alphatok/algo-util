package com.alphatok.util;

import com.alphatok.domain.TreeNode;
import com.alphatok.domain.TreeNodeLevelBuilder;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class GraphUtilTest {

    @Test
    public void paintAndOpen() throws IOException, InterruptedException {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        GraphUtil.paintAndOpen(root, true);
    }

    @Test
    public void paintViz() throws IOException, InterruptedException {
        File directory = new File("");
        String projecRoot = directory.getCanonicalPath();
        String tmpPath = projecRoot + File.separator + "target" + File.separator ;
        String paintfile = tmpPath + "demo.gif";
        File destFile = new File(paintfile);
        if (destFile.exists()) {
            destFile.delete();
        }
        GraphUtil.paintViz(tmpPath + "demo.dot", paintfile);
    }

    @Test
    public void writeGraphToFile() throws IOException {
        File directory = new File("");
        String projecRoot = directory.getCanonicalPath();
        String tmpPath = projecRoot + File.separator + "target" + File.separator ;
        String content = "digraph G {\n" +
                "    No2 [label=2];\n" +
                "    No1 [label=1];\n" +
                "    No4 [color=white,fontcolor=white];\n" +
                "    No3 [label=3];\n" +
                "    No6 [color=white,fontcolor=white];\n" +
                "    No5 [color=white,fontcolor=white];\n" +
                "    No7 [color=white,fontcolor=white];\n" +
                "    No2->No5 [color=white,fontcolor=white];\n" +
                "    No3->No6 [color=white,fontcolor=white];\n" +
                "    No3->No7 [color=white,fontcolor=white];\n" +
                "    No1->No2 [];\n" +
                "    No1->No3 [];\n" +
                "    No2->No4 [color=white,fontcolor=white];\n" +
                "}";
        String filename = tmpPath + "demo.dot";
        File destFile = new File(filename);
        if (destFile.exists()) {
            destFile.delete();
        }
        GraphUtil.writeGraphToFile(content, filename);
    }

    @Test
    public void paintAndOpenBuilder() throws IOException, InterruptedException {
        TreeNodeLevelBuilder builder = TreeNodeLevelBuilder.create("1,2,30,#,#,4,#,#,-5");
        TreeNode root = builder.build();
        GraphUtil.paintAndOpen(root, false);
    }

    @Test
    @Ignore
    public void openByDefaultImageView()  throws IOException, InterruptedException {
        String imagePath = getImagePath();
        GraphUtil.openByDefaultImageView(imagePath);
    }


    private String getImagePath() throws IOException {
        File directory = new File("");
        String projecRoot = directory.getCanonicalPath();
        return projecRoot + File.separator +  "src\\test\\resources\\read.jpg";
    }

}