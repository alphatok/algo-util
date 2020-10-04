package com.alphatok.domain;

public class ListNodeBuilder {

    private ListNode head = null;
    private ListNode last = null;

    public final static String SEPARATOR = ",";
    /**
     * separated by ,
     * @param str
     * @return
     */
    public static ListNodeBuilder create(String str){
        int[] vals = parseInts(str.trim());
        return new ListNodeBuilder(vals);
    }

    private static int[] parseInts(String str) {
        String[] strs = str.split(SEPARATOR);
        int[] vals = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            vals[i] = Integer.parseInt(strs[i].trim());
        }
        return vals;
    }

    @Deprecated
    public ListNodeBuilder(int headVal) {
        head = new ListNode(headVal);
        last = head;
    }

    @Deprecated
    public ListNodeBuilder(int[] vals) {
        head = new ListNode(vals[0]);
        last = head;
        for (int i = 1; i < vals.length; i++) {
            append(vals[i]);
        }
    }

    public ListNodeBuilder append(int val){
        ListNode tmp = new ListNode(val);
        last.next = tmp;
        last = tmp;
        return this;
    }

    public ListNodeBuilder append(ListNode node){
        last.next = node;
        last = node;
        return this;
    }

    public ListNodeBuilder append(String str){
        return append(parseInts(str.trim()));
    }

    @Deprecated
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
