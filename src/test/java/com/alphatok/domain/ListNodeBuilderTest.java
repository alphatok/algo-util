package com.alphatok.domain;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class ListNodeBuilderTest {

    @Test
    public void testAppend() {
        ListNodeBuilder builder = new ListNodeBuilder(1).append(2).append(3);
        ListNode head = builder.getHead();
        Assert.assertEquals(1, head.val);
        Assert.assertEquals(2, head.next.val);
        Assert.assertEquals(3, head.next.next.val);
        Assert.assertNull(head.next.next.next);
    }

    @Test
    public void testTestAppend() {
        ListNodeBuilder builder = new ListNodeBuilder(1).append(new int[]{2,3});
        ListNode head = builder.getHead();
        Assert.assertEquals(1, head.val);
        Assert.assertEquals(2, head.next.val);
        Assert.assertEquals(3, head.next.next.val);
        Assert.assertNull(head.next.next.next);
    }
}