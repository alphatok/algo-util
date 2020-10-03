package com.alphatok.domain;

import org.junit.Assert;
import org.junit.Test;

public class TreeNodeLevelBuilderTest  {

    @Test
    public void testBuild() {
        TreeNodeLevelBuilder builder = TreeNodeLevelBuilder.create("1,2,3,#,#,4,#,#,5");
        TreeNode root = builder.build();
        Assert.assertEquals(1, root.val);

        Assert.assertEquals(2, root.left.val);
        Assert.assertNull(root.left.left);
        Assert.assertNull(root.left.right);

        Assert.assertEquals(3, root.right.val);
        Assert.assertNull(root.right.right);

        Assert.assertEquals(4, root.right.left.val);
        Assert.assertNull(root.right.left.left);

        Assert.assertEquals(5, root.right.left.right.val);
        Assert.assertEquals(5, root.right.left.right.val);

        Assert.assertNull(root.right.left.right.left);
        Assert.assertNull(root.right.left.right.right);
    }

    @Test
    public void testBuildCust() {
        {
            TreeNodeLevelBuilder builder = TreeNodeLevelBuilder.create("132,98");
            TreeNode root = builder.build();
            Assert.assertEquals(132, root.val);
            Assert.assertEquals(98, root.left.val);
            Assert.assertNull(root.right);
        }

        {
            TreeNodeLevelBuilder builder = TreeNodeLevelBuilder.create("132,98,32");
            TreeNode root = builder.build();
            Assert.assertEquals(132, root.val);
            Assert.assertEquals(98, root.left.val);
            Assert.assertEquals(32, root.right.val);
        }
    }
}