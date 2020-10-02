package com.alphatok.util;

import com.alphatok.domain.ListNode;
import com.alphatok.domain.ListNodeBuilder;
import org.junit.Assert;
import org.junit.Test;


public class ListNodeUtilTest {

    @Test
    public void testPrint() {
        String nullResult = PrintInterceptor.print(() -> {
            ListNodeUtil.print(null);
        });

        assert ("\r\n".equals(nullResult));


        String oneResult = PrintInterceptor.print(() -> {
            ListNodeUtil.print(new ListNode(1));
        });

        assert ("1->nil\r\n".equals(oneResult));
        System.out.println("oneResult = " + oneResult);

        String threeResult = PrintInterceptor.print(() -> {
            ListNode head = new ListNode(1);
            head.next = new ListNode(2);
            head.next.next = new ListNode(3);

            ListNodeUtil.print(head);
        });

        assert ("1->2->3->nil\r\n".equals(threeResult));
        System.out.println("threeResult = " + threeResult);
    }

    @Test
    public void testToBigInt() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        Assert.assertEquals("123", ListNodeUtil.toBigInt(head));

        Assert.assertEquals("", ListNodeUtil.toBigInt(null));

    }

    @Test
    public void testToListNodes() {
        {
            ListNode listNode = ListNodeUtil.toListNodes(new int[]{});
            Assert.assertNull(listNode);
        }

        {
            ListNode listNode = ListNodeUtil.toListNodes(new int[]{1});
            Assert.assertNotNull(listNode);
            Assert.assertEquals(1, listNode.val);
            Assert.assertNull(listNode.next);
        }

        {
            ListNode listNode = ListNodeUtil.toListNodes(new int[]{1,2});
            Assert.assertNotNull(listNode);
            Assert.assertEquals(1, listNode.val);

            Assert.assertNotNull(listNode.next);
            Assert.assertEquals(2, listNode.next.val);

            Assert.assertNull(listNode.next.next);
        }

    }

    @Test
    public void testCountNode() {
        Assert.assertEquals(0, ListNodeUtil.countNode(null));
        Assert.assertEquals(1, ListNodeUtil.countNode(new ListNode(1)));

        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        Assert.assertEquals(2, ListNodeUtil.countNode(listNode));
    }

    @Test
    public void testLength() {
        Assert.assertEquals(0, ListNodeUtil.length(null));
        Assert.assertEquals(1, ListNodeUtil.length(new ListNode(1)));

        Assert.assertEquals(2, ListNodeUtil.length(new ListNodeBuilder(1).append(2).getHead()));
        Assert.assertEquals(5, ListNodeUtil.length(new ListNodeBuilder(1).append(new int[]{2,3,4,5}).getHead()));
    }

    @Test
    public void testTestEquals() {
        Assert.assertTrue(ListNodeUtil.equals(null, null));
        Assert.assertTrue(ListNodeUtil.equals(new ListNode(+2), new ListNode(2)));
        Assert.assertTrue(ListNodeUtil.equals(
                new ListNodeBuilder(1).append(2).getHead(),
                new ListNodeBuilder(1).append(2).getHead()));

        Assert.assertFalse(ListNodeUtil.equals(null, new ListNode(1)));
        Assert.assertFalse(ListNodeUtil.equals(new ListNode(1), null));
        Assert.assertFalse(ListNodeUtil.equals(new ListNode(1), new ListNode(2)));

        Assert.assertFalse(ListNodeUtil.equals(
                new ListNodeBuilder(1).append(2).getHead(),
                new ListNodeBuilder(1).append(2).append(3).getHead()));

        Assert.assertFalse(ListNodeUtil.equals(
                new ListNodeBuilder(1).append(2).append(4).getHead(),
                new ListNodeBuilder(1).append(2).append(3).getHead()));
    }
}