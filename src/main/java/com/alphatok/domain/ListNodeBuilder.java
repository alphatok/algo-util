package com.alphatok.domain;

import java.util.List;

public class ListNodeBuilder {

    private ListNode head = null;
    private ListNode last = null;

    public ListNodeBuilder(int headVal) {
        head = new ListNode(headVal);
        last = head;
    }

    public ListNodeBuilder append(int val){
        ListNode tmp = new ListNode(val);
        last.next = tmp;
        last = tmp;
        return this;
    }

    public ListNodeBuilder append(int[] vals){
        for (int val : vals) {
            append(val);
        }

        return this;
    }

    public ListNode getHead(){
        return head;
    }
}
