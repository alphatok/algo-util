package com.alphatok.util;


import com.alphatok.domain.ListNode;

public class ListNodeUtil{
	
	public static void print(ListNode head) {
		if (null == head){
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
		while(listNode.next != null){
			count++;
			listNode = listNode.next;
		}

		return count;
	}
}
