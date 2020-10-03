package com.alphatok.util;

import com.alphatok.domain.TreeNode;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeNodeLevelUtilTest {

    @Test
    public void println() {
        {
            String result = PrintInterceptor.print(() -> {
                TreeNodeLevelUtil.println(null);
            });

            assertEquals("null\r\n", result);
        }

        {
            String result = PrintInterceptor.print(() -> {
                TreeNodeLevelUtil.println(new TreeNode(132));
            });

            System.out.println("result = " + result);
            assertEquals("132\r\n", result);
        }

        {
            String result = PrintInterceptor.print(() -> {
                TreeNode root = new TreeNode(132);
                root.left = new TreeNode(98);
                TreeNodeLevelUtil.println(root);
            });

            System.out.println("result = " + result);
            assertEquals("132,98\r\n", result);
        }

        {
            String result = PrintInterceptor.print(() -> {
                TreeNode root = new TreeNode(132);
                root.left = new TreeNode(98);
                root.right = new TreeNode(32);
                TreeNodeLevelUtil.println(root);
            });

            System.out.println("result = " + result);
            assertEquals("132,98,32\r\n", result);
        }
    }
}