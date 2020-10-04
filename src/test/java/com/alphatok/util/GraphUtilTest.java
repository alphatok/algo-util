package com.alphatok.util;

import com.alphatok.domain.TreeNode;
import com.alphatok.domain.TreeNodeLevelBuilder;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class GraphUtilTest {

    @Test
    public void paintAndOpen() throws IOException, InterruptedException {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        GraphUtil.paintAndOpen(root);
    }

    @Test
    public void paintAndOpenBuilder() throws IOException, InterruptedException {
        TreeNodeLevelBuilder builder = TreeNodeLevelBuilder.create("1,2,30,#,#,4,#,#,-5");
        TreeNode root = builder.build();
        GraphUtil.paintAndOpen(root);
    }

    @Test
    public void openByDefaultImageView() {
        try {
            GraphUtil.openByDefaultImageView("E:\\background\\WeChat Image_20200214210752.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void openByDefaultImageViewCmdline() {
        try {
            GraphUtil.openByDefaultImageViewCmdline("E:\\background\\WeChat Image_20200214210752.jpg");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}