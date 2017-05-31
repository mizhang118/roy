package com.roy.tree;

public class Tree {
	private TreeNode root;

	public void insert(TreeNode dataNode) {
		if ( root == null ) {
			root = dataNode;
		}
		else {
			insertRecursive(root, dataNode);
		}
	}
	
	public TreeNode find(Integer data) {
		return findRecursive(root, data);
	}
	
	private void insertRecursive(TreeNode parentNode, TreeNode dataNode) {
		//
	}
	
	private TreeNode findRecursive(TreeNode node, Integer data) {
		return null;
	}
}
