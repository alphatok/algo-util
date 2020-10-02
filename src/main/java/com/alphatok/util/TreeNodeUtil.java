package com.alphatok.util;


import com.alphatok.domain.TreeNode;

import java.util.*;
import java.util.Map.Entry;

public class TreeNodeUtil {

	public static TreeNode toTree(List<Integer> values){
		if (null == values){
			return null;
		}
		return toTree(values.toArray(new Integer[0]));
	}
	public static TreeNode toTree(Integer[] values){
		if (values == null || values.length == 0){
			return null;
		}
		
		Integer val = values[0];
		if (val == null){
			return null;
		}
		
		Map<Integer, TreeNode> map = new HashMap<Integer, TreeNode>(values.length);
		map.put(0, new TreeNode(val));
		for (int i = 1; i < values.length; i++) {
			if (values[i] == null){
				continue;
			}
			
			val = values[i];
			if (i % 2 == 0){
				TreeNode parent = map.get(i/2 - 1);
				if (parent != null){
					parent.right = new TreeNode(val);
					map.put(i, parent.right);
				}else {
					throw new RuntimeException("parent should not be null, index:" + (i/2 - 1));
				}
			}else {
				TreeNode parent = map.get(i/2);
				if (parent != null){
					parent.left = new TreeNode(val);
					map.put(i, parent.left);
				}else {
					throw new RuntimeException("parent should not be null, index:" + (i/2));
				}
			}
		}
		
		return map.get(0);
	}
	
	/**
	 *   12
	 *  /  \
	 * 1    2
	 **/
	public static void printAsTree(TreeNode root){
		List<String> result = binaryTreePaths(root);
		for (Iterator<String> iterator = result.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
	}
	
	public static void printAsPaths(TreeNode root){
		List<String> result = binaryTreePaths(root);
		for (Iterator<String> iterator = result.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
	}

	private static List<String> binaryTreePaths(TreeNode root) {  
        List<String> result = new ArrayList<>();  
        if(root == null) {
        	return result;  
        }
        StringBuilder path = new StringBuilder();   // cache from root(do reset length if returned from child)
        recursiveVisit(root, result, path);  
        
        return result;  
    }

	private static void recursiveVisit(TreeNode root, List<String> result, StringBuilder path) {
		if(root.left == null && root.right == null){
			path.append(root.val);
			result.add(path.toString());
			return;
		}
		
		path.append(root.val).append("->");
		int fromPathLength = path.length();
		
		if (root.left != null){
			recursiveVisit(root.left, result, path);
		}
		
		path.setLength(fromPathLength);
		
		if (root.right != null){
			recursiveVisit(root.right, result, path);
		}
	}  
	
	public static int depth(TreeNode root){
		if (root == null){
			return 0;
		}
		
		int depthOfLeft = depth(root.left);
		int depthOfRigth = depth(root.right);
		
		return (depthOfLeft > depthOfRigth) ? (depthOfLeft + 1) : (depthOfRigth + 1);
	}
	
	public static List<Integer> toList(TreeNode root){
		Integer depth = depth(root);
		if (depth == 0){
			return Collections.emptyList();
		}
		
		int length = MathUtil.pow(2, depth) - 1;
		List<Integer> result = new ArrayList<>(length);
		for (int i = 0; i < length; i++) {
			result.add(null);
		}
		btreeToList(root, 0, result);
		return result;
	}
	
	private static void btreeToList(TreeNode node, Integer index, List<Integer> result){
		if (null == node){
			return;
		}
		result.set(index, node.val);
		btreeToList(node.left, 2*index + 1, result);
		btreeToList(node.right, 2*index + 2, result);
	}
	
	private static void testPrintByLevel(){
		TreeNode  root = toTree(new Integer[]{1,2,3,null,5,null,null,null,null,10,1});
		
		int depth = depth(root);
		List<Integer> list = toList(root);
		CollectionUtil.print(list);
//		int total = MathUtil.pow(2, depth + 1) - 3;
		Map<Integer, String> print = new HashMap<>();
		for (int i = 0; i < 2*depth; i++) {
			print.put(i + 1, "");
		}
		
		for (int i = 0; i < list.size(); i++) {
			Integer integer = list.get(i);
			String str = (null == integer) ? "":String.format("%d", integer);
			Integer level = Math.getExponent(i + 1) + 1;
			if (MathUtil.powBasedOnTwo((i+1))){
				print.put(2*level - 1, (print.get(2*level - 1) + StringUtil.repeat(" ", (depth - level)) + str));
			}else {
				print.put(2*level -1, (print.get(2*level - 1) + StringUtil.repeat(" ", 1) + str));
			}
		}
		
		for (Entry<Integer, String> entry : print.entrySet()) {
			System.out.println(entry.getValue());
		}
	}
	
	public static void main(String[] args) {
		TreeNode root = toTree(new Integer[]{1,2,3,null,5,null,null,null,null,10,1});
		printAsTree(root);
		testPrintByLevel();
	}
}
