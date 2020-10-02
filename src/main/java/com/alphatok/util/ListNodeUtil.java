package com.alphatok.util;


import com.alphatok.domain.ListNode;

import java.util.List;

public class ListNodeUtil {

    public static int length(ListNode head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }

        return len;
    }

    public static boolean equals(ListNode a, ListNode b) {
        if (length(a) != length(b)) {
            return false;
        }

        while (a != null || b != null) {
            if (a != null && b != null) {
                if (a.val != b.val) {
                    return false;
                }
                a = a.next;
                b = b.next;
            } else if (a == null && b != null) {
                return false;
            } else if (a != null && b == null) {
                return false;
            }
        }

        return true;
    }

    public static void print(ListNode head) {
        if (null == head) {
            System.out.println();
            return;
        }

        while (head.next != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }

        System.out.print(head.val);
        System.out.println("->nil");
    }

    public static String toBigInt(ListNode head) {
        StringBuilder buffer = new StringBuilder();
        while (head != null) {
            buffer.append(head.val);
            head = head.next;
        }

        return buffer.toString();
    }

    public static ListNode toListNodes(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode pre = head;
        for (int i = 1; i < arr.length; i++) {
            ListNode node = new ListNode(arr[i]);
            pre.next = node;
            pre = node;
        }

        return head;
    }


    public static int countNode(ListNode listNode) {
        if (listNode == null) {
            return 0;
        }

        int count = 1;
        while (listNode.next != null) {
            count++;
            listNode = listNode.next;
        }

        return count;
    }

    public static ListNode sortAsc(ListNode head) {
        // length
        int len = 0;
        ListNode nodeTmp = head;
        while (nodeTmp != null) {
            len++;
            nodeTmp = nodeTmp.getNext();
        }

        // bubble
        for (int i = 0; i < len; i++) {
            int innnerStopCount = len - i;
            ListNode pre = null;
            ListNode cur = head;
            while (innnerStopCount >= 0 && cur != null) {
                ListNode next = cur.getNext();
                if (next == null) {
                    break;
                } else {
                    if (next.getVal() < cur.getVal()) {
                        if (pre == null) {
                            // cur is head, switch cur and next then next as head
                            cur.setNext(next.getNext());
                            next.setNext(cur);
                            head = next;
                        } else {
                            // switch cur and next
                            pre.setNext(next);
                            cur.setNext(next.getNext());
                            next.setNext(cur);
                        }
                        pre = next;
                        // cur remains
                    } else {
                        pre = cur;
                        cur = next;
                    }

                }
            }
        }

        return head;
    }

    /**
     * @param arr non null
     * @return
     */
    public static ListNode toLinkedList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        ListNode linkedList = new ListNode(arr[0]);
        ListNode pre = linkedList;
        for (int i = 1; i < arr.length; i++) {
            ListNode listNode = new ListNode(arr[i]);
            pre.next = listNode;
            pre = listNode;
        }

        return linkedList;
    }

    /**
     * @param list non null
     * @return
     */
    public static int[] toArray(ListNode list) {
        // length
        int len = 0;
        ListNode tmp = list;
        while (tmp != null) {
            len++;
            tmp = tmp.getNext();
        }

        int[] result = new int[len];
        ListNode node = list;
        int index = 0;
        while (node != null) {
            result[index++] = node.getVal();
            node = node.getNext();
        }

        return result;
    }
}
